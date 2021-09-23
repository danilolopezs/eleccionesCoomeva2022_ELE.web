package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;


/**
 * Interface for EleAsociadoDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleAsociadoDAO {
    public void save(EleAsociado instance);

    public void delete(EleAsociado instance);

    public void update(EleAsociado instance);

    public EleAsociado findById(Long id);

    public List<EleAsociado> findByExample(EleAsociado instance);

    public List<EleAsociado> findByProperty(String propertyName, Object value);

    public List<EleAsociado> findAll();

    public List<EleAsociado> findByCriteria(String whereCondition);

    public List<EleAsociado> findPageEleAsociado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleAsociado();

    public List<EleAsociado> findByApellido1(Object apellido1);

    public List<EleAsociado> findByApellido2(Object apellido2);

    public List<EleAsociado> findByCodCiudadAsociado(Object codCiudadAsociado);

    public List<EleAsociado> findByCodEstratoSocial(Object codEstratoSocial);

    public List<EleAsociado> findByCodZonaElectAso(Object codZonaElectAso);

    public List<EleAsociado> findByCodigoAsociado(Object codigoAsociado);

    public List<EleAsociado> findByDescCiudadAsociado(Object descCiudadAsociado);

    public List<EleAsociado> findByDescEstratoSocial(Object descEstratoSocial);

    public List<EleAsociado> findByDescZonaElectAso(Object descZonaElectAso);

    public List<EleAsociado> findByDireccionCorrespondencia(
        Object direccionCorrespondencia);

    public List<EleAsociado> findByEmail(Object email);

    public List<EleAsociado> findByEstadoAsociado(Object estadoAsociado);

    public List<EleAsociado> findByFechaNacimiento(Object fechaNacimiento);

    public List<EleAsociado> findByFechaVinculacion(Object fechaVinculacion);

    public List<EleAsociado> findByGeneroAsociado(Object generoAsociado);

    public List<EleAsociado> findByNombre1(Object nombre1);

    public List<EleAsociado> findByNombre2(Object nombre2);

    public List<EleAsociado> findByNumeroDocumento(Object numeroDocumento);

    public List<EleAsociado> findByProfesion(Object profesion);

    public List<EleAsociado> findByTelefonoCelular(Object telefonoCelular);

    public List<EleAsociado> findByTelefonoFijo(Object telefonoFijo);

    public List<EleAsociado> findByTipoDocumento(Object tipoDocumento);
}
