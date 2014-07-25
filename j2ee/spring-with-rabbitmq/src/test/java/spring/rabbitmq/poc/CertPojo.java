package spring.rabbitmq.poc;

import java.util.Date;

public class CertPojo {
	private String keystoreName;
	private String certAlias;
	private Date expireDate;
	private String cnName;
	private String certType;
	private int expiresInDays;

	/**
	 * @return the keystoreName
	 */
	public String getKeystoreName() {
		return keystoreName;
	}

	/**
	 * @param keystoreName
	 *            the keystoreName to set
	 */
	public void setKeystoreName(String keystoreName) {
		this.keystoreName = keystoreName;
	}

	/**
	 * @return the certAlias
	 */
	public String getCertAlias() {
		return certAlias;
	}

	/**
	 * @param certAlias
	 *            the certAlias to set
	 */
	public void setCertAlias(String certAlias) {
		this.certAlias = certAlias;
	}

	/**
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate
	 *            the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}

	/**
	 * @param cnName
	 *            the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	/**
	 * @return the certType
	 */
	public String getCertType() {
		return certType;
	}

	/**
	 * @param certType
	 *            the certType to set
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}

	/**
	 * @return the expiresInDays
	 */
	public int getExpiresInDays() {
		return expiresInDays;
	}

	/**
	 * @param expiresInDays
	 *            the expiresInDays to set
	 */
	public void setExpiresInDays(int expiresInDays) {
		this.expiresInDays = expiresInDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CertPojo [keystoreName=" + keystoreName + ", certAlias="
				+ certAlias + ", expireDate=" + expireDate + ", cnName="
				+ cnName + ", certType=" + certType + ", expiresInDays="
				+ expiresInDays + "]";
	}

}