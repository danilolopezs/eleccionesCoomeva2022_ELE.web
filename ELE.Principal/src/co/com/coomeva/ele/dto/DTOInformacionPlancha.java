package co.com.coomeva.ele.dto;

import java.util.List;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class DTOInformacionPlancha
 * @date 2/12/2012
 *
 */
public class DTOInformacionPlancha {
	
	public Long consecutivoPlancha;
	public String numeroZonaElectoral;
	public String ciudad;
	public String anno;
	public String mes;
	public String dia;
	public String hora;
	public List<DTOMiembroPlancha> miembrosTitulares;
	public List<DTOMiembroPlancha> miembrosSuplentes;
	public String estadoPlancha;
	public String nombresApellidosCabeza;
	public String numeroIdentificacionCabeza;
	public String fechaHoraInscripcionPlancha;
	public String numeroPlancha;
	private String descripcionZonaElectoral;
	private String descripcionExcepciones;
	private Long firmaVirtual;
	
	public String numeroZonaElectoralEspecial;
	private String descripcionZonaElectoralEspecial;
	
	public String getNumeroZonaElectoral() {
		return numeroZonaElectoral;
	}
	public void setNumeroZonaElectoral(String numeroZonaElectoral) {
		this.numeroZonaElectoral = numeroZonaElectoral;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public List<DTOMiembroPlancha> getMiembrosTitulares() {
		return miembrosTitulares;
	}
	public void setMiembrosTitulares(List<DTOMiembroPlancha> miembrosTitulares) {
		this.miembrosTitulares = miembrosTitulares;
	}
	public List<DTOMiembroPlancha> getMiembrosSuplentes() {
		return miembrosSuplentes;
	}
	public void setMiembrosSuplentes(List<DTOMiembroPlancha> miembrosSuplentes) {
		this.miembrosSuplentes = miembrosSuplentes;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Long getConsecutivoPlancha() {
		return consecutivoPlancha;
	}
	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}
	public String getEstadoPlancha() {
		return estadoPlancha;
	}
	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}
	public String getNombresApellidosCabeza() {
		return nombresApellidosCabeza;
	}
	public void setNombresApellidosCabeza(String nombresApellidosCabeza) {
		this.nombresApellidosCabeza = nombresApellidosCabeza;
	}
	public String getNumeroIdentificacionCabeza() {
		return numeroIdentificacionCabeza;
	}
	public void setNumeroIdentificacionCabeza(String numeroIdentificacionCabeza) {
		this.numeroIdentificacionCabeza = numeroIdentificacionCabeza;
	}
	public String getFechaHoraInscripcionPlancha() {
		return fechaHoraInscripcionPlancha;
	}
	public void setFechaHoraInscripcionPlancha(String fechaHoraInscripcionPlancha) {
		this.fechaHoraInscripcionPlancha = fechaHoraInscripcionPlancha;
	}
	public String getNumeroPlancha() {
		return numeroPlancha;
	}
	public void setNumeroPlancha(String numeroPlancha) {
		this.numeroPlancha = numeroPlancha;
	}
	public String getDescripcionZonaElectoral() {
		return descripcionZonaElectoral;
	}
	public void setDescripcionZonaElectoral(String descripcionZonaElectoral) {
		this.descripcionZonaElectoral = descripcionZonaElectoral;
	}
	public String getDescripcionExcepciones() {
		return descripcionExcepciones;
	}
	public void setDescripcionExcepciones(String descripcionExcepciones) {
		this.descripcionExcepciones = descripcionExcepciones;
	}
	public String getNumeroZonaElectoralEspecial() {
		return numeroZonaElectoralEspecial;
	}
	public void setNumeroZonaElectoralEspecial(String numeroZonaElectoralEspecial) {
		this.numeroZonaElectoralEspecial = numeroZonaElectoralEspecial;
	}
	public String getDescripcionZonaElectoralEspecial() {
		return descripcionZonaElectoralEspecial;
	}
	public void setDescripcionZonaElectoralEspecial(
			String descripcionZonaElectoralEspecial) {
		this.descripcionZonaElectoralEspecial = descripcionZonaElectoralEspecial;
	}
	public Long getFirmaVirtual() {
		return firmaVirtual;
	}
	public void setFirmaVirtual(Long firmaVirtual) {
		this.firmaVirtual = firmaVirtual;
	}
}
