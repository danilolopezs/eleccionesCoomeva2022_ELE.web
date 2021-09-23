package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class VerSanearPlanchaUGA extends BaseVista{

	private String nroCabezaPlancha;
	private String profesion;
	private boolean renderBoton;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String imagenCabPlancha;
	private String cargosDirectivos;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<EleSuplentesDTO> listaSuplentes;
	private String nroIdentificacionDetalle;
	private String nombreAsociadoDetalle;
	private String inhabilidadesDetalle;
	private String profesionDetalle;
	private boolean visibleDetalle;
	private boolean visibleConfirmar;
	private boolean visibleConfirmarOk;
	private String mensajeConfirmar;
	private String imagenConf;
	private String estadoPlancha;
	private EleCabPlanchaDTO cabezaPlancha;
	private ElePlanchaDTO planchaVerificada ;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private String estudios1;
	private String estudios2;
	private String msgOk;
	private String zona;
	private boolean renderSuplentes;
	private String style;
	private UserVo userVo;

	public VerSanearPlanchaUGA() {
		init(); 
	}
	/**
	 * Metodo que inicializa los componentes en la vista 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 */

	public void init() 
	{
		try 
		{
			userVo = (UserVo) FacesUtils.getSessionParameter("user");
			
			planchaVerificada = (ElePlanchaDTO) FacesUtils.getSessionParameter("planchaVerificada");

			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");

			cabezaPlancha = new EleCabPlanchaDTO();
			cabezaPlancha = planchaVerificada.getEleCabPlanchaDTO();
			zona = planchaVerificada.getEleZonas().getNomZona();
			nroCabezaPlancha = cabezaPlancha.getNroIdentificacion();
			profesion = cabezaPlancha.getProfesion();
			antiguedad = cabezaPlancha.getAntiguedad()+" Años";
			nombreCabezaPlancha = cabezaPlancha.getNombreCompleto();
			listaPrincipales = planchaVerificada.getListaPrincipales();
			listaSuplentes = planchaVerificada.getListaSuplentes();
			cargosDirectivos = cabezaPlancha.getCargosDirectivos();
			
			estadoPlancha = planchaVerificada.getEstado();//UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8");
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado8");
			}else
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado9");
			
			style = "Color: black;";
			
			if (listaSuplentes.size()==0) {
				renderSuplentes = false;
			}else
				renderSuplentes = true;

			List<EleExperienciaLaboral> listExperienciaLaboral = planchaVerificada.getListExperienciaLaboral();
			EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;
			listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();

			int iCont = 0;
			
			int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");

			for (EleExperienciaLaboral eleExperienciaLaboral : listExperienciaLaboral) 
			{
				eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
				eleTablaEmpresaCargoDTO.setCargo(eleExperienciaLaboral.getCargo());
				eleTablaEmpresaCargoDTO.setEmpresa(eleExperienciaLaboral.getEmpresa());
				listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
				iCont++;
			}
			
			if (listEmpresaCargo.size() == 0) {
				for (int i = 0; i < noEmpresaCargo; i++) {
					eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
					eleTablaEmpresaCargoDTO.setCargo("-");
					eleTablaEmpresaCargoDTO.setEmpresa("-");
					listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
					iCont++;
				}
			}

			String[] estudios = planchaVerificada.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")); 
			estudios1 = estudios[0];
			estudios2 = estudios[1];

			if (cabezaPlancha.getRutaImagen() == null||cabezaPlancha.getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = cabezaPlancha.getRutaImagen();
		}
		catch (Exception e) 
		{
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	/**
	 * Que envia la modificacion de la plancha 
	 * @author Manuel Galvez y Ricardo Chiriboga 
	 * @return String
	 */
	public String action_modificar()
	{
		String planchaEstado = "";
		visibleConfirmar = false;
		ElePlanchas elplancha = new ElePlanchas();
		elplancha.setFechaInscripcion(ManipulacionFechas.getFechaActual());
		
		if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "displayEstado8"))) {
			planchaEstado = UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8");
		}else
			planchaEstado = UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha9");
		
		elplancha.setEstado(planchaEstado);
		elplancha.setNroCabPlancha(planchaVerificada.getNroCabPlancha());
		elplancha.setDescEstado(planchaVerificada.getDescEstado());
		elplancha.setEleZonas(planchaVerificada.getEleZonas());

		try 
		{
			EleLogDTO eleLogDTO = new EleLogDTO();
			eleLogDTO.setNombreUsuario(userVo.getName().toString());
			eleLogDTO.setNombreTransaccion(UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccion3"));
			eleLogDTO.setTipoTransaccion(UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion3"));
			
			DelegadoPlanchas.getInstance().modificarPlanchaInscrita(elplancha, planchaVerificada.getEleCabPlanchaDTO(), planchaVerificada.getListaPrincipales(), planchaVerificada.getListaSuplentes(), planchaVerificada.getListExperienciaLaboral(), eleLogDTO);
			
			msgOk = UtilAcceso.getParametroFuenteS("mensajes", "mensajeExito");
			
			FacesUtils.setSessionParameter("userPlancha", null);
			FacesUtils.setSessionParameter("planchaVerificada", null);
			
			List<ElePrincipalesDTO> listPrincipalesDTO = planchaVerificada.getListaPrincipales();
			
			for (ElePrincipalesDTO elePrincipalesDTO : listPrincipalesDTO) {
				String completoNombre = elePrincipalesDTO.getPrimerApellido()+" "+elePrincipalesDTO.getSegundoApellido()+" "+elePrincipalesDTO.getPrimerNombre()+" "+ elePrincipalesDTO.getSegundoNombre();
				elePrincipalesDTO.setNombreCompleto(completoNombre);
			}
			
			List<EleSuplentesDTO> listSuplentesDTO = planchaVerificada.getListaSuplentes();
			
			for (EleSuplentesDTO eleSuplentesDTO : listSuplentesDTO) {
				String completoNombre = eleSuplentesDTO.getPrimerApellido()+" "+eleSuplentesDTO.getSegundoApellido()+" "+eleSuplentesDTO.getPrimerNombre()+" "+ eleSuplentesDTO.getSegundoNombre();
				eleSuplentesDTO.setNombreCompleto(completoNombre);
			}
			
			FacesUtils.setSessionParameter("planchaImprimir", planchaVerificada);
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "servletInvokerCuadernillo();");

		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
			return "";
		}
		visibleConfirmarOk = true;
		return "";
	}
	/**
	 * Metodo que muestra el detalle del asociado
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String action_verDetalle(){
		visibleDetalle = true;
		String idSuplente = FacesUtils.getRequestParameter("suId");
		String idPrincipal = FacesUtils.getRequestParameter("priId");

		if (idSuplente != null&&!idSuplente.trim().equals("")) {
			for (EleSuplentesDTO suplentes : listaSuplentes) {
				if (suplentes.getNroSuIdentificacion().equalsIgnoreCase(idSuplente)) {
					nroIdentificacionDetalle = suplentes.getNroSuIdentificacion();
					nombreAsociadoDetalle = suplentes.getNombreCompleto();
					profesionDetalle = suplentes.getProfesion();
					inhabilidadesDetalle = suplentes.getStringInhabilidades();
				}
			}
		}else{
			for (ElePrincipalesDTO principal : listaPrincipales) {
				if (principal.getNroPriIdentificacion().equalsIgnoreCase(idPrincipal)) {
					nroIdentificacionDetalle = principal.getNroPriIdentificacion();
					nombreAsociadoDetalle = principal.getNombreCompleto();
					profesionDetalle = principal.getProfesion();
					inhabilidadesDetalle = principal.getStringInhabilidades();
				}
			}
		}

		return "";
	}
	/**
	 * Metodo que cierra el detalle del asociado
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeDetalle(){
		visibleDetalle = false;
		nroIdentificacionDetalle = "";
		nombreAsociadoDetalle = "";
		profesionDetalle = "";
		return "";
	}
	/**
	 * Metodo que redirige al usuario a la pantalla inicial de Sanear plancha
	 * @author Manuel Galvez y Ricardo Chiribogas
	 * @return String
	 */
	public String action_cerrar()
	{
		visibleConfirmarOk = false;
		return "goSanearPlanchasUGA";
	}
	/**
	 * Metodo que Abre el popup de confirmar la transaccion
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 * 
	 */
	public String action_verConfirmar(){
		visibleConfirmar = true;
		if(planchaVerificada.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))){
			mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "confirmarPlanchaModHabil") ;
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");
			renderBoton = true;
		}
		if(planchaVerificada.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))){
			mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "confirmarPlanchaInhabil");//confirmarPlanchaSanearNO
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfnoHabPlancha");
			renderBoton = true;
		}
		
		return "";
	}
	/**
	 * Cierra el popup de Confirmar 
	 * @return String
	 */
	public String action_closeConfirmar(){
		visibleConfirmar = false;		
		return "";
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_Volver()
	{
		return "goSanearPlanchasOkUGA";
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return boolean
	 */

	public boolean isVisibleDetalle() {
		return visibleDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param visibleDetalle
	 */
	public void setVisibleDetalle(boolean visibleDetalle) {
		this.visibleDetalle = visibleDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return
	 */
	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param visibleConfirmar
	 */
	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getImagenConf() {
		return imagenConf;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiribogas
	 * @param imagenConf
	 */

	public void setImagenConf(String imagenConf) {
		this.imagenConf = imagenConf;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return EleCabPlanchaDTO
	 */

	public EleCabPlanchaDTO getCabezaPlancha() {
		return cabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param cabezaPlancha
	 */

	public void setCabezaPlancha(EleCabPlanchaDTO cabezaPlancha) {
		this.cabezaPlancha = cabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return ElePlanchaDTO
	 */
	public ElePlanchaDTO getPlanchaVerificada() {
		return planchaVerificada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param planchaVerificada
	 */
	public void setPlanchaVerificada(ElePlanchaDTO planchaVerificada) {
		this.planchaVerificada = planchaVerificada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getEstadoPlancha() {
		return estadoPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param estadoPlancha
	 */
	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return List<EleTablaEmpresaCargoDTO>
	 */
	public List<EleTablaEmpresaCargoDTO> getListEmpresaCargo() {
		return listEmpresaCargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listEmpresaCargo
	 */
	public void setListEmpresaCargo(List<EleTablaEmpresaCargoDTO> listEmpresaCargo) {
		this.listEmpresaCargo = listEmpresaCargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleConfirmarOk() {
		return visibleConfirmarOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param visibleConfirmarOk
	 */
	public void setVisibleConfirmarOk(boolean visibleConfirmarOk) {
		this.visibleConfirmarOk = visibleConfirmarOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getMsgOk() {
		return msgOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param msgOk
	 */

	public void setMsgOk(String msgOk) {
		this.msgOk = msgOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String 
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param zona
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getMensajeConfirmar() {
		return mensajeConfirmar;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param mensajeConfirmar
	 */

	public void setMensajeConfirmar(String mensajeConfirmar) {
		this.mensajeConfirmar = mensajeConfirmar;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getNroIdentificacionDetalle() {
		return nroIdentificacionDetalle;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacionDetalle
	 */

	public void setNroIdentificacionDetalle(String nroIdentificacionDetalle) {
		this.nroIdentificacionDetalle = nroIdentificacionDetalle;
	}


	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getNombreAsociadoDetalle() {
		return nombreAsociadoDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nombreAsociadoDetalle
	 */


	public void setNombreAsociadoDetalle(String nombreAsociadoDetalle) {
		this.nombreAsociadoDetalle = nombreAsociadoDetalle;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getProfesionDetalle() {
		return profesionDetalle;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param profesionDetalle
	 */

	public void setProfesionDetalle(String profesionDetalle) {
		this.profesionDetalle = profesionDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getNroCabezaPlancha() {
		return nroCabezaPlancha;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 */
	public void setNroCabezaPlancha(String nroCabezaPlancha) {
		this.nroCabezaPlancha = nroCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String 
	 */
	public String getProfesion() {
		return profesion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param profesion
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getAntiguedad() {
		return antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param antiguedad
	 */
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getNombreCabezaPlancha() {
		return nombreCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nombreCabezaPlancha
	 */
	public void setNombreCabezaPlancha(String nombreCabezaPlancha) {
		this.nombreCabezaPlancha = nombreCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getImagenCabPlancha() {
		return imagenCabPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param imagenCabPlancha
	 */
	public void setImagenCabPlancha(String imagenCabPlancha) {
		this.imagenCabPlancha = imagenCabPlancha;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return List<ElePrincipalesDTO>
	 */

	public List<ElePrincipalesDTO> getListaPrincipales() {
		return listaPrincipales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaPrincipales
	 */


	public void setListaPrincipales(List<ElePrincipalesDTO> listaPrincipales) {
		this.listaPrincipales = listaPrincipales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return
	 */


	public List<EleSuplentesDTO> getListaSuplentes() {
		return listaSuplentes;
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaSuplentes
	 */
	public void setListaSuplentes(List<EleSuplentesDTO> listaSuplentes) {
		this.listaSuplentes = listaSuplentes;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getEstudios1() {
		return estudios1;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param estudios1
	 */
	public void setEstudios1(String estudios1) {
		this.estudios1 = estudios1;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getEstudios2() {
		return estudios2;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param estudios2
	 */
	public void setEstudios2(String estudios2) {
		this.estudios2 = estudios2;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return String
	 */
	public String getInhabilidadesDetalle() {
		return inhabilidadesDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param inhabilidadesDetalle
	 */
	public void setInhabilidadesDetalle(String inhabilidadesDetalle) {
		this.inhabilidadesDetalle = inhabilidadesDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderSuplentes() {
		return renderSuplentes;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderSuplentes
	 */
	public void setRenderSuplentes(boolean renderSuplentes) {
		this.renderSuplentes = renderSuplentes;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderBoton() {
		return renderBoton;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderBoton
	 */
	public void setRenderBoton(boolean renderBoton) {
		this.renderBoton = renderBoton;
	}
	public String getCargosDirectivos() {
		return cargosDirectivos;
	}
	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}
}