package co.com.coomeva.ele.json;

public class RequestBodyVO {
	private String chanelToken;
	private String clientUser;
	private String passwordUser;

	public RequestBodyVO() {

	}

	public RequestBodyVO(String chanelToken, String clientUser, String passwordUser) {
		this.chanelToken = chanelToken;
		this.clientUser = clientUser;
		this.passwordUser = passwordUser;
	}

	public String getChanelToken() {
		return chanelToken;
	}

	public void setChanelToken(String chanelToken) {
		this.chanelToken = chanelToken;
	}

	public String getClientUser() {
		return clientUser;
	}

	public void setClientUser(String clientUser) {
		this.clientUser = clientUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

}
