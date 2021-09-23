package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleRegla;


/**
 * Interface for EleReglaDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleReglaDAO {
    public void save(EleRegla instance);

    public void delete(EleRegla instance);

    public void update(EleRegla instance);

    public EleRegla findById(Long id);

    public List<EleRegla> findByExample(EleRegla instance);

    public List<EleRegla> findByProperty(String propertyName, Object value);

    public List<EleRegla> findAll();

    public List<EleRegla> findByCriteria(String whereCondition);

    public List<EleRegla> findPageEleRegla(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleRegla();

    public List<EleRegla> findByCodigoRegla(Object codigoRegla);

    public List<EleRegla> findByDescripcionRegla(Object descripcionRegla);

    public List<EleRegla> findByEstadoRegla(Object estadoRegla);
}
