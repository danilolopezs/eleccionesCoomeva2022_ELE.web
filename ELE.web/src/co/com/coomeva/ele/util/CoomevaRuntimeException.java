package co.com.coomeva.ele.util;

public class CoomevaRuntimeException extends Exception {
	private static final long serialVersionUID = 3075237238439931994L;
	private String msm;

	public CoomevaRuntimeException(String msm) {
		this.msm = msm;
	}

	public String getMsm() {
		return msm;
	}

	public void setMsm(String msm) {
		this.msm = msm;
	}
}