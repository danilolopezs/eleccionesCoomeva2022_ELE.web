package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.EleNumerarPlanchasDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.InputFileData;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.inputfile.FileInfo;

public class NumerarPlanchasCCEE extends BaseVista
{
	private HtmlSelectOneMenu zonasElectorales;
	private List<SelectItem> listZonasElectorales;
	private List<EleNumerarPlanchasDTO> listNumerarPlanchas;
	private boolean renderPanelGrid;
	private boolean renderCollapsible;
	private boolean visibleNumerarPlancha;
	private boolean visiblePopUpOk;
	private boolean visibleConfirmar;
	private String numeroSorteo;
	private String msgOk;
	private String msgTituloPopUp;
	List<EleNumerarPlanchasDTO> listaPlanchasCheck;
	private String imagenConf;
	private String mensajeConfirmar;

	public NumerarPlanchasCCEE() 
	{
		init();
	}

	/**
	 * Metodo que inicializa el comboBox con las zonas electores y zonas especiales disponibles 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	public void init()
	{
		renderCollapsible = true;
		imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");

	}

	/**
	 * Metodo que maneja las visualizaciones de los PopUp
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_goConfirmar()
	{
		visibleNumerarPlancha = false;
		visibleConfirmar = true;
		mensajeConfirmar = UtilAcceso.getParametroFuenteS("mensajes", "confirmarPlanchaModHabil") ;
		imagenConf = UtilAcceso.getParametroFuenteS("parametros", "imagenConfHabPlancha");
		return "";
	}
	/**
	 * Metodo que maneja las visualizaciones de los PopUp
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConfirmar()
	{
		visibleConfirmar = false;
		visibleNumerarPlancha = false;
		return "";
	}
	/**
	 * Metodo que realiza una busqueda de todas las planchas por zona
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_BuscarPlanchas()
	{
		try 
		{
			String zona = zonasElectorales.getValue().toString();

			if (zona.equalsIgnoreCase("-1")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noSelecZona"));
			}

			EleZonas elZonas = DelegadoZona.getInstance().consultarZona(zona);
			msgTituloPopUp = UtilAcceso.getParametroFuenteS("parametros", "ttlNumerarPlanchas")+" "+"''"+elZonas.getNomZona()+"''";

			List<ElePlanchas> listPlanchas = DelegadoPlanchas.getInstance().consultarPlanchas(zona, null, null, UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"),false);

			if (listPlanchas == null || listPlanchas.size() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPlanchasHabiles2"));
			}

			visibleNumerarPlancha = true;

			EleNumerarPlanchasDTO eleNumerarPlanchasDTO;
			listNumerarPlanchas = new ArrayList<EleNumerarPlanchasDTO>();


			for (ElePlanchas elePlanchas : listPlanchas) 
			{
				eleNumerarPlanchasDTO = new EleNumerarPlanchasDTO();
				eleNumerarPlanchasDTO.setNoCabezaPlancha(elePlanchas.getNroCabPlancha());

				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(elePlanchas.getNroCabPlancha());

				if (eleCabPlancha.getSegundoApellido() == null) {
					eleNumerarPlanchasDTO.setApellidos(eleCabPlancha.getPrimerApellido());
				}else
				{
					eleNumerarPlanchasDTO.setApellidos(eleCabPlancha.getPrimerApellido()+" "+eleCabPlancha.getSegundoApellido());
				}
				if (eleCabPlancha.getSegundoNombre() == null) {
					eleNumerarPlanchasDTO.setNombres(eleCabPlancha.getPrimerNombre());
				}else
				{
					eleNumerarPlanchasDTO.setNombres(eleCabPlancha.getPrimerNombre()+" "+eleCabPlancha.getSegundoNombre());
				}
				eleNumerarPlanchasDTO.setNoPrincipales(elePlanchas.getElePrincipaleses().size());
				eleNumerarPlanchasDTO.setNoSuplentes(elePlanchas.getEleSuplenteses().size());
				eleNumerarPlanchasDTO.setFechaInscripcion(elePlanchas.getFechaInscripcion());

				ElePlanchas elePlanchas2 = DelegadoPlanchas.getInstance().consultarPlancha(elePlanchas.getNroCabPlancha());

				if (elePlanchas2.getNroPlancha() == null) {
					String numeroPlancha = "";
					eleNumerarPlanchasDTO.setNumerarPlancha(numeroPlancha);
				}else
				{
					eleNumerarPlanchasDTO.setNumerarPlancha(elePlanchas2.getNroPlancha().toString().trim());
				}

				listNumerarPlanchas.add(eleNumerarPlanchasDTO);
			}
		} 
		catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo que permite la numeración de las planchas y renombra la imagen con el formato: "nombrecompleto_zona_numeroplancha.jpg"
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_guardarNumeracionPlanchas()
	{
		String zona = zonasElectorales.getValue().toString();

		try 
		{
			HashMap<String, String> listaPlanchasMap = new HashMap<String, String>();


			listaPlanchasCheck = new ArrayList<EleNumerarPlanchasDTO>();
			EleNumerarPlanchasDTO numerarPlanchasDTO;

			for (EleNumerarPlanchasDTO eleNumerarPlanchasDTO : listNumerarPlanchas) 
			{
				if (eleNumerarPlanchasDTO.getNumerarPlancha().equalsIgnoreCase("")) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumPlanchas"));
				}

				validarNumLong(eleNumerarPlanchasDTO.getNumerarPlancha());

				Long numeroPlancha = Long.parseLong(eleNumerarPlanchasDTO.getNumerarPlancha());

				if(numeroPlancha < 1)
				{
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumPlanchasMenor1"));
				}

				if (listaPlanchasMap.get(eleNumerarPlanchasDTO.getNumerarPlancha()) != null) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noRepNuPlanchas"));
				}
				if (eleNumerarPlanchasDTO.isActualiza()) {
					numerarPlanchasDTO = new EleNumerarPlanchasDTO();
					numerarPlanchasDTO.setNombres(eleNumerarPlanchasDTO.getNombres());
					numerarPlanchasDTO.setApellidos(eleNumerarPlanchasDTO.getApellidos());
					numerarPlanchasDTO.setNoCabezaPlancha(eleNumerarPlanchasDTO.getNoCabezaPlancha());
					numerarPlanchasDTO.setFechaInscripcion(eleNumerarPlanchasDTO.getFechaInscripcion());
					numerarPlanchasDTO.setNoPrincipales(eleNumerarPlanchasDTO.getNoPrincipales());
					numerarPlanchasDTO.setNoSuplentes(eleNumerarPlanchasDTO.getNoSuplentes());
					numerarPlanchasDTO.setNumerarPlancha(eleNumerarPlanchasDTO.getNumerarPlancha());
					listaPlanchasCheck.add(numerarPlanchasDTO);
				}
				listaPlanchasMap.put(eleNumerarPlanchasDTO.getNumerarPlancha(),eleNumerarPlanchasDTO.getNoCabezaPlancha() );
			}

			if(listaPlanchasCheck.size() == 0)
			{
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCheckPlacha"));
			}

			List<ElePlanchas> listaPlanchas = DelegadoPlanchas.getInstance().consultarPlanchas(zona, null, null, UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"),false);


			for (EleNumerarPlanchasDTO eleNumerarPlanchasDTO : listaPlanchasCheck) 
			{
				Long nroPlancha =  Long.parseLong(eleNumerarPlanchasDTO.getNumerarPlancha().trim());

				if (nroPlancha > listaPlanchas.size()) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPlachaMayor"));
				}

				UserVo userVo = (UserVo) FacesUtils.getSessionParameter("user");

				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(eleNumerarPlanchasDTO.getNoCabezaPlancha());

				//File
				ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(eleNumerarPlanchasDTO.getNoCabezaPlancha());

				EleCabPlanchaDTO eleCabPlanchaDTO = new EleCabPlanchaDTO(eleCabPlancha);

				String nombreAsociado = eleCabPlanchaDTO.getNombreCompleto();
				nombreAsociado = nombreAsociado.replace(" ", "");
				
				nombreAsociado.trim();

				String zonaAsociado = elePlanchas.getEleZonas().getNomZona();
				zonaAsociado = zonaAsociado.replace(" ", "");
				zonaAsociado = zonaAsociado.replace ('á','a');
				zonaAsociado = zonaAsociado.replace ('é','e');
				zonaAsociado = zonaAsociado.replace ('í','i');
				zonaAsociado = zonaAsociado.replace ('ó','o');
				zonaAsociado = zonaAsociado.replace ('ú','u');
				zonaAsociado.trim(); 

				String noPlancha = "";

				String nombreImagen;

				if (elePlanchas.getNroPlancha() != null) {
					noPlancha = elePlanchas.getNroPlancha().toString();
					nombreImagen = zonaAsociado+"_"+noPlancha+"_"+nombreAsociado+UtilAcceso.getParametroFuenteS("parametros", "extension");
				}else
				{
					nombreImagen = zonaAsociado+"_"+nombreAsociado+UtilAcceso.getParametroFuenteS("parametros", "extension");
				}

				//Trae la ruta del servidor
				//String path = PathRequest.getInstance().getPathContext("");
				String path =PathRequest.getInstance().getPathServerContextPath(FacesUtils.getServletContext(),UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso2")+nombreImagen);
				

//				//Saco la ruta hasta antes del WEB-INF
//				String[] sbCadena = path.split("WEB-INF");
//				//Se arma la ruta de las imagenes de asociados
//				String realPath = sbCadena[0] + UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso");
//
//				String rutaImagenAnterior = realPath+nombreImagen;
//				rutaImagenAnterior.trim();

				File fileAnterior = new File(path);

				//				if (!fileAnterior.exists()) {
				//					getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "recuerdeImagen"));
				//				}

				FileInfo fileInfo = new FileInfo();
				fileInfo.setFile(fileAnterior);

				InputFileData currentFile = new InputFileData(fileInfo);

				String zonas = elePlanchas.getEleZonas().getNomZona();
				zonas = zonas.replace(" ", "");
				zonas = zonas.replace ('á','a');
				zonas = zonas.replace ('é','e');
				zonas = zonas.replace ('í','i');
				zonas = zonas.replace ('ó','o');
				zonas = zonas.replace ('ú','u');
				zonas.trim();

				if (fileAnterior.exists())
				{
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zonas+"_"+eleNumerarPlanchasDTO.getNumerarPlancha()+"_"+nombreAsociado+".jpg"));
					fileAnterior.delete();

					String rutaImagen = UtilAcceso.getParametroFuenteS("parametros", "directorioImagenesAso")+zonas+"_"+eleNumerarPlanchasDTO.getNumerarPlancha()+"_"+nombreAsociado+".jpg";
					rutaImagen.trim();
					DelegadoCabezaPlancha.getInstance().modificarRutaImagen(eleCabPlancha.getNroIdentificacion(), rutaImagen);	
				}
				else
				{
					String rutaImagen = UtilAcceso.getParametroFuenteS("parametros", "directorioImagenesAso")+zonas+"_"+eleNumerarPlanchasDTO.getNumerarPlancha()+"_"+nombreAsociado+".jpg";
					rutaImagen.trim();
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zonas+"_"+eleNumerarPlanchasDTO.getNumerarPlancha()+"_"+nombreAsociado+".jpg"));
					DelegadoCabezaPlancha.getInstance().modificarRutaImagen(eleCabPlancha.getNroIdentificacion(), rutaImagen);
				}

				EleLogDTO eleLogDTO = new EleLogDTO();
				eleLogDTO.setNombreUsuario(userVo.getName().toString());
				eleLogDTO.setNombreTransaccion(UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccion2"));
				eleLogDTO.setTipoTransaccion(UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion2"));

				DelegadoPlanchas.getInstance().modificarPlanchaNumerada(eleNumerarPlanchasDTO.getNoCabezaPlancha(), nroPlancha, userVo.getName().toString(), eleLogDTO, UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
			}

			visiblePopUpOk = true;
			visibleNumerarPlancha = false;
			msgOk = UtilAcceso.getParametroFuenteS("mensajes", "NumeroPlanchas");
		} 
		catch (Exception e) {
			msgOk = UtilAcceso.getParametroFuenteS("mensajes", "noNumeroPlanchas");
			getMensaje().mostrarMensaje(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Metodo que cierra el PopUp de finalización del proceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cerrar()
	{
		visiblePopUpOk = false;
		return "";
	}
	/**
	 * Metodo que cierra el PopUp de numerar plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeCancelar()
	{
		visibleNumerarPlancha = false;
		return "";
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getZonasElectorales() {
		return zonasElectorales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param zonasElectorales
	 */
	public void setZonasElectorales(HtmlSelectOneMenu zonasElectorales) {
		this.zonasElectorales = zonasElectorales;
	}	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListZonasElectorales() {
		EleZonas eleZonaSub = (EleZonas)FacesUtils.getSessionParameter("zonaSubComision");
		EleZonas eleZonaSubEsp  = null;
		try {
			eleZonaSubEsp = DelegadoZona.getInstance().consultarZona(eleZonaSub.getZonEspecial());
			listZonasElectorales = new ArrayList<SelectItem>();
			listZonasElectorales.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));
			if (eleZonaSub!=null&&eleZonaSubEsp!=null) {
				listZonasElectorales.add(new SelectItem(eleZonaSub.getCodZona(),eleZonaSub.getNomZona()));
				listZonasElectorales.add(new SelectItem(eleZonaSubEsp.getCodZona(),eleZonaSubEsp.getNomZona()));
			}
		} catch (Exception e) {
			
		} 
		
		return listZonasElectorales;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listZonasElectorales
	 */
	public void setListZonasElectorales(List<SelectItem> listZonasElectorales) {
		this.listZonasElectorales = listZonasElectorales;
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
	 * @return List<EleNumerarPlanchasDTO>
	 */
	public List<EleNumerarPlanchasDTO> getListNumerarPlanchas() {
		return listNumerarPlanchas;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listNumerarPlanchas
	 */
	public void setListNumerarPlanchas(
			List<EleNumerarPlanchasDTO> listNumerarPlanchas) {
		this.listNumerarPlanchas = listNumerarPlanchas;
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
	 * @return String
	 */
	public String getNumeroSorteo() {
		return numeroSorteo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param numeroSorteo
	 */
	public void setNumeroSorteo(String numeroSorteo) {
		this.numeroSorteo = numeroSorteo;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleNumerarPlancha() {
		return visibleNumerarPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleNumerarPlancha
	 */
	public void setVisibleNumerarPlancha(boolean visibleNumerarPlancha) {
		this.visibleNumerarPlancha = visibleNumerarPlancha;
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
	public String getMsgTituloPopUp() {
		return msgTituloPopUp;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param msgTituloPopUp
	 */
	public void setMsgTituloPopUp(String msgTituloPopUp) {
		this.msgTituloPopUp = msgTituloPopUp;
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
}