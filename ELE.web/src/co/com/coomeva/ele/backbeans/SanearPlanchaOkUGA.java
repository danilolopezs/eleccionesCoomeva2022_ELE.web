package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaDocumentosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.InputFileData;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.context.effects.JavascriptContext;

public class SanearPlanchaOkUGA extends BaseVista{

	private String nroCabezaPlancha;
	private String profesion;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String imagenCabPlancha;
	private String zona;
	private InputFileData currentFile;
	private List<EleTablaDocumentosDTO> listaDocumentos;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private boolean visibleFoto;
	private EleCabPlanchaDTO cabezaPlancha;
	private String cargosDirectivos = "";
	private String estudios1;
	private String estudios2;
	private String cargo;
	private String empresa;
	private String estadoPlancha;
	private String conceptoCambio;
	private ElePlanchaDTO elePlanchaDTO;

	public SanearPlanchaOkUGA() {
		init(); 
	}

	/**
	 * Metodo que recibe los tados del cabeza de plancha y su plancha e inicializa todos los componentes 
	 */
	private void init() 
	{
		try 
		{
			elePlanchaDTO = (ElePlanchaDTO) FacesUtils.getSessionParameter("userPlancha");
			cabezaPlancha =  elePlanchaDTO.getEleCabPlanchaDTO();
			nroCabezaPlancha = cabezaPlancha.getNroIdentificacion();
			profesion = cabezaPlancha.getProfesion();
			Long ant = cabezaPlancha.getAntiguedad();
			antiguedad = ant+" años";
			zona = elePlanchaDTO.getEleZonas().getNomZona();
			nombreCabezaPlancha = cabezaPlancha.getNombreCompleto();
			estadoPlancha = elePlanchaDTO.getEstado();
			conceptoCambio = elePlanchaDTO.getDescEstado();
			imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");

			int numeroFilas =  elePlanchaDTO.getEleZonas().getMaxPrincipales().intValue();

			ElePlanchaDTO planchaDTO = (ElePlanchaDTO) FacesUtils.getSessionParameter("planchaVerificada");
			
			if (planchaDTO != null) 
			{
				cargosDirectivos = planchaDTO.getEleCabPlanchaDTO().getCargosDirectivos().trim().toString();
				if (cargosDirectivos.equalsIgnoreCase("-")) {
					cargosDirectivos = "";
				}
				String separador[] = planchaDTO.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
				estudios1 = separador[0];
				if (estudios1.equalsIgnoreCase("-")) {
					estudios1 = "";
				}
				
				estudios2 = separador[1];
				if (estudios2.equalsIgnoreCase("-")) {
					estudios2 = "";
				}
				
				List<EleExperienciaLaboral> list = planchaDTO.getListExperienciaLaboral();
				if (list.size() == 0) {
					EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;
					listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();

					int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");

					for (int i = 0; i < noEmpresaCargo; i++) {
						eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
						eleTablaEmpresaCargoDTO.setEmpresa("");
						eleTablaEmpresaCargoDTO.setCargo("");
						listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
					}
				}else
				{
					EleTablaEmpresaCargoDTO empresaCargoDTO;
					listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();
					
					for (EleExperienciaLaboral eleExperienciaLaboral : list) {
						empresaCargoDTO = new EleTablaEmpresaCargoDTO();
						empresaCargoDTO.setEmpresa(eleExperienciaLaboral.getEmpresa());
						empresaCargoDTO.setCargo(eleExperienciaLaboral.getCargo());
						listEmpresaCargo.add(empresaCargoDTO);
					}
				}
				
				
				List<ElePrincipalesDTO> listPrincipales = new ArrayList<ElePrincipalesDTO>();
				listPrincipales = planchaDTO.getListaPrincipales();
				List<EleSuplentesDTO> listSuplentes = new ArrayList<EleSuplentesDTO>();
				listSuplentes = planchaDTO.getListaSuplentes();
				
				listaDocumentos = new ArrayList<EleTablaDocumentosDTO>();
//				EleTablaDocumentosDTO eleTablaCabezaPlancha = new EleTablaDocumentosDTO();
//				eleTablaCabezaPlancha.setCedulaPrincipal(nroCabezaPlancha);
//				eleTablaCabezaPlancha.setCabezaPlancha(true);
//				listaDocumentos.add(eleTablaCabezaPlancha);
				
				EleTablaDocumentosDTO eleTablaAyudantes;
				int iContador =0;
				
				for (int i = 0; i < numeroFilas; i++) {
					eleTablaAyudantes = new EleTablaDocumentosDTO();
					
					if (i <= listPrincipales.size()-1) {
						eleTablaAyudantes.setCedulaPrincipal(listPrincipales.get(i).getNroPriIdentificacion());
					}else
						eleTablaAyudantes.setCedulaPrincipal("");
					if (i <= listSuplentes.size()-1) {
						eleTablaAyudantes.setCedulaSuplente(listSuplentes.get(i).getNroSuIdentificacion());
					}else
						eleTablaAyudantes.setCedulaSuplente("");
					
					if (i == 0) {
						eleTablaAyudantes.setCabezaPlancha(true);
					}else
						eleTablaAyudantes.setCabezaPlancha(false);
					iContador++;
					listaDocumentos.add(eleTablaAyudantes);
				}
				
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
				FacesUtils.setSessionParameter("planchaVerificada", null);
			}

			if (planchaDTO == null) {

				if (cabezaPlancha.getCargosDirectivos().trim().equalsIgnoreCase("-")) {
					cargosDirectivos = "";
				}else
					cargosDirectivos = cabezaPlancha.getCargosDirectivos();
				
				cargosDirectivos = cargosDirectivos.replace(",", "");

				//Documentos Principales y Suplentes
				listaDocumentos = new ArrayList<EleTablaDocumentosDTO>();
				List<ElePrincipalesDTO> listPrincipales = elePlanchaDTO.getListaPrincipales();
				List<EleSuplentesDTO> listSuplentes = elePlanchaDTO.getListaSuplentes();

				EleTablaDocumentosDTO eleTablaAyudantes;
				int iContador =0;

				for (int i = 0; i < listPrincipales.size(); i++) 
				{
					eleTablaAyudantes = new EleTablaDocumentosDTO();

					eleTablaAyudantes.setCedulaPrincipal(listPrincipales.get(i).getNroPriIdentificacion());

					if (i <= listSuplentes.size()-1) {
						eleTablaAyudantes.setCedulaSuplente(listSuplentes.get(i).getNroSuIdentificacion());
					}


					if (listPrincipales.get(i).getNroPriIdentificacion().equals(nroCabezaPlancha))
					{
						eleTablaAyudantes.setCabezaPlancha(true);
					}
					else
					{
						eleTablaAyudantes.setCabezaPlancha(false);
					}
					listaDocumentos.add(eleTablaAyudantes);
				}

				int iTamZona = elePlanchaDTO.getEleZonas().getMaxPrincipales().intValue()-listPrincipales.size();


				for (int i = 0; i < iTamZona; i++) {
					eleTablaAyudantes = new EleTablaDocumentosDTO();
					eleTablaAyudantes.setCedulaPrincipal("");
					eleTablaAyudantes.setCedulaSuplente("");
					iContador++;
					eleTablaAyudantes.setCabezaPlancha(false);
					listaDocumentos.add(eleTablaAyudantes);
				}

				//Experiencia Laboral
				List<EleExperienciaLaboral> listEleExperienciaLaboral = elePlanchaDTO.getListExperienciaLaboral();
				listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();

				EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;

				int iCont = 0;
				int iCont2 = 0;

				int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");

				for (EleExperienciaLaboral eleExperienciaLaboral : listEleExperienciaLaboral) {
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


				String[] estudios = elePlanchaDTO.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")); 
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
			}


			visibleFoto = false;
			if (cabezaPlancha.getRutaImagen() == null||cabezaPlancha.getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = cabezaPlancha.getRutaImagen();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}
	/**
	 * Metodo que valida las inhabilidades de los miembros principales y suplentes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_Verificar() 
	{
		ElePlanchaDTO elePlanchaDTOs = new ElePlanchaDTO();

		List<ElePrincipalesDTO> listaPrincipales = new ArrayList<ElePrincipalesDTO>();
		List<EleSuplentesDTO> listaSuplentes = new ArrayList<EleSuplentesDTO>();
		
		ElePrincipalesDTO elePrincipalesDTO;
		EleSuplentesDTO eleSuplentesDTO;

//		for (EleTablaDocumentosDTO eleTablaDocumentos : listaDocumentos) 
//		{
//			if (eleTablaDocumentos.getCedulaPrincipal()!= null && !eleTablaDocumentos.getCedulaPrincipal().trim().equals("")) 
//			{
//				elePrincipalesDTO = new ElePrincipalesDTO();
//				elePrincipalesDTO.setNroPriIdentificacion(eleTablaDocumentos.getCedulaPrincipal());
//				listaPrincipales.add(elePrincipalesDTO);
//			}
//			if (eleTablaDocumentos.getCedulaSuplente()!= null && !eleTablaDocumentos.getCedulaSuplente().trim().equals("")) 
//			{
//				eleSuplentesDTO = new EleSuplentesDTO();
//				eleSuplentesDTO.setNroSuIdentificacion(eleTablaDocumentos.getCedulaSuplente());
//				elePrincipalesDTO = new ElePrincipalesDTO();
//				elePrincipalesDTO.setNroPriIdentificacion(eleTablaDocumentos.getCedulaPrincipal());
//				eleSuplentesDTO.setElePrincipales(elePrincipalesDTO);
//				listaSuplentes.add(eleSuplentesDTO);
//			}
//		}
		
		for (EleTablaDocumentosDTO eleTablaDocumentos : listaDocumentos) {
			if (eleTablaDocumentos.getCedulaPrincipal()!= null&&!eleTablaDocumentos.getCedulaPrincipal().trim().equals("")) {
				if (!eleTablaDocumentos.getCedulaPrincipal().equalsIgnoreCase("")) {
					elePrincipalesDTO = new ElePrincipalesDTO();
					elePrincipalesDTO.setNroPriIdentificacion(eleTablaDocumentos.getCedulaPrincipal());
					listaPrincipales.add(elePrincipalesDTO);
				}
			}
			if (eleTablaDocumentos.getCedulaSuplente()!= null&&!eleTablaDocumentos.getCedulaSuplente().trim().equals("")) {
				if (!eleTablaDocumentos.getCedulaSuplente().equalsIgnoreCase("")) {
					eleSuplentesDTO = new EleSuplentesDTO();
					eleSuplentesDTO.setNroSuIdentificacion(eleTablaDocumentos.getCedulaSuplente());
					listaSuplentes.add(eleSuplentesDTO);
				}
				
			}
		}
		
		try 
		{
			if (listaSuplentes.size() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noSuplente"));
			}
			
			if (listaSuplentes.size()>listaPrincipales.size()) 
			{
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "suplentesMayores"));
			}
			
			EleTablaDocumentosDTO eleDocumentosDTO;
			List<EleSuplentesDTO> listaSuplentes2 = new ArrayList<EleSuplentesDTO>();
			String suplente;
			
			for (int i = 0; i < listaSuplentes.size(); i++) {
				String principal = listaPrincipales.get(i).getNroPriIdentificacion().toString();
				suplente = listaSuplentes.get(i).getNroSuIdentificacion().toString();


				eleDocumentosDTO = new EleTablaDocumentosDTO();
				eleSuplentesDTO = new EleSuplentesDTO();
				eleSuplentesDTO.setNroSuIdentificacion(suplente);
				elePrincipalesDTO = new ElePrincipalesDTO();
				elePrincipalesDTO.setNroPriIdentificacion(principal);
				eleSuplentesDTO.setElePrincipales(elePrincipalesDTO);

				listaSuplentes2.add(eleSuplentesDTO);
			}

			List<EleExperienciaLaboral> listExperienciaLaboral = new ArrayList<EleExperienciaLaboral>();
			EleExperienciaLaboral eleExperienciaLaboral;

			boolean save = false;

			for (EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO : listEmpresaCargo) {
				eleExperienciaLaboral = new EleExperienciaLaboral();
				save = false;
				if (!eleTablaEmpresaCargoDTO.getCargo().equalsIgnoreCase("")) {
					eleExperienciaLaboral.setCargo(eleTablaEmpresaCargoDTO.getCargo());
				}
				if (!eleTablaEmpresaCargoDTO.getEmpresa().equalsIgnoreCase("")) {
					eleExperienciaLaboral.setEmpresa(eleTablaEmpresaCargoDTO.getEmpresa());
					save = true;
				}
				if (save) {
					listExperienciaLaboral.add(eleExperienciaLaboral);
				}
			}
			if (cargosDirectivos.trim().equalsIgnoreCase("")) {
				cargosDirectivos = "-";
			}

			cabezaPlancha.setCargosDirectivos(cargosDirectivos);
			if (estudios1.trim().equalsIgnoreCase("")) {
				estudios1="-";
			}

			if (estudios2.trim().equalsIgnoreCase("")) {
				estudios2="-";
			}
			String estudios = estudios1+UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")+estudios2;
			cabezaPlancha.setOtrosEstudios(estudios);
			cabezaPlancha.setProfesion(profesion);

			elePlanchaDTOs.setListaPrincipales(listaPrincipales);
			elePlanchaDTOs.setListaSuplentes(listaSuplentes2);
			elePlanchaDTOs.setEleCabPlanchaDTO(cabezaPlancha);
			elePlanchaDTOs.setListExperienciaLaboral(listExperienciaLaboral);
			elePlanchaDTOs.setEleZonas(elePlanchaDTO.getEleZonas());

			ElePrincipales elePrincipales;
			EleSuplentes eleSuplentes;
			EleCabPlancha eleCabPlancha;

			for (ElePrincipalesDTO principalesDTO : listaPrincipales) 
			{
				elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(principalesDTO.getNroPriIdentificacion());
				eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(principalesDTO.getNroPriIdentificacion());
				eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(principalesDTO.getNroPriIdentificacion());

				if (elePrincipales != null && !elePrincipales.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdPrincipal")+" "+"''"+principalesDTO.getNroPriIdentificacion()+"''");
				}
				if (eleCabPlancha != null && !eleCabPlancha.getNroIdentificacion().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdCabPlancha")+" "+"''"+principalesDTO.getNroPriIdentificacion()+"''");
				}
				if (eleSuplentes != null && !eleSuplentes.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdSuplente")+" "+"''"+principalesDTO.getNroPriIdentificacion()+"''");
				}
			}

			for (EleSuplentesDTO suplentesDTO : listaSuplentes) 
			{
				eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(suplentesDTO.getNroSuIdentificacion());
				eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(suplentesDTO.getNroSuIdentificacion());
				elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(suplentesDTO.getNroSuIdentificacion());

				if (eleSuplentes != null && !eleSuplentes.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdSuplente")+" "+"''"+suplentesDTO.getNroSuIdentificacion()+"''");
				}
				if (eleCabPlancha != null && !eleCabPlancha.getNroIdentificacion().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdCabPlancha")+" "+"''"+suplentesDTO.getNroSuIdentificacion()+"''");
				}
				if (elePrincipales != null && !elePrincipales.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(cabezaPlancha.getNroIdentificacion())) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgExisteIdPrincipal")+" "+"''"+suplentesDTO.getNroSuIdentificacion()+"''");
				}
			}

			elePlanchaDTOs = DelegadoHabilidad.getInstance().validatePlancha(elePlanchaDTOs);

			FacesUtils.setSessionParameter("planchaVerificada", elePlanchaDTOs);

		} 
		catch (Exception e) 
		{
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
			return "";
		}

		return "goVerPlanchaSaneada";
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
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExtension"));
			}
			if (size<UtilAcceso.getParametroFuenteI("parametros", "minImage") || size>UtilAcceso.getParametroFuenteI("parametros", "maxImage")) 
			{
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
				cabezaPlancha.setRutaImagen(imagenCabPlancha);
				FacesUtils.setSessionParameter("cabezaPlancha", cabezaPlancha);
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
				visibleFoto = false;
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
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
		}
	}

	/**
	 * Metodo que visualiza el PopUp de carga de imagen
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cambiarFoto(){
		visibleFoto = true;
		return "";
	}
	/**
	 * Metodo que cierra el PopUp de carga de imagen y refresca la pagina
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cancelar(){
		visibleFoto = false;
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "refreshPage();");
		return "";
	}
	
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_Volver()
	{
		return "goSanearPlanchasUGA";
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<EleTablaDocumentosDTO>
	 */
	public List<EleTablaDocumentosDTO> getListaDocumentos() {
		return listaDocumentos;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listaDocumentos
	 */
	public void setListaDocumentos(List<EleTablaDocumentosDTO> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
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
	 * @return String
	 */
	public String getCargo() {
		return cargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
}