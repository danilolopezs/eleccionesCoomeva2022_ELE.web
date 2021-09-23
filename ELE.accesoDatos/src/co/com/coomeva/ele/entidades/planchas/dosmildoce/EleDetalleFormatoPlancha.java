package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;
import java.util.Date;

/**
 * EleDetalleFormatoPlancha entity. @author MyEclipse Persistence Tools
 */

public class EleDetalleFormatoPlancha implements java.io.Serializable {

	// Fields

	private EleDetalleFormatoPlanchaId id;
	private String otrosEstudios;
	private String ultimoCargoCoomeva;
	private String cargoActual;
	private String empresaActual;
	private Timestamp fechaEnvio;
	private Timestamp fechaImpresion;
	private String otrosEstudiosDos;
	private Long numeroResolucion;
	private Long numeroActa;
	private Timestamp fechaActa;
	private Integer numeroComisionElect;
	private String razonInadmision1;
	private String razonInadmision2;
	private String razonInadmision3;
	private String razonInadmision4;
	private String razonRechazo;
	private Date fechaResolucion;
	private Date fechaGradoBUC;
	private Date fechaGradoMod;
	private Long antigLaboral;
	private String perfilCandidatoUno;
	private String perfilCandidatoDos;
	private String cuentaPersonalMail;
	private String cuentaPersonalTwitter;
	private String cuentaPersonalFacebook;
	private String argumentoResolucion1;
	private String argumentoResolucion2;
	private String argumentoResolucion3;
	private String razonResolucion1;
	private String razonResolucion2;
	private String comisionElectoral;
	private String resolucionImpugnada;
	private String resolucionInterpuesta;
	private String argumentoRecurrente1;
	private String argumentoRecurrente2;
	private String argumentoComision1;
	private String argumentoComision2;
	private String pronunciamientoTribunal1;
	private String pronunciamientoTribunal2;
	private String consecuenciaTribunal1;
	private String consecuenciaTribunal2;
	private String estado;

	// Constructors

	/** default constructor */
	public EleDetalleFormatoPlancha() {
	}

	/** minimal constructor */
	public EleDetalleFormatoPlancha(EleDetalleFormatoPlanchaId id) {
		this.id = id;
	}

	/** full constructor */
	public EleDetalleFormatoPlancha(EleDetalleFormatoPlanchaId id,
			String otrosEstudios, String ultimoCargoCoomeva,
			String cargoActual, String empresaActual, Timestamp fechaEnvio,
			Timestamp fechaImpresion, String otrosEstudiosDos,
			Long numeroResolucion, Long numeroActa, Timestamp fechaActa,
			Integer numeroComisionElect, String razonInadmision1,
			String razonInadmision2, String razonInadmision3,
			String razonInadmision4, String razonRechazo, Date fechaResolucion) {
		this.id = id;
		this.otrosEstudios = otrosEstudios;
		this.ultimoCargoCoomeva = ultimoCargoCoomeva;
		this.cargoActual = cargoActual;
		this.empresaActual = empresaActual;
		this.fechaEnvio = fechaEnvio;
		this.fechaImpresion = fechaImpresion;
		this.otrosEstudiosDos = otrosEstudiosDos;
		this.numeroResolucion = numeroResolucion;
		this.numeroActa = numeroActa;
		this.fechaActa = fechaActa;
		this.numeroComisionElect = numeroComisionElect;
		this.razonInadmision1 = razonInadmision1;
		this.razonInadmision2 = razonInadmision2;
		this.razonInadmision3 = razonInadmision3;
		this.razonInadmision4 = razonInadmision4;
		this.razonRechazo = razonRechazo;
		this.fechaResolucion = fechaResolucion;
	}

	// Property accessors

	public EleDetalleFormatoPlanchaId getId() {
		return this.id;
	}

	public void setId(EleDetalleFormatoPlanchaId id) {
		this.id = id;
	}

	public String getOtrosEstudios() {
		return this.otrosEstudios;
	}

	public void setOtrosEstudios(String otrosEstudios) {
		this.otrosEstudios = otrosEstudios;
	}

	public String getUltimoCargoCoomeva() {
		return this.ultimoCargoCoomeva;
	}

	public void setUltimoCargoCoomeva(String ultimoCargoCoomeva) {
		this.ultimoCargoCoomeva = ultimoCargoCoomeva;
	}

	public String getCargoActual() {
		return this.cargoActual;
	}

	public void setCargoActual(String cargoActual) {
		this.cargoActual = cargoActual;
	}

	public String getEmpresaActual() {
		return this.empresaActual;
	}

	public void setEmpresaActual(String empresaActual) {
		this.empresaActual = empresaActual;
	}

	public Timestamp getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Timestamp getFechaImpresion() {
		return this.fechaImpresion;
	}

	public void setFechaImpresion(Timestamp fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public String getOtrosEstudiosDos() {
		return this.otrosEstudiosDos;
	}

	public void setOtrosEstudiosDos(String otrosEstudiosDos) {
		this.otrosEstudiosDos = otrosEstudiosDos;
	}

	public Long getNumeroResolucion() {
		return this.numeroResolucion;
	}

	public void setNumeroResolucion(Long numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public Long getNumeroActa() {
		return this.numeroActa;
	}

	public void setNumeroActa(Long numeroActa) {
		this.numeroActa = numeroActa;
	}

	public Timestamp getFechaActa() {
		return this.fechaActa;
	}

	public void setFechaActa(Timestamp fechaActa) {
		this.fechaActa = fechaActa;
	}

	public Integer getNumeroComisionElect() {
		return this.numeroComisionElect;
	}

	public void setNumeroComisionElect(Integer numeroComisionElect) {
		this.numeroComisionElect = numeroComisionElect;
	}

	public String getRazonInadmision1() {
		return this.razonInadmision1;
	}

	public void setRazonInadmision1(String razonInadmision1) {
		this.razonInadmision1 = razonInadmision1;
	}

	public String getRazonInadmision2() {
		return this.razonInadmision2;
	}

	public void setRazonInadmision2(String razonInadmision2) {
		this.razonInadmision2 = razonInadmision2;
	}

	public String getRazonInadmision3() {
		return this.razonInadmision3;
	}

	public void setRazonInadmision3(String razonInadmision3) {
		this.razonInadmision3 = razonInadmision3;
	}

	public String getRazonInadmision4() {
		return this.razonInadmision4;
	}

	public void setRazonInadmision4(String razonInadmision4) {
		this.razonInadmision4 = razonInadmision4;
	}

	public String getRazonRechazo() {
		return this.razonRechazo;
	}

	public void setRazonRechazo(String razonRechazo) {
		this.razonRechazo = razonRechazo;
	}

	public Date getFechaResolucion() {
		return this.fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public Date getFechaGradoBUC() {
		return fechaGradoBUC;
	}

	public void setFechaGradoBUC(Date fechaGradoBUC) {
		this.fechaGradoBUC = fechaGradoBUC;
	}

	public Date getFechaGradoMod() {
		return fechaGradoMod;
	}

	public void setFechaGradoMod(Date fechaGradoMod) {
		this.fechaGradoMod = fechaGradoMod;
	}

	public Long getAntigLaboral() {
		return antigLaboral;
	}

	public void setAntigLaboral(Long antigLaboral) {
		this.antigLaboral = antigLaboral;
	}

	public String getPerfilCandidatoUno() {
		return perfilCandidatoUno;
	}

	public void setPerfilCandidatoUno(String perfilCandidatoUno) {
		this.perfilCandidatoUno = perfilCandidatoUno;
	}

	public String getPerfilCandidatoDos() {
		return perfilCandidatoDos;
	}

	public void setPerfilCandidatoDos(String perfilCandidatoDos) {
		this.perfilCandidatoDos = perfilCandidatoDos;
	}

	public String getCuentaPersonalMail() {
		return cuentaPersonalMail;
	}

	public void setCuentaPersonalMail(String cuentaPersonalMail) {
		this.cuentaPersonalMail = cuentaPersonalMail;
	}

	public String getCuentaPersonalTwitter() {
		return cuentaPersonalTwitter;
	}

	public void setCuentaPersonalTwitter(String cuentaPersonalTwitter) {
		this.cuentaPersonalTwitter = cuentaPersonalTwitter;
	}

	public String getCuentaPersonalFacebook() {
		return cuentaPersonalFacebook;
	}

	public void setCuentaPersonalFacebook(String cuentaPersonalFacebook) {
		this.cuentaPersonalFacebook = cuentaPersonalFacebook;
	}

	public String getArgumentoResolucion1() {
		return argumentoResolucion1;
	}

	public void setArgumentoResolucion1(String argumentoResolucion1) {
		this.argumentoResolucion1 = argumentoResolucion1;
	}

	public String getArgumentoResolucion2() {
		return argumentoResolucion2;
	}

	public void setArgumentoResolucion2(String argumentoResolucion2) {
		this.argumentoResolucion2 = argumentoResolucion2;
	}

	public String getArgumentoResolucion3() {
		return argumentoResolucion3;
	}

	public void setArgumentoResolucion3(String argumentoResolucion3) {
		this.argumentoResolucion3 = argumentoResolucion3;
	}

	public String getRazonResolucion1() {
		return razonResolucion1;
	}

	public void setRazonResolucion1(String razonResolucion1) {
		this.razonResolucion1 = razonResolucion1;
	}

	public String getRazonResolucion2() {
		return razonResolucion2;
	}

	public void setRazonResolucion2(String razonResolucion2) {
		this.razonResolucion2 = razonResolucion2;
	}

	public String getComisionElectoral() {
		return comisionElectoral;
	}

	public void setComisionElectoral(String comisionElectoral) {
		this.comisionElectoral = comisionElectoral;
	}

	public String getResolucionImpugnada() {
		return resolucionImpugnada;
	}

	public void setResolucionImpugnada(String resolucionImpugnada) {
		this.resolucionImpugnada = resolucionImpugnada;
	}

	public String getResolucionInterpuesta() {
		return resolucionInterpuesta;
	}

	public void setResolucionInterpuesta(String resolucionInterpuesta) {
		this.resolucionInterpuesta = resolucionInterpuesta;
	}

	public String getArgumentoRecurrente1() {
		return argumentoRecurrente1;
	}

	public void setArgumentoRecurrente1(String argumentoRecurrente1) {
		this.argumentoRecurrente1 = argumentoRecurrente1;
	}

	public String getArgumentoRecurrente2() {
		return argumentoRecurrente2;
	}

	public void setArgumentoRecurrente2(String argumentoRecurrente2) {
		this.argumentoRecurrente2 = argumentoRecurrente2;
	}

	public String getArgumentoComision1() {
		return argumentoComision1;
	}

	public void setArgumentoComision1(String argumentoComision1) {
		this.argumentoComision1 = argumentoComision1;
	}

	public String getArgumentoComision2() {
		return argumentoComision2;
	}

	public void setArgumentoComision2(String argumentoComision2) {
		this.argumentoComision2 = argumentoComision2;
	}

	public String getPronunciamientoTribunal1() {
		return pronunciamientoTribunal1;
	}

	public void setPronunciamientoTribunal1(String pronunciamientoTribunal1) {
		this.pronunciamientoTribunal1 = pronunciamientoTribunal1;
	}

	public String getPronunciamientoTribunal2() {
		return pronunciamientoTribunal2;
	}

	public void setPronunciamientoTribunal2(String pronunciamientoTribunal2) {
		this.pronunciamientoTribunal2 = pronunciamientoTribunal2;
	}

	public String getConsecuenciaTribunal1() {
		return consecuenciaTribunal1;
	}

	public void setConsecuenciaTribunal1(String consecuenciaTribunal1) {
		this.consecuenciaTribunal1 = consecuenciaTribunal1;
	}

	public String getConsecuenciaTribunal2() {
		return consecuenciaTribunal2;
	}

	public void setConsecuenciaTribunal2(String consecuenciaTribunal2) {
		this.consecuenciaTribunal2 = consecuenciaTribunal2;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}