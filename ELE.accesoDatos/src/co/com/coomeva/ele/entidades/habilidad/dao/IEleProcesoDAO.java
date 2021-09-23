package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleProceso;


/**
 * Interface for EleProcesoDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleProcesoDAO {
    public void save(EleProceso instance);

    public void delete(EleProceso instance);

    public void update(EleProceso instance);

    public EleProceso findById(Long id);

    public List<EleProceso> findByExample(EleProceso instance);

    public List<EleProceso> findByProperty(String propertyName, Object value);

    public List<EleProceso> findAll();

    public List<EleProceso> findByCriteria(String whereCondition);

    public List<EleProceso> findPageEleProceso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleProceso();

    public List<EleProceso> findByCodigoProceso(Object codigoProceso);

    public List<EleProceso> findByEstadoProceso(Object estadoProceso);

    public List<EleProceso> findByFechaProgramacion(Object fechaProgramacion);
}
