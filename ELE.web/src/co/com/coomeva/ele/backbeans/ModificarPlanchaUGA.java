package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.InputFileData;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.context.effects.JavascriptContext;

public class ModificarPlanchaUGA extends BaseVista
{
	private String documento;
	private boolean renderPanelGrid;
	private boolean renderCollapsible;
	private boolean renderSuplentes;
	private String cargosDirectivos;
	private String estadoPlancha;
	private String profesion;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String nroCabezaPlancha;
	private String imagenCabPlancha;
	private boolean visiblePopUpRecibir;
	private boolean visiblePopUpOk;
	private boolean visibleConfirmar;
	private boolean visibleFoto;
	private boolean visibleDetalle;
	private String conceptoCambio;
	private String estudios1;
	private String estudios2;
	private String imagenConf;
	private String mensajeConfirmar;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<EleSuplentesDTO> listaSuplentes;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private List<EleExperienciaLaboral> listExperienciaLaboral;
	private String msgOk;
	private List<SelectItem> listTipoTransaccion;
	private HtmlSelectOneMenu tiposTransaccion;
	private InputFileData currentFile;
	private String zona;
	private String nroIdentificacionDetalle;
	private String nombreAsociadoDetalle;
	private String profesionDetalle;
	private String inhabilidadesDetalle;
	private boolean disable;

	public ModificarPlanchaUGA() 
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
			String estadoPlanchaOld = DelegadoPlanchas.getInstance().findPlanchasEstadoNative(documento);
			
			int iTamanoDoc = documento.length();
			validarMaximo(iTamanoDoc, UtilAcceso.getParametroFuenteI("parametros", "maxNoDoc"), false, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoDoc"));

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
			zona = elePlanchas.getEleZonas().getNomZona();
			renderPanelGrid = true;
			renderCollapsible = false;

			EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);

			listaPrincipales = new ArrayList<ElePrincipalesDTO>();
			listaSuplentes = new ArrayList<EleSuplentesDTO>();
			listExperienciaLaboral = new ArrayList<EleExperienciaLaboral>();

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
				renderSuplentes = false;
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
			listExperienciaLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(documento);

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
					eleTablaEmpresaCargoDTO.setCargo("");
					eleTablaEmpresaCargoDTO.setEmpresa("");
					listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
					iCont++;
				}
			}


			String[] estudios = eleCabPlancha.getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")); 
			estudios1 = estudios[0];
			estudios2 = estudios[1];

			if (!estudios1.equalsIgnoreCase("-")) {
				estudios1 = estudios[0];
			}else
			{
				estudios1 = "";
			}

			if (!estudios2.equalsIgnoreCase("-")) {
				estudios2 = estudios[1];
			}else
			{
				estudios2 = "";
			}

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
			antiguedad = ""+eleCabPlancha.getAntiguedad() + " años";
			cargosDirectivos = eleCabPlancha.getCargosDirectivos();
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado3");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado2");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado7");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado1");
			}
			imagenCabPlancha = "";
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
	 * Metodo que valida todas las modificaciones que hizo el usuario (revision ortigrafica)
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_RecibirPlancha()
	{
		int iTamanoDocEmpresa = 0;
		int iTamanoDocCargo = 0;
		int iTamanoOstrosEstudios = 0;

		int iCont = 0;

		try 
		{
			for (EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO : listEmpresaCargo) {
				iTamanoDocEmpresa = eleTablaEmpresaCargoDTO.getEmpresa().length();
				iTamanoDocCargo = eleTablaEmpresaCargoDTO.getCargo().length();

				iCont++;
				validarMaximo(iTamanoDocEmpresa, UtilAcceso.getParametroFuenteI("parametros", "maxExperienciaLab"), false, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoEmpresa") + iCont + " " + UtilAcceso.getParametroFuenteS("mensajes", "mayor"));
				validarMaximo(iTamanoDocCargo, UtilAcceso.getParametroFuenteI("parametros", "maxExperienciaLab"), false, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoCargo") + iCont + " "+ UtilAcceso.getParametroFuenteS("mensajes", "mayor"));
			}


			String estudios = estudios1+UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")+estudios2;

			iTamanoOstrosEstudios = estudios.length();
			validarMaximo(iTamanoOstrosEstudios, UtilAcceso.getParametroFuenteI("parametros", "maxOtrosEstudios"), false, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoOtrosEstudios"));

			validarNull(profesion, UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));

			visibleConfirmar = true;
			imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");
			mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "msgOrtografia");
		} 
		catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo que cierra el PopUp de ingreso de concepto de modificación
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConcepto()
	{
		visiblePopUpRecibir = false;
		return "";
	}

	/**
	 * Metodo que cierra los PopUps de confirmación y de finalización de proceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cerrar()
	{
		visibleConfirmar = false;
		visiblePopUpOk = false;
		documento = "";
		return "";
	}
	
	public String action_modificarProfesion() throws Exception
	{
		String id = nroIdentificacionDetalle;
		String sbProfesion = profesionDetalle;
		
		if (sbProfesion.trim().equals("") || sbProfesion == null) {
			getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}
		
		try 
		{
			if (disable) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "cabIgualPrincipal"));
			}else
			{
				EleSuplentes suplentes = DelegadoSuplente.getInstance().consultarSuplente(id);
				ElePrincipales principales =  DelegadoPrincipal.getInstance().consultarPrincipal(id);
				if (principales != null) {
					DelegadoPrincipal.getInstance().modificarProfesionPrincipal(sbProfesion, id);
					getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "modificoProfesion"));
				}
				
				if (suplentes != null) {
					DelegadoSuplente.getInstance().modificarProfesionSuplente(sbProfesion, id);
					getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "modificoProfesion"));
				}
				
			}
		} catch (Exception e) 
		{
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo que recibe todas las modificaciones que hizo el usuario y actualiza la plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_modificar()
	{
		try 
		{
			validarNull(conceptoCambio, UtilAcceso.getParametroFuenteS("mensajes", "noConcpTransaccion"));

			if (tiposTransaccion.getValue().toString().trim().equalsIgnoreCase("-1")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noTipoTransaccion"));
			}

			visiblePopUpOk = true;
			visibleConfirmar = false;
			renderCollapsible = true;
			renderPanelGrid = false;

			String tipoTransaccion = tiposTransaccion.getValue().toString();

			msgOk = UtilAcceso.getParametroFuenteS("mensajes", "msgOkModPlan");
			List<EleExperienciaLaboral> listaExperiencia = new ArrayList<EleExperienciaLaboral>();
			EleExperienciaLaboral eleExperienciaLaboral;

			for (EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO : listEmpresaCargo) {
				eleExperienciaLaboral = new EleExperienciaLaboral();
				eleExperienciaLaboral.setCargo(eleTablaEmpresaCargoDTO.getCargo());
				eleExperienciaLaboral.setEmpresa(eleTablaEmpresaCargoDTO.getEmpresa());
				listaExperiencia.add(eleExperienciaLaboral);
			}

			UserVo userVo = (UserVo) FacesUtils.getSessionParameter("user");
			if (estudios1.trim().equalsIgnoreCase("")) {
				estudios1="-";
			}

			if (estudios2.trim().equalsIgnoreCase("")) {
				estudios2="-";
			}
			String estudios = estudios1+UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")+estudios2;
			if (imagenCabPlancha == null && imagenCabPlancha.equalsIgnoreCase("")) {
				imagenCabPlancha = "";
			}
			DelegadoCabezaPlancha.getInstance().modificarOrtografiaUGA(nroCabezaPlancha, listaExperiencia, estudios, cargosDirectivos, tipoTransaccion, conceptoCambio, userVo.getName().toString(), profesion, imagenCabPlancha);
		} 
		catch (Exception e) 
		{
			msgOk = UtilAcceso.getParametroFuenteS("mensajes", "msgNoModPlan");
			getMensaje().mostrarMensaje(e.getMessage());
			e.printStackTrace();
		}

		return "";
	}
	/**
	 * Metodo que visualiza los detalles de cada uno de los miembros principales y suplentes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verDetalle(){
		visibleDetalle = true;
		String idSuplente = FacesUtils.getRequestParameter("suId");
		String idPrincipal = FacesUtils.getRequestParameter("priId");
		
		disable = false;
		if (idPrincipal.equals(nroCabezaPlancha)) {
			disable = true;
		}

		if (idSuplente != null&&!idSuplente.trim().equals("")) {
			for (EleSuplentesDTO suplentes : listaSuplentes) {
				if (suplentes.getNroSuIdentificacion().equalsIgnoreCase(idSuplente)) {
					inhabilidadesDetalle = suplentes.getStringInhabilidades();
				}
			}
			
			try 
			{
				EleSuplentes eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(idSuplente);
				String sbProfesion = DelegadoSuplente.getInstance().findPlanchasProfesionNative(idSuplente);
				
				nroIdentificacionDetalle = eleSuplentes.getNroSuIdentificacion();
				if (eleSuplentes.getPrimerNombre() == null) {
					eleSuplentes.setPrimerNombre("");
				}
				if (eleSuplentes.getSegundoNombre() == null) {
					eleSuplentes.setSegundoNombre("");
				}
				if (eleSuplentes.getPrimerApellido() == null) {
					eleSuplentes.setPrimerApellido("");
				}
				if (eleSuplentes.getSegundoApellido() == null) {
					eleSuplentes.setSegundoApellido("");
				}
				nombreAsociadoDetalle = eleSuplentes.getPrimerNombre()+" "+eleSuplentes.getSegundoNombre()+" "+eleSuplentes.getPrimerApellido()+" "+eleSuplentes.getSegundoApellido();
				
				profesionDetalle = sbProfesion;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else
		{
			try 
			{
				for (ElePrincipalesDTO principal : listaPrincipales) {
					if (principal.getNroPriIdentificacion().equalsIgnoreCase(idPrincipal)) {
						inhabilidadesDetalle = principal.getStringInhabilidades();
					}
				}
				
				ElePrincipales elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(idPrincipal);
				String sbProfesion = DelegadoPrincipal.getInstance().findPlanchasProfesionNative(idPrincipal);
				
				if (elePrincipales.getPrimerNombre() == null) {
					elePrincipales.setPrimerNombre("");
				}
				if (elePrincipales.getSegundoNombre() == null) {
					elePrincipales.setSegundoNombre("");
				}
				if (elePrincipales.getPrimerApellido() == null) {
					elePrincipales.setPrimerApellido("");
				}
				if (elePrincipales.getSegundoApellido() == null) {
					elePrincipales.setSegundoApellido("");
				}
				nroIdentificacionDetalle = elePrincipales.getNroPriIdentificacion();
				nombreAsociadoDetalle = elePrincipales.getPrimerNombre()+" "+elePrincipales.getSegundoNombre()+" "+elePrincipales.getPrimerApellido()+" "+elePrincipales.getSegundoApellido();
				profesionDetalle = sbProfesion;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		return "";
	}
	/**
	 * Metodo que cierra el PopUp de los detalles de los miembros principales y suplentes
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
	 * Metodo que se encarga de la cargar las imagenes de los asociados
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param event
	 */
	public void uploadFile(ActionEvent event) 
	{
		InputFile inputFile = (InputFile) event.getSource();

		FileInfo fileInfo = inputFile.getFileInfo();

		Long size = fileInfo.getSize();

		String filename = fileInfo.getFileName().trim();

		String filenameMayus = filename.toUpperCase();

		String nombreImagen = "";

		try 
		{
			if (!filenameMayus.contains(UtilAcceso.getParametroFuenteS("parametros", "enabledExtension"))) 
			{
				visibleFoto = false;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExtension"));
			}
			if (size<UtilAcceso.getParametroFuenteI("parametros", "minImage") || size>UtilAcceso.getParametroFuenteI("parametros", "maxImage")) 
			{
				visibleFoto = false;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noRangoImagen"));
			}

			nombreImagen = nombreCabezaPlancha.replace(" ", "");

			if (fileInfo.getStatus() == FileInfo.SAVED) 
			{
				String zona2 = zona.replace(" ", "");
				zona2 = zona2.replace ('á','a');
				zona2 = zona2.replace ('é','e');
				zona2 = zona2.replace ('í','i');
				zona2 = zona2.replace ('ó','o');
				zona2 = zona2.replace ('ú','u');
				zona2.trim();

				currentFile = new InputFileData(fileInfo);
				
				ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroCabezaPlancha);
				
				String noPlancha = "";
				if (elePlanchas !=null) {
					if (elePlanchas.getNroPlancha() != null) {
						noPlancha = "_"+elePlanchas.getNroPlancha().toString();
					}
				}

				File archivoAnterior = new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zona2+noPlancha+"_"+nombreImagen+".jpg");

				if (archivoAnterior.exists()) 
				{
					archivoAnterior.delete();
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zona2+noPlancha+"_"+nombreImagen+".jpg"));
				}else
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zona2+noPlancha+"_"+nombreImagen+".jpg"));
				
				currentFile.getFile().setWritable(true);
				currentFile.getFile().setReadable(true);

				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "directorioImagenesAso")+zona2+noPlancha+"_"+nombreImagen+".jpg";
				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);
				eleCabPlancha.setRutaImagen(imagenCabPlancha);
				FacesUtils.setSessionParameter("cabezaPlancha", eleCabPlancha);
				
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
				visibleFoto = false;
			
			}
		} 
		catch (Exception e) 
		{
			if (fileInfo!=null) {
				fileInfo.getFile().delete();
			}
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}finally{
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
		}
		
	}

	/**
	 * Metodo que visualiza el PopUp de carga de imagenes
	 * @author Manuel Galvez, Ricardo Chiriboga 
	 * @return String
	 */
	public String action_cambiarFoto()
	{
		visibleFoto = true;
		return "";
	}

	/**
	 * Metotdo que cerra el PopUp de carga de Imagen y refreca la pagina
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cancelar()
	{
		visibleFoto = false;
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
		return "";
	}

	/**
	 * Metodo que llena el comboBox con los tipos de transaccion disponibles
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListTipoTransaccion() {
		listTipoTransaccion = new ArrayList<SelectItem>();
		listTipoTransaccion.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));

		for (int i = 0; i < UtilAcceso.getParametroFuenteI("parametros", "numeroTipoRevision"); i++) {
			listTipoTransaccion.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "tipoRevision"+(i+1)),UtilAcceso.getParametroFuenteS("parametros", "nombreRevision"+(i+1))));
		}
		return listTipoTransaccion;
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
	 * @return boolean
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
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getTiposTransaccion() {
		return tiposTransaccion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param tiposTransaccion
	 */
	public void setTiposTransaccion(HtmlSelectOneMenu tiposTransaccion) {
		this.tiposTransaccion = tiposTransaccion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listTipoTransaccion
	 */
	public void setListTipoTransaccion(List<SelectItem> listTipoTransaccion) {
		this.listTipoTransaccion = listTipoTransaccion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleFoto() {
		return visibleFoto;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleFoto
	 */
	public void setVisibleFoto(boolean visibleFoto) {
		this.visibleFoto = visibleFoto;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return InputFileData
	 */
	public InputFileData getCurrentFile() {
		return currentFile;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param currentFile
	 */
	public void setCurrentFile(InputFileData currentFile) {
		this.currentFile = currentFile;
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

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
}