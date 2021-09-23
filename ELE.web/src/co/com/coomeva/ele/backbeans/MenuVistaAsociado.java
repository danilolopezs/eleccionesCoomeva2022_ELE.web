package co.com.coomeva.ele.backbeans;
/**
 * MenuVistaAsociado.java
 * 
 * @author Carlos Ernesto Ortega Q. Contratista Coomeva 16/01/2014
 */

import co.com.coomeva.ele.util.FacesUtils;

public class MenuVistaAsociado {

	private boolean renderInicioMenu;
	private boolean renderSalir;

	public MenuVistaAsociado() {
		if (FacesUtils.getSessionParameter("userComision") != null) {
			this.renderInicioMenu = true;
			this.renderSalir = false;
		} else {
			this.renderInicioMenu = false;
			this.renderSalir = true;
		}
	}

	public boolean isRenderInicioMenu() {
		return renderInicioMenu;
	}

	public void setRenderInicioMenu(boolean renderInicioMenu) {
		this.renderInicioMenu = renderInicioMenu;
	}

	public boolean isRenderSalir() {
		return renderSalir;
	}

	public void setRenderSalir(boolean renderSalir) {
		this.renderSalir = renderSalir;
	}

}