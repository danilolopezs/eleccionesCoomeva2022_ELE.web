package co.com.coomeva.ele.backbeans;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoEnvioRadicacion;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.util.acceso.UtilAcceso;

public class EnviarRadicacionVista extends BaseVista{
	private static final String VIRTUAL = "virtual";
	private static final String PRESENCIAL = "presencial";
	
	private Boolean opcionVirtual;
	private Boolean opcionPresencial;
	private String opcionFirma;
	private SelectItem[] listaOpcionFirma;
	private Boolean existenPendientes;
	private DTOHabilidadAsociado pendientes;
	
	public EnviarRadicacionVista() {
		init();
	}
	
	private void init() {
		opcionVirtual = Boolean.FALSE;
		opcionPresencial = Boolean.FALSE;
		listaOpcionFirma = new SelectItem[2];
		listaOpcionFirma[0] = new SelectItem(VIRTUAL, "Firma Virtual");
		listaOpcionFirma[1] = new SelectItem(PRESENCIAL, "Firma Presencial");
		existenPendientes = Boolean.FALSE;
		consultarPendientes();
	}
	
	public void consultarPendientes() {
		try {
			pendientes = DelegadoEnvioRadicacion.getInstance().consultarPendientesAsociadoPorId(10276122L);
			if(pendientes != null) {
				existenPendientes = Boolean.TRUE;
			}
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}
	
	public void action_cambioDeFirma(ValueChangeEvent e) {
		opcionFirma = String.valueOf(e.getNewValue());
		System.out.println("cambio de opcion firma " + opcionFirma );
	}
	
	//GETTERS AND SETTERS
	public Boolean getOpcionVirtual() {
		return opcionVirtual;
	}

	public void setOpcionVirtual(Boolean opcionVirtual) {
		this.opcionVirtual = opcionVirtual;
	}

	public Boolean getOpcionPresencial() {
		return opcionPresencial;
	}

	public void setOpcionPresencial(Boolean opcionPresencial) {
		this.opcionPresencial = opcionPresencial;
	}

	public String getOpcionFirma() {
		
		return opcionFirma;
	}

	public void setOpcionFirma(String opcionFirma) {
		this.opcionFirma = opcionFirma;
	}

	public SelectItem[] getListaOpcionFirma() {
		return listaOpcionFirma;
	}

	public void setListaOpcionFirma(SelectItem[] listaOpcionFirma) {
		this.listaOpcionFirma = listaOpcionFirma;
	}

	public Boolean getExistenPendientes() {
		return existenPendientes;
	}

	public void setExistenPendientes(Boolean existenPendientes) {
		this.existenPendientes = existenPendientes;
	}

	public DTOHabilidadAsociado getPendientes() {
		return pendientes;
	}

	public void setPendientes(DTOHabilidadAsociado pendientes) {
		this.pendientes = pendientes;
	}

}
