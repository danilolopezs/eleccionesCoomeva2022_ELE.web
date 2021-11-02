package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

public class AsociadoVO {
	private String consecutivo;
	private String cedula;
	private String nombre;
	private String profesion;
	private String correo;
	private String firma;

	public AsociadoVO(String consecutivo, String cedula, String nombre, String profesion, String correo, String firma) {
		super();
		this.consecutivo = consecutivo;
		this.cedula = cedula;
		this.nombre = nombre;
		this.profesion = profesion;
		this.correo = correo;
		this.firma = firma;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}
}