package co.com.coomeva.ele.backbeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoAuditoriaExcepcion;
import co.com.coomeva.ele.delegado.DelegadoEleAsocSancionado;
import co.com.coomeva.ele.delegado.DelegadoOtrasEntElect;
import co.com.coomeva.ele.delegado.DelegadoSuspendido;
import co.com.coomeva.ele.delegado.DelegadoSuspendidoAUD;
import co.com.coomeva.ele.dto.InputFileDataDTO;
import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocSancionados5Annos;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAuditoriaExcepcion;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.EleAsocSancionados5AnnosDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;
import co.com.coomeva.ele.util.DataPage;
import co.com.coomeva.ele.util.DataSource;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PagedListDataModel;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;


/**
 * 
 * @author Mario Alejandro Acevedo
 *
 */
public class CargarArchivoSancionadosVista extends DataSource implements Renderable, DisposableBean
{
	
	
	private InputFileDataDTO currentFile;
	private int fileProgress;
	private UserVo user;
	private List<EleAsocSancionados5AnnosDTO> sancionados = new ArrayList<EleAsocSancionados5AnnosDTO>();
	
	/** Variables del paginador **/
	private DataPaginator dataPaginator;
	private boolean renderDataTable = false;
    private boolean flag = true;
    private RenderManager renderManager;    
    private PersistentFacesState state = PersistentFacesState.getInstance();    
    protected String sortColumnName;
    protected boolean sortAscending;
    protected int pageSize = 10;
    
    // Mensaje de error para la carga no exitosa
    // Modificado Juan Diego Montoya
    private boolean mostrarPopupErrorCargaArchivo;
	private String mensajePopupErrorCargaArchivo;
	
    
	public CargarArchivoSancionadosVista() {
		super("");
		init();
	}
	
	private void init(){
		user = (UserVo)FacesUtils.getSessionParameter("user");
	}
	
	

	private List<InputFileDataDTO> fileList = null;
	
	public void actionUploadFile(ActionEvent event) {
		InputFile inputFile = (InputFile) event.getSource();
		FileInfo fileInfo = inputFile.getFileInfo();
		if (!fileInfo.getFileName().toString().toLowerCase().endsWith(".txt")) 
		{
			this.mostrarPopupErrorCargaArchivo = Boolean.TRUE;
			this.mensajePopupErrorCargaArchivo = UtilAcceso.getParametroFuenteS("mensajes",
			"msgErrorExtensionArchivo");
		}
		else
		{
			if (fileInfo.getStatus() == FileInfo.SAVED) {
				this.currentFile = new InputFileDataDTO(fileInfo);
				
				// Antes de usar cualquier cosa limpiamos los componentes para realizar los Uploads
				this.fileList = Collections.synchronizedList(new ArrayList<InputFileDataDTO>());
				onePageDataModel = null;
				renderDataTable = false;
				procesarArchivo();
			}
		}
	}
	
	/**Procesa el archivo seleciconado para generar registros en ELE_ASOC_SANCIONADOS_5_ANNOS
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */
	private void procesarArchivo() {

		try {
			
			String linea = "";
			BufferedReader reader = new BufferedReader(new FileReader(this.currentFile.getFile()));
			//boolean existenOtrasEntidades = false;
			List<EleAsocSancionados5Annos> sancionadosAnnos = new ArrayList<EleAsocSancionados5Annos>();
			
			//consultar si existen registros en EleAsocSancionados5Annos
			sancionadosAnnos = DelegadoEleAsocSancionado.getInstance().obtenerSancionados();
			
			if (sancionadosAnnos.size() > 0){
				
				for (EleAsocSancionados5Annos sancionado : sancionadosAnnos) {
				
					//copiar los registro encontrados a la tabla ele_auditoria_excepcion
					DelegadoAuditoriaExcepcion.getInstance().crearAuditoriaExcepcion(sancionado.getCodigoAsociado(), sancionado.getUsuario(), sancionado.getFechaSuspension(), sancionado.getMotivo(), "S", new Date());
				}
								
				//borrar el contenido de la tabla EleAsocSancionados5Annos
				DelegadoEleAsocSancionado.getInstance().eliminarSancionados();
				
				// se insertan los registros del archivo
				while ((linea = reader.readLine()) != null) {
					
					//se obtienen los tokens y se arma y se inserta
					//System.out.println("linea -> "+linea);
					String[] tokens = linea.split("\\|", -1);
					
					Long documento = new Long(tokens[0]);
					Date fechaSuspension = new Date(formateaStringFecha(tokens[1].toString()));
					String motivo = tokens[2].toString();
					
										
					Long codAso = null;
					//con el documento se obtiene el codigo del asociado
					codAso = DelegadoOtrasEntElect.getInstance().obtieneNumintAsociado(documento);
					
					//se crea registro en EleAsocSancionados5Annos					
					DelegadoEleAsocSancionado.getInstance().crearSancionados(codAso, new Date(), user.getUserId(), fechaSuspension, motivo);
				}
				
				getData();
				renderDataTable = true;
				
			} else {
				
				while ((linea = reader.readLine()) != null) {
					
					//se obtienen los tokens y se arma y se inserta
					//System.out.println("linea -> "+linea);
					String[] tokens = linea.split("\\|", -1);
					
					Long documento = new Long(tokens[0]);
					Date fechaSuspension = new Date(formateaStringFecha(tokens[1].toString()));
					String motivo = tokens[2].toString();	
					
					
					
					Long codAso = null;
					//con el documento se obtiene el codigo del asociado
					codAso = DelegadoOtrasEntElect.getInstance().obtieneNumintAsociado(documento);
					
					//se crea registro en EleAsocSancionados5Annos
					DelegadoEleAsocSancionado.getInstance().crearSancionados(codAso, new Date(), user.getUserId(), fechaSuspension, motivo);
					
				}
				
				getData();
				renderDataTable = true;
			}	
			
			reader.close();
		} catch (FileNotFoundException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			this.mostrarPopupErrorCargaArchivo = Boolean.TRUE;
			this.mensajePopupErrorCargaArchivo = UtilAcceso.getParametroFuenteS("mensajes",
			"msgErrorCargaArchivosExternos");
		} catch (IOException e) {
			FacesUtils.addErrorMessage(e.getMessage());
			this.mostrarPopupErrorCargaArchivo = Boolean.TRUE;
			this.mensajePopupErrorCargaArchivo = UtilAcceso.getParametroFuenteS("mensajes",
			"msgErrorCargaArchivosExternos");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
			this.mostrarPopupErrorCargaArchivo = Boolean.TRUE;
			this.mensajePopupErrorCargaArchivo = UtilAcceso.getParametroFuenteS("mensajes",
			"msgErrorCargaArchivosExternos");
		}
		
	}
	
	public String formateaStringFecha(String fecha){
		
		String[] arrayString;
		
		arrayString = fecha.split("/");
		
		String dia = arrayString[0];
		String mes = arrayString[1];
		String anho = arrayString[2];
		
		String nuevaFecha = mes+"/"+dia+"/"+anho;
		
		System.out.println("nuevaFecha -> "+nuevaFecha);
		
		return nuevaFecha;
	}
	
	public void actionFileUploadProgress(EventObject event) {
		InputFile ifile = (InputFile) event.getSource();
		this.fileProgress = ifile.getFileInfo().getPercent();
	}
	
	 public DataModel getData() {
	        state = PersistentFacesState.getInstance();

	        if (onePageDataModel == null) {
	            onePageDataModel = new LocalDataModel(pageSize);
	        }

	        return onePageDataModel;
	    }
	 
	 private DataPage<EleAsocSancionados5AnnosDTO> getDataPage(int startRow, int pageSize) {
         // Retrieve the total number of objects from the database.  This
         // number is required by the DataPage object so the paginator will know
         // the relative location of the page data.
         int totalSancionados = 0;

         try {
        	 totalSancionados = DelegadoEleAsocSancionado.getInstance().obtenerSancionados().size();
         } catch (Exception e) {
        	 //mensaje.mostrarMensaje(e.getMessage());
        	 e.printStackTrace();
         }

         // Calculate indices to be displayed in the ui.
         int endIndex = startRow + pageSize;

         if (endIndex > totalSancionados) {
             endIndex = totalSancionados;
         }

         try {
             sancionados = DelegadoEleAsocSancionado.getInstance().consultarSancionados(sortColumnName, sortAscending, startRow, endIndex - startRow);
            	 
             // Remove this Renderable from the existing render groups.
             //leaveRenderGroups();        			
         } catch (Exception e) {
        	 //mensaje.mostrarMensaje(e.getMessage()); 
        	 e.printStackTrace();
         }
         
         if (sancionados != null && !sancionados.isEmpty()) {
			renderDataTable = true;
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

         return new DataPage<EleAsocSancionados5AnnosDTO>(totalSancionados,
             startRow, sancionados);
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
	     private class LocalDataModel extends PagedListDataModel {
	         public LocalDataModel(int pageSize) {
	             super(pageSize);
	         }

	         public DataPage<EleAsocSancionados5AnnosDTO> fetchPage(int startRow, int pageSize) {
	             // call enclosing managed bean method to fetch the data
	             return getDataPage(startRow, pageSize);
	         }
	     }
	     
	     public InputFileDataDTO getCurrentFile() {
	 		return currentFile;
	 	}

	 	public void setCurrentFile(InputFileDataDTO currentFile) {
	 		this.currentFile = currentFile;
	 	}

	 	public int getFileProgress() {
	 		return fileProgress;
	 	}

	 	public void setFileProgress(int fileProgress) {
	 		this.fileProgress = fileProgress;
	 	}

	 	public List<InputFileDataDTO> getFileList() {
	 		return fileList;
	 	}

	 	public void setFileList(List<InputFileDataDTO> fileList) {
	 		this.fileList = fileList;
	 	}

		public UserVo getUser() {
			return user;
		}

		public void setUser(UserVo user) {
			this.user = user;
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
		
		public boolean isMostrarPopupErrorCargaArchivo() {
			return mostrarPopupErrorCargaArchivo;
		}

		public void setMostrarPopupErrorCargaArchivo(boolean mostrarPopupErrorCargaArchivo) {
			this.mostrarPopupErrorCargaArchivo = mostrarPopupErrorCargaArchivo;
		}

		public String getMensajePopupErrorCargaArchivo() {
			return mensajePopupErrorCargaArchivo;
		}

		public void setMensajePopupErrorCargaArchivo(String mensajePopupErrorCargaArchivo) {
			this.mensajePopupErrorCargaArchivo = mensajePopupErrorCargaArchivo;
		}
		
		public String ocultarMensajePopupErrorCargaArchivo(){
			this.mostrarPopupErrorCargaArchivo = Boolean.FALSE;
			this.fileProgress = 0;
			return "";
		}

}