package co.com.coomeva.ele.json;

public class AuthApplication implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1847722167210929745L;
	private java.lang.String appDesc;
	private java.lang.String enterpriceDesc;
	private java.lang.String enterpriceNit;

	public AuthApplication() {
	}

	public AuthApplication(String appDesc, String enterpriceDesc, String enterpriceNit) {
		super();
		this.appDesc = appDesc;
		this.enterpriceDesc = enterpriceDesc;
		this.enterpriceNit = enterpriceNit;
	}

	public java.lang.String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(java.lang.String appDesc) {
		this.appDesc = appDesc;
	}

	public java.lang.String getEnterpriceDesc() {
		return enterpriceDesc;
	}

	public void setEnterpriceDesc(java.lang.String enterpriceDesc) {
		this.enterpriceDesc = enterpriceDesc;
	}

	public java.lang.String getEnterpriceNit() {
		return enterpriceNit;
	}

	public void setEnterpriceNit(java.lang.String enterpriceNit) {
		this.enterpriceNit = enterpriceNit;
	}

	@Override
	public String toString() {
		return "AuthApplication [appDesc=" + appDesc + ", enterpriceDesc=" + enterpriceDesc + ", enterpriceNit="
				+ enterpriceNit + "]";
	}
}