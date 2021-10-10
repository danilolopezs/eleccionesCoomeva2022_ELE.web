package co.com.coomeva.ele.backbeans.planchas;

import co.com.coomeva.ele.util.FacesUtils;

public class MenuPlanchaAsociado {

	private boolean renderRegistro;
	private boolean renderConsulta;
	private boolean renderModificacion;

	public MenuPlanchaAsociado() 
	{
		init();
	}

	/**
	 * Metodo que recibe las secciones de usuarios y dependiendo de las secciones habilitadas renderiza el menu de las JSPX
	 * @author Oscar Javier Salazar
	 */
	@SuppressWarnings("unchecked")
	private void init() 
	{
	}

	/**
	 * Metodo de Acceso
	 * @return boolean
	 */
	public String salir(){
		FacesUtils.getSession().removeAttribute("asociado");
		FacesUtils.getSession().removeAttribute("delegadosZona");
		FacesUtils.getSession().removeAttribute("numeroDocAsociado");
		return "goInicioAsociado";
	//	return "goLInicioRegitroPlanchas";
	}

	public boolean isRenderRegistro() {
		return renderRegistro;
	}

	public void setRenderRegistro(boolean renderRegistro) {
		this.renderRegistro = renderRegistro;
	}

	public boolean isRenderConsulta() {
		return renderConsulta;
	}

	public void setRenderConsulta(boolean renderConsulta) {
		this.renderConsulta = renderConsulta;
	}

	public boolean isRenderModificacion() {
		return renderModificacion;
	}

	public void setRenderModificacion(boolean renderModificacion) {
		this.renderModificacion = renderModificacion;
	}
}