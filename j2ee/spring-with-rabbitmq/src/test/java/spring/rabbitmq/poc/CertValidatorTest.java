package spring.rabbitmq.poc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

public class CertValidatorTest {

	/**
	 * @param args
	 */
	private static final String CERT_LIST = "/apps/int/apd1/KeyStore/mlskeystore.jks;Changeme;;/apps/int/apd1/KeyStore/mlstruststoredlc.jks;Changeme";
	private static final int ALERTINDAYS = 412;
	public static void main(String[] args) {
		X509CertificateInfoExtractor certInfoExtractor = new X509CertificateInfoExtractor();
		certInfoExtractor.setCertList(CERT_LIST);
		certInfoExtractor.setEntrySplitterRegex("\\;\\;");
		certInfoExtractor.setPropertySplitterRegex("\\;");
		try {
			List<CertPojo> certPojoList = certInfoExtractor.extractCertsInfo();
			for(CertPojo certPojo:certPojoList){
				if(certPojo.getExpiresInDays()<=ALERTINDAYS){
					System.err.println("I Am alerting this..");
					System.out.println(certPojo);
					System.err.println("I Am alerting this..");
				}else{
					System.err.println("Cert is not expiring very soon..");
					System.out.println(certPojo);
				}
			}
			
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
