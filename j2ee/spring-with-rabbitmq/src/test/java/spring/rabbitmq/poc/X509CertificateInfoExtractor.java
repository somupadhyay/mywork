package spring.rabbitmq.poc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * 
 * @author Somnath.Upadhyay
 * 
 * Constraints the password or file name location should not contain '|' symbol
 *
 */
public class X509CertificateInfoExtractor {

	/**
	 * @param args
	 * @throws KeyStoreException
	 */
	private static final String X509_TYPE = "X.509";
	private String entrySplitterRegex;
	private String propertySplitterRegex;
	private String certList;
	
	public List<CertPojo> extractCertsInfo() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException{
		
		
		String [] certEntry = certList.split(entrySplitterRegex);
		String [] certProperties = null;
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		
		List<CertPojo> certPojos=new ArrayList<CertPojo>();
		for(String entry:certEntry){
			certProperties = entry.split(propertySplitterRegex);
			certPojos.addAll(getCertValidity(keystore, certProperties[0], certProperties[1]));
			
		}
		return certPojos;
	}
	
	private List<CertPojo> getCertValidity(KeyStore keystore, String file, String password)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException, IOException {
		CertPojo certPojo = null;
		List<CertPojo> certPojos = new ArrayList<CertPojo>();
		keystore.load(new FileInputStream(file), password.toCharArray());
		Enumeration<String> aliases = keystore.aliases();
		while (aliases.hasMoreElements()) {
			certPojo = new CertPojo();
			String alias = aliases.nextElement();
			certPojo.setCertAlias(alias);
			certPojo.setKeystoreName(file);
			String certType = keystore.getCertificate(alias).getType();
			if(null!=certType && certType.equals(X509_TYPE)){
				X509Certificate x509Cert = (X509Certificate) keystore.getCertificate(alias);
				certPojo.setCertType(certType);
				certPojo.setCnName(x509Cert.getIssuerDN().getName());
				certPojo.setExpireDate(x509Cert.getNotAfter());
				certPojo.setExpiresInDays(getDifferenceInDays(x509Cert.getNotAfter()));
			}
			certPojos.add(certPojo);
		}
		return certPojos;
	}

	private int getDifferenceInDays(Date date){
		
		DateTime currentDateTime = new DateTime(new Date());
		DateTime expiryDateTime = new DateTime(date);
		return Days.daysBetween(currentDateTime, expiryDateTime).getDays();
		
	}

	/**
	 * @return the entrySplitterRegex
	 */
	public String getEntrySplitterRegex() {
		return entrySplitterRegex;
	}

	/**
	 * @param entrySplitterRegex the entrySplitterRegex to set
	 */
	public void setEntrySplitterRegex(String entrySplitterRegex) {
		this.entrySplitterRegex = entrySplitterRegex;
	}

	/**
	 * @return the propertySplitterRegex
	 */
	public String getPropertySplitterRegex() {
		return propertySplitterRegex;
	}

	/**
	 * @param propertySplitterRegex the propertySplitterRegex to set
	 */
	public void setPropertySplitterRegex(String propertySplitterRegex) {
		this.propertySplitterRegex = propertySplitterRegex;
	}

	/**
	 * @return the certList
	 */
	public String getCertList() {
		return certList;
	}

	/**
	 * @param certList the certList to set
	 */
	public void setCertList(String certList) {
		this.certList = certList;
	}
	
}
