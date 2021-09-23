package co.com.coomeva.ele.logica;
import co.com.coomeva.ele.entidades.planchas.EleAsesores;
import co.com.coomeva.ele.entidades.planchas.EleAsesoresDAO;
import co.com.coomeva.util.acceso.UtilAcceso;


public class LogicaAsesor extends EleAsesoresDAO{

	private static LogicaAsesor instance;

	//Constructor de la clase
	private LogicaAsesor() {
	}

	//Patròn Singular
	public static LogicaAsesor getInstance() {
		if (instance == null) {
			instance = new LogicaAsesor();
		}
		return instance;
	}
/**
 * Metodo que verifica si el asociado ingresado hace 
 * se encuentra o no en la tabla ELEASESORES  
 * @param nroIdentificacion
 * @author Manuel Galvez y Ricardo Chiriboga
 * @return boolean
 * @throws Exception
 */
	public boolean existAsesor(String nroIdentificacion) throws Exception{
		EleAsesores eleAsesores = findById(nroIdentificacion);
		

		if (eleAsesores!= null) {
			if (!eleAsesores.getRetirado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "esAsesorRetirado"))) {
				return true;
			}else
				return false;
		}else
			return false;
		
	}


}
