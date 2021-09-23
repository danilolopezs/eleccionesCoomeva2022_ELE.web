package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleNovedad;


/**
 * Interface for EleNovedadDAO.
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public interface IEleNovedadDAO {
    public void save(EleNovedad instance);

    public void delete(EleNovedad instance);

    public void update(EleNovedad instance);

    public EleNovedad findById(Long id);

    public List<EleNovedad> findByExample(EleNovedad instance);

    public List<EleNovedad> findByProperty(String propertyName, Object value);

    public List<EleNovedad> findAll();

    public List<EleNovedad> findByCriteria(String whereCondition);

    public List<EleNovedad> findPageEleNovedad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberEleNovedad();

    public List<EleNovedad> findByConsecutivoNovedad(Object consecutivoNovedad);

    public List<EleNovedad> findByEstadoHabilidad(Object estadoHabilidad);

    public List<EleNovedad> findByFechaRegistro(Object fechaRegistro);
}
