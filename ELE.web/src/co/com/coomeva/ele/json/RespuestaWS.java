package co.com.coomeva.ele.json;

import java.util.ArrayList;

public class RespuestaWS implements java.io.Serializable {
	private static final long serialVersionUID = -3841380313091364449L;
	private String statusCode;
	private java.lang.String descStatusCode;
	private Client client;
//	private ArrayList<AuthApplication> authApplications;
//	private ArrayList<Enterprise> enterprise;

	public RespuestaWS() {
	}

	public RespuestaWS(String statusCode, String descStatusCode, Client client,
			ArrayList<AuthApplication> authApplications, ArrayList<Enterprise> enterprise) {
		super();
		this.statusCode = statusCode;
		this.descStatusCode = descStatusCode;
		this.client = client;
//		this.authApplications = authApplications;
//		this.enterprise = enterprise;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public java.lang.String getDescStatusCode() {
		return descStatusCode;
	}

	public void setDescStatusCode(java.lang.String descStatusCode) {
		this.descStatusCode = descStatusCode;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "RespuestaWS [statusCode=" + statusCode + ", descStatusCode=" + descStatusCode + ", client=" + client
				+ "]";
	}

//	public ArrayList<AuthApplication> getAuthApplications() {
//		return authApplications;
//	}
//
//	public void setAuthApplications(ArrayList<AuthApplication> authApplications) {
//		this.authApplications = authApplications;
//	}
//
//	public ArrayList<Enterprise> getEnterprise() {
//		return enterprise;
//	}
//
//	public void setEnterprise(ArrayList<Enterprise> enterprise) {
//		this.enterprise = enterprise;
//	}
//
//	@Override
//	public String toString() {
//		return "RespuestaWS [statusCode=" + statusCode + ", descStatusCode=" + descStatusCode + ", client=" + client
//				+ ", authApplications=" + authApplications + ", enterprise=" + enterprise + "]";
//	}
	
}