package org.demo.si.dsl;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.DirectoryScanner;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.RecursiveDirectoryScanner;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.RegexPatternFileListFilter;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@IntegrationComponentScan
@SpringBootApplication
public class SiDslDemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SiDslDemoApplication.class);

	@Autowired
	@Qualifier("inboundReadDirectory")
	public File inboundReadDirectory;

	public static void main(String[] args) {
		SpringApplication.run(SiDslDemoApplication.class, args);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public IntegrationFlow myDatabaseTriggeredFlow(FileReadingMessageSource fileReadingMessageSource) {
		return IntegrationFlows.from(fileReadingMessageSource, c -> c.poller(Pollers.fixedDelay(20000, 5000))) /*
																												 * in ms
																												 */
				.handle(new GenericHandler() {

					@Override
					public Object handle(Object payload, Map headers) {
						File file = (File) payload;
						LOG.info("Scanned Filename: {}", file.getName());
						LOG.info("Headers {}", headers);
						file.renameTo(new File(file.getAbsolutePath()+System.currentTimeMillis()+".done"));
						return file.getName();
					}
				}).enrichHeaders(s -> s.headerExpressions(h -> h.put("myHeader", "payload")))
				.wireTap(flow -> flow.handle(message -> LOG.warn("MESSAGE Headers {}", message.getHeaders())))
				.handle(new GenericHandler() {
					@Override
					public Object handle(Object payload, Map headers) {
						LOG.info("Second handler payload {}", payload);
						LOG.info("Second handler Headers {}", headers);
						return "Done";
					}
				}).log().get();
	}

	@Bean
	public FileReadingMessageSource fileReadingMessageSource(DirectoryScanner directoryScanner) {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(this.inboundReadDirectory);
		source.setScanner(directoryScanner);
		source.setAutoCreateDirectory(true);
		return source;
	}

	@SuppressWarnings("unchecked")
	@Bean
	public DirectoryScanner directoryScanner(@Value("${inbound.filename.regex}") String regex) {
		DirectoryScanner scanner = new RecursiveDirectoryScanner();
		@SuppressWarnings("rawtypes")
		CompositeFileListFilter filter = new CompositeFileListFilter<>(
				Arrays.asList(new AcceptOnceFileListFilter<>(), new RegexPatternFileListFilter(regex)));
		scanner.setFilter(filter);
		return scanner;
	}

	@Bean(name = "inboundReadDirectory")
	public File inboundReadDirectory(@Value("${inbound.read.path}") String path) {
		File file = new File(path);
		return file;
	}

	@Bean
	public IntegrationFlow errorFlow() {
		return IntegrationFlows.from("errorChannel").handle(new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				MessagingException messagingException = (MessagingException) message.getPayload();
				LOG.error("Error occured in integration flow {}", messagingException);
			}
		}).get();
	}
}
