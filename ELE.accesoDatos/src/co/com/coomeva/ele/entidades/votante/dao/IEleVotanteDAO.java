package co.com.coomeva.ele.entidades.votante.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.juego.EleParticipanteJuego;
import co.com.coomeva.ele.entidades.votante.EleVotante;

public interface IEleVotanteDAO {

	public void save(EleVotante transientInstance);
	
	public void delete(EleVotante persistentInstance);

	public EleVotante findById(java.lang.Long id);

	public List findByExample(EleVotante instance);

	public List findByProperty(String propertyName, Object value);

	public List findByDocumentoVotante(Object documentoVotante);

	public List findByUsuarioRegistro(Object usuarioRegistro);

	public List findByZonaUsuarioRegistro(Object zonaUsuarioRegistro);
	
	public List findByAgenciaUsuarioRegistro(Object agenciaUsuarioRegistro);
	
	public List findByIpRegistro(Object ipRegistro);

	public List findAll();

	public EleVotante merge(EleVotante detachedInstance);

	public void attachDirty(EleVotante instance);

	public void attachClean(EleVotante instance);

}
