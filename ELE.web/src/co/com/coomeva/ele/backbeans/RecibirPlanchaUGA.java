package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoParametros;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.ElePParametros;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.modelo.Parametro;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class RecibirPlanchaUGA extends BaseVista
{
	private String documento;
	private boolean renderPanelGrid;
	private boolean renderCollapsible;
	private boolean renderSuplentes;
	private String estadoPlancha;
	private String profesion;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String nroCabezaPlancha;
	private String imagenCabPlancha;
	private boolean visiblePopUpRecibir;
	private boolean visiblePopUpOk;
	private String estudios1;
	private String estudios2;
	private String conceptoCambio;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<EleSuplentesDTO> listaSuplentes;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private String msgOk;
	private boolean visibleDetalle;
	private String nroIdentificacionDetalle;
	private String nombreAsociadoDetalle;
	private String profesionDetalle;
	private String inhabilidadesDetalle;
	private String cargosDirectivos;
	private String zona;

	public RecibirPlanchaUGA() 
	{
		init();
	}
	
	/**
	 * Metodo que visualiza el panel de busqueda por numero de documento
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	private void init()
	{
		renderCollapsible = true;
	}
	
	/**
	 * Metodo que realiza la busqueda de la plancha por medio del numero de documento y renderiza todos los datos que contiene la plancha
	 *(No. documento, nombre cabeza plancha, profesion, antigüedad, experiencia laboral, cargos directivos, otros estudios,
	 * miembros principales y miembros suplentes).
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_BuscarUGA()
	{
		try 
		{
			Date dateToday = new Date();

			Parametro parametroIni = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaIniInscripcion");
			Parametro parametroFin = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaFinInscripcion");

			ElePParametros elePParametrosIni = parametroIni.getParametro();
			ElePParametros elePParametrosFin = parametroFin.getParametro();

			Date dateFechaIniInscrpcion = ManipulacionFechas.stringToDate(elePParametrosIni.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(elePParametrosFin.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			
			String estadoPlanchaOld = DelegadoPlanchas.getInstance().findPlanchasEstadoNative(documento);

			int iTamanoDoc = documento.length();
			validarMaximo(iTamanoDoc, UtilAcceso.getParametroFuenteI("parametros", "maxNoDoc"), true, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoDoc"));

			if (documento == null || documento.equalsIgnoreCase("")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noIngresoDoc"));
			}

			EleAsociadoDTO asociadoDTO = DelegadoClimae.getInstance().find(documento);

			if (asociadoDTO.getNombre() == null || asociadoDTO.getNombre().equalsIgnoreCase("")) 
			{
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));
			}

			EleCabPlanchaDTO eleCabPlanchaDTO = DelegadoCabezaPlancha.getInstance().consultarCabezaPlanchaDTO(documento);

			ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(documento);

			if (elePlanchas == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
			}
			
			if (elePParametrosIni != null && elePParametrosFin !=null) 
			{
				if (dateToday.compareTo(dateFechaIniInscrpcion)<0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}

				if (dateToday.compareTo(dateFechaFinInscrpcion)>0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}
			}

			if (!estadoPlanchaOld.trim().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1")) && !elePlanchas.getEstado().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "planchaNoEstInscrita"));
			}

			renderPanelGrid = true;
			renderCollapsible = false;

			EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);

			listaPrincipales = new ArrayList<ElePrincipalesDTO>();
			listaSuplentes = new ArrayList<EleSuplentesDTO>();

			listaPrincipales = DelegadoPrincipal.getInstance().consultarPrincipales(documento);
			for (ElePrincipalesDTO elePrincipal : listaPrincipales) {
				List<EleInhabilidades> list = DelegadoHabilidad.getInstance().buscarInhabilidadesById(elePrincipal.getNroPriIdentificacion());
				if (list.size() != 0) {
					elePrincipal.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
					elePrincipal.setInhabilidades(list);
				}else
				{
					elePrincipal.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));
				}	
				
			}
			listaSuplentes = DelegadoSuplente.getInstance().consultarSuplentes(documento);
			
			if (listaSuplentes.size() == 0) {
				renderSuplentes =  false;
			}else
				renderSuplentes = true;
			
			for (EleSuplentesDTO eleSuplentes : listaSuplentes) {
				List<EleInhabilidades> list = DelegadoHabilidad.getInstance().buscarInhabilidadesById(eleSuplentes.getNroSuIdentificacion());
				if (list.size() != 0) {
					eleSuplentes.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
					eleSuplentes.setInhabilidades(list);
				}else
				{
					eleSuplentes.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));
				}	
				
			}
			List<EleExperienciaLaboral> listExperienciaLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(documento);

			listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();
			EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;

			int iCont = 0;
			
			int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");
			
			for (EleExperienciaLaboral eleExperienciaLaboral : listExperienciaLaboral) {
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


			String[] estudios = eleCabPlancha.getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")); 
			estudios1 = estudios[0];
			estudios2 = estudios[1];

			ElePlanchaDTO elePlanchaDTO  = new ElePlanchaDTO();
			elePlanchaDTO.setListaPrincipales(listaPrincipales);
			elePlanchaDTO.setListaSuplentes(listaSuplentes);

			eleCabPlanchaDTO = new EleCabPlanchaDTO(eleCabPlancha);
			elePlanchaDTO.setEleCabPlanchaDTO(eleCabPlanchaDTO);
			elePlanchaDTO.setEstado(elePlanchas.getEstado());
			elePlanchaDTO.setFechaInscripcion(elePlanchas.getFechaInscripcion());
			elePlanchaDTO.setNroCabPlancha(elePlanchas.getNroCabPlancha());
			elePlanchaDTO.setEleZonas(elePlanchas.getEleZonas());
			elePlanchaDTO.setNroPlancha(elePlanchas.getNroPlancha());
			elePlanchaDTO.setDescEstado(elePlanchas.getDescEstado());
			elePlanchaDTO.setListExperienciaLaboral(listExperienciaLaboral);

			nroCabezaPlancha = elePlanchas.getNroCabPlancha();
			nombreCabezaPlancha = asociadoDTO.getNombre().toString();
			estadoPlancha = estadoPlanchaOld;
			profesion = eleCabPlancha.getProfesion();
			antiguedad = ""+eleCabPlancha.getAntiguedad()+" años";
			cargosDirectivos = eleCabPlancha.getCargosDirectivos();
			zona = elePlanchas.getEleZonas().getNomZona();
		
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado3");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado1");
			}

			if (eleCabPlancha.getRutaImagen() == null||eleCabPlancha.getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = eleCabPlancha.getRutaImagen();
		}
		catch (Exception e) 
		{
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Metodo que visualiza el PopUp de recibir plancha 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_RecibirPlancha()
	{
		visiblePopUpRecibir = true;
		return "";
	}
	
	/**
	 * Metodo que agrega un concepto y un tipo de transaccions al recibir la plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_guardarCambios()
	{
		try 
		{
			Long idNroCabezaPlancha = Long.valueOf(nroCabezaPlancha);
			UserVo userVo = (UserVo) FacesUtils.getSessionParameter("user");

			validarNull(conceptoCambio, UtilAcceso.getParametroFuenteS("mensajes", "noConcpTransaccion"));
			
			String estadoPlanchaFinal = "";
			ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(documento);
			if (elePlanchas.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlanchaFinal = UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2");
			}
			else
				estadoPlanchaFinal = UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7");
			
			EleLogDTO eleLogDTO = new EleLogDTO();
			eleLogDTO.setNombreUsuario(userVo.getName().toString());
			eleLogDTO.setTipoTransaccion(UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion4"));

			DelegadoPlanchas.getInstance().cambiarEstadoPlancha(idNroCabezaPlancha, conceptoCambio, estadoPlanchaFinal, userVo.getName().toString(), eleLogDTO,false);

			visiblePopUpRecibir = false;
			visiblePopUpOk = true;
			msgOk= UtilAcceso.getParametroFuenteS("mensajes", "msgOkReciPlan");

			renderCollapsible = true;
			renderPanelGrid = false;
			visiblePopUpRecibir = false;

			conceptoCambio = "";
			documento = "";
		}
		catch (Exception e) 
		{
			msgOk= UtilAcceso.getParametroFuenteS("mensajes", "msgNoReciPlan");
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Metodo que cierra el PopUp de recibir plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConcepto()
	{
		visiblePopUpRecibir = false;
		return "";
	}
	/**
	 * Metodo que cierra el PopUp de finalizacion de proceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cerrar()
	{
		visiblePopUpOk = false;
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
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getDocumento() {
		return documento;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
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
	 * @return boolean
	 */
	public boolean isRenderPanelGrid() {
		return renderPanelGrid;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderPanelGrid
	 */
	public void setRenderPanelGrid(boolean renderPanelGrid) {
		this.renderPanelGrid = renderPanelGrid;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderCollapsible() {
		return renderCollapsible;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderCollapsible
	 */
	public void setRenderCollapsible(boolean renderCollapsible) {
		this.renderCollapsible = renderCollapsible;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	public boolean isVisiblePopUpRecibir() {
		return visiblePopUpRecibir;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visiblePopUpRecibir
	 */
	public void setVisiblePopUpRecibir(boolean visiblePopUpRecibir) {
		this.visiblePopUpRecibir = visiblePopUpRecibir;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getConceptoCambio() {
		return conceptoCambio;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param conceptoCambio
	 */
	public void setConceptoCambio(String conceptoCambio) {
		this.conceptoCambio = conceptoCambio;
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
	public boolean isVisiblePopUpOk() {
		return visiblePopUpOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visiblePopUpOk
	 */
	public void setVisiblePopUpOk(boolean visiblePopUpOk) {
		this.visiblePopUpOk = visiblePopUpOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMsgOk() {	
		return msgOk;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
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
	public String getInhabilidadesDetalle() {
		return inhabilidadesDetalle;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param inhabilidadesDetalle
	 */
	public void setInhabilidadesDetalle(String inhabilidadesDetalle) {
		this.inhabilidadesDetalle = inhabilidadesDetalle;
	}

	public String getCargosDirectivos() {
		return cargosDirectivos;
	}

	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}