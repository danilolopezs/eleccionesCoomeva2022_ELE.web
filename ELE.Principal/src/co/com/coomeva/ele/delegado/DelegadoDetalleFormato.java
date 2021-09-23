package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.logica.LogicaDetalleFormato;

public class DelegadoDetalleFormato {

	private static DelegadoDetalleFormato instance;
	private static LogicaDetalleFormato logicaDetalleFormato;

	private DelegadoDetalleFormato() {
	}

	public static DelegadoDetalleFormato getInstance() {
		if (instance == null) {
			instance = new DelegadoDetalleFormato();
			logicaDetalleFormato = new LogicaDetalleFormato();
		}
		return instance;
	}

	public void crearDetalleFormato(
			EleDetalleFormatoPlancha detalleFormatoPlancha) {
		logicaDetalleFormato.crearDetalleFormato(detalleFormatoPlancha);
	}

	public EleDetalleFormatoPlancha findById(EleDetalleFormatoPlanchaId id) {
		return logicaDetalleFormato.findById(id);
	}
}
