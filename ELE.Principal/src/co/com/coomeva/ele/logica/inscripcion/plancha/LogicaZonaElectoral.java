package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleZonaElectoralDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londo�o</a> -
 *         Premize SAS <br>
 * @project ELE.Principal
 * @class LogicaZonaElectoral
 * @date 6/12/2012
 *
 */
public class LogicaZonaElectoral extends EleZonaElectoralDAO implements ILogicaZonaElectoral {

	private static LogicaZonaElectoral instance;

	// Patr�n Singular
	public static LogicaZonaElectoral getInstance() {
		if (instance == null) {
			instance = new LogicaZonaElectoral();
		}
		return instance;
	}

	public List<FiltrosConsultasDTO> consultarZonasElectorales() throws EleccionesDelegadosException {
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		FiltrosConsultasDTO dto = null;
		try {
			List<EleZonaElectoral> listaZonas = findAll();
			if (listaZonas != null && listaZonas.size() > 0) {
				for (EleZonaElectoral zonaElect : listaZonas) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro(Long.parseLong(zonaElect.getCodigoZonaEle()));
					dto.setDescripcionFiltro(zonaElect.getDescripcionZonaEle());
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new EleccionesDelegadosException();
		}
		return list;
	}

	/**
	 * @author <a href="mailto:julianaa_coomeva.com.co">Juliana Nobile</a> - Coomeva
	 *         <br>
	 *         Consulta Los codigos de las {@link EleZonaElectoral} y su regional.
	 * @return List<EleZonaElectoral>
	 */
	public List<EleZonaElectoralRegionalDTO> consultarCodigosZonasElectoralesRegionales()
			throws EleccionesDelegadosException {
		List<EleZonaElectoralRegionalDTO> list = new ArrayList<EleZonaElectoralRegionalDTO>();
		List<Object[]> elements = null;
		EleZonaElectoralRegionalDTO dto = null;
		Session session = getSession();

		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_ZONAS_ELECTORALES_REGIONAL);

			elements = queryObject.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					// S�lo se admiten regionales con c�digo < a 6, se duplicaban las regionales
					if (Integer.valueOf(object[0].toString()) <= 6) {
						dto = new EleZonaElectoralRegionalDTO();
						dto.setCodigoZona((Long) object[1]);
						dto.setCodigoRegional((Long) object[0]);
						list.add(dto);
					}
				}
			}

			return list;
		} catch (Exception e) {
			System.out.println("No se logr� consultar las zonas electorales" + e.toString());
			e.printStackTrace();
			throw new EleccionesDelegadosException(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public List<EleZonaElectoralRegionalDTO> consultarCodigosRegionalDeZonaElectoral()
			throws EleccionesDelegadosException {
		List<EleZonaElectoralRegionalDTO> list = new ArrayList<EleZonaElectoralRegionalDTO>();
		List<Object[]> elements = null;
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_REGIONALES_DE_ZONA_ELECTORAL);
			elements = queryObject.list();
			if (!elements.isEmpty()) {
				EleZonaElectoralRegionalDTO dto;
				for (Object[] object : elements) {
						dto = new EleZonaElectoralRegionalDTO();
						dto.setCodigoZona((Long) object[1]);
						dto.setCodigoRegional((Long) object[0]);
						list.add(dto);
				}
			}
			return list;
		} catch (Exception e) {
			System.out.println("No se logr� consultar las zonas electorales" + e.toString());
			e.printStackTrace();
			throw new EleccionesDelegadosException(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public String consultarZonaElectoralByCodigo(Long codigo) {
		String nombre_zona_ele = null;
		Session session = getSession();
		try {
			Query queryObject = session.getNamedQuery("consultar.zona.electoral.por.codigo");
			queryObject.setLong("codigoZonaEle", codigo);
			nombre_zona_ele = (String) queryObject.uniqueResult();
			return nombre_zona_ele != null ? nombre_zona_ele : "";
		} catch (Exception e) {
			System.out.println("No se logr� consultar el nombre de la zona electoral");
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "";
	}
	
	@Override
	public String consultarZonaElectoralEspecialByCodigo(Long codigo) {
		String nombre_zona_ele = null;
		Session session = getSession();
		try {
			Query queryObject = session.getNamedQuery("consultar.zona.electoral.especial.por.codigo");
			queryObject.setLong("codigoZonaEle", codigo);
			nombre_zona_ele = (String) queryObject.uniqueResult();
			return nombre_zona_ele != null ? nombre_zona_ele : "";
		} catch (Exception e) {
			System.out.println("No se logr� consultar el nombre de la zona electoral");
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "";
	}
}
