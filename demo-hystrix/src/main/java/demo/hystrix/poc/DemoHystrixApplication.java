package demo.hystrix.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@SpringBootApplication
@RestController
public class DemoHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHystrixApplication.class, args);
	}

	@Autowired
	private MyService service;

	@GetMapping("/hello/delay/{delay}")
	public String helloResource(@PathVariable(name = "delay") int delay) {
		return service.sayHello(delay);
	}

	@GetMapping("/hello")
	public String helloExceptionResource(@RequestParam(name = "exception", defaultValue = "false") boolean flag) {
		return service.sayHelloExc(flag);
	}

	@Service
	class MyService {

		@HystrixCommand(fallbackMethod = "sayHelloExcFB", commandKey = "sayHelloExc")
		public String sayHelloExc(boolean flag) {
			if (flag) {
				throw new RuntimeException("say hello exception");
			}
			return "no exception, direct return";
		}

		@HystrixCommand(fallbackMethod = "sayHelloFB", commandKey = "sayHello")
		public String sayHello(int delay) {
			long original = System.currentTimeMillis();
			long wait = delay * 1000;
			while (true) {
				if (System.currentTimeMillis() - original >= wait) {
					break;
				}
			}
			return "Direct Hello after delay of " + delay;
		}

		public String sayHelloFB(int delay) {
			return "Fallback hello after delay of " + delay;
		}

		public String sayHelloExcFB(boolean falg) {
			return "Fallback test for exception";
		}
	}

}
