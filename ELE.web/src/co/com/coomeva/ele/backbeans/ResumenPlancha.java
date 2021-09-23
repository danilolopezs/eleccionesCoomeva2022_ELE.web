package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

public class ResumenPlancha extends BaseVista
{
	private String nroCabezaPlancha;
	private String nombreCabezaPlancha;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<ElePrincipalesDTO> listaPrincipalesUser;
	private List<EleSuplentesDTO> listaSuplentes;
	private List<EleSuplentesDTO> listaSuplentesUser;
	private boolean visibleConfirmar;
	private String mensajeConfirmar;
	private String imagenConf;
	private String imagenEstado;
	private String estadoPlancha;
	private EleCabPlanchaDTO cabezaPlancha;
	private ElePlanchaDTO planchaVerificada ;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private String estudios1;
	private String estudios2;
	private String cargosDirectivos;
	private String imagenCabPlancha;
	private String zona;

	public ResumenPlancha() {
		init(); 
	}
	
	/**
	 * Metodo que recibe los tados del cabeza de plancha y su plancha e inicializa todos los componentes 
	 */
	private void init() 
	{
		try 
		{
			planchaVerificada = (ElePlanchaDTO) FacesUtils.getSessionParameter("userPlancha");
			
			zona = planchaVerificada.getEleZonas().getNomZona();
			

			nroCabezaPlancha = planchaVerificada.getEleCabPlanchaDTO().getNroIdentificacion().toString();
			nombreCabezaPlancha = planchaVerificada.getEleCabPlanchaDTO().getNombreCompleto().toString();
			
			listaPrincipales = planchaVerificada.getListaPrincipales();
			
			ElePrincipalesDTO elePrincipalesDTO;
			listaPrincipalesUser = new ArrayList<ElePrincipalesDTO>();
			
			for (ElePrincipalesDTO principalesDTO : listaPrincipales) {
				elePrincipalesDTO = new ElePrincipalesDTO();
				elePrincipalesDTO.setNroPriIdentificacion(principalesDTO.getNroPriIdentificacion());
				elePrincipalesDTO.setNombreCompleto(principalesDTO.getNombreCompleto());
				
				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(principalesDTO.getNroPriIdentificacion());
				
				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk");
				}	
				
				principalesDTO.setImagenEstado(imagenEstado);
				listaPrincipalesUser.add(principalesDTO);
			}
			
			listaSuplentes = planchaVerificada.getListaSuplentes();
			
			EleSuplentesDTO eleSuplentesDTO;
			listaSuplentesUser = new ArrayList<EleSuplentesDTO>();
			
			for (EleSuplentesDTO suplentesDTO : listaSuplentes) {
				eleSuplentesDTO = new EleSuplentesDTO();
				eleSuplentesDTO.setNroSuIdentificacion(suplentesDTO.getNroSuIdentificacion());
				eleSuplentesDTO.setNombreCompleto(suplentesDTO.getNombreCompleto());
				
				List<EleInhabilidades> listaInhabilidades = DelegadoHabilidad.getInstance().buscarInhabilidadesById(suplentesDTO.getNroSuIdentificacion());
				
				if (listaInhabilidades.size() != 0) {
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo");
				}else
				{
					imagenEstado = UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk");
				}	
				
				eleSuplentesDTO.setImagenEstado(imagenEstado);
				listaSuplentesUser.add(eleSuplentesDTO);
			}
			
			estadoPlancha = planchaVerificada.getEstado();
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado3");
			}
			
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado1");
			}

			List<EleExperienciaLaboral> listExperienciaLaboral = planchaVerificada.getListExperienciaLaboral();

			EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;
			listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();

			int iCont2 = 0;

			for (EleExperienciaLaboral elExperienciaLaborales: listExperienciaLaboral) 
			{
				eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
				eleTablaEmpresaCargoDTO.setCargo(elExperienciaLaborales.getCargo());
				eleTablaEmpresaCargoDTO.setEmpresa(elExperienciaLaborales.getEmpresa());
				listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
			}
			
			int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");
			
			if (listEmpresaCargo.size() == 0) {
				for (int i = 0; i < noEmpresaCargo; i++) {
					eleTablaEmpresaCargoDTO = new EleTablaEmpresaCargoDTO();
					eleTablaEmpresaCargoDTO.setCargo("-");
					eleTablaEmpresaCargoDTO.setEmpresa("-");
					listEmpresaCargo.add(eleTablaEmpresaCargoDTO);
				}
			}
			String[] estudios =  planchaVerificada.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios")); 
			estudios1 = estudios[0];
			estudios2 = estudios[1];
			cargosDirectivos = planchaVerificada.getEleCabPlanchaDTO().getCargosDirectivos();
			
			if (planchaVerificada.getEleCabPlanchaDTO().getRutaImagen() == null||planchaVerificada.getEleCabPlanchaDTO().getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = planchaVerificada.getEleCabPlanchaDTO().getRutaImagen();
			
		} 
		catch (Exception e) 
		{
			
		}
	}	
	
	public String action_goModificar()
	{
		visibleConfirmar = false;
		return "goModPlancha";
	}

	public String action_irModificar()
	{
		visibleConfirmar = false;
		return "goModPlancha";
	}
	
	public String action_closeConfirmar(){
		visibleConfirmar = false;		
		return "";
	}
	
	public String action_volver(){
		return "goInicioAsociado";
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
	 * @return String
	 */
	public String getImagenEstado() {
		return imagenEstado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param imagenEstado
	 */
	public void setImagenEstado(String imagenEstado) {
		this.imagenEstado = imagenEstado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<EleSuplentesDTO>
	 */
	public List<EleSuplentesDTO> getListaSuplentesUser() {
		return listaSuplentesUser;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listaSuplentesUser
	 */
	public void setListaSuplentesUser(List<EleSuplentesDTO> listaSuplentesUser) {
		this.listaSuplentesUser = listaSuplentesUser;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<ElePrincipalesDTO>
	 */
	public List<ElePrincipalesDTO> getListaPrincipalesUser() {
		return listaPrincipalesUser;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param listaPrincipalesUser
	 */
	public void setListaPrincipalesUser(List<ElePrincipalesDTO> listaPrincipalesUser) {
		this.listaPrincipalesUser = listaPrincipalesUser;
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

	public String getCargosDirectivos() {
		return cargosDirectivos;
	}

	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}
}