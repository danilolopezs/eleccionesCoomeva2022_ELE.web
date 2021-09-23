package co.com.coomeva.ele.logica;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.votante.EleVotante;
import co.com.coomeva.ele.entidades.votante.dao.EleVotanteDAO;
import co.com.coomeva.ele.modelo.EleUsuarioComisionDTO;
import co.com.coomeva.ele.modelo.EleVotanteDatosDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaVotante extends EleVotanteDAO {

	private static LogicaVotante instance;

	/**
	 * Patrón Singleton
	 * 
	 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
	 * 
	 */
	public static LogicaVotante getInstance() {
		if (instance == null) {
			instance = new LogicaVotante();
		}
		return instance;
	}

	private Session getSession() {
		return HibernateSessionFactoryElecciones2012.getSession();
	}

	/**
	 * Método que verifica el juego de un participante y de acuerdo a las reglas
	 * del negocio retorna un número como respuesta
	 * 
	 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
	 * @param numeroDocumento
	 * @param fase
	 * @return String
	 * @throws Exception
	 */

	public EleVotanteDatosDTO validarVotante(Long documentoVotante,
			String zonaUsuarioRegistro) throws Exception {
		if (documentoVotante == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
					"noNumIdentificacion"));
		}
		EleVotanteDatosDTO dtoVotanteDatosDTO = new EleVotanteDatosDTO();

		// Validación de la habilidad del votante:
		DTOHabilidadAsociado dtoHabilidad = LogicaAsociado.getInstance()
				.consultarHabilidadAsociado(documentoVotante);
		// Se valida si el usuario no está habilitado:
		if (dtoHabilidad.getAsociadoHabil() == Boolean.FALSE) {
			dtoVotanteDatosDTO
					.setRespuesta(ConstantesProperties.PAR_MENSAJE_VOTANTE_INHAHABILITADO);
			return dtoVotanteDatosDTO;
		}

		// Se consultan los datos del votante:
		dtoVotanteDatosDTO = this.consultarDatosVotante(documentoVotante);
		
		String zonaVotante=dtoVotanteDatosDTO.getCodZona();
		
		// Se valida si la zona del votante es la misma del lugar de votación:
		if (!zonaVotante.isEmpty() && !zonaUsuarioRegistro.equals(zonaVotante)) {
			dtoVotanteDatosDTO
					.setRespuesta(ConstantesProperties.PAR_MENSAJE_VOTANTE_NO_PERTENECE_A_ZONA_VOTACION);
			return dtoVotanteDatosDTO;
		}
		// Se valida si el votante no ha registrado votación:
		EleVotanteDatosDTO dtoVotanteDatosDTO2=this.verificarVotacionPrimeraVez(documentoVotante);
		if (dtoVotanteDatosDTO2!=null) {
			dtoVotanteDatosDTO2
					.setRespuesta(ConstantesProperties.PAR_MENSAJE_VOTANTE_YA_REGISTRA_VOTACION);
			return dtoVotanteDatosDTO2;

		}
		dtoVotanteDatosDTO
				.setRespuesta(ConstantesProperties.PAR_MENSAJE_VOTANTE_HABILITADO_PARA_VOTAR);
		return dtoVotanteDatosDTO;
	}

	public void registrarVotante(Long documentoVotante, String usuarioRegistro,
			String zonaUsuarioRegistro, Integer agenciaUsuarioRegistro,
			String ipRegistro) throws Exception {

		if (documentoVotante == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
					"noNumIdentificacion"));
		}
		EleVotanteDatosDTO dto = this.validarVotante(documentoVotante,
				zonaUsuarioRegistro);
		String respuesta = dto.getRespuesta();
		if (!respuesta
				.equals(ConstantesProperties.PAR_MENSAJE_VOTANTE_HABILITADO_PARA_VOTAR)) {
			throw new Exception(
					"No se puede registrar el votante: no tiene habilidad, no pertenece a la zona de votación actual o ya registra votación");
		}

		java.util.Date utilDate = new java.util.Date(); // fecha actual

		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp fechaRegistro = new java.sql.Timestamp(
				lnMilisegundos);// tiempo
		// actual
		EleVotante instancia = new EleVotante();
		instancia.setAgenciaUsuarioRegistro(agenciaUsuarioRegistro);
		instancia.setDocumentoVotante(documentoVotante);
		instancia.setFechaRegistro(fechaRegistro);
		instancia.setIpRegistro(ipRegistro);
		instancia.setUsuarioRegistro(usuarioRegistro);
		instancia.setZonaUsuarioRegistro(zonaUsuarioRegistro);
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			this.save(instancia);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	private EleVotanteDatosDTO verificarVotacionPrimeraVez(Long documentoVotante)
			throws Exception {
		List<Object[]> objectList = null;
		Session session = getSession();
		EleVotanteDatosDTO dto =null;
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_VOTANTE_PRIMERA_VEZ);
			queryObject.setLong("numeroDocumento", documentoVotante);
			objectList = queryObject.list();
			if (objectList != null && !objectList.isEmpty()) {
				dto =new EleVotanteDatosDTO();
				for (int i = 0; i < objectList.size(); i++) {
					Object[] element = (Object[]) objectList.get(i);
					dto.setDocumento(String.valueOf((Long) element[0]));					
					dto.setNombres((String) element[1]);
					dto.setCodZona((String) element[2]);
					dto.setDesZona((String) element[3]);
					dto.setAgencia((String) element[4]+"-"+(String) element[5]);
				}
			}

		} catch (Exception e) {
			System.out
					.println("No se logró consultar la votación de primera vez"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return dto;
	}

	private EleVotanteDatosDTO consultarDatosVotante(Long documentoVotante) throws Exception {

		List<Object[]> objectList = null;
		EleVotanteDatosDTO dto =new EleVotanteDatosDTO();
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_DATOS_VOTANTE);
			queryObject.setLong("numeroDocumento", documentoVotante);
			objectList = queryObject.list();

			if (objectList != null && !objectList.isEmpty()) {
				for (int i = 0; i < objectList.size(); i++) {
					Object[] element = (Object[]) objectList.get(i);
					dto.setDocumento(String.valueOf((Long) element[0]));
					dto.setNombres((String) element[1]);
					dto.setCodZona((String) element[2]);
					dto.setDesZona((String) element[3]);
				}
			}
		} catch (Exception e) {
			System.out
					.println("No se logró consultar los datos del votante"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return dto;
	}

	public EleUsuarioComisionDTO consultarUsuarioComision(String usuarioRegistro)
			throws Exception {

		List<Object[]> objectList = null;
		EleUsuarioComisionDTO dto = null;
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_VOTANTE_USUARIO_QUE_REGISTRA);
			queryObject.setString("idUsuario", usuarioRegistro);
			objectList = queryObject.list();

			if (objectList != null && !objectList.isEmpty()) {
				dto = new EleUsuarioComisionDTO();
				for (int i = 0; i < objectList.size(); i++) {
					Object[] element = (Object[]) objectList.get(i);
					dto.setIdUsuario((String) element[0]);
					dto.setCodigoZonaEle((String) element[1]);
					dto.setDesZonaEle((String) element[2]);
					dto.setCodAgenciaUsuario((Integer) element[3]);
					dto.setDesAgenciaUsuario((String) element[4]);
				}
			}
		} catch (Exception e) {
			System.out
					.println("No se logró consultar los datos del usuario de la comisión"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return dto;
	}

}