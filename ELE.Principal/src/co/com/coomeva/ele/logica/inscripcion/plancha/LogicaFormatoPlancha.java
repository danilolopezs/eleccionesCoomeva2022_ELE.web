package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleDetalleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleFormatoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class LogicaFormatoPlancha implements ILogicaFormatoPlancha {

	private final static Long CODIGO_FORMATO_RESOLUCION_RECHAZO = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.resolucion.rechazo"));

	private final static Long CODIGO_TIPO_PARAMETRO_FECHAS_LIMITE_GENERACION_RECHAZO = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.tipo.parametro.fecha.limite.generacion.resolucion.rechazo"));

	public static final String COD_ESTADO_PLANCHA_ADMITIDA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_ADMITIDA);

	private EleDetalleFormatoPlanchaDAO dao = new EleDetalleFormatoPlanchaDAO();
	private EleFormatoPlanchaDAO daoFormatoPlancha = new EleFormatoPlanchaDAO();

	@Override
	public InfoDetalleFormatoPlanchaDTO guardarInformacionResolucionRechazo(InfoDetalleFormatoPlanchaDTO dto,
			String userId) throws EleccionesDelegadosException {

		Transaction tr = null;
		Session session = null;
		try {

			Date dateToday = new Date();

			ParametroPlanchaDTO parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(19L,
					CODIGO_TIPO_PARAMETRO_FECHAS_LIMITE_GENERACION_RECHAZO);
			ParametroPlanchaDTO parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(20L,
					CODIGO_TIPO_PARAMETRO_FECHAS_LIMITE_GENERACION_RECHAZO);

			Date dateFechaIniInscrpcion = ManipulacionFechas.stringToDate(parametroFechaInicial.getStrValor(),
					"yyyy-MM-dd hh:mm:ss");
			Date dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
					"yyyy-MM-dd hh:mm:ss");

			SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm");

			if ((dateToday.getTime() < dateFechaIniInscrpcion.getTime())
					|| (dateToday.getTime() > dateFechaFinInscrpcion.getTime())) {
				throw new EleccionesDelegadosException(
						"Está intentando generar una resolución de rechazo en una fecha no permitida.  "
								+ "Recuerde que solo se pueden generar estas entre el "
								+ formatterFecha.format(dateFechaIniInscrpcion) + " y el "
								+ formatterFecha.format(dateFechaFinInscrpcion));
			}

			if (dto.getFechaResolucion() == null) {
				throw new EleccionesDelegadosException("Por favor ingrese la fecha de resolución");
			}

			if (dto.getNumeroResolucion() == null) {
				throw new EleccionesDelegadosException("Por favor ingrese el número de resolución");
			}

			if (dto.getNumeroActa() == null) {
				throw new EleccionesDelegadosException("Por favor ingrese el número de acta");
			}

			if (dto.getFechaActa() == null) {
				throw new EleccionesDelegadosException("Por favor ingrese la fecha de acta");
			}

			if (dto.getRazon() == null || "".equals(dto.getRazon())) {
				throw new EleccionesDelegadosException("Por favor ingrese la razón");
			}

			InfoPlanchaDTO infoPlancha = LogicaPlanchas.getInstance().obtenerInfoPlancha(dto.getNumeroDocumento(),
					userId);

			if (COD_ESTADO_PLANCHA_ADMITIDA.equals(infoPlancha.getCodigoEstadoPlancha())) {
				throw new EleccionesDelegadosException(
						"No se permite rechazar planchas que se encuentran " + " admitidas");
			}

			ElePlancha laPlancha = new ElePlanchaDAO().findById(infoPlancha.getConsecutivoPlancha());

			dto.setConsecutivoPlancha(laPlancha.getConsecutivoPlancha());
			dto.setNumeroComisionElectoral(laPlancha.getEleZonaElectoral().getCodigoZonaEle());
			dto.setNombresApellidosAsociadoCabeza(
					LogicaAsociado.getInstance().consultarNombreAsociadoBuc(dto.getNumeroDocumento()));
			dto.setNombresApellidosAsociadoInscribe(
					LogicaAsociado.getInstance().consultarNombreAsociadoBuc(laPlancha.getCodigoUsuario()));
			dto.setCedulaAsociadoInscribe(laPlancha.getCodigoUsuario());

			session = HibernateSessionFactoryElecciones2012.getSession();

			Long codigoAsociado = LogicaAsociado.getInstance()
					.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());

			EleDetalleFormatoPlancha entity = new EleDetalleFormatoPlancha();

			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
			id.setCodigoAsociado(codigoAsociado);
			id.setCodigoFormato(CODIGO_FORMATO_RESOLUCION_RECHAZO.byteValue());

			entity.setId(id);

			entity.setFechaResolucion(dto.getFechaResolucion());
			entity.setNumeroResolucion(dto.getNumeroResolucion());
			entity.setNumeroActa(dto.getNumeroActa());
//			entity.setFechaActa(new Timestamp(dto.getFechaActa().getTime()));
			entity.setRazonRechazo(dto.getRazon());

			tr = session.beginTransaction();
			if (dao.findById(id) != null) {
				dao.merge(entity);
			} else {
				dao.save(entity);
			}
			tr.commit();
			return dto;
		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}
			throw new EleccionesDelegadosException(e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			tr = null;
		}
	}

	public void registrarFormatoPlancha(String usuario, Long codigoFormato, Long consecutivoPlancha) throws Exception {

		EleFormatoPlancha formatoPlancha = new EleFormatoPlancha();

		EleFormato formato = new EleFormatoDAO().findById(codigoFormato.byteValue());
		formatoPlancha.setEleFormato(formato);

		ElePlancha plancha = new ElePlanchaDAO().findById(consecutivoPlancha);
		formatoPlancha.setElePlancha(plancha);

		formatoPlancha.setFechaImpresion(new Timestamp(new Date().getTime()));
		formatoPlancha.setFechaRegistro(new Timestamp(new Date().getTime()));
		formatoPlancha.setUsuarioGenera(usuario);
		formatoPlancha.setUsuarioImpresion(usuario);
		guardarFormatoPlancha(formatoPlancha);
	}

	/**
	 * Metodo que guarda registro en ele_formato_plancha
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 2/12/2012
	 * @param formatoPlancha
	 * @return
	 * @throws Exception
	 */
	public void guardarFormatoPlancha(EleFormatoPlancha formatoPlancha) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			formatoPlancha.setConsecutivo(obtenerConsecutivoFormatoPlancha());
			daoFormatoPlancha.save(formatoPlancha);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public Long obtenerConsecutivoFormatoPlancha() throws Exception {
		Long consecutivo = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			consecutivo = GeneradorConsecutivos.getInstance().getConsecutivo("obtener.consecutivo.formato.plancha",
					session);
		} catch (Exception e) {
			throw e;
		}
		return consecutivo;
	}

}
