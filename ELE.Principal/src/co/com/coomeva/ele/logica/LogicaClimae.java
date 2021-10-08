package co.com.coomeva.ele.logica;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.delegado.DelegadoLico;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoSie;
import co.com.coomeva.ele.entidades.climae.HibernateSessionFactoryClimae;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.util.WorkStrigs;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.DateManipultate;
import co.com.coomeva.util.date.ManipulacionFechas;

public class LogicaClimae {

	private static LogicaClimae instance;

	// Constructor de la clase
	private LogicaClimae() {
	}

	// Patròn Singular
	public static LogicaClimae getInstance() {
		if (instance == null) {
			instance = new LogicaClimae();
		}
		return instance;
	}

	/**
	 * Metodo que realiza una consulta en el BUC el asociado y muestra sus valores
	 * en un objecto EleAsociadoDTO
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param idAsociado
	 * @return EleAsociadoDTO
	 * @throws Exception
	 */

	public EleAsociadoDTO find(String idAsociado) throws Exception {
		EleAsociadoDTO asociadoDTO = new EleAsociadoDTO();
		Long number = 0L;
		number = (Long) Long.parseLong(idAsociado);
		List<Object[]> listObject;

		Session session = null;
		Query query = null;
		session = HibernateSessionFactoryClimae.getSession();
		query = session.getNamedQuery("asociado.find");
		query.setInteger(0, 0);
		query.setInteger(1, 1);
		query.setLong(2, number);
		query.setLong(3, 10);
		query.setLong(4, 18);
		query.setLong(5, 2);

		listObject = query.list();
		if (listObject.size() == 0) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado") + idAsociado
					+ UtilAcceso.getParametroFuenteS("mensajes", "noAsociado1"));
		}

		String nombreBUC = "";

		for (Object[] object : listObject) {
			if (object[0] != null) {
				asociadoDTO.setId(object[0].toString());
			} else {
				asociadoDTO.setId("");
			}
			if (object[1] != null) {
				nombreBUC = object[1].toString();

			} else {
				nombreBUC = "";
			}
			if (object[2] != null && !object[2].toString().equalsIgnoreCase("")) {
				asociadoDTO.setProfesion(DelegadoPlanchas.getInstance().formatearCadena(object[2].toString()));
			} else {
				asociadoDTO.setProfesion(UtilAcceso.getParametroFuenteS("parametros", "noInscrito"));
			}
			if (object[3] != null && !object[3].toString().equalsIgnoreCase("")
					&& !object[3].toString().equalsIgnoreCase("0")) {
				asociadoDTO.setEdad(DelegadoSie.getInstance().calculateTime(object[3].toString()));
			} else {
				asociadoDTO.setEdad(0);
			}
			asociadoDTO.setFechaVinculacion(DateManipultate.stringToDate(object[4].toString(), "yyyyMMdd"));

			Date fechaIngreso = DateManipultate.stringToDate(object[4].toString(), "yyyyMMdd");
			int annosAntiguedad = (ManipulacionFechas.calcularEdad(fechaIngreso, new Date()));
			asociadoDTO.setAntiguedad(annosAntiguedad * 12);

			if (object[5] != null) {
				asociadoDTO.setOficina(object[5].toString());
			} else {
				asociadoDTO.setOficina("");
			}
			if (object[6] != null) {
				asociadoDTO.setEmail(object[6].toString());
			} else {
//				asociadoDTO.setEmail("No contiene");
				asociadoDTO.setEmail(UtilAcceso.getParametroFuenteS("parametros", "noInscritoEmail"));
			}

			Long longFechaObtencionTitulo = obtenerFechaObtencionTitulo(Long.parseLong(idAsociado));

			if (longFechaObtencionTitulo != null && !new Long(0).equals(longFechaObtencionTitulo)) {
				Date fechaObtencionTitulo = DateManipultate.stringToDate(longFechaObtencionTitulo.toString(),
						"yyyyMMdd");
				Long annosAntiguedadObtencionTitulo = (ManipulacionFechas.calculaTiempoTranascurridoDias(new Date(),
						fechaObtencionTitulo) / 365);
				asociadoDTO.setAntiguedadDesdeObtencionTitulo(annosAntiguedadObtencionTitulo);
				asociadoDTO.setFechaObtencionTitulo(fechaObtencionTitulo);
			} else {
				asociadoDTO.setAntiguedadDesdeObtencionTitulo(0L);
			}

			break;
		}
		if (nombreBUC != null && !nombreBUC.equalsIgnoreCase("")) {

			String primerNombre = (WorkStrigs.getValue(nombreBUC, "2", "3"));
			String segundoNombre = (WorkStrigs.getValue(nombreBUC, "3", "4"));
			String primerApellido = (WorkStrigs.getValue(nombreBUC, "0", "1"));
			String segundoApellido = (WorkStrigs.getValue(nombreBUC, "1", "2"));

			primerNombre = DelegadoPlanchas.getInstance().formatearCadena(primerNombre);
			segundoNombre = DelegadoPlanchas.getInstance().formatearCadena(segundoNombre);
			primerApellido = DelegadoPlanchas.getInstance().formatearCadena(primerApellido);
			segundoApellido = DelegadoPlanchas.getInstance().formatearCadena(segundoApellido);

			asociadoDTO.setNombre(primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido);
			asociadoDTO.setPrimerNombre(primerNombre);
			asociadoDTO.setSegundoNombre(segundoNombre);
			asociadoDTO.setPrimerApellido(primerApellido);
			asociadoDTO.setSegundoApellido(segundoApellido);

		} else
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociado"));

		return asociadoDTO;
	}

	/**
	 * Consulta la fecha de obtención del título de un asociado
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 21/12/2012
	 * @return Long
	 */
	private Long obtenerFechaObtencionTitulo(Long nitcli) {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryClimae.getSession();
			query = session.getNamedQuery("consultar.fecha.obtencion.titulo.asociado");
			query.setLong("asocia", 1L);
			query.setLong("nitcli", nitcli);
			query.setLong("tipCli", 2L);
			List<Long> fecha = (List<Long>) query.list();

			if (fecha != null && !fecha.isEmpty()) {
				return fecha.get(0);
			}

			return null;
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

}