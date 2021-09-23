package co.com.coomeva.ele.entidades.admhabilidad;

/**
 * AbstractAsoelecf entity provides the base persistence definition of the
 * Asoelecf entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractAsoelecf implements java.io.Serializable {

	// Fields

	private Long wnitcli;
	private String wnomcli;
	private Long wcodreg;
	private String wnomreg;
	private String wzona;
	private String wnomzon;
	private Long wagcori;
	private String wnomagc;
	private String windhab;
	private String windret;
	private String wdebaut;
	private Long wmedvot;
	private Long wcodciuc;
	private String wnomciuc;
	private String wnomdirc;
	private Long westaso;
	private String wdesesta;
	private Long wasocor;
	private String wdescor;
	private Long wvlrvcdo;
	private Long wvlrcuom;
	private Long wmorad;
	private Long wmorac;
	private Long wfeccon;
	private Long wedad;
	private Long wfecing;
	private Long wantig;
	private Long wcodsex;
	private String wsexo;
	private Long wfecprow;
	private Long whorprow;
	private String wusrprow;
	private Long wfecproa;
	private Long whorproa;
	private String wusrproa;
	private Long wfecpror;
	private Long whorpror;
	private String wusrpror;
	private Long wnumint;

	// Constructors

	/** default constructor */
	public AbstractAsoelecf() {
	}

	/** full constructor */
	public AbstractAsoelecf(Long wnitcli, String wnomcli, Long wcodreg,
			String wnomreg, String wzona, String wnomzon, Long wagcori,
			String wnomagc, String windhab, String windret, String wdebaut,
			Long wmedvot, Long wcodciuc, String wnomciuc, String wnomdirc,
			Long westaso, String wdesesta, Long wasocor, String wdescor,
			Long wvlrvcdo, Long wvlrcuom, Long wmorad, Long wmorac,
			Long wfeccon, Long wedad, Long wfecing, Long wantig, Long wcodsex,
			String wsexo, Long wfecprow, Long whorprow, String wusrprow,
			Long wfecproa, Long whorproa, String wusrproa, Long wfecpror,
			Long whorpror, String wusrpror, Long wnumint) {
		this.wnitcli = wnitcli;
		this.wnomcli = wnomcli;
		this.wcodreg = wcodreg;
		this.wnomreg = wnomreg;
		this.wzona = wzona;
		this.wnomzon = wnomzon;
		this.wagcori = wagcori;
		this.wnomagc = wnomagc;
		this.windhab = windhab;
		this.windret = windret;
		this.wdebaut = wdebaut;
		this.wmedvot = wmedvot;
		this.wcodciuc = wcodciuc;
		this.wnomciuc = wnomciuc;
		this.wnomdirc = wnomdirc;
		this.westaso = westaso;
		this.wdesesta = wdesesta;
		this.wasocor = wasocor;
		this.wdescor = wdescor;
		this.wvlrvcdo = wvlrvcdo;
		this.wvlrcuom = wvlrcuom;
		this.wmorad = wmorad;
		this.wmorac = wmorac;
		this.wfeccon = wfeccon;
		this.wedad = wedad;
		this.wfecing = wfecing;
		this.wantig = wantig;
		this.wcodsex = wcodsex;
		this.wsexo = wsexo;
		this.wfecprow = wfecprow;
		this.whorprow = whorprow;
		this.wusrprow = wusrprow;
		this.wfecproa = wfecproa;
		this.whorproa = whorproa;
		this.wusrproa = wusrproa;
		this.wfecpror = wfecpror;
		this.whorpror = whorpror;
		this.wusrpror = wusrpror;
		this.wnumint = wnumint;
	}

	// Property accessors

	public Long getWnitcli() {
		return this.wnitcli;
	}

	public void setWnitcli(Long wnitcli) {
		this.wnitcli = wnitcli;
	}

	public String getWnomcli() {
		return this.wnomcli;
	}

	public void setWnomcli(String wnomcli) {
		this.wnomcli = wnomcli;
	}

	public Long getWcodreg() {
		return this.wcodreg;
	}

	public void setWcodreg(Long wcodreg) {
		this.wcodreg = wcodreg;
	}

	public String getWnomreg() {
		return this.wnomreg;
	}

	public void setWnomreg(String wnomreg) {
		this.wnomreg = wnomreg;
	}

	public String getWzona() {
		return this.wzona;
	}

	public void setWzona(String wzona) {
		this.wzona = wzona;
	}

	public String getWnomzon() {
		return this.wnomzon;
	}

	public void setWnomzon(String wnomzon) {
		this.wnomzon = wnomzon;
	}

	public Long getWagcori() {
		return this.wagcori;
	}

	public void setWagcori(Long wagcori) {
		this.wagcori = wagcori;
	}

	public String getWnomagc() {
		return this.wnomagc;
	}

	public void setWnomagc(String wnomagc) {
		this.wnomagc = wnomagc;
	}

	public String getWindhab() {
		return this.windhab;
	}

	public void setWindhab(String windhab) {
		this.windhab = windhab;
	}

	public String getWindret() {
		return this.windret;
	}

	public void setWindret(String windret) {
		this.windret = windret;
	}

	public String getWdebaut() {
		return this.wdebaut;
	}

	public void setWdebaut(String wdebaut) {
		this.wdebaut = wdebaut;
	}

	public Long getWmedvot() {
		return this.wmedvot;
	}

	public void setWmedvot(Long wmedvot) {
		this.wmedvot = wmedvot;
	}

	public Long getWcodciuc() {
		return this.wcodciuc;
	}

	public void setWcodciuc(Long wcodciuc) {
		this.wcodciuc = wcodciuc;
	}

	public String getWnomciuc() {
		return this.wnomciuc;
	}

	public void setWnomciuc(String wnomciuc) {
		this.wnomciuc = wnomciuc;
	}

	public String getWnomdirc() {
		return this.wnomdirc;
	}

	public void setWnomdirc(String wnomdirc) {
		this.wnomdirc = wnomdirc;
	}

	public Long getWestaso() {
		return this.westaso;
	}

	public void setWestaso(Long westaso) {
		this.westaso = westaso;
	}

	public String getWdesesta() {
		return this.wdesesta;
	}

	public void setWdesesta(String wdesesta) {
		this.wdesesta = wdesesta;
	}

	public Long getWasocor() {
		return this.wasocor;
	}

	public void setWasocor(Long wasocor) {
		this.wasocor = wasocor;
	}

	public String getWdescor() {
		return this.wdescor;
	}

	public void setWdescor(String wdescor) {
		this.wdescor = wdescor;
	}

	public Long getWvlrvcdo() {
		return this.wvlrvcdo;
	}

	public void setWvlrvcdo(Long wvlrvcdo) {
		this.wvlrvcdo = wvlrvcdo;
	}

	public Long getWvlrcuom() {
		return this.wvlrcuom;
	}

	public void setWvlrcuom(Long wvlrcuom) {
		this.wvlrcuom = wvlrcuom;
	}

	public Long getWmorad() {
		return this.wmorad;
	}

	public void setWmorad(Long wmorad) {
		this.wmorad = wmorad;
	}

	public Long getWmorac() {
		return this.wmorac;
	}

	public void setWmorac(Long wmorac) {
		this.wmorac = wmorac;
	}

	public Long getWfeccon() {
		return this.wfeccon;
	}

	public void setWfeccon(Long wfeccon) {
		this.wfeccon = wfeccon;
	}

	public Long getWedad() {
		return this.wedad;
	}

	public void setWedad(Long wedad) {
		this.wedad = wedad;
	}

	public Long getWfecing() {
		return this.wfecing;
	}

	public void setWfecing(Long wfecing) {
		this.wfecing = wfecing;
	}

	public Long getWantig() {
		return this.wantig;
	}

	public void setWantig(Long wantig) {
		this.wantig = wantig;
	}

	public Long getWcodsex() {
		return this.wcodsex;
	}

	public void setWcodsex(Long wcodsex) {
		this.wcodsex = wcodsex;
	}

	public String getWsexo() {
		return this.wsexo;
	}

	public void setWsexo(String wsexo) {
		this.wsexo = wsexo;
	}

	public Long getWfecprow() {
		return this.wfecprow;
	}

	public void setWfecprow(Long wfecprow) {
		this.wfecprow = wfecprow;
	}

	public Long getWhorprow() {
		return this.whorprow;
	}

	public void setWhorprow(Long whorprow) {
		this.whorprow = whorprow;
	}

	public String getWusrprow() {
		return this.wusrprow;
	}

	public void setWusrprow(String wusrprow) {
		this.wusrprow = wusrprow;
	}

	public Long getWfecproa() {
		return this.wfecproa;
	}

	public void setWfecproa(Long wfecproa) {
		this.wfecproa = wfecproa;
	}

	public Long getWhorproa() {
		return this.whorproa;
	}

	public void setWhorproa(Long whorproa) {
		this.whorproa = whorproa;
	}

	public String getWusrproa() {
		return this.wusrproa;
	}

	public void setWusrproa(String wusrproa) {
		this.wusrproa = wusrproa;
	}

	public Long getWfecpror() {
		return this.wfecpror;
	}

	public void setWfecpror(Long wfecpror) {
		this.wfecpror = wfecpror;
	}

	public Long getWhorpror() {
		return this.whorpror;
	}

	public void setWhorpror(Long whorpror) {
		this.whorpror = whorpror;
	}

	public String getWusrpror() {
		return this.wusrpror;
	}

	public void setWusrpror(String wusrpror) {
		this.wusrpror = wusrpror;
	}

	public Long getWnumint() {
		return this.wnumint;
	}

	public void setWnumint(Long wnumint) {
		this.wnumint = wnumint;
	}

}