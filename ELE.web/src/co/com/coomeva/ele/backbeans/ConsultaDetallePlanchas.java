package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleNumerarPlanchasDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class ConsultaDetallePlanchas extends BaseVista{

	
	private final Log log  = LogFactory.getLog(ConsultaDetallePlanchas.class);
	private HtmlSelectOneMenu zonaSelect;
	private List<SelectItem> zonas;
	private boolean renderTablePlanchas;
	private boolean renderTable;
	private boolean visibleConfirmar;
	private boolean visiblePlancha;
	private List<EleNumerarPlanchasDTO> listPlanchasDTO;
	private String profesion;
	private String estadoPlancha;
	private String nroCabezaPlancha;
	private String imagenCabPlancha;
	private String nombreCabezaPlancha;
	private String zona;
	private String cargosDirectivos;
	private String estudios1;
	private String estudios2;
	private String antiguedad;
	private List<EleExperienciaLaboral> listExpLaboral;
	private List<ElePrincipalesDTO> listPrincipalesDTO;
	private List<EleSuplentesDTO> listSuplentesDTO;
	private String imagenEstado;
	private String cantidadPlanchas;
	private boolean renderButtonNext;
	private boolean renderButtonBack;
	private Long noPlanchas;
	private int noListaPlancha;
	private int iCont = 1;
	private String imagenBackButton;
	private String imagenNextButton;


	public ConsultaDetallePlanchas() {
		renderTable = true;
		renderTablePlanchas = false;
		visiblePlancha = false;
		renderButtonBack = false;
		renderButtonNext = false;
	}

	/**
	 * Metodo que limpia los valores de los componenetes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_limpiar(){
		zonaSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		renderTablePlanchas = false;
		return "";
	}

	/**
	 * Metodo que genera el PDF de planchas
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_generarPDF(){
		try {

			String zona;

			if (zonaSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noSelecZona"));
			}else
				zona = zonaSelect.getValue().toString();


			List<ElePlanchas> listPlanchas = DelegadoPlanchas.getInstance().consultarPlanchas(zona, null, null, UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"),true);

			if (listPlanchas == null || listPlanchas.size() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPlanchasHabiles"));
			}

			renderTablePlanchas = true;

			listPlanchasDTO = new ArrayList<EleNumerarPlanchasDTO>();

			EleNumerarPlanchasDTO eleNumerarPlanchasDTO;
			iCont = 1;
			for (ElePlanchas elePlanchas : listPlanchas) {
				eleNumerarPlanchasDTO = new EleNumerarPlanchasDTO();
				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(elePlanchas.getNroCabPlancha().toString());
				
				String nameCabPlancha = eleCabPlancha.getPrimerNombre() +" "+ eleCabPlancha.getSegundoNombre() +" "+ eleCabPlancha.getPrimerApellido() +" "+ eleCabPlancha.getSegundoApellido();
				
				eleNumerarPlanchasDTO.setNombres(nameCabPlancha);
				eleNumerarPlanchasDTO.setNoCabezaPlancha(elePlanchas.getNroCabPlancha());
				eleNumerarPlanchasDTO.setNoPrincipales(elePlanchas.getElePrincipaleses().size());
				eleNumerarPlanchasDTO.setNoSuplentes(elePlanchas.getEleSuplenteses().size());
				eleNumerarPlanchasDTO.setNumerarPlancha(""+elePlanchas.getNroPlancha());
				eleNumerarPlanchasDTO.setIndex(iCont);
				listPlanchasDTO.add(eleNumerarPlanchasDTO);
				iCont++;
			}
		} catch (Exception e) {
			log.error("Error al generar el PDF", e);
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo que visualiza los detalles de cada uno de los miembros principales y suplentes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verDetalle()
	{
		String nroPlancha = FacesUtils.getRequestParameter("idPlancha");
		String index = FacesUtils.getRequestParameter("idIndex");

		if (!nroPlancha.trim().equals("") && nroPlancha != null) {
			
			visiblePlancha = true;
			try {

				EleCabPlanchaDTO eleCabPlanchaDTO = DelegadoCabezaPlancha.getInstance().consultarCabezaPlanchaDTO(nroPlancha);

				noPlanchas = new Long(index);
				noListaPlancha = listPlanchasDTO.size();
				cantidadPlanchas = noPlanchas+"/"+ noListaPlancha;
				
				imagenBackButton = "";
				imagenNextButton = "";
				
				if (noListaPlancha == 1) {
					renderButtonNext = true;
					imagenNextButton = "./imagenes/nextGray.png";
					renderButtonBack = true;
					imagenBackButton = "./imagenes/backGray.png";
				}else{
					if (noPlanchas >= noListaPlancha) {
						renderButtonNext = true;
						imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNextGray");
						renderButtonBack = false;
						imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBack");
					}else
						if (noPlanchas <= 1) {
							renderButtonBack = true;
							imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBackGray");
							renderButtonNext = false;
							imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNext");
						}else{
							renderButtonNext = false;
							imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNext");
							renderButtonBack = false;
							imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBack");
						}

				}

				profesion = eleCabPlanchaDTO.getProfesion();
				estadoPlancha = eleCabPlanchaDTO.getElePlanchas().getEstado();
				zona = eleCabPlanchaDTO.getElePlanchas().getEleZonas().getNomZona();
				nombreCabezaPlancha = eleCabPlanchaDTO.getPrimerNombre() + " " + eleCabPlanchaDTO.getSegundoNombre() + " " +
				eleCabPlanchaDTO.getPrimerApellido() + " " + eleCabPlanchaDTO.getSegundoApellido();
				nroCabezaPlancha = eleCabPlanchaDTO.getNroIdentificacion();



				if (eleCabPlanchaDTO.getRutaImagen() !=null) {
					
					String rutaImagen = eleCabPlanchaDTO.getRutaImagen();
					rutaImagen = rutaImagen.replace("..", ".");

					String nombreImagen = rutaImagen;

					String[] arrayNombreImagen = nombreImagen.split("/");

					nombreImagen = arrayNombreImagen[3];
					nombreImagen = nombreImagen.replace ('á','a');
					nombreImagen = nombreImagen.replace ('é','e');
					nombreImagen = nombreImagen.replace ('í','i');
					nombreImagen = nombreImagen.replace ('ó','o');
					nombreImagen = nombreImagen.replace ('ú','u');
					nombreImagen.trim();

					//Trae la ruta del servidor
//					String path = PathRequest.getInstance().getPathContext("");
					String path =PathRequest.getInstance().getPathServerContextPath(FacesUtils.getServletContext(),UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso2")+nombreImagen);
					
					//Saco la ruta hasta antes del WEB-INF
//					String[] sbCadena = path.split("WEB-INF");
//					String sbCadenaPath = sbCadena[0];
//					sbCadenaPath = sbCadenaPath.replace("/", "//");
					//Se arma la ruta de las imagenes de asociados
//					String realPath = sbCadenaPath + UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso");
//					realPath.trim();
//					
//					String rutaImagenAnterior = realPath+nombreImagen;
//					rutaImagenAnterior.trim();
					
					File fileAnterior = new File(path);
					
					if (fileAnterior.exists())
					{
						rutaImagen = rutaImagen.replace("/", "//");
						imagenCabPlancha = rutaImagen.trim();
					}else
						imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");
					
				}else
					imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");

				String OtrosEstudios = eleCabPlanchaDTO.getOtrosEstudios();

				String[] arrayOtrosEstudios = OtrosEstudios.split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
				estudios1 = arrayOtrosEstudios[0];
				estudios2 = arrayOtrosEstudios[1];

				antiguedad = ""+eleCabPlanchaDTO.getAntiguedad()+ " años";

				cargosDirectivos = eleCabPlanchaDTO.getCargosDirectivos();

				listExpLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(nroCabezaPlancha);

				listPrincipalesDTO = DelegadoPrincipal.getInstance().consultarPrincipales(nroCabezaPlancha);

				for (ElePrincipalesDTO elePrincipalesDTO : listPrincipalesDTO) {

					List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(elePrincipalesDTO.getNroPriIdentificacion());

					if (listaInhabilidades.size() != 0) {
						imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
					}else
					{
						imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
					}
					elePrincipalesDTO.setImagenEstado(imagenEstado);
				}

				listSuplentesDTO = DelegadoSuplente.getInstance().consultarSuplentes(nroCabezaPlancha);

				for (EleSuplentesDTO eleSuplentesDTO : listSuplentesDTO) {

					List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(eleSuplentesDTO.getNroSuIdentificacion());

					if (listaInhabilidades.size() != 0) {
						imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
					}else
					{
						imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
					}
					eleSuplentesDTO.setImagenEstado(imagenEstado);
				}

			} catch (Exception e) {
				log.error("Error al ver el Detalle de los miembros", e);
			}
		}

		return "";
	}

	public String action_cerrar()
	{
		visiblePlancha = false;
		renderButtonBack = false;
		renderButtonNext = false;
		return "";
	}

	public String action_next()
	{
		Long newNroplancha = noPlanchas+1;
		EleNumerarPlanchasDTO numerarPlanchasDTO = listPlanchasDTO.get(newNroplancha.intValue()-1);

		try {

			EleCabPlanchaDTO eleCabPlanchaDTO = DelegadoCabezaPlancha.getInstance().consultarCabezaPlanchaDTO(numerarPlanchasDTO.getNoCabezaPlancha());

			noPlanchas = new Long(numerarPlanchasDTO.getIndex());
			noListaPlancha = listPlanchasDTO.size();
			cantidadPlanchas = noPlanchas+"/"+ noListaPlancha;
			
			if (noListaPlancha == 1) {
				renderButtonNext = true;
				imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNextGray");
				renderButtonBack = true;
				imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBackGray");
			}else{
				if (noPlanchas >= noListaPlancha) {
					renderButtonNext = true;
					imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNextGray");
					renderButtonBack = false;
					imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBack");
				}else{
					renderButtonNext = false;
					imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNext");
					renderButtonBack = false;
					imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBack");
				}

			}

			profesion = eleCabPlanchaDTO.getProfesion();
			estadoPlancha = eleCabPlanchaDTO.getElePlanchas().getEstado();
			zona = eleCabPlanchaDTO.getElePlanchas().getEleZonas().getNomZona();
			nombreCabezaPlancha = eleCabPlanchaDTO.getPrimerNombre() + " " + eleCabPlanchaDTO.getSegundoNombre() + " " +
			eleCabPlanchaDTO.getPrimerApellido() + " " + eleCabPlanchaDTO.getSegundoApellido();
			nroCabezaPlancha = eleCabPlanchaDTO.getNroIdentificacion();

			if (eleCabPlanchaDTO.getRutaImagen() !=null) {
				String rutaImagen = eleCabPlanchaDTO.getRutaImagen();
				rutaImagen = rutaImagen.replace("..", ".");

				String nombreImagen = rutaImagen;

				String[] arrayNombreImagen = nombreImagen.split("/");

				nombreImagen = arrayNombreImagen[3];
				nombreImagen = nombreImagen.replace ('á','a');
				nombreImagen = nombreImagen.replace ('é','e');
				nombreImagen = nombreImagen.replace ('í','i');
				nombreImagen = nombreImagen.replace ('ó','o');
				nombreImagen = nombreImagen.replace ('ú','u');
				nombreImagen.trim();

				//Trae la ruta del servidor
//				String path = PathRequest.getInstance().getPathContext("");
				String path =PathRequest.getInstance().getPathServerContextPath(FacesUtils.getServletContext(),UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso2")+nombreImagen);
				
				//Saco la ruta hasta antes del WEB-INF
//				String[] sbCadena = path.split("WEB-INF");
//				String sbCadenaPath = sbCadena[0];
//				sbCadenaPath = sbCadenaPath.replace("/", "//");
				//Se arma la ruta de las imagenes de asociados
//				String realPath = sbCadenaPath + UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso");
//				realPath.trim();
//				
//				String rutaImagenAnterior = realPath+nombreImagen;
//				rutaImagenAnterior.trim();
				
				File fileAnterior = new File(path);
				
				if (fileAnterior.exists())
				{
					rutaImagen = rutaImagen.replace("/", "//");
					imagenCabPlancha = rutaImagen.trim();
				}else
					imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");
			}else
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");

			String OtrosEstudios = eleCabPlanchaDTO.getOtrosEstudios();

			String[] arrayOtrosEstudios = OtrosEstudios.split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
			estudios1 = arrayOtrosEstudios[0];
			estudios2 = arrayOtrosEstudios[1];

			antiguedad = ""+eleCabPlanchaDTO.getAntiguedad()+" años";

			cargosDirectivos = eleCabPlanchaDTO.getCargosDirectivos();

			listExpLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(nroCabezaPlancha);

			listPrincipalesDTO = DelegadoPrincipal.getInstance().consultarPrincipales(nroCabezaPlancha);

			for (ElePrincipalesDTO elePrincipalesDTO : listPrincipalesDTO) {

				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(elePrincipalesDTO.getNroPriIdentificacion());

				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
				}
				elePrincipalesDTO.setImagenEstado(imagenEstado);
			}

			listSuplentesDTO = DelegadoSuplente.getInstance().consultarSuplentes(nroCabezaPlancha);

			for (EleSuplentesDTO eleSuplentesDTO : listSuplentesDTO) {

				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(eleSuplentesDTO.getNroSuIdentificacion());

				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
				}
				eleSuplentesDTO.setImagenEstado(imagenEstado);
			}

		} catch (Exception e) {
			log.error("Error", e);
		}
		return "";
	}

	public String action_back()
	{
		Long newNroplancha = noPlanchas-1;
		EleNumerarPlanchasDTO numerarPlanchasDTO = listPlanchasDTO.get(newNroplancha.intValue()-1);

		try {

			EleCabPlanchaDTO eleCabPlanchaDTO = DelegadoCabezaPlancha.getInstance().consultarCabezaPlanchaDTO(numerarPlanchasDTO.getNoCabezaPlancha());

			noPlanchas = new Long(numerarPlanchasDTO.getIndex());
			noListaPlancha = listPlanchasDTO.size();
			cantidadPlanchas = noPlanchas+"/"+ noListaPlancha;

			if (noListaPlancha == 1) {
				renderButtonNext = true;
				imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNextGray");
				renderButtonBack = true;
				imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBackGray");
			}else{
				if (noPlanchas <= 1) {
					renderButtonBack = true;
					imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBackGray");
					renderButtonNext = false;
					imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNext");
				}else{
					renderButtonNext = false;
					imagenNextButton = UtilAcceso.getParametroFuenteS("parametros", "imageNext");
					renderButtonBack = false;
					imagenBackButton = UtilAcceso.getParametroFuenteS("parametros", "imageBack");
				}

			}

			profesion = eleCabPlanchaDTO.getProfesion();
			estadoPlancha = eleCabPlanchaDTO.getElePlanchas().getEstado();
			zona = eleCabPlanchaDTO.getElePlanchas().getEleZonas().getNomZona();
			nombreCabezaPlancha = eleCabPlanchaDTO.getPrimerNombre() + " " + eleCabPlanchaDTO.getSegundoNombre() + " " +
			eleCabPlanchaDTO.getPrimerApellido() + " " + eleCabPlanchaDTO.getSegundoApellido();
			nroCabezaPlancha = eleCabPlanchaDTO.getNroIdentificacion();

			if (eleCabPlanchaDTO.getRutaImagen() !=null) {
				String rutaImagen = eleCabPlanchaDTO.getRutaImagen();
				rutaImagen = rutaImagen.replace("..", ".");

				String nombreImagen = rutaImagen;

				String[] arrayNombreImagen = nombreImagen.split("/");

				nombreImagen = arrayNombreImagen[3];
				nombreImagen = nombreImagen.replace ('á','a');
				nombreImagen = nombreImagen.replace ('é','e');
				nombreImagen = nombreImagen.replace ('í','i');
				nombreImagen = nombreImagen.replace ('ó','o');
				nombreImagen = nombreImagen.replace ('ú','u');
				nombreImagen.trim();

				//Trae la ruta del servidor
//				String path = PathRequest.getInstance().getPathContext("");
				String path =PathRequest.getInstance().getPathServerContextPath(FacesUtils.getServletContext(),UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso2")+nombreImagen);
				
				//Saco la ruta hasta antes del WEB-INF
//				String[] sbCadena = path.split("WEB-INF");
//				String sbCadenaPath = sbCadena[0];
//				sbCadenaPath = sbCadenaPath.replace("/", "//");
				//Se arma la ruta de las imagenes de asociados
//				String realPath = sbCadenaPath + UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso");
//				realPath.trim();
//				
//				String rutaImagenAnterior = realPath+nombreImagen;
//				rutaImagenAnterior.trim();
				
				File fileAnterior = new File(path);
				
				if (fileAnterior.exists())
				{
					rutaImagen = rutaImagen.replace("/", "//");
					imagenCabPlancha = rutaImagen.trim();
				}else
					imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");
			}else
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros", "imagenDefault2");

			String OtrosEstudios = eleCabPlanchaDTO.getOtrosEstudios();

			String[] arrayOtrosEstudios = OtrosEstudios.split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
			estudios1 = arrayOtrosEstudios[0];
			estudios2 = arrayOtrosEstudios[1];

			antiguedad = ""+eleCabPlanchaDTO.getAntiguedad()+" años";

			cargosDirectivos = eleCabPlanchaDTO.getCargosDirectivos();

			listExpLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(nroCabezaPlancha);

			listPrincipalesDTO = DelegadoPrincipal.getInstance().consultarPrincipales(nroCabezaPlancha);

			for (ElePrincipalesDTO elePrincipalesDTO : listPrincipalesDTO) {

				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(elePrincipalesDTO.getNroPriIdentificacion());

				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
				}
				elePrincipalesDTO.setImagenEstado(imagenEstado);
			}

			listSuplentesDTO = DelegadoSuplente.getInstance().consultarSuplentes(nroCabezaPlancha);

			for (EleSuplentesDTO eleSuplentesDTO : listSuplentesDTO) {

				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(eleSuplentesDTO.getNroSuIdentificacion());

				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo2");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk2");
				}
				eleSuplentesDTO.setImagenEstado(imagenEstado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Metodo que retorna todas las zonas disponibles
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getZonas() {
		zonas = new ArrayList<SelectItem>();
		zonas.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));
		try {
			List<EleZonas> listaZonas = DelegadoZona.getInstance().consultarZonas();

			for (EleZonas elZonas : listaZonas) {
				zonas.add(new SelectItem(elZonas.getCodZona(),elZonas.getNomZona()));				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return zonas;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param zonas
	 */
	public void setZonas(List<SelectItem> zonas) {
		this.zonas = zonas;
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
	 * @return TimeZone
	 */
	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getZonaSelect() {
		return zonaSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param zonaSelect
	 */
	public void setZonaSelect(HtmlSelectOneMenu zonaSelect) {
		this.zonaSelect = zonaSelect;
	}

	public boolean isRenderTablePlanchas() {
		return renderTablePlanchas;
	}

	public void setRenderTablePlanchas(boolean renderTablePlanchas) {
		this.renderTablePlanchas = renderTablePlanchas;
	}

	public boolean isRenderTable() {
		return renderTable;
	}

	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}

	public List<EleNumerarPlanchasDTO> getListPlanchasDTO() {
		return listPlanchasDTO;
	}

	public void setListPlanchasDTO(List<EleNumerarPlanchasDTO> listPlanchasDTO) {
		this.listPlanchasDTO = listPlanchasDTO;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEstadoPlancha() {
		return estadoPlancha;
	}

	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}

	public String getNroCabezaPlancha() {
		return nroCabezaPlancha;
	}

	public void setNroCabezaPlancha(String nroCabezaPlancha) {
		this.nroCabezaPlancha = nroCabezaPlancha;
	}

	public String getImagenCabPlancha() {
		return imagenCabPlancha;
	}

	public void setImagenCabPlancha(String imagenCabPlancha) {
		this.imagenCabPlancha = imagenCabPlancha;
	}

	public String getNombreCabezaPlancha() {
		return nombreCabezaPlancha;
	}

	public void setNombreCabezaPlancha(String nombreCabezaPlancha) {
		this.nombreCabezaPlancha = nombreCabezaPlancha;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCargosDirectivos() {
		return cargosDirectivos;
	}

	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}

	public String getEstudios1() {
		return estudios1;
	}

	public void setEstudios1(String estudios1) {
		this.estudios1 = estudios1;
	}

	public String getEstudios2() {
		return estudios2;
	}

	public void setEstudios2(String estudios2) {
		this.estudios2 = estudios2;
	}

	public boolean isVisiblePlancha() {
		return visiblePlancha;
	}

	public void setVisiblePlancha(boolean visiblePlancha) {
		this.visiblePlancha = visiblePlancha;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public List<EleExperienciaLaboral> getListExpLaboral() {
		return listExpLaboral;
	}

	public void setListExpLaboral(List<EleExperienciaLaboral> listExpLaboral) {
		this.listExpLaboral = listExpLaboral;
	}


	public List<ElePrincipalesDTO> getListPrincipalesDTO() {
		return listPrincipalesDTO;
	}

	public void setListPrincipalesDTO(List<ElePrincipalesDTO> listPrincipalesDTO) {
		this.listPrincipalesDTO = listPrincipalesDTO;
	}

	public List<EleSuplentesDTO> getListSuplentesDTO() {
		return listSuplentesDTO;
	}

	public void setListSuplentesDTO(List<EleSuplentesDTO> listSuplentesDTO) {
		this.listSuplentesDTO = listSuplentesDTO;
	}

	public String getCantidadPlanchas() {
		return cantidadPlanchas;
	}

	public void setCantidadPlanchas(String cantidadPlanchas) {
		this.cantidadPlanchas = cantidadPlanchas;
	}

	public String getImagenEstado() {
		return imagenEstado;
	}

	public void setImagenEstado(String imagenEstado) {
		this.imagenEstado = imagenEstado;
	}

	public boolean isRenderButtonNext() {
		return renderButtonNext;
	}

	public void setRenderButtonNext(boolean renderButtonNext) {
		this.renderButtonNext = renderButtonNext;
	}

	public boolean isRenderButtonBack() {
		return renderButtonBack;
	}

	public void setRenderButtonBack(boolean renderButtonBack) {
		this.renderButtonBack = renderButtonBack;
	}

	public String getImagenBackButton() {
		return imagenBackButton;
	}

	public void setImagenBackButton(String imagenBackButton) {
		this.imagenBackButton = imagenBackButton;
	}

	public String getImagenNextButton() {
		return imagenNextButton;
	}

	public void setImagenNextButton(String imagenNextButton) {
		this.imagenNextButton = imagenNextButton;
	}
}