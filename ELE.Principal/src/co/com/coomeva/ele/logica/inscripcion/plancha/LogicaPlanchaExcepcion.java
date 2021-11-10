package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaExcepcionDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class LogicaPlanchaExcepcion
 * @date 11/01/2013
 */
public class LogicaPlanchaExcepcion implements ILogicaPlanchaExcepcion {

	private ElePlanchaExcepcionDAO elePlanchaExcepcionDAO;

	@Override
	public void registrarPlanchaExcepcion(Long consecutivoPlancha,
			Long codigoAsociado, String excepciones)
			throws EleccionesDelegadosException {
		try {
			elePlanchaExcepcionDAO = new ElePlanchaExcepcionDAO();
			ElePlanchaExcepcion excepcion = new ElePlanchaExcepcion();

			Long consecutivo = null; 
			Boolean esModificar = Boolean.FALSE;
			
			List<ElePlanchaExcepcion> listaObs = consultarExcepcionesByCodigoAsociado(codigoAsociado, ""+consecutivoPlancha);
			for (ElePlanchaExcepcion elePlanchaExcepcion : listaObs) {
				if (elePlanchaExcepcion.getConsecutivo() != null
						&& elePlanchaExcepcion.getCodigoAsociado().longValue() == codigoAsociado
						&& elePlanchaExcepcion.getConsecutivoPlancha().longValue() == consecutivoPlancha) {
					consecutivo = elePlanchaExcepcion.getConsecutivo();
					esModificar = Boolean.TRUE;
				}
			}
			
			excepcion.setCodigoAsociado(codigoAsociado);
			excepcion.setConsecutivoPlancha(consecutivoPlancha);
			excepcion.setDescExcepcion(excepciones);
			
			if (!esModificar) {
				consecutivo = GeneradorConsecutivos.getInstance().getConsecutivo(
						ConstantesNamedQueries.QUERY_SEQ_PLANCHA_EXCEPCION,
						HibernateSessionFactoryElecciones2012.getSession());
			}
			
			excepcion.setConsecutivo(consecutivo);
			if(esModificar) {
				elePlanchaExcepcionDAO.merge(excepcion);
			} else {
				elePlanchaExcepcionDAO.save(excepcion);
			}
			
		} catch (Exception e) {
			throw new EleccionesDelegadosException(
					"Se ha presentado un error al intentar"
							+ " registrar una excepción para la plancha actual",
					e);
		} finally {
			elePlanchaExcepcionDAO = null;
		}
	}
	
	/**
	 * Consulta las excepciones registradas a un asociado en el proceso
	 * de inscripción de planchas.
	 * 
	 * @param identificacionAsociado
	 * @return List<ElePlanchaExcepcion> Listado de excepciones relacionadas a un asociado
	 */
	public List<ElePlanchaExcepcion> consultarExcepciones(String identificacionAsociado, String consecutivoPlancha) throws EleccionesDelegadosException {
		List<ElePlanchaExcepcion> resultado = null; 
		
		try {
			Long codigoAsociado = LogicaAsociado.getInstance().consultarCodigoAsociadoPorNumeroDocumento(new Long(identificacionAsociado));
			
			if( codigoAsociado != null ){
				elePlanchaExcepcionDAO = new ElePlanchaExcepcionDAO();
				Criteria criteria = elePlanchaExcepcionDAO.getSession().createCriteria(ElePlanchaExcepcion.class);
		    	criteria.add(Expression.eq("codigoAsociado", codigoAsociado));
		    	criteria.add(Expression.eq("consecutivoPlancha", Long.parseLong(consecutivoPlancha)));
		    	return criteria.list();
			}
			
		} catch (NumberFormatException e) {
			throw new EleccionesDelegadosException("La identificación del asociado debe ser numérica");
		} catch (Exception e) {
			throw new EleccionesDelegadosException("No fue posible consultar las excepciones del asociado");
		}
		return resultado;
	}
	
	public List<ElePlanchaExcepcion> consultarExcepcionesByCodigoAsociado(Long codigoAsociado, String consecutivoPlancha) throws EleccionesDelegadosException {
		List<ElePlanchaExcepcion> resultado = null; 
		
		try {
			if( codigoAsociado != null ){
				elePlanchaExcepcionDAO = new ElePlanchaExcepcionDAO();
				Criteria criteria = elePlanchaExcepcionDAO.getSession().createCriteria(ElePlanchaExcepcion.class);
		    	criteria.add(Expression.eq("codigoAsociado", codigoAsociado));
		    	criteria.add(Expression.eq("consecutivoPlancha", Long.parseLong(consecutivoPlancha)));
		    	return criteria.list();
			}
			
		} catch (NumberFormatException e) {
			throw new EleccionesDelegadosException("La identificación del asociado debe ser numérica");
		} catch (Exception e) {
			throw new EleccionesDelegadosException("No fue posible consultar las excepciones del asociado");
		}
		return resultado;
	}

}
