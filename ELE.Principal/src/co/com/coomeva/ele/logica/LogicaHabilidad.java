package co.com.coomeva.ele.logica;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.delegado.wsempleados.DelegadoWsEmpleados;
import co.com.coomeva.ele.delegado.DelegadoAsesor;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoLico;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoSalud;
import co.com.coomeva.ele.delegado.DelegadoSidco;
import co.com.coomeva.ele.delegado.DelegadoSie;
import co.com.coomeva.ele.delegado.DelegadoSrh;
import co.com.coomeva.ele.delegado.DelegadoSubcomision;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.delegado.DelegadoZonaFinanciero;
import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidadesDAO;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidadesId;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class LogicaHabilidad extends EleInhabilidadesDAO {
	private static LogicaHabilidad instance;

	// Constructor de la clase
	private LogicaHabilidad() {
	}

	// Patròn Singular
	public static LogicaHabilidad getInstance() {
		if (instance == null) {
			instance = new LogicaHabilidad();
		}
		return instance;
	}

	/**
	 * Valida la habilidad de una plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param plancha
	 * @return ElePlanchaDTO
	 * @throws Exception
	 */
	public ElePlanchaDTO validatePlancha(ElePlanchaDTO plancha) throws Exception {

		EleAsociadoDTO principalValidado;
		EleAsociadoDTO suplenteValidado;
		List<ElePrincipalesDTO> listaPrincipales = plancha.getListaPrincipales();
		List<EleSuplentesDTO> listSuplentes = plancha.getListaSuplentes();
		boolean planchaHabil = true;
		ElePlanchas elePlanchas = new ElePlanchas();
		elePlanchas.setNroCabPlancha(plancha.getEleCabPlanchaDTO().getNroIdentificacion());

		HashMap<String, String> listaAsociados = new HashMap<String, String>();
		for (ElePrincipalesDTO elePrincipalesDTO : listaPrincipales) {
			if (listaAsociados.get(elePrincipalesDTO.getNroPriIdentificacion()) == null) {
				principalValidado = new EleAsociadoDTO();
				principalValidado = validateAsociadoDTO(elePrincipalesDTO.getNroPriIdentificacion(),
						plancha.getEleZonas(), plancha.getEleCabPlanchaDTO().getNroIdentificacion());
				elePrincipalesDTO.setEmail(principalValidado.getEmail());
				elePrincipalesDTO.setAntiguedad(principalValidado.getAntiguedad() + " años");
				elePrincipalesDTO.setEdad(principalValidado.getEdad());
				elePrincipalesDTO.setElePlanchas(elePlanchas);

				String nombreBUC = principalValidado.getNombre();

				if (nombreBUC == null || nombreBUC.equalsIgnoreCase("")) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));
				}

				elePrincipalesDTO.setNombreCompleto(principalValidado.getNombre());
				elePrincipalesDTO.setPrimerNombre(principalValidado.getPrimerNombre());
				elePrincipalesDTO.setSegundoNombre(principalValidado.getSegundoNombre());
				elePrincipalesDTO.setPrimerApellido(principalValidado.getPrimerApellido());
				elePrincipalesDTO.setSegundoApellido(principalValidado.getSegundoApellido());
				if (principalValidado.getListaInhabilidades().size() != 0) {
					planchaHabil = false;
					elePrincipalesDTO.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
				} else
					elePrincipalesDTO.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));

				elePrincipalesDTO.setInhabilidades(principalValidado.getListaInhabilidades());
				elePrincipalesDTO.setProfesion(principalValidado.getProfesion());
				listaAsociados.put(elePrincipalesDTO.getNroPriIdentificacion(), elePrincipalesDTO.getNombreCompleto());
			} else
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "idDuplicado")
						+ elePrincipalesDTO.getNroPriIdentificacion()
						+ UtilAcceso.getParametroFuenteS("mensajes", "idDuplicado1"));

		}
		for (EleSuplentesDTO eleSuplentesDTO : listSuplentes) {
			if (listaAsociados.get(eleSuplentesDTO.getNroSuIdentificacion()) == null) {
				suplenteValidado = new EleAsociadoDTO();
				suplenteValidado = validateAsociadoDTO(eleSuplentesDTO.getNroSuIdentificacion(), plancha.getEleZonas(),
						plancha.getEleCabPlanchaDTO().getNroIdentificacion());
				eleSuplentesDTO.setEmail(suplenteValidado.getEmail());
				eleSuplentesDTO.setAntiguedad(suplenteValidado.getAntiguedad() + " años");
				eleSuplentesDTO.setEdad(suplenteValidado.getEdad());
				eleSuplentesDTO.setElePlanchas(elePlanchas);

				String nombreBUC = suplenteValidado.getNombre();

				if (nombreBUC == null || nombreBUC.equalsIgnoreCase("")) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));
				}

				eleSuplentesDTO.setNombreCompleto(suplenteValidado.getNombre());
				eleSuplentesDTO.setPrimerNombre(suplenteValidado.getPrimerNombre());
				eleSuplentesDTO.setSegundoNombre(suplenteValidado.getSegundoNombre());
				eleSuplentesDTO.setPrimerApellido(suplenteValidado.getPrimerApellido());
				eleSuplentesDTO.setSegundoApellido(suplenteValidado.getSegundoApellido());
				if (suplenteValidado.getListaInhabilidades().size() != 0) {
					planchaHabil = false;
					eleSuplentesDTO.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoNo"));
				} else
					eleSuplentesDTO.setImagenEstado(UtilAcceso.getParametroFuenteS("parametros", "imagenEstadoOk"));

				eleSuplentesDTO.setInhabilidades(suplenteValidado.getListaInhabilidades());
				eleSuplentesDTO.setProfesion(suplenteValidado.getProfesion());
				listaAsociados.put(eleSuplentesDTO.getNroSuIdentificacion(), eleSuplentesDTO.getNombreCompleto());
			} else
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "idDuplicado")
						+ eleSuplentesDTO.getNroSuIdentificacion()
						+ UtilAcceso.getParametroFuenteS("mensajes", "idDuplicado1"));

		}

		if (planchaHabil) {
			plancha.setEstado(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"));
		} else
			plancha.setEstado(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"));

		plancha.setNroCabPlancha(plancha.getEleCabPlanchaDTO().getNroIdentificacion());

		return plancha;

	}

	/**
	 * Valida un asociado la habilidad de un asociado en la base de datos
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param elZona
	 * @param nroCabPlancha
	 * @return EleAsociadoDTO
	 * @throws Exception
	 */

	public EleAsociadoDTO validateAsociadoDTO(String nroIdentificacion, EleZonas elZona, String nroCabPlancha)
			throws Exception {
		
		EleAsociadoDTO asociado = null;
		asociado = DelegadoClimae.getInstance().find(nroIdentificacion);
		
		Long cons = 0l;
		asociado.setZonaPlancha(elZona);
		
		asociado.setEstadoHabilidad(Boolean.TRUE);
		asociado = validaSancion(asociado, nroIdentificacion, cons++);
		asociado = validaAntiguedadAsociado(asociado, cons++);
		asociado = validaAntiguedadTitulo(asociado, cons++);
		asociado = validaZonaAsociado(elZona, asociado, cons++);
		//asociado = validadHorasDemocracia(nroIdentificacion, asociado, cons++);
		asociado = validaSubComision(nroIdentificacion, asociado, cons++);

		validaOtraPlancha(nroIdentificacion, nroCabPlancha, asociado, cons++);
		return asociado;
		/*
		 * if (!DelegadoHabilidad.getInstance().validateEmpleado(nroIdentificacion,
		 * elZona) && elZona.getZonEspecial()
		 * .equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros",
		 * "esZonaEspecial"))) { throw new
		 * Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoEmpleadoNatural")
		 * + " " + nroIdentificacion + " " + UtilAcceso.getParametroFuenteS("mensajes",
		 * "msgNoEmpleadoNatural1")); } else { if
		 * (DelegadoHabilidad.getInstance().validateEmpleado(nroIdentificacion, elZona)
		 * && !elZona.getZonEspecial()
		 * .equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros",
		 * "esZonaEspecial"))) { // mario throw new
		 * Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoEmpleadoNatural")
		 * + " " + nroIdentificacion + "				 " +
		 * UtilAcceso.getParametroFuenteS("mensajes", "msgNoEmpleadoNatural2")); } }
		 */
	}

	private EleAsociadoDTO validaOtraPlancha(String nroIdentificacion, String nroCabPlancha, EleAsociadoDTO asociado,
			Long cons) throws Exception {
		if (DelegadoPlanchas.getInstance().validarOtraPlancha(nroIdentificacion, nroCabPlancha)) {
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldAsociadoOtraPlancha")));
//			asociado.setEstadoHabilidad(false);
			asociado.setEstadoHabilidad(true);
		}
		return asociado;
	}

	private EleAsociadoDTO validaSubComision(String nroIdentificacion, EleAsociadoDTO asociado, Long cons)
			throws Exception {
		if (DelegadoSubcomision.getInstance().existSubcomision(nroIdentificacion)) {
			cons++;
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldSubComision")));
			asociado.setEstadoHabilidad(false);
			//asociado.setEstadoHabilidad(true);
		}
		return asociado;
	}

	private EleAsociadoDTO validadHorasDemocracia(String nroIdentificacion, EleAsociadoDTO asociado, Long cons)
			throws Exception {
		Long horasAsociado = DelegadoSidco.getInstance().consultarHorasDemocraciaAsociado(nroIdentificacion);
		if(horasAsociado != null && horasAsociado.longValue() > 0) {
			Integer horasDemocracia = UtilAcceso.getParametroFuenteI("parametros", "horasDemocracia");
			if(horasDemocracia <= horasAsociado ) {
				cons++;
				asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
						UtilAcceso.getParametroFuenteS("mensajes", "vldHorasDemocracia")));
				asociado.setEstadoHabilidad(false);
			}
		}
		return asociado;
//		if (DelegadoSie.getInstance().validateHorasDemocracia(nroIdentificacion)) {
//			cons++;
//			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
//					UtilAcceso.getParametroFuenteS("mensajes", "vldHorasDemocracia")));
//			asociado.setEstadoHabilidad(false);
//			//asociado.setEstadoHabilidad(true);
//		}
	}

	private EleAsociadoDTO validaZonaAsociado(EleZonas elZona, EleAsociadoDTO asociado, Long cons) {
		if (elZona == null) {
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldZonaValida")));
			asociado.setEstadoHabilidad(false);
		}
		return asociado;
	}

	private EleAsociadoDTO validaAntiguedadAsociado(EleAsociadoDTO asociado, Long cons) {
		if (asociado.getAntiguedad() < UtilAcceso.getParametroFuenteI("parametros", "antiguedadMinima")) {
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldAsociadoMayor3")));
			asociado.setEstadoHabilidad(false);
			//asociado.setEstadoHabilidad(true);
		}
		return asociado;
	}

	private EleAsociadoDTO validaAntiguedadTitulo(EleAsociadoDTO asociado, Long cons) {
		if (asociado.getAntiguedadDesdeObtencionTitulo() < UtilAcceso.getParametroFuenteI("parametros",
				"antiguedadMinima")) {
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldAsociadoFechaObtencionTitulo")));
			asociado.setEstadoHabilidad(false);
			//asociado.setEstadoHabilidad(true);
		}
		return asociado;
	}

	private EleAsociadoDTO validaSancion(EleAsociadoDTO asociado, String nroIdentificacion, Long cons)
			throws Exception {
		boolean isValid = DelegadoClimae.getInstance().validaSancion(nroIdentificacion);

		if (isValid) {
			asociado.addInhabilidad(new EleInhabilidades(new EleInhabilidadesId(cons, asociado.getId()),
					UtilAcceso.getParametroFuenteS("mensajes", "vldAsociadoFechaObtencionTitulo")));
			asociado.setEstadoHabilidad(false);
			//asociado.setEstadoHabilidad(true);
		}
		return asociado;
	}

	/**
	 * Metodo que incribe la inhabilidades de un asociado en la base de datos
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listInhabilidades
	 */

	public void inscribirInhabilidades(List<EleInhabilidades> listInhabilidades) {
		if (listInhabilidades != null) {
			for (EleInhabilidades eleInhabilidades : listInhabilidades) {
				save(eleInhabilidades);
			}
		}

	}

	/**
	 * Metodo que remueve las inhabilidades de la base de datos de un asociado
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 */
	public void removerInhabilidades(String nroIdentificacion) {
		Criteria criteria = getSession().createCriteria(EleInhabilidades.class);
		criteria.add(Restrictions.eq("id.nroIdentificacion", nroIdentificacion));
		List<EleInhabilidades> lista = criteria.list();
		for (EleInhabilidades eleInhabilidades : lista) {
			delete(eleInhabilidades);
		}

	}

	/**
	 * Metodo que busca todas las inhabilidades inscritas de un asociado
	 * 
	 * @param nroIdentificacion
	 * @return List<EleInhabilidades>
	 */

	public List<EleInhabilidades> buscarInhabilidadesById(String nroIdentificacion) throws Exception {
		Criteria criteria = getSession().createCriteria(EleInhabilidades.class);
		List<EleInhabilidades> lista = null;
		try {
			criteria.add(Restrictions.eq("id.nroIdentificacion", nroIdentificacion));

			lista = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return lista;
	}

	/**
	 * Valida si el asociado es empleado, asesor de coomeva,asociado especial
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabIdentificacion
	 * @param elZona
	 * @return boolean
	 * @throws Exception
	 */

	public boolean validateEmpleado(String nroCabIdentificacion, EleZonas elZona) throws Exception {
		boolean existAsesorFin = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);// elepromot
		boolean existAsesorSrh = DelegadoLico.getInstance().existAsesor(nroCabIdentificacion);// ele_asocia
		boolean existAsesorPla = DelegadoAsociado.getInstance().existAsociadoEspecial(nroCabIdentificacion);// ele_asociado_especial

		boolean isAsesor = false;
		if (existAsesorSrh || existAsesorFin || existAsesorPla) {
			isAsesor = true;
		}
		return isAsesor;
	}

	/**
	 * Indica si el número de identificación ingresado corresponde a una persona que
	 * labora en el grupo empresarial coomeva
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 1/12/2012
	 * @param nroCabIdentificacion
	 * @return
	 * @throws Exception
	 */
	public boolean esEmpleadoGECoomeva(String nroCabIdentificacion) throws Exception {

		boolean existAsesorFin = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);
		boolean existAsesorPla = DelegadoAsesor.getInstance().existAsesor(nroCabIdentificacion);
		boolean existAsesorMP = DelegadoSalud.getInstance().existAsesor(nroCabIdentificacion);
		boolean existAsesorSrh = DelegadoWsEmpleados.getInstance().existEmpleado(nroCabIdentificacion);

		if (existAsesorSrh || existAsesorFin || existAsesorMP || existAsesorPla) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
