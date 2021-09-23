package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.delegado.DelegadoSie;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaAsociadoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaAsociado;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaProcesoMarcacion implements ILogicaProcesoMarcacion {
	
	public static final String COD_ESTADO_PLANCHA_ADMITIDA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_ADMITIDA);
	
	private static final String COD_VIOLACION_NO_CUMPLE_HORAS_SIE = "1";
	private static final String COD_VIOLACION_NO_FECHA_RETIRO_ASOCIADO_ESPECIAL = "2";
	private static final String COD_VIOLACION_NO_CUMPLE_HORAS_SIE_NO_FECHA_RETIRO_ASOCIADO_ESPECIAL = "3";
	
	private ElePlanchaDAO elePlanchaDAO; 
	private ElePlanchaAsociadoDAO elePlanchaAsociadoDAO;
	private LogicaPlancha iLogicaPlancha;
	
	public void marcarAsociadosConViolaciones(){
		Session session = null;
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			List<ElePlanchaAsociado> asociadosConViolaciones = seleccionarIntegrantesConViolaciones();
			elePlanchaAsociadoDAO = new ElePlanchaAsociadoDAO();
			
			session = HibernateSessionFactoryElecciones2012.getSession();
			for(ElePlanchaAsociado asoPlancha : asociadosConViolaciones){
				elePlanchaAsociadoDAO.merge(asoPlancha);
			}
			tr.commit();
		} catch (Exception e) {
			
			if(tr != null && tr.isActive()){
				tr.rollback();
			}
			
			throw new RuntimeException("Se presentó un error intentando registrar" +
					" los asociados con violaciones a las reglas de negocio. ",e);
		} finally{
			tr = null;
			session.close();
			session = null;
		}
	}
	
	public List<ElePlanchaAsociado> seleccionarIntegrantesConViolaciones() throws Exception{
		String[] estadosPlanchas = {COD_ESTADO_PLANCHA_ADMITIDA};
		iLogicaPlancha = new LogicaPlancha();

		List<ElePlancha> planchasAdmitidas =  obtenerPlanchasPorEstados(estadosPlanchas);
		List<ElePlanchaAsociado> miembrosPlanchasAdmitidas = null;
		List<ElePlanchaAsociado> miembrosConViolaciones = new ArrayList<ElePlanchaAsociado>();
		
		boolean noCumpleHorasSie = Boolean.FALSE;
		boolean noCumpleFechaRetiroAsociadoEspecial = Boolean.FALSE;
		
		elePlanchaAsociadoDAO = new ElePlanchaAsociadoDAO();
		for(ElePlancha laPlancha : planchasAdmitidas){
			miembrosPlanchasAdmitidas = elePlanchaAsociadoDAO.findByConsecutivoPlancha(laPlancha.getConsecutivoPlancha());
			for(ElePlanchaAsociado planchaAsociado : miembrosPlanchasAdmitidas){
				
				if(DelegadoSie.getInstance().validateHorasDemocracia(planchaAsociado.getEleAsociado().getNumeroDocumento().toString())){
					noCumpleHorasSie = Boolean.TRUE;
				}
				
				if(iLogicaPlancha.validarEmpleadoFechaRetiro(planchaAsociado.getEleAsociado().getNumeroDocumento()) != null){
					noCumpleFechaRetiroAsociadoEspecial = Boolean.TRUE;
				}
				
				if (noCumpleHorasSie || noCumpleFechaRetiroAsociadoEspecial) {
					planchaAsociado
							.setCodViolacion(noCumpleHorasSie
									&& noCumpleFechaRetiroAsociadoEspecial ? COD_VIOLACION_NO_CUMPLE_HORAS_SIE_NO_FECHA_RETIRO_ASOCIADO_ESPECIAL
									: noCumpleHorasSie ? COD_VIOLACION_NO_CUMPLE_HORAS_SIE
											: noCumpleFechaRetiroAsociadoEspecial ? COD_VIOLACION_NO_FECHA_RETIRO_ASOCIADO_ESPECIAL
													: null);
					miembrosConViolaciones.add(planchaAsociado);
				}
			}
		}
		
		return miembrosConViolaciones;
	}
	
	private List<ElePlancha> obtenerPlanchasPorEstados(String[] estados){
		try {
			elePlanchaDAO = new ElePlanchaDAO();
			return elePlanchaDAO.findPlanchasPorEstado(estados);
		} finally {
			elePlanchaDAO = null;
		}
	}
	
	public static void main(String[] args) {
		new LogicaProcesoMarcacion().marcarAsociadosConViolaciones();
	}

	public List<DTOAsociadoMarcado> generarReporteAsociadosMarcados() {
		List<DTOAsociadoMarcado> resultado = new ArrayList<DTOAsociadoMarcado>();
		Session session = null;
		DTOAsociadoMarcado dtoAsociado = null;

		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			Query query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_MARCACION_ASOCIADOS_ESCRUTINIOS);
			List<Object[]> data = query.list();
			
			for(Object[] datos : data){
				dtoAsociado = new DTOAsociadoMarcado();
				dtoAsociado.setCodigoZonaElectoral(datos[0] != null ? datos[0].toString() : null);
				dtoAsociado.setConsecutivoPlancha(datos[1] != null ? Long.parseLong(datos[1].toString()) : null);
				dtoAsociado.setNumeroZona(datos[0] != null ? datos[2].toString() : null);
				dtoAsociado.setNombreZonaElectoral(datos[3] != null ? datos[3].toString() : null);
				dtoAsociado.setNumeroPlancha(datos[4] != null ? Long.parseLong(datos[4].toString()) : null);
				dtoAsociado.setNumeroDocumento(datos[5] != null ? Long.parseLong(datos[5].toString()) : null);
				dtoAsociado.setNombreAsociado(datos[6] != null ? datos[6].toString() : null);
				dtoAsociado.setTipo(datos[7] != null ? datos[7].toString() : null);
				dtoAsociado.setPosicion(datos[8] != null ? Long.parseLong(datos[8].toString()) : null);
				dtoAsociado.setCabezaPlancha(datos[9] != null ? datos[9].toString() : null);
				dtoAsociado.setObservacionViolaciones(datos[10] != null ? datos[10].toString() : null);
				resultado.add(dtoAsociado);
			}
		} catch (Exception e) {
			throw new RuntimeException("No se logró generar el reporte de asociados marcados : "
					+ e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			dtoAsociado = null;
		}

		return resultado;
	}
}
