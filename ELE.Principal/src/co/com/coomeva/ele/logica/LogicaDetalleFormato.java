package co.com.coomeva.ele.logica;

import co.com.coomeva.ele.entidades.planchas.dao.EleDetalleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;

public class LogicaDetalleFormato {

	private EleDetalleFormatoPlanchaDAO dao;

	public LogicaDetalleFormato() {
		dao = new EleDetalleFormatoPlanchaDAO();
	}

	public void crearDetalleFormato(
			EleDetalleFormatoPlancha detalleFormatoPlancha) {
		dao.save(detalleFormatoPlancha);
	}

	public EleDetalleFormatoPlancha findById(EleDetalleFormatoPlanchaId id) {
		return dao.findById(id);
	}
}
