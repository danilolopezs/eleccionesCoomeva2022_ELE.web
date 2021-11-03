package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import co.com.coomeva.ele.delegado.DelegadoAsesor;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoLico;
import co.com.coomeva.ele.delegado.DelegadoSalud;
import co.com.coomeva.ele.delegado.DelegadoSrh;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.delegado.DelegadoZonaFinanciero;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.entidades.planchas.EleZonasDAO;
import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaEspecial;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaZonaElectoral;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaZonaElectoralEspecial;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleZonaElectoralEspecialDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaZona extends EleZonasDAO {
	private static LogicaZona instance;

	// Constructor de la clase
	private LogicaZona() {
	}

	// Patròn Singular
	public static LogicaZona getInstance() {
		if (instance == null) {
			instance = new LogicaZona();
		}
		return instance;
	}

	/**
	 * Consulta una zona mediante el codigo de la zona
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param zona
	 * @return EleZonas
	 * @throws Exception
	 */

	public EleZonas consultarZona(String zona) throws Exception {
		if (zona == null || zona.equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCodZona"));
		}

		EleZonas elZona = findById(zona);
		return elZona;
	}

	/**
	 * Consulta que zona a la cual se va a matricular la plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabIdentificacion
	 * @return EleZonas
	 * @throws Exception
	 */

	public EleZonas consultarZonaPlancha(String nroCabIdentificacion) throws Exception {
		//EleAsociadoDTO asociadoDTO = DelegadoClimae.getInstance().find(nroCabIdentificacion);
		
		boolean esAsesor = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);// elepromot
		boolean esEmpleado = DelegadoAsociado.getInstance().existAsociadoEspecial(nroCabIdentificacion);// ele_asociado_especial
		EleZonaElectoralEspecialDTO zonaElecEspeAsociado = null;
		if (esAsesor || esEmpleado) {
			//consultar zona especial del asociado
			zonaElecEspeAsociado = consultarZonaElecEspByIdAsociado(nroCabIdentificacion);
		} else {
			// consultar zona electoral
			zonaElecEspeAsociado = consultarZonaElecByIdAsociado(nroCabIdentificacion);
		}
		EleZonas eleZonas = null; 
		if(zonaElecEspeAsociado != null) {
			eleZonas = new EleZonas();
			eleZonas.setCodZona(zonaElecEspeAsociado.getCodigo());
			eleZonas.setNomZona(zonaElecEspeAsociado.getDescripcion());
			eleZonas.setZonEspecial(zonaElecEspeAsociado.getNumeroZona());
		}
		return eleZonas;
		
//		EleZonas elZona = new EleZonas();
//		EleZonasFinanciero eleZonasFinanciero = new EleZonasFinanciero();
//
//		boolean existAsesorFin = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);// elepromot
//		boolean existAsesorSrh = DelegadoLico.getInstance().existAsesor(nroCabIdentificacion);// ele_asocia
//		boolean existAsesorPla = DelegadoAsociado.getInstance().existAsociadoEspecial(nroCabIdentificacion);// ele_asociado_especial
//
//		boolean isAsesor = existAsesorSrh || existAsesorFin || existAsesorPla;
//		
//		if (isAsesor) {
//			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
//			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
//		} else {
//			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
//			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
//		}

		//return elZona;
	}
	
	public EleZonaElectoralEspecialDTO consultarZonaElecEspByIdAsociado(String numeroDocumento) {
		Session session = null;
		Query query = null;
		Object[] element = null;
		EleZonaElectoralEspecialDTO dto = null;
		session = HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery("consulta.zona.electorale.por.asociado.especial");
		query.setLong("numeroDocumento", Long.valueOf(numeroDocumento));
		
		//codigo, descripcion, numero zona
		element = (Object[]) query.uniqueResult();
		if (element != null) {
			dto = new EleZonaElectoralEspecialDTO();
			dto.setCodigo((Long)element[0] + "");
			dto.setDescripcion(element[1].toString());
			dto.setNumeroZona(element[2].toString());
		}
		return dto;
	}
	
	public EleZonaElectoralEspecialDTO consultarZonaElecByIdAsociado(String numeroDocumento) {
		Session session = null;
		Query query = null;
		Object[] element = null;
		EleZonaElectoralEspecialDTO dto = null;
		session = HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery("consulta.zona.electorale.por.asociado");
		query.setLong("numeroDocumento", Long.valueOf(numeroDocumento));
		
		//codigo, descripcion, numero zona
		element = (Object[]) query.uniqueResult();
		if (element != null) {
			dto = new EleZonaElectoralEspecialDTO();
			dto.setCodigo((Long)element[0] + "");
			dto.setDescripcion(element[1].toString());
			dto.setNumeroZona(element[2].toString());
		}
		return dto;
	}
	
	//metodo antiguo de consulta de zona
	public EleZonas consultarZonaPlancha_old(String nroCabIdentificacion) throws Exception
	{
		EleAsociadoDTO asociadoDTO =  DelegadoClimae.getInstance().find(nroCabIdentificacion);
		EleZonas elZona = new EleZonas();
		EleZonasFinanciero eleZonasFinanciero = new EleZonasFinanciero();

		boolean existAsesorFin = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);
		boolean existAsesorPla = DelegadoAsesor.getInstance().existAsesor(nroCabIdentificacion);
		boolean existAsesorMP = DelegadoSalud.getInstance().existAsesor(nroCabIdentificacion);
		boolean existAsesorSrh = DelegadoSrh.getInstance().existEmpleado(nroCabIdentificacion);

		boolean isAsesor = false;
		if (existAsesorSrh||existAsesorFin||existAsesorMP||existAsesorPla) {
			isAsesor = true;
		}
		if (isAsesor) {
			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
			//elZona = DelegadoZona.getInstance().consultarZona(elZona.getZonEspecial());
			
			//hay que quitarlo cuando se terminen las pruebas de ingreso
			elZona = DelegadoZona.getInstance().consultarZona("05");
		}else{
			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
		}

		return elZona;
	}
	

	/**
	 * Consulta todas la zonas que estan en la tabla ELEZONAS
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return
	 * @throws Exception
	 */

	public List<EleZonas> consultarZonas() throws Exception {
		List<EleZonas> listZonas = new ArrayList<EleZonas>();

		Criteria crit = this.getSession().createCriteria(EleZonas.class);
		crit.addOrder(Order.asc("nomZona"));
		try {
			listZonas = crit.list();
		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return listZonas;
	}
}
