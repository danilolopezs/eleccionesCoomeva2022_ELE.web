package co.com.coomeva.habilidad.reglas;

import java.lang.reflect.Constructor;

import co.com.coomeva.ele.entidades.habilidad.EleRegla;
import co.com.coomeva.ele.entidades.habilidad.dao.EleReglaDAO;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class FabricaRegla {
	
	private static EleReglaDAO eleReglaDAO;
	
	public static ReglaHabAsociado construirRegla(Long codRegla)
			throws AssociateAbilityException {

		Class classRegla = null;
		Constructor consRegla = null;
		
		Class classDatasource = null;
		Constructor consDatasource = null;
		
		ReglaHabAsociado regla = null;
		IDataSourceReglaHabAsociado dataSourceRegla = null;
		
		eleReglaDAO = new EleReglaDAO();

		if (codRegla == null) {
			throw new AssociateAbilityException(
					"Se ha presentado un error intentando crear"
							+ " la nueva regla dado que el código de la regla es nulo");
		}

		EleRegla eleRegla = eleReglaDAO.findById(codRegla);

		if (codRegla == null) {
			throw new AssociateAbilityException(
					"No se halló una regla con el código " + codRegla);
		}

		if (eleRegla.getNombreReglaJava() != null
				&& !eleRegla.getNombreReglaJava().isEmpty()) {

			try {

				String nombreClaseDatasource = LoaderResourceElements
						.getInstance().getKeyResourceValue(
								Constantes.NOMBRE_ARCHIVO_PARAMETROS,
								eleRegla.getNombreReglaJava());

				if (nombreClaseDatasource == null
						|| nombreClaseDatasource.isEmpty()) {
					throw new AssociateAbilityException(
							"El nombre de clase datasource para la" + " regla "
									+ eleRegla.getNombreReglaJava()
									+ " no existe parametrizado");
				}
				
				classDatasource = Class.forName(nombreClaseDatasource);
				consDatasource = classDatasource.getConstructors()[0];
				dataSourceRegla = (IDataSourceReglaHabAsociado) consDatasource.newInstance();
				
				classRegla = Class.forName(eleRegla.getNombreReglaJava());
				consRegla = classRegla.getConstructors()[0];
				regla = (ReglaHabAsociado) consRegla.newInstance(dataSourceRegla);
			} catch (Exception e) {
				throw new AssociateAbilityException(
						"Se ha presentado un error al "
								+ "intentar instanciar la regla "
								+ eleRegla.getNombreReglaJava() + ". "
								+ e.getMessage());
			}
		}

		return regla;
	}
}
