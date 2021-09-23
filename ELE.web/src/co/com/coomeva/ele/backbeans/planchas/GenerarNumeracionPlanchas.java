package co.com.coomeva.ele.backbeans.planchas;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.backbeans.MensajesVista;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.web
 * @class GenerarNumeracionPlanchas
 * @date 6/12/2012
 * 
 */
public class GenerarNumeracionPlanchas {

	private LoaderResourceElements loaderResourceElements = LoaderResourceElements
			.getInstance();
	private SelectItem[] zonasElectorales;
	private Long codZonaElectoral;
	private String tablaDinamicaHTML;
	private MensajesVista mensajeVista = new MensajesVista();
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarGenerar;
	
	private SelectItem[] zonasElectoralesEspe;
	private Long codZonaElectoralEspe;
	private String tipoZona;
	private boolean esZonaEspecial;
	private SelectItem[] listaTipoZona;

	public GenerarNumeracionPlanchas() {
		tipoZona = "ZONA";
	}
	
	public void activarzonaElectoralListener(ValueChangeEvent evento)
	{
		if(evento.getNewValue() != null)
		{
			tipoZona = evento.getNewValue().toString();
			
			if(tipoZona.equalsIgnoreCase("ZONA_ESPE"))
			{
				esZonaEspecial = true;
				this.tablaDinamicaHTML = null;
			}
			else
			{
				esZonaEspecial = false;
				this.tablaDinamicaHTML = null;
			}
		}
		else
		{
			esZonaEspecial = false;
			this.tablaDinamicaHTML = null;
		}
	}
	
	public String consultarPlanchasPorZonaElectoral() {
		try {
			
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
					.obtenerHtmlPlanchasAdmitidasPorRegional(
							this.codZonaElectoral, user.getUserId(), 0);
			codZonaElectoralEspe = -1L;
		} catch (Exception e) {
			this.tablaDinamicaHTML = null;
			this.codZonaElectoralEspe = -1L;
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}
	
	public String consultarPlanchasPorZonaElectoralEspe() {
		try {
			
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
					.obtenerHtmlPlanchasAdmitidasPorZonaEspec(
							this.codZonaElectoralEspe, user.getUserId(), 0);
			codZonaElectoral = -1L;
		} catch (Exception e) {
			this.tablaDinamicaHTML = null;
			this.codZonaElectoral = -1L;
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}
	
	public String consultarPlanchasPorZonaElectoralRec() {
		try {
			
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
					.obtenerHtmlPlanchasAdmitidasPorRegional(
							this.codZonaElectoral, user.getUserId(), 1);
		} catch (Exception e) {
			this.tablaDinamicaHTML = null;
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}
	
	public String consultarPlanchasPorZonaElectoralEspeRec() {
		try {
			
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
					.obtenerHtmlPlanchasAdmitidasPorZonaEspec(
							this.codZonaElectoralEspe, user.getUserId(), 1);
		} catch (Exception e) {
			this.tablaDinamicaHTML = null;
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}

	public String generarNumeracionPlanchasZonaElect() {
		try {
			this.visibleConfirmarGenerar = Boolean.FALSE;
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			
			if(esZonaEspecial)
			{
				DelegadoPlancha.getInstance().generarNumeracionPlanchas(this.codZonaElectoralEspe, user.getUserId(), esZonaEspecial);
				
				this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
				.obtenerHtmlPlanchasAdmitidasPorZonaEspec(
						this.codZonaElectoralEspe, user.getUserId(), 0);
			}
			else
			{
				DelegadoPlancha.getInstance().generarNumeracionPlanchas(this.codZonaElectoral, user.getUserId(), esZonaEspecial);
				this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
				.obtenerHtmlPlanchasAdmitidasPorRegional(
						this.codZonaElectoral, user.getUserId(), 0);
			}
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se generó satisfactoriamente la numeración" +
					" para la zona electoral seleccionada");
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}
	
	public String generarNumeracionPlanchasZonaElectEnRecurso() {
		try {
			this.visibleConfirmarGenerar = Boolean.FALSE;
			UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
			
			if(esZonaEspecial)
			{
				DelegadoPlancha.getInstance().generarNumeracionPlanchasRecurso(this.codZonaElectoralEspe, user.getUserId(), esZonaEspecial);
				
				this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
				.obtenerHtmlPlanchasAdmitidasPorZonaEspec(
						this.codZonaElectoralEspe, user.getUserId(), 1);
			}
			else
			{
				DelegadoPlancha.getInstance().generarNumeracionPlanchasRecurso(this.codZonaElectoral, user.getUserId(), esZonaEspecial);
				this.tablaDinamicaHTML = DelegadoPlancha.getInstance()
				.obtenerHtmlPlanchasAdmitidasPorRegional(
						this.codZonaElectoral, user.getUserId(), 1);
			}
			
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se generó satisfactoriamente la numeración" +
					" para la zona electoral seleccionada");
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}

	public String actionConfirmarProcesoNumeracion() {
		try {
			
			if(esZonaEspecial)
			{
				if (this.codZonaElectoralEspe == null
						|| this.codZonaElectoralEspe.intValue() == -1) {
					throw new EleccionesDelegadosException("Por favor seleccione una" +
							" zona electoral especial para generar la numeración de planchas");
				}
			}
			else
			{
				if (this.codZonaElectoral == null
						|| this.codZonaElectoral.intValue() == -1) {
					throw new EleccionesDelegadosException("Por favor seleccione una" +
							" zona electoral para generar la numeración de planchas");
				}
			}
			
			this.visibleConfirmarGenerar = Boolean.TRUE;
			this.mensajeConfirmacion = "Esta seguro que desea generar la numeración para las"
					+ " planchas de la zona actual?";
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}

	public SelectItem[] getZonasElectorales() {
		List<FiltrosConsultasDTO> list = null;
		if (this.zonasElectorales == null) {
			try {
				list = DelegadoZonaElectoral.getInstance()
						.consultarZonasElectorales();
				int i = 0;
				this.zonasElectorales = new SelectItem[list.size() + 1];
				this.zonasElectorales[i] = new SelectItem(
						-1,
						loaderResourceElements
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
										"lblOpcionSeleccionarCombo"));
				i++;
				for (FiltrosConsultasDTO dto : list) {
					this.zonasElectorales[i] = new SelectItem(dto
							.getCodigoFiltro(), dto.getDescripcionFiltro());
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return zonasElectorales;
	}

	public String actionCloseConfirmar() {
		this.visibleConfirmarGenerar = Boolean.FALSE;
		return "";
	}

	public void setZonasElectorales(SelectItem[] zonasElectorales) {
		this.zonasElectorales = zonasElectorales;
	}

	public Long getCodZonaElectoral() {
		return codZonaElectoral;
	}

	public void setCodZonaElectoral(Long codZonaElectoral) {
		this.codZonaElectoral = codZonaElectoral;
	}

	public String getTablaDinamicaHTML() {
		return tablaDinamicaHTML;
	}

	public void setTablaDinamicaHTML(String tablaDinamicaHTML) {
		this.tablaDinamicaHTML = tablaDinamicaHTML;
	}

	public MensajesVista getMensajeVista() {
		return mensajeVista;
	}

	public void setMensajeVista(MensajesVista mensajeVista) {
		this.mensajeVista = mensajeVista;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public boolean isVisibleConfirmarGenerar() {
		return visibleConfirmarGenerar;
	}

	public void setVisibleConfirmarGenerar(boolean visibleConfirmarGenerar) {
		this.visibleConfirmarGenerar = visibleConfirmarGenerar;
	}

	public boolean isVisibleBotonIniciarProceso() {
		return tablaDinamicaHTML != null && tablaDinamicaHTML.length() > 0;
	}

	public void setVisibleBotonIniciarProceso(boolean visibleBotonIniciarProceso) {
	}

	public SelectItem[] getZonasElectoralesEspe() {
		List<FiltrosConsultasDTO> list = null;
		if (this.zonasElectoralesEspe == null) {
			try {
				list = DelegadoZonaElectoral.getInstance()
						.consultarZonasElectoralesEspecial();
				int i = 0;
				this.zonasElectoralesEspe = new SelectItem[list.size() + 1];
				this.zonasElectoralesEspe[i] = new SelectItem(
						-1,
						loaderResourceElements
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
										"lblOpcionSeleccionarCombo"));
				i++;
				for (FiltrosConsultasDTO dto : list) {
					this.zonasElectoralesEspe[i] = new SelectItem(dto
							.getCodigoFiltro(), dto.getDescripcionFiltro());
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return zonasElectoralesEspe;
	}

	public void setZonasElectoralesEspe(SelectItem[] zonasElectoralesEspe) {
		this.zonasElectoralesEspe = zonasElectoralesEspe;
	}

	public Long getCodZonaElectoralEspe() {
		return codZonaElectoralEspe;
	}

	public void setCodZonaElectoralEspe(Long codZonaElectoralEspe) {
		this.codZonaElectoralEspe = codZonaElectoralEspe;
	}

	public String getTipoZona() {
		return tipoZona;
	}

	public void setTipoZona(String tipoZona) {
		this.tipoZona = tipoZona;
	}

	public SelectItem[] getListaTipoZona() {
		if(listaTipoZona == null)
		{
			listaTipoZona = new SelectItem[2];
			
			listaTipoZona[0] = new SelectItem("ZONA", "Zona Electoral");
			listaTipoZona[1] = new SelectItem("ZONA_ESPE", "Zona Electoral Especial");
		}
		return listaTipoZona;
	}

	public void setListaTipoZona(SelectItem[] listaTipoZona) {
		this.listaTipoZona = listaTipoZona;
	}

	public boolean isEsZonaEspecial() {
		return esZonaEspecial;
	}

	public void setEsZonaEspecial(boolean esZonaEspecial) {
		this.esZonaEspecial = esZonaEspecial;
	}
}
