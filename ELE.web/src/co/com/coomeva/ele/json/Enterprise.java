package co.com.coomeva.ele.json;

public class Enterprise implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6428383704469107395L;
	private java.lang.String enterpriseDesc;
	private java.lang.String enterpriseNit;
	private java.lang.String enterpriseERP;

	public Enterprise() {
	}

	public Enterprise(String enterpriseDesc, String enterpriseNit, String enterpriseERP) {
		super();
		this.enterpriseDesc = enterpriseDesc;
		this.enterpriseNit = enterpriseNit;
		this.enterpriseERP = enterpriseERP;
	}

	public java.lang.String getEnterpriseDesc() {
		return enterpriseDesc;
	}

	public void setEnterpriseDesc(java.lang.String enterpriseDesc) {
		this.enterpriseDesc = enterpriseDesc;
	}

	public java.lang.String getEnterpriseNit() {
		return enterpriseNit;
	}

	public void setEnterpriseNit(java.lang.String enterpriseNit) {
		this.enterpriseNit = enterpriseNit;
	}

	public java.lang.String getEnterpriseERP() {
		return enterpriseERP;
	}

	public void setEnterpriseERP(java.lang.String enterpriseERP) {
		this.enterpriseERP = enterpriseERP;
	}

	@Override
	public String toString() {
		return "Enterprise [enterpriseDesc=" + enterpriseDesc + ", enterpriseNit=" + enterpriseNit + ", enterpriseERP="
				+ enterpriseERP + "]";
	}
}