package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;


import co.com.coomeva.ele.delegado.DelegadoAsesor;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoLico;
import co.com.coomeva.ele.delegado.DelegadoSalud;
import co.com.coomeva.ele.delegado.DelegadoSrh;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.delegado.DelegadoZonaFinanciero;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.entidades.planchas.EleZonasDAO;
import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaZona extends EleZonasDAO {
	private static LogicaZona instance;

	//Constructor de la clase
	private LogicaZona() {
	}

	//Patròn Singular
	public static LogicaZona getInstance() {
		if (instance == null) {
			instance = new LogicaZona();
		}
		return instance;
	}


	/**
	 * Consulta una zona mediante el codigo de la zona
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param zona
	 * @return EleZonas
	 * @throws Exception
	 */

	public EleZonas consultarZona(String zona) throws Exception {
		if (zona == null|| zona.equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCodZona"));
		}

		EleZonas elZona = findById(zona); 
		return elZona;
	}
	/**
	 * Consulta que zona a la cual se va a matricular la plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabIdentificacion
	 * @return EleZonas
	 * @throws Exception
	 */

	public EleZonas consultarZonaPlancha(String nroCabIdentificacion) throws Exception
	{
		EleAsociadoDTO asociadoDTO =  DelegadoClimae.getInstance().find(nroCabIdentificacion);
		EleZonas elZona = new EleZonas();
		EleZonasFinanciero eleZonasFinanciero = new EleZonasFinanciero();

		boolean existAsesorFin = DelegadoLico.getInstance().existAsesorFin(nroCabIdentificacion);
		boolean existAsesorPla = DelegadoAsesor.getInstance().existAsesor(nroCabIdentificacion);//table="ELE_ASESORES" schema="ELECCION"
		boolean existAsesorMP = false;//DelegadoSalud.getInstance().existAsesor(nroCabIdentificacion);
		boolean existAsesorSrh = true;// DelegadoSrh.getInstance().existEmpleado(nroCabIdentificacion);

		boolean isAsesor = false;
		if (existAsesorSrh||existAsesorFin||existAsesorMP||existAsesorPla) {
			isAsesor = true;
		}
		if (isAsesor) {
			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
			elZona = DelegadoZona.getInstance().consultarZona(elZona.getZonEspecial());
		}else{
			eleZonasFinanciero = DelegadoZonaFinanciero.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
			elZona = DelegadoZona.getInstance().consultarZona(eleZonasFinanciero.getId().getCodZonaElec());
		}

		return elZona;
	}
	/**
	 * Consulta todas la zonas que estan en la tabla ELEZONAS
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return
	 * @throws Exception
	 */

	public List<EleZonas> consultarZonas() throws Exception{
		List<EleZonas> listZonas = new ArrayList<EleZonas>();

		Criteria crit = this.getSession().createCriteria(EleZonas.class);
		crit.addOrder(Order.asc("nomZona"));
		try {
			listZonas =  crit.list();
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}

		return listZonas;
	}
}
