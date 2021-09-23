package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.planchas.dao.EleLogAsociadoDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleLogAsociado;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

public class LogicaLogAsociado extends EleLogAsociadoDAO {

	private static LogicaLogAsociado instance;
	private String NO_REGISTRA = "NO REGISTRA";

	// Constructor de la clase
	private LogicaLogAsociado() {
	}

	// Patròn Singular
	public static LogicaLogAsociado getInstance() {
		if (instance == null) {
			instance = new LogicaLogAsociado();
		}
		return instance;
	}

	@SuppressWarnings("unused")
	public void crearRegistroLogAsociado(Integer id, String tipoTrn,
			String ipTrn, Timestamp fecha, String nroIdentificacion,
			String descripcion) throws Exception {

		if (id == null) {
			id = new Integer(GeneradorConsecutivos
			.getInstance().getConsecutivo(
					ConstantesNamedQueries.QUERY_SEQ_ELE_LOG_ASOCIADO, this.getSession()).toString());
		}
		if (ipTrn == null) {
			ipTrn = NO_REGISTRA;
		}
		if (tipoTrn == null) {
			tipoTrn = NO_REGISTRA;
		}
		if (fecha == null) {
			fecha = new Timestamp(new Date().getTime());
		}

		if (nroIdentificacion == null) {
			nroIdentificacion = NO_REGISTRA;
		}
		if (descripcion == null) {
			descripcion = NO_REGISTRA;
		}
		
		EleLogAsociado eleLogAsociado = new EleLogAsociado();
		eleLogAsociado.setId(id);
		eleLogAsociado.setTipoTrn(tipoTrn);
		eleLogAsociado.setIpTrn(ipTrn);
		eleLogAsociado.setFecha(fecha);
		eleLogAsociado.setNroIdentificacion(nroIdentificacion);
		eleLogAsociado.setDescripcion(descripcion);
		
		Transaction transaction = null;
		
		try 
		{
			transaction = this.getSession().beginTransaction();
			save(eleLogAsociado);
			this.getSession().flush();
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally{
			this.getSession().clear();
			this.getSession().close();
		}
	}

}
