package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.icesoft.faces.application.state.Util;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.inputfile.FileInfo;

import co.com.coomeva.ele.delegado.DelegadoAutenticacion;
import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoParametros;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.EleLog;
import co.com.coomeva.ele.entidades.planchas.ElePParametros;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleEstadosDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaCargosDirectivosDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.InputFileData;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.profile.ws.client.CaasStub.Application;
import co.com.coomeva.profile.ws.client.CaasStub.Role;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class EvaluarPlanchaCCEE extends BaseVista
{
	private String documento;
	private boolean renderPanelGrid;
	private boolean renderCollapsible;
	private String estadoPlancha;
	private String profesion;
	private String antiguedad;
	private String nombreCabezaPlancha;
	private String nroCabezaPlancha;
	private String imagenCabPlancha;
	private String cargosDirectivos;
	private boolean visiblePopUpRecibir;
	private boolean visiblePopUpOk;
	private String conceptoCambio;
	private String estudios1;
	private String estudios2;
	private List<ElePrincipalesDTO> listaPrincipales;
	private List<EleSuplentesDTO> listaSuplentes;
	private List<EleTablaEmpresaCargoDTO> listEmpresaCargo;
	private String msgOk;
	private List<SelectItem> listTipoTransaccion;
	private HtmlSelectOneMenu tiposTransaccion;
	private boolean visibleDetalle;
	private String nroIdentificacionDetalle;
	private String nombreAsociadoDetalle;
	private String profesionDetalle;
	private String inhabilidadesDetalle;
	private boolean renderSuplentes;
	private boolean guardoPlanchaIn;
	private boolean hayInhabilidadPri;
	private boolean hayInhabilidadSup;
	private String zona;

	public EvaluarPlanchaCCEE() 
	{
		init();
	}

	/**
	 * Metodo que inicializa el componente de tipo de transacción
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	private void init()
	{
		renderCollapsible = true;
	}


	/**
	 * Metodo que realiza la busqueda de una plancha con sus asociados y su informacion
	 * por medio del Numero de plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_BuscarCCEE()
	{
		try 
		{
			Date dateToday = new Date();

			ElePParametros elePParametrosAdmIni1 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaIniAdmision1").getParametro();
			ElePParametros elePParametrosAdmFin1 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaFinAdmision1").getParametro();
			ElePParametros elePParametrosAdmIni2 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaIniAdmision2").getParametro();
			ElePParametros elePParametrosAdmFin2 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaFinAdmision2").getParametro();
			ElePParametros elePParametrosAdmIni3 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaIniAdmision3").getParametro();
			ElePParametros elePParametrosAdmFin3 = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaFinAdmision3").getParametro();


			Date dateFechaIniAdm1 = ManipulacionFechas.stringToDate(elePParametrosAdmIni1.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaFinAdm1 = ManipulacionFechas.stringToDate(elePParametrosAdmFin1.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaIniAdm2 = ManipulacionFechas.stringToDate(elePParametrosAdmIni2.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaFinAdm2 = ManipulacionFechas.stringToDate(elePParametrosAdmFin2.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaIniAdm3 = ManipulacionFechas.stringToDate(elePParametrosAdmIni3.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaFinAdm3 = ManipulacionFechas.stringToDate(elePParametrosAdmFin3.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");

			boolean fecha = false;
			guardoPlanchaIn = true;
			hayInhabilidadPri = false;
			hayInhabilidadSup = false;

			List<EleEstadosDTO> listEstados;
			EleEstadosDTO eleEstadosDTO;

			ElePlanchas planchas = DelegadoPlanchas.getInstance().consultarPlancha(documento);

			String estadoPlanchaOld = "";
			
			EleAsociadoDTO asociadoDTO = DelegadoClimae.getInstance().find(documento);

			if (asociadoDTO.getNombre() == null || asociadoDTO.getNombre().equalsIgnoreCase("")) 
			{
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));
			}

			if (planchas != null) {
				estadoPlanchaOld = DelegadoPlanchas.getInstance().findPlanchasEstadoNative(documento);//planchas.getEstado().trim().toString();
			}else
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha")+ " " +documento);

			if (elePParametrosAdmIni1 != null && elePParametrosAdmFin1 !=null&&elePParametrosAdmIni2 != null && elePParametrosAdmFin2 !=null&&elePParametrosAdmIni3 != null && elePParametrosAdmFin3 !=null) 
			{
				if (planchas.getNroPlancha()!=null) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgPlanchaNumerdada"));
				}
				
				if ((dateToday.compareTo(dateFechaIniAdm1)>=0 && dateToday.compareTo(dateFechaFinAdm1)<=0)) {

					if (!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2")) && 
							!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7")) && 
							!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5")) &&
							!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha4"))) {
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgEstadoEvaluar")+" "+
								UtilAcceso.getParametroFuenteS("parametros", "displayEstado2")+", "+
								UtilAcceso.getParametroFuenteS("parametros", "displayEstado7")+", "+
								UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5")+" ó "+
								UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha4"));
					}

					fecha = true;

					listEstados = new ArrayList<EleEstadosDTO>();

					eleEstadosDTO = new EleEstadosDTO();
					eleEstadosDTO.setEsadoCodigo(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
					eleEstadosDTO.setEsadoNombre(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
					listEstados.add(eleEstadosDTO);

					eleEstadosDTO = new EleEstadosDTO();
					eleEstadosDTO.setEsadoCodigo(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha4"));
					eleEstadosDTO.setEsadoNombre(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha4"));
					listEstados.add(eleEstadosDTO);

					guardoPlanchaIn = false;
					FacesUtils.setSessionParameter("listEstados", listEstados);
				}else
				{
					if ((dateToday.compareTo(dateFechaIniAdm2)>=0 && dateToday.compareTo(dateFechaFinAdm2)<=0)) {

						if (!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8")) && 
								!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha9"))) {
							throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgEstadoEvaluar")+" "+
									UtilAcceso.getParametroFuenteS("parametros", "displayEstado8")+" ó "+
									UtilAcceso.getParametroFuenteS("parametros", "displayEstado9"));
						}

						fecha = true;

						listEstados = new ArrayList<EleEstadosDTO>();

						eleEstadosDTO = new EleEstadosDTO();
						eleEstadosDTO.setEsadoCodigo(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
						eleEstadosDTO.setEsadoNombre(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
						listEstados.add(eleEstadosDTO);

						eleEstadosDTO = new EleEstadosDTO();
						eleEstadosDTO.setEsadoCodigo(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha6"));
						eleEstadosDTO.setEsadoNombre(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha6"));
						listEstados.add(eleEstadosDTO);

						guardoPlanchaIn = false;
						FacesUtils.setSessionParameter("listEstados", listEstados);
					}else
					{
						if ((dateToday.compareTo(dateFechaIniAdm3)>=0 && dateToday.compareTo(dateFechaFinAdm3)<=0)) {

							if (!estadoPlanchaOld.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha6"))) {
								throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgEstadoEvaluar")+" "+
										UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha6"));
							}
							UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
							Application[] applications = user.getApplications();
							Role[] rol = DelegadoAutenticacion.getInstance().getRol(applications[0]);
							if (rol!=null) {
								if (!rol[0].getName().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "rolCCEE"))) {
									throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msjNoRolCCEE"));
								}
							}
							fecha = true;

							listEstados = new ArrayList<EleEstadosDTO>();

							eleEstadosDTO = new EleEstadosDTO();
							eleEstadosDTO.setEsadoCodigo(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
							eleEstadosDTO.setEsadoNombre(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"));
							listEstados.add(eleEstadosDTO);

							guardoPlanchaIn = true;
							FacesUtils.setSessionParameter("listEstados", listEstados);
						}

					}
				}
			}

			if (!fecha) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaAdmExpired"));
			}


			int iTamanoDoc = documento.length();
			validarMaximo(iTamanoDoc, UtilAcceso.getParametroFuenteI("parametros", "maxNoDoc"), true, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoDoc"));

			if (documento == null || documento.equalsIgnoreCase("")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noIngresoDoc"));
			}

//			EleAsociadoDTO asociadoDTO = DelegadoClimae.getInstance().find(documento);
//
//			if (asociadoDTO.getNombre() == null || asociadoDTO.getNombre().equalsIgnoreCase("")) 
//			{
//				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));
//			}

			EleCabPlanchaDTO eleCabPlanchaDTO = DelegadoCabezaPlancha.getInstance().consultarCabezaPlanchaDTO(documento);

			ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(documento);

			if (elePlanchas == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
			}
			UserVo user  = (UserVo) FacesUtils.getSessionParameter("user");
			Application[] applications = user.getApplications();
			Role[] rol = DelegadoAutenticacion.getInstance().getRol(applications[0]);
			if (rol!=null) {
				if (rol[0].getName().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "rolSubcomision"))) {
					EleZonas eleZonaPlancha =  elePlanchas.getEleZonas();
					EleZonas eleZonaSub = (EleZonas) FacesUtils.getSessionParameter("zonaSubComision");
					EleZonas eleZonaSubEsp  = DelegadoZona.getInstance().consultarZona(eleZonaSub.getZonEspecial());
					if (eleZonaSub==null||eleZonaSubEsp==null) 
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEvaluaZonaSub"));
					if (!eleZonaSub.getCodZona().equalsIgnoreCase(eleZonaPlancha.getCodZona())&&!eleZonaSubEsp.getCodZona().equalsIgnoreCase(eleZonaPlancha.getCodZona())) 
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEvaluaZonaSub"));
						
				}
			}
			
			

			//			if (!elePlanchas.getEstado().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2")) && !elePlanchas.getEstado().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7")) && !elePlanchas.getEstado().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8"))) {
			//			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "planchaNoEstRecibida"));
			//			}

			renderPanelGrid = true;
			renderCollapsible = false;

			EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);

			listaPrincipales = new ArrayList<ElePrincipalesDTO>();
			listaSuplentes = new ArrayList<EleSuplentesDTO>();

			listaPrincipales = DelegadoPrincipal.getInstance().consultarPrincipales(documento);
			for (ElePrincipalesDTO elePrincipal : listaPrincipales) {
				List<EleInhabilidades> list = DelegadoHabilidad.getInstance().buscarInhabilidadesById(elePrincipal.getNroPriIdentificacion());
				if (list.size() != 0) {
					hayInhabilidadPri = true;
					elePrincipal.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
					elePrincipal.setInhabilidades(list);
				}else
				{	
					hayInhabilidadPri = false;
					elePrincipal.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));
				}	

			}
			listaSuplentes = DelegadoSuplente.getInstance().consultarSuplentes(documento);
			for (EleSuplentesDTO eleSuplentes : listaSuplentes) {
				List<EleInhabilidades> list = DelegadoHabilidad.getInstance().buscarInhabilidadesById(eleSuplentes.getNroSuIdentificacion());
				if (list.size() != 0) {
					hayInhabilidadSup = true;
					eleSuplentes.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
					eleSuplentes.setInhabilidades(list);
				}else
				{
					hayInhabilidadSup = false;
					eleSuplentes.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));
				}	

			}
			List<EleExperienciaLaboral> listExperienciaLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(documento);

			listEmpresaCargo = new ArrayList<EleTablaEmpresaCargoDTO>();
			EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO;

			int noEmpresaCargo = UtilAcceso.getParametroFuenteI("parametros", "noEmpresaCargo");

			int iCont = 0;

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
			nombreCabezaPlancha = asociadoDTO.getNombre();
			estadoPlancha = estadoPlanchaOld;
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado2");
			}else
				if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7"))) {
					estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado7");
				}
			if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8"))) {
				estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado8");
			}else
				if (estadoPlancha.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha9"))) {
					estadoPlancha = UtilAcceso.getParametroFuenteS("parametros", "displayEstado9");
				}

			profesion = eleCabPlancha.getProfesion();
			antiguedad = ""+eleCabPlancha.getAntiguedad()+ " años";
			cargosDirectivos = eleCabPlancha.getCargosDirectivos();
			if (listaSuplentes.size()==0) {
				renderSuplentes = false;
			}else
				renderSuplentes = true;
			zona = elePlanchas.getEleZonas().getNomZona();

			if (eleCabPlancha.getRutaImagen() == null||eleCabPlancha.getRutaImagen().trim().equals("")) {
				imagenCabPlancha = UtilAcceso.getParametroFuenteS("parametros","imagenDefault");;
			}else
				imagenCabPlancha = eleCabPlancha.getRutaImagen();

			//Lleno la lista de transacciones con los parametros que puse en la sesion

			listTipoTransaccion = new ArrayList<SelectItem>();
			listTipoTransaccion.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));
			//			int iCont2 = 3;

			List<EleEstadosDTO> list = (List<EleEstadosDTO>) FacesUtils.getSessionParameter("listEstados");

			for (EleEstadosDTO eleEstadosDTO2 : list) {
				listTipoTransaccion.add(new SelectItem(eleEstadosDTO2.getEsadoCodigo(),eleEstadosDTO2.getEsadoNombre()));
			}

			FacesUtils.setSessionParameter("listEstados", null);
			//			for (int i = 0; i < list.size(); i++) {
			//			listTipoTransaccion.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+(iCont+1)),UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+(iCont+1))));
			//			iCont2++;
			//			}

		}
		catch (Exception e) 
		{
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	/**
	 * Metodo que visualiza el PopUp de Evaluar Plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_EvaluarPlancha()
	{
		visiblePopUpRecibir = true;
		return "";
	}

	/**
	 * Metodo que evalua y modifica el estado de la plancha
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

			if (tiposTransaccion.getValue().toString().trim().equalsIgnoreCase("-1")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noTipoTransaccion"));
			}

			if ((hayInhabilidadPri == true || hayInhabilidadSup == true) && guardoPlanchaIn == false && tiposTransaccion.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha5"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEvaluaPlanchaIn"));
			}

			String estado = tiposTransaccion.getValue().toString();

			EleLogDTO eleLogDTO = new EleLogDTO();
			eleLogDTO.setNombreUsuario(userVo.getName().toString());
			eleLogDTO.setTipoTransaccion(UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion1"));
			eleLogDTO.setNombreTransaccion(UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccion1"));
			if (!guardoPlanchaIn) {
				DelegadoPlanchas.getInstance().cambiarEstadoPlancha(idNroCabezaPlancha, conceptoCambio, estado, userVo.getName().toString(), eleLogDTO,false);
			}else
			{
				DelegadoPlanchas.getInstance().cambiarEstadoPlancha(idNroCabezaPlancha, conceptoCambio, estado, userVo.getName().toString(), eleLogDTO,true);
				
				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(idNroCabezaPlancha.toString());

				ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(idNroCabezaPlancha.toString());

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

				String nombreImagen = nombreAsociado+"_"+zonaAsociado+UtilAcceso.getParametroFuenteS("parametros", "extension");

				//Trae la ruta del servidor
				String path = PathRequest.getInstance().getPathContext("");

				//Saco la ruta hasta antes del WEB-INF
				String[] sbCadena = path.split("WEB-INF");
				//Se arma la ruta de las imagenes de asociados
				String realPath = sbCadena[0] + UtilAcceso.getParametroFuenteS("parametros", "directorioGuiImagenesAso");

				String rutaImagenAnterior = realPath+nombreImagen;
				rutaImagenAnterior.trim();

				File fileAnterior = new File(rutaImagenAnterior);

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
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zonas+"_"+elePlanchas.getNroPlancha()+"_"+nombreAsociado+".jpg"));
					fileAnterior.delete();

					String rutaImagen = UtilAcceso.getParametroFuenteS("parametros", "directorioImagenesAso")+zonas+"_"+elePlanchas.getNroPlancha()+"_"+nombreAsociado+".jpg";
					rutaImagen.trim();
					DelegadoCabezaPlancha.getInstance().modificarRutaImagen(eleCabPlancha.getNroIdentificacion(), rutaImagen);	
				}
				else
				{
					String rutaImagen = UtilAcceso.getParametroFuenteS("parametros", "directorioImagenesAso")+zonas+"_"+elePlanchas.getNroPlancha()+"_"+nombreAsociado+".jpg";
					rutaImagen.trim();
					currentFile.getFile().renameTo(new File(currentFile.getFile().getParentFile().getAbsolutePath()+"//"+zonas+"_"+elePlanchas.getNroPlancha()+"_"+nombreAsociado+".jpg"));
					DelegadoCabezaPlancha.getInstance().modificarRutaImagen(eleCabPlancha.getNroIdentificacion(), rutaImagen);
				}
				
			}

			visiblePopUpRecibir = false;
			visiblePopUpOk = true;
			msgOk= UtilAcceso.getParametroFuenteS("mensajes", "msgOkEvaluarPlan");

			renderCollapsible = true;
			renderPanelGrid = false;
			visiblePopUpRecibir = false;

			conceptoCambio = "";
			documento = "";
		}
		catch (Exception e) 
		{
			msgOk= UtilAcceso.getParametroFuenteS("mensajes", "msgNoEvaluarPlan");
			getMensaje().mostrarMensaje(e.getMessage());
		}
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
	 * Metodo que cierra el popUp que recibe el concepto de cambio de estado
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConcepto()
	{
		visiblePopUpRecibir = false;
		return "";
	}

	/**
	 * Metodo que cierra el popUp de exito o error
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_cerrar()
	{
		visiblePopUpOk = false;
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
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListTipoTransaccion() {
		return listTipoTransaccion;
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