package co.com.coomeva.ele.entidades.juego.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.juego.EleParticipanteJuego;

public interface IEleParticipanteJuegoDAO {

	public void save(EleParticipanteJuego transientInstance);

	public void delete(EleParticipanteJuego persistentInstance);

	public EleParticipanteJuego findById(java.lang.Long id);

	public List findByExample(EleParticipanteJuego instance);

	public List findByProperty(String propertyName, Object value);

	public List findByNumeroDocumento(Object numeroDocumento);

	public List findByCodZonaAso(Object codZonaAso);

	public List findByFaseJuego(Object faseJuego);

	public List findAll();

	public EleParticipanteJuego merge(EleParticipanteJuego detachedInstance);

	public void attachDirty(EleParticipanteJuego instance);

	public void attachClean(EleParticipanteJuego instance);

}
