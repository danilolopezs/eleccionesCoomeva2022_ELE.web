package co.com.coomeva.ele.delegado;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.planchas.ElePParametros;
import co.com.coomeva.ele.entidades.planchas.ElePValorParametros;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.logica.LogicaAsesor;
import co.com.coomeva.ele.logica.LogicaElePParametro;
import co.com.coomeva.ele.logica.LogicaElePValorParametro;
import co.com.coomeva.ele.modelo.EleAsesorLicoDTO;
import co.com.coomeva.ele.modelo.Parametro;

public class DelegadoParametros {

	private static DelegadoParametros instance;
	private static LogicaElePParametro logicaElePParametro;
	private static LogicaElePValorParametro logicaElePValorParametro;

	//Constructor de la clase
	private DelegadoParametros() {

	}

	//Patron Singular
	public static DelegadoParametros getInstance() {
		if (instance == null) {
			instance = new DelegadoParametros();
			logicaElePParametro = LogicaElePParametro.getInstance();
			logicaElePValorParametro = LogicaElePValorParametro.getInstance();
		}
		return instance;
	}

	public Parametro getParametroFuenteP(String origen, String nombre) {
		return logicaElePParametro.getParametroFuenteP(origen, nombre);
	}
	
	public Parametro getParametroFuenteP2012(String origen, String nombre) {
		return logicaElePParametro.getParametroFuenteP2012(origen, nombre);
	}

	public List<ElePValorParametros> listaParametros(Parametro parametro) {
		return logicaElePValorParametro.listaParametros(parametro);
	}

	
}
