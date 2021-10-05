package co.com.coomeva.ele.json;

public class Client implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7101970019967829042L;
	private java.lang.String codUser;
	private java.lang.String typeID;
	private java.lang.String typeIdDesc;
	private java.lang.String participantId;
	private java.lang.String user;
	private java.lang.String state;
	private java.lang.String registerDate;
	private java.lang.String block;

	public Client(String codUser, String typeID, String typeIdDesc, String participantId, String user, String state,
			String registerDate, String block) {
		super();
		this.codUser = codUser;
		this.typeID = typeID;
		this.typeIdDesc = typeIdDesc;
		this.participantId = participantId;
		this.user = user;
		this.state = state;
		this.registerDate = registerDate;
		this.block = block;
	}

	public Client() {
	}

	public java.lang.String getCodUser() {
		return codUser;
	}

	public void setCodUser(java.lang.String codUser) {
		this.codUser = codUser;
	}

	public java.lang.String getTypeID() {
		return typeID;
	}

	public void setTypeID(java.lang.String typeID) {
		this.typeID = typeID;
	}

	public java.lang.String getTypeIdDesc() {
		return typeIdDesc;
	}

	public void setTypeIdDesc(java.lang.String typeIdDesc) {
		this.typeIdDesc = typeIdDesc;
	}

	public java.lang.String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(java.lang.String participantId) {
		this.participantId = participantId;
	}

	public java.lang.String getUser() {
		return user;
	}

	public void setUser(java.lang.String user) {
		this.user = user;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(java.lang.String registerDate) {
		this.registerDate = registerDate;
	}

	public java.lang.String getBlock() {
		return block;
	}

	public void setBlock(java.lang.String block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Client [codUser=" + codUser + ", typeID=" + typeID + ", typeIdDesc=" + typeIdDesc + ", participantId="
				+ participantId + ", user=" + user + ", state=" + state + ", registerDate=" + registerDate + ", block="
				+ block + "]";
	}
}