package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;


/**
 * Interface for EleSuspendidoDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleSuspendidoDAO {
    public void save(EleSuspendido instance);

    public void delete(EleSuspendido instance);

    public void update(EleSuspendido instance);

    public EleSuspendido findById(Long id);

    public List<EleSuspendido> findByExample(EleSuspendido instance);

    public List<EleSuspendido> findByProperty(String propertyName, Object value);

    public List<EleSuspendido> findAll();

    public List<EleSuspendido> findByCriteria(String whereCondition);

    public List<EleSuspendido> findPageEleSuspendido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleSuspendido();

    public List<EleSuspendido> findByCodigoAsociado(Object codigoAsociado);

    public List<EleSuspendido> findByEstado(Object estado);

    public List<EleSuspendido> findByFechaRegistro(Object fechaRegistro);

    public List<EleSuspendido> findByMotivo(Object motivo);
}
