package co.com.coomeva.ele.backbeans.selectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoFiltros;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * @author Oscar Javier Salazar Amaya Marzo 03 de 2011 Clase encargada de Crear
 *         las listas de selección usadas en el modelo administrativo.
 */
public class SelectFactoryView {

	private static SelectFactoryView instance;

	private SelectFactoryView() {
	}

	public static SelectFactoryView getInstance() {
		if (instance == null) {
			instance = new SelectFactoryView();
		}
		return instance;
	}

	public List<SelectItem> cargarOpcionDefecto(List<SelectItem> listSelectItem) throws Exception{
		
		if( listSelectItem != null ){
			
			String valueDefault = UtilAcceso.getParametroFuenteS("parametros","selectValueDefault");
			String labelDefault = UtilAcceso.getParametroFuenteS("parametros","selectLabelDefault");
			
			SelectItem selectItem = new SelectItem();
			selectItem.setValue(valueDefault);
			selectItem.setLabel(labelDefault);
			listSelectItem.add(selectItem);
		}
		return listSelectItem;
	}
	
	/**
	 * Construye una lista con datos de Zonas
	 * 
	 * @param propertyName
	 * @param value
	 * @return List<SelectItem> Listado de Select Item
	 */
	public List<SelectItem> listSelectItem_Zonas(Long codRegional) {

		List<SelectItem> list_zonas = null;
		List<FiltrosConsultasDTO> listadoZonas = null;
		
		try {
			list_zonas = new ArrayList<SelectItem>();
			listadoZonas = DelegadoFiltros.getInstance().consultarZonasPorRegional(codRegional);
			
			SelectItem selectItem;
			list_zonas = cargarOpcionDefecto(list_zonas);
			
			for (FiltrosConsultasDTO objeto : listadoZonas) {
				selectItem = new SelectItem();
				selectItem.setLabel(objeto.getDescripcionFiltro().toUpperCase());
				selectItem.setValue(new Long(objeto.getCodigoFiltro().toString()));
				list_zonas.add(selectItem);
			}
			
		} catch (Exception e) {
			list_zonas = null;
			listadoZonas = null;
		}
		
		return list_zonas;
	}

	/**
	 * Construye una lista con datos de Regionales
	 * 
	 * @param propertyName
	 * @param value
	 * @return List<SelectItem> Listado de Select Item
	 */
	public List<SelectItem> listSelectItem_Regionales() {

		List<SelectItem> list_regionales = null;
		List<FiltrosConsultasDTO> listadoRegionales = null;
		
		try {
			list_regionales = new ArrayList<SelectItem>();
			listadoRegionales = DelegadoFiltros.getInstance().consultarRegionales();
			
			SelectItem selectItem;
			list_regionales = cargarOpcionDefecto(list_regionales);
			
			for (FiltrosConsultasDTO objeto : listadoRegionales) {
				selectItem = new SelectItem();
				selectItem.setLabel(objeto.getDescripcionFiltro().toUpperCase());
				selectItem.setValue(new Long(objeto.getCodigoFiltro().toString()));
				list_regionales.add(selectItem);
			}
			
		} catch (Exception e) {
			list_regionales = null;
			listadoRegionales = null;
		}
		
		return list_regionales;
	}
	
	public List<SelectItem> cargarOpcionOrdenamiento(List<SelectItem> listSelectItem){
		
		if( listSelectItem == null ){
			listSelectItem = new ArrayList<SelectItem>();
		}
			
			String valueDefault = UtilAcceso.getParametroFuenteS("parametros","selectValueDefault");
			String labelDefault = UtilAcceso.getParametroFuenteS("parametros","selectLabelDefault");
			
			SelectItem selectItem = new SelectItem();
			selectItem.setValue(valueDefault);
			selectItem.setLabel(labelDefault);
			listSelectItem.add(selectItem);
			
			valueDefault = UtilAcceso.getParametroFuenteS("parametros","campo.ordenamiento.delegadosVal");
			labelDefault = UtilAcceso.getParametroFuenteS("parametros","campo.ordenamiento.delegadosLbl");
			
			selectItem = new SelectItem();
			selectItem.setValue(valueDefault);
			selectItem.setLabel(labelDefault);
			listSelectItem.add(selectItem);
			
			valueDefault = UtilAcceso.getParametroFuenteS("parametros","campo.ordenamiento.residuoVal");
			labelDefault = UtilAcceso.getParametroFuenteS("parametros","campo.ordenamiento.residuoLbl");
			
			selectItem = new SelectItem();
			selectItem.setValue(valueDefault);
			selectItem.setLabel(labelDefault);
			listSelectItem.add(selectItem);
		
		return listSelectItem;
	}
}
