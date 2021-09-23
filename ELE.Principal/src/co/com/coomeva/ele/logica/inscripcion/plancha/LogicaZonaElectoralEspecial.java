package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleZonaElectoralDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleZonaElectoralEspecialDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoralEspecial;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;


/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class LogicaZonaElectoral
 * @date 6/12/2012
 *
 */
public class LogicaZonaElectoralEspecial  extends EleZonaElectoralEspecialDAO implements ILogicaZonaElectoralEspecial {

	private static LogicaZonaElectoralEspecial instance;

	//Patròn Singular
	public static LogicaZonaElectoralEspecial getInstance() {
		if (instance == null) {
			instance = new LogicaZonaElectoralEspecial();
		}
		return instance;
	}

	public List<FiltrosConsultasDTO> consultarZonasElectoralesEspeciales()
	throws EleccionesDelegadosException {
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		FiltrosConsultasDTO dto = null;
		try {
			List<EleZonaElectoralEspecial> listaZonas = findAll();
			if (listaZonas != null && listaZonas.size() > 0) {
				for (EleZonaElectoralEspecial zonaElect : listaZonas) {
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

}
