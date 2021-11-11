package co.com.coomeva.ele.logica;

import co.com.coomeva.ele.entidades.planchas.EleSubcomision;
import co.com.coomeva.ele.entidades.planchas.EleSubcomisionDAO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaSubComision extends EleSubcomisionDAO {
	private static LogicaSubComision instance;

	//Constructor de la clase
	private LogicaSubComision() {
	}

	//Patròn Singular
	public static LogicaSubComision getInstance() {
		if (instance == null) {
			instance = new LogicaSubComision();
		}
		return instance;
	}
	
	/**
	 * Valida si existe el asociado en la subcomision
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @return boolean
	 * @throws Exception
	 */
	public boolean existSubcomision(String nroIdentificacion)throws Exception{
		if (nroIdentificacion == null || nroIdentificacion.equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		EleSubcomision subcomision = null;
		
		try {
			subcomision = findById(nroIdentificacion);
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}
		
		return subcomision != null;
	}

	public EleSubcomision consultarSubComision(String id)throws Exception {
		EleSubcomision eleSubcomision = null;
		try {
			eleSubcomision = findById(id);
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}
		
		return eleSubcomision;
	}
}
