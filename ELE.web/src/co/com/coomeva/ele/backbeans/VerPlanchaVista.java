package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class VerPlanchaVista extends BaseVista{

	private String nroCabezaPlancha;
	private String profesion;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String imagenCabPlancha;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<EleSuplentesDTO> listaSuplentes;
	private String nroIdentificacionDetalle;
	private String nombreAsociadoDetalle;
	private String profesionDetalle;
	private boolean visibleDetalle;
	private boolean visibleConfirmar;
	private boolean visiblePlanchaCreada;
	
	private String mensajeConfirmar;
	private String mensaje2; 
	private String imagenConf;
	private String estadoPlancha;
	private EleCabPlanchaDTO cabezaPlancha;
	private ElePlanchaDTO planchaVerificada;
	private String estudios1;
	private String estudios2;
	private String cargosDirectivos;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private String btnCerrar;
	private String style;
	private String zona;

	public VerPlanchaVista() {
		init(); 
	}
	
	/**
	 * Metodo que recibe la informaci�n de la plancha verificada e inicializa los componentes
	 */
	public void init() 
	{
		try 
		{
			planchaVerificada = (ElePlanchaDTO) FacesUtils.getSessionParameter("planchaVerificada");
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");

			cabezaPlancha = planchaVerificada.getEleCabPlanchaDTO();
			nroCabezaPlancha = cabezaPlancha.getNroIdentificacion();
			profesion = cabezaPlancha.getProfesion();
			antiguedad = cabezaPlancha.getAntiguedad()+" A�os";
			nombreCabezaPlancha = cabezaPlancha.getNombreCompleto();
			listaPrincipales = planchaVerificada.getListaPrincipales();
			listaSuplentes = planchaVerificada.getListaSuplentes();
			estadoPlancha = planchaVerificada.getEstado();
			cargosDirectivos = planchaVerificada.getEleCabPlanchaDTO().getCargosDirectivos();
			String[] estudios = planchaVerificada.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
			estudios1 = estudios[0];
			estudios2 = estudios[1];
			zona = planchaVerificada.getEleZonas().getNomZona();
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado3");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado1");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				style = "Color:Red;";
			}
			
			List<EleExperienciaLaboral> listExperienciaLaboral = planchaVerificada.getListExperienciaLaboral();
			listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();
			EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;

			int iCont = 0;
			
			for (EleExperienciaLaboral eleExperienciaLaboral : listExperienciaLaboral) 
			{
				eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
				eleTablaEmpresaCargoDTO.setCargo(eleExperienciaLaboral.getCargo());
				eleTablaEmpresaCargoDTO.setEmpresa(eleExperienciaLaboral.getEmpresa());
				listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
				iCont++;
			}
			
			int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");
			
			if (listEmpresaCargo.size() == 0) {
				for (int i = 0; i < noEmpresaCargo; i++) {
					eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
					eleTablaEmpresaCargoDTO.setCargo("-");
					eleTablaEmpresaCargoDTO.setEmpresa("-");
					listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
					iCont++;
				}
			}
			
			
			if (cabezaPlancha.getRutaImagen() == null||cabezaPlancha.getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = cabezaPlancha.getRutaImagen();
		}
		catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}	
	
	/**
	 * Metodo que toma la informaci�n ya modificada y validada e inscribe la plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_inscribir()
	{
		visibleConfirmar = false;
		ElePlanchaDTO elplancha = new ElePlanchaDTO();
		elplancha.setFechaInscripcion(ManipulacionFechas.getFechaActual());
		elplancha.setEstado(planchaVerificada.getEstado());
		elplancha.setNroCabPlancha(planchaVerificada.getNroCabPlancha());
		elplancha.setDescEstado(planchaVerificada.getDescEstado());
		elplancha.setEleZonas(planchaVerificada.getEleZonas());
		
		planchaVerificada.getEleCabPlanchaDTO().setCargosDirectivos(cargosDirectivos);
		if (estudios1.trim().equalsIgnoreCase("")) {
			estudios1="-";
		}
		
		if (estudios2.trim().equalsIgnoreCase("")) {
			estudios2="-";
		}
		planchaVerificada.getEleCabPlanchaDTO().setOtrosEstudios(estudios1+UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")+estudios2);
		
		List<EleTablaEmpresaCargoDTO> listaEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();
		EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;
		boolean save = false;
		
		for (EleTablaEmpresaCargoDTO eleTablaEmpresaCargo : listEmpresaCargo) {
			eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
			
			if (!eleTablaEmpresaCargo.getEmpresa().equalsIgnoreCase("") && !eleTablaEmpresaCargo.getEmpresa().equalsIgnoreCase("-") ) {
				eleTablaEmpresaCargoDTO.setEmpresa(eleTablaEmpresaCargo.getEmpresa());
				save = true;
			}
			if (!eleTablaEmpresaCargo.getCargo().equalsIgnoreCase("") && !eleTablaEmpresaCargo.getCargo().equalsIgnoreCase("-")) {
				eleTablaEmpresaCargoDTO.setCargo(eleTablaEmpresaCargo.getCargo());
			}
			if (save) {
				listaEmpresaCargo.add(eleTablaEmpresaCargoDTO);
			}
			save=false;
		}
		
		try {
			
			if (planchaVerificada.getEleCabPlanchaDTO().getCargosDirectivos().equalsIgnoreCase(",")) {
				planchaVerificada.getEleCabPlanchaDTO().setCargosDirectivos("-");
			}
			
			DelegadoPlanchas.getInstance().inscripcionPlancha(elplancha, planchaVerificada.getEleCabPlanchaDTO(), planchaVerificada.getListaPrincipales(), planchaVerificada.getListaSuplentes(),listaEmpresaCargo);
			
			FacesUtils.setSessionParameter("userPlancha", null);
			FacesUtils.setSessionParameter("planchaVerificada", null);
			
			btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblCerrar");
			visiblePlanchaCreada = true;
			
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
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
			return "";
		}
		return "";
	}
	
	/**
	 * Metodo que permite ver los detalles de cada uno de los miembros principales y suplentes
	 * @author Manuel Galvez, Ricardo Chiriboga
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

				}
			}
		}else{
			for (ElePrincipalesDTO principal : listaPrincipales) {
				if (principal.getNroPriIdentificacion().equalsIgnoreCase(idPrincipal)) {
					nroIdentificacionDetalle = principal.getNroPriIdentificacion();
					nombreAsociadoDetalle = principal.getNombreCompleto();
					profesionDetalle = principal.getProfesion();
				}
			}
		}

		return "";
	}
	/**
	 * Metodo que cierra el PopUp de detalles de miembros principales y suplentes
	 * @author Manuel Galvez, Ricardo Chiriboga
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
	 * Metodo que asigna la imagen de la habilidad o inhabilidad a los miembros principales y sulentes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verConfirmar(){
		visibleConfirmar = true;
		if(estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "displayEstado1"))){
			mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "confirmarPlanchaHabil") ;
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");
		}
		if(estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "displayEstado3"))){
			mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "confirmarPlanchaInhabil");
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfnoHabPlancha");
			mensaje2 = UtilAcceso.getParametroFuenteS("mensajes", "msgNotaPlancha");
		}
		return "";
	}
	/**
	 * Metodo que devuelve al usuario a crear plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return
	 */
	public String action_Volver()
	{
		return "goCrearPlancha";
	}
	/**
	 * Metodo que cierra el PopUp de confirmacion 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return Srting
	 */
	public String action_closeConfirmar(){
		visibleConfirmar = false;		
		return "";
	}
	/**
	 * Metodo que cierra el PopUp de finalizacion de transacci�n y redirecciona al incicio de sesion de asociado
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_redireccion()
	{
		visiblePlanchaCreada = false;
		return "goInicioAsociado"; 
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMensajeConfirmar() {
		return mensajeConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param mensajeConfirmar
	 */
	public void setMensajeConfirmar(String mensajeConfirmar) {
		this.mensajeConfirmar = mensajeConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getNroIdentificacionDetalle() {
		return nroIdentificacionDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param nroIdentificacionDetalle
	 */
	public void setNroIdentificacionDetalle(String nroIdentificacionDetalle) {
		this.nroIdentificacionDetalle = nroIdentificacionDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getNombreAsociadoDetalle() {
		return nombreAsociadoDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga 
	 * @param nombreAsociadoDetalle
	 */
	public void setNombreAsociadoDetalle(String nombreAsociadoDetalle) {
		this.nombreAsociadoDetalle = nombreAsociadoDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getProfesionDetalle() {
		return profesionDetalle;
	}
	/**
	 * Metodo de Acceso 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param profesionDetalle
	 */
	public void setProfesionDetalle(String profesionDetalle) {
		this.profesionDetalle = profesionDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getNroCabezaPlancha() {
		return nroCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 */
	public void setNroCabezaPlancha(String nroCabezaPlancha) {
		this.nroCabezaPlancha = nroCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getProfesion() {
		return profesion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param profesion
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	/**
	 * Metodo de Acceso 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getAntiguedad() {
		return antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param antiguedad
	 */
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga 
	 * @return String
	 */
	public String getNombreCabezaPlancha() {
		return nombreCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param nombreCabezaPlancha
	 */
	public void setNombreCabezaPlancha(String nombreCabezaPlancha) {
		this.nombreCabezaPlancha = nombreCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getImagenCabPlancha() {
		return imagenCabPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param imagenCabPlancha
	 */
	public void setImagenCabPlancha(String imagenCabPlancha) {
		this.imagenCabPlancha = imagenCabPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<ElePrincipalesDTO>
	 */
	public List<ElePrincipalesDTO> getListaPrincipales() {
		return listaPrincipales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listaPrincipales
	 */
	public void setListaPrincipales(List<ElePrincipalesDTO> listaPrincipales) {
		this.listaPrincipales = listaPrincipales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<EleSuplentesDTO>
	 */
	public List<EleSuplentesDTO> getListaSuplentes() {
		return listaSuplentes;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listaSuplentes
	 */
	public void setListaSuplentes(List<EleSuplentesDTO> listaSuplentes) {
		this.listaSuplentes = listaSuplentes;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleDetalle() {
		return visibleDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleDetalle
	 */
	public void setVisibleDetalle(boolean visibleDetalle) {
		this.visibleDetalle = visibleDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleConfirmar
	 */
	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getImagenConf() {
		return imagenConf;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param imagenConf
	 */
	public void setImagenConf(String imagenConf) {
		this.imagenConf = imagenConf;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return EleCabPlanchaDTO
	 */
	public EleCabPlanchaDTO getCabezaPlancha() {
		return cabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param cabezaPlancha
	 */
	public void setCabezaPlancha(EleCabPlanchaDTO cabezaPlancha) {
		this.cabezaPlancha = cabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return ElePlanchaDTO
	 */
	public ElePlanchaDTO getPlanchaVerificada() {
		return planchaVerificada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param planchaVerificada
	 */
	public void setPlanchaVerificada(ElePlanchaDTO planchaVerificada) {
		this.planchaVerificada = planchaVerificada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getEstadoPlancha() {
		return estadoPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param estadoPlancha
	 */
	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getCargosDirectivos() {	
		return cargosDirectivos;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param cargosDirectivos
	 */
	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<EleTablaEmpresaCargoDTO>
	 */
	public List<EleTablaEmpresaCargoDTO> getListEmpresaCargo() {
		return listEmpresaCargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listEmpresaCargo
	 */
	public void setListEmpresaCargo(List<EleTablaEmpresaCargoDTO> listEmpresaCargo) {
		this.listEmpresaCargo = listEmpresaCargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisiblePlanchaCreada() {
		return visiblePlanchaCreada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visiblePlanchaCreada
	 */
	public void setVisiblePlanchaCreada(boolean visiblePlanchaCreada) {
		this.visiblePlanchaCreada = visiblePlanchaCreada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getBtnCerrar() {
		return btnCerrar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param btnCerrar
	 */
	public void setBtnCerrar(String btnCerrar) {
		this.btnCerrar = btnCerrar;
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
	 * @return String
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
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

	public String getMensaje2() {
		return mensaje2;
	}

	public void setMensaje2(String mensaje2) {
		this.mensaje2 = mensaje2;
	}
}