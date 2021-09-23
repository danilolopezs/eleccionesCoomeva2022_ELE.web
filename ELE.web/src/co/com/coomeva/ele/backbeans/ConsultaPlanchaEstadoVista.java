package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.util.DataPage;
import co.com.coomeva.ele.util.DataSource;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PagedListDataModel;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;

import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.context.effects.JavascriptContext;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;


/**
 * 
 * @author Manuel Galvez, Ricardo Chiriboga
 *
 */
public class ConsultaPlanchaEstadoVista extends DataSource implements Renderable, DisposableBean
{
	
	
	private List<PlanchaPorEstadoDTO> planchas = new ArrayList<PlanchaPorEstadoDTO>();
	
	/** Variables del paginador **/
	private DataPaginator dataPaginator;
	private boolean renderDataTable = false;
    private boolean flag = true;
    private RenderManager renderManager;    
    private PersistentFacesState state = PersistentFacesState.getInstance();    
    protected String sortColumnName;
    protected boolean sortAscending;
    protected int pageSize = 40;
	
    
    private Long numCedula;
    
    private InfoPlanchaDTO infoPlancha = new InfoPlanchaDTO();
    private Long numPlancha;
    private boolean visibleConfirmar;
    
    private HtmlSelectOneMenu selEstado;
    private List<SelectItem> itemsEstados;
    private List<ParametroPlanchaDTO> estadosPlancha;
    private boolean muestraInfoPlanchas = false;
    
	public ConsultaPlanchaEstadoVista() {
		super("");
		//init();
	}
	
	public void limpiarCampos(){
		numCedula = null;
		muestraInfoPlanchas = false;
		numPlancha = null;
		onePageDataModel = null;
	}
	
	public void listener_link(ActionEvent ae){
		Long ced = new Long( FacesUtils.getRequestParameter("numCedula").toString());
		FacesUtils.getSession().setAttribute("numCedula", ced);
		FacesUtils.getSession().setAttribute("referido", 1);
		
	}
	
	public String action_verDetalles(){
		
		return "goConsultaCabezaPlancha";
	}
	
	public String action_generarReporte(){
		
		try {
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"ServletReportePlanchasEstado();");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
		return "";
	}
	
	public void action_find(){	
		
		onePageDataModel = null;
		UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
		
		try {
			 if(!selEstado.getValue().toString().equals("-1")){
				 	if (DelegadoPlanchas.getInstance().obtenerPlanchasPorEstado(new Long(selEstado.getValue().toString()), user.getUserId()).size() > 0){
				 		muestraInfoPlanchas = true;
				 		FacesUtils.getSession().setAttribute("estado", new Long(selEstado.getValue().toString()));
						getData();					
				 	} else {
				 		muestraInfoPlanchas = false;
				 		onePageDataModel = null;
				 	}
				
			 } else {
				 muestraInfoPlanchas = false;
			 	 onePageDataModel = null;
				 throw new Exception("Debe seleccionar un Estado");
			 }	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getMensaje().mostrarMensaje(e.getMessage());
		}
						
	}

	 public DataModel getData() {
	        state = PersistentFacesState.getInstance();

	        if (onePageDataModel == null) {
	            onePageDataModel = new LocalDataModelPlancha(pageSize);
	        }

	        return onePageDataModel;
	    }
	 
	 private DataPage<PlanchaPorEstadoDTO> getDataPageParam(int startRow, int pageSize) {
         // Retrieve the total number of objects from the database.  This
         // number is required by the DataPage object so the paginator will know
         // the relative location of the page data.
         int totalPlanchaEstado = 0;
         Long codEstado = new Long(selEstado.getValue().toString());
         UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");

         try {
        	 totalPlanchaEstado = DelegadoPlanchas.getInstance().obtenerPlanchasPorEstado(codEstado, user.getUserId()).size();
         } catch (Exception e) {
        	 //mensaje.mostrarMensaje(e.getMessage());
        	 e.printStackTrace();
         }

         // Calculate indices to be displayed in the ui.
         int endIndex = startRow + pageSize;

         if (endIndex > totalPlanchaEstado) {
             endIndex = totalPlanchaEstado;
         }

         try {
        	 
             planchas = DelegadoPlanchas.getInstance().obtenerPlanchasPorEstadoPag( sortColumnName,
                     sortAscending, startRow, endIndex - startRow, codEstado, user.getUserId());

             // Remove this Renderable from the existing render groups.
             //leaveRenderGroups();        			
         } catch (Exception e) {
        	 //mensaje.mostrarMensaje(e.getMessage()); 
        	 e.printStackTrace();
         }
         
         if (planchas != null && !planchas.isEmpty()) {
			muestraInfoPlanchas = true;
		 } 
				 
		 

         // Add this Renderable to the new render groups.
         //joinRenderGroups();
         // Reset the dirtyData flag.
         onePageDataModel.setDirtyData(false);
         // This is required when using Hibernate JPA.  If the EntityManager is not
         // cleared or closed objects are cached and stale objects will show up
         // in the table.
         // This way, the detached objects are reread from the database.
         // This call is not required with TopLink JPA, which uses a Query Hint
         // to clear the l2 cache in the DAO.
         //EntityManagerHelper.getEntityManager().clear();
         flag = true;

         return new DataPage<PlanchaPorEstadoDTO>(totalPlanchaEstado,
             startRow, planchas);
     }

	 
	 /**
	     * A special type of JSF DataModel to allow a datatable and datapaginator
	     * to page through a large set of data without having to hold the entire
	     * set of data in memory at once.
	     * Any time a managed bean wants to avoid holding an entire dataset,
	     * the managed bean declares this inner class which extends PagedListDataModel
	     * and implements the fetchData method. fetchData is called
	     * as needed when the table requires data that isn't available in the
	     * current data page held by this object.
	     * This requires the managed bean (and in general the business
	     * method that the managed bean uses) to provide the data wrapped in
	     * a DataPage object that provides info on the full size of the dataset.
	     */
	     private class LocalDataModelPlancha extends PagedListDataModel {
	         public LocalDataModelPlancha(int pageSize) {
	             super(pageSize);
	         }

	         public DataPage<PlanchaPorEstadoDTO> fetchPage(int startRow, int pageSize) {
	             // call enclosing managed bean method to fetch the data
	        	 return getDataPageParam(startRow, pageSize);
	        	
	         }
	     }
	     
	     
	     public void cargarEstadosPlanchas(){
	    	 
	    	 estadosPlancha = null;
	    	 itemsEstados = new ArrayList<SelectItem>();
	    	 
	    	 try {
				estadosPlancha = new ArrayList<ParametroPlanchaDTO>();
				estadosPlancha = DelegadoPlanchas.getInstance().obtenerParametrosTipo(1L);
				
				if(!(estadosPlancha == null || estadosPlancha.size() == 0)){
					
					if(estadosPlancha.size() > 1){
						itemsEstados.add(new SelectItem("-1", "- Seleccionar -"));
						
						for (ParametroPlanchaDTO param : estadosPlancha) {
							itemsEstados.add(new SelectItem(param.getValor(), param.getNombre()));
						}
					} else {
						ParametroPlanchaDTO param = (ParametroPlanchaDTO) estadosPlancha.get(0);
						itemsEstados.add(new SelectItem(param.getValor(), param.getNombre()));
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	     }
	   
		public DataPaginator getDataPaginator() {
			return dataPaginator;
		}

		public void setDataPaginator(DataPaginator dataPaginator) {
			this.dataPaginator = dataPaginator;
		}

		public boolean isRenderDataTable() {
			return renderDataTable;
		}

		public void setRenderDataTable(boolean renderDataTable) {
			this.renderDataTable = renderDataTable;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public RenderManager getRenderManager() {
			return renderManager;
		}

		public void setRenderManager(RenderManager renderManager) {
			this.renderManager = renderManager;
		}

		public PersistentFacesState getState() {
			return state;
		}

		public void setState(PersistentFacesState state) {
			this.state = state;
		}

		public String getSortColumnName() {
			return sortColumnName;
		}

		public void setSortColumnName(String sortColumnName) {
			this.sortColumnName = sortColumnName;
		}

		public boolean isSortAscending() {
			return sortAscending;
		}

		public void setSortAscending(boolean sortAscending) {
			this.sortAscending = sortAscending;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		@Override
		protected boolean isDefaultAscending(String sortColumn) {
			// TODO Auto-generated method stub
			return false;
		}

		public void renderingException(RenderingException arg0) {
			// TODO Auto-generated method stub
			
		}

		public void dispose() throws Exception {
			// TODO Auto-generated method stub
			
		}

		public Long getNumCedula() {
			return numCedula;
		}

		public void setNumCedula(Long numCedula) {
			this.numCedula = numCedula;
		}

		public InfoPlanchaDTO getInfoPlancha() {
			return infoPlancha;
		}

		public void setInfoPlancha(InfoPlanchaDTO infoPlancha) {
			this.infoPlancha = infoPlancha;
		}

		public boolean isMuestraInfoPlanchas() {
			return muestraInfoPlanchas;
		}

		public void setMuestraInfoPlanchas(boolean muestraInfoPlancha) {
			this.muestraInfoPlanchas = muestraInfoPlanchas;
		}

		public Long getNumPlancha() {
			return numPlancha;
		}

		public void setNumPlancha(Long numPlancha) {
			this.numPlancha = numPlancha;
		}

		public boolean isVisibleConfirmar() {
			return visibleConfirmar;
		}


		public void setVisibleConfirmar(boolean visibleConfirmar) {
			this.visibleConfirmar = visibleConfirmar;
		}

		public HtmlSelectOneMenu getSelEstado() {
			cargarEstadosPlanchas();
			return selEstado;
		}

		public void setSelEstado(HtmlSelectOneMenu selEstado) {
			this.selEstado = selEstado;
		}

		public List<SelectItem> getItemsEstados() {
			return itemsEstados;
		}

		public void setItemsEstados(List<SelectItem> itemsEstados) {
			this.itemsEstados = itemsEstados;
		}

		public List<ParametroPlanchaDTO> getEstadosPlancha() {
			return estadosPlancha;
		}

		public void setEstadosPlancha(List<ParametroPlanchaDTO> estadosPlancha) {
			this.estadosPlancha = estadosPlancha;
		}

		public List<PlanchaPorEstadoDTO> getPlanchas() {
			return planchas;
		}

		public void setPlanchas(List<PlanchaPorEstadoDTO> planchas) {
			this.planchas = planchas;
		}

		
}