package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleInhabilidad;


/**
 * Interface for EleInhabilidadDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleInhabilidadDAO {
    public void save(EleInhabilidad instance);

    public void delete(EleInhabilidad instance);

    public void update(EleInhabilidad instance);

    public EleInhabilidad findById(Long id);

    public List<EleInhabilidad> findByExample(EleInhabilidad instance);

    public List<EleInhabilidad> findByProperty(String propertyName, Object value);

    public List<EleInhabilidad> findAll();

    public List<EleInhabilidad> findByCriteria(String whereCondition);

    public List<EleInhabilidad> findPageEleInhabilidad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleInhabilidad();

    public List<EleInhabilidad> findByConsecutivoInhabilidad(
        Object consecutivoInhabilidad);

    public List<EleInhabilidad> findByObservaciones(Object observaciones);
}
