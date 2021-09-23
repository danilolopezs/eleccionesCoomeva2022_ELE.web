package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;


/**
 * Interface for EleProcesoReglaDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleProcesoReglaDAO {
    public void save(EleProcesoRegla instance);

    public void delete(EleProcesoRegla instance);

    public void update(EleProcesoRegla instance);

    public EleProcesoRegla findById(Long id);

    public List<EleProcesoRegla> findByExample(EleProcesoRegla instance);

    public List<EleProcesoRegla> findByProperty(String propertyName,
        Object value);

    public List<EleProcesoRegla> findAll();

    public List<EleProcesoRegla> findByCriteria(String whereCondition);

    public List<EleProcesoRegla> findPageEleProcesoRegla(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults);

    public Long findTotalNumberEleProcesoRegla();

    public List<EleProcesoRegla> findByConsecutivoProRegla(
        Object consecutivoProRegla);

    public List<EleProcesoRegla> findByEstadoProgramacion(
        Object estadoProgramacion);

    public List<EleProcesoRegla> findByFechaFinEjecucion(
        Object fechaFinEjecucion);

    public List<EleProcesoRegla> findByFechaInicioEjecucion(
        Object fechaInicioEjecucion);

    public List<EleProcesoRegla> findByUsuarioRegistra(Object usuarioRegistra);
}
