package co.com.coomeva.ele.dto;

public class DTOMiembroPlancha implements Comparable{
	
	public String posicionPlancha;
	public Long numeroDocumento;
	public String apellidosNombres;
	public String profesion;
	public String observacionAdicionMiembro;
	public String tipoMiembro;
	public boolean tieneProfesion = Boolean.TRUE;
	
	public String getPosicionPlancha() {
		return posicionPlancha;
	}
	public void setPosicionPlancha(String posicionPlancha) {
		this.posicionPlancha = posicionPlancha;
	}
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getApellidosNombres() {
		return apellidosNombres;
	}
	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	
	public String getObservacionAdicionMiembro() {
		return observacionAdicionMiembro;
	}
	
	public void setObservacionAdicionMiembro(String observacionAdicionMiembro) {
		this.observacionAdicionMiembro = observacionAdicionMiembro;
	}
	
	public final int compareTo(Object o) {
		if(o instanceof DTOMiembroPlancha){
			return new Long(this.getPosicionPlancha())
			.compareTo(new Long(((DTOMiembroPlancha)o).getPosicionPlancha()));
		}
		return 0;
	}
	public String getTipoMiembro() {
		return tipoMiembro;
	}
	public void setTipoMiembro(String tipoMiembro) {
		this.tipoMiembro = tipoMiembro;
	}
	public boolean isTieneProfesion() {
		return tieneProfesion;
	}
	public void setTieneProfesion(boolean tieneProfesion) {
		this.tieneProfesion = tieneProfesion;
	}
}
