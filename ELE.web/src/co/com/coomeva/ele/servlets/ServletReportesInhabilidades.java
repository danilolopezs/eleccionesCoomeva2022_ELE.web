package co.com.coomeva.ele.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.coomeva.delegado.reportesToExcel.DelegadoReportesToExcel;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoReportes;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.modelo.reportesToExcel.ReporteExcel;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class ServletReportesInhabilidades extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletReportesInhabilidades() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			executeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ejecuta el reporte depende del enviado por paramtro.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 6/11/2012
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		
		String reporte = (String)req.getSession().getAttribute("reporteParam");
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		ServletOutputStream toClient=  null;
		boolean flag = false;
		
		try {
			String tipoReporte = (String)req.getSession().getAttribute("tipoReporte");
			List<EleAsociadoDatosDTO> listAsociadosHabiles = null;
			String tipoHabilidad = "";
			
			if (reporte.equals(LoaderResourceElements.getInstance().getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "reporte_inhabiles"))) {
				listAsociadosHabiles = DelegadoAsociado.getInstance().consultarAsociadosInhabiles();
				tipoHabilidad = "Inhábil";
			}else{
				listAsociadosHabiles = DelegadoAsociado.getInstance().consultarAsociadosHabiles();
				tipoHabilidad = "Hábil";
			}
			
			if (listAsociadosHabiles == null || listAsociadosHabiles.size() <= 0) {
				flag = true;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoDatosBusqueda"));
			}
			
			
			
			if (tipoReporte.equals(LoaderResourceElements.getInstance().getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "tipoReporteHabilidadExcel"))) {
				
				String encabezados = LoaderResourceElements.getInstance().getKeyResourceValue("parametros", "encabezado_reporte_excel_asos_inhabiles");
				String periodo = LoaderResourceElements.getInstance().getKeyResourceValue("parametros", "periodoelectoral");
				
				if(encabezados == null || encabezados.equals("")){
					throw new Exception("No se encontró el parámetro con los encabezados del reporte de asociados inhábiles");
				}
				
				List<String[]> datosFilasExcel = new ArrayList<String[]>();
				String[] datosColumnas = null;
				Integer i = 1;
				for (EleAsociadoDatosDTO registroReporte : listAsociadosHabiles) {
					datosColumnas = new String[10];
					
					datosColumnas[0] = i.toString();
					datosColumnas[1] = periodo;
					datosColumnas[2] = registroReporte.getZonaElectoral() != null ? registroReporte.getZonaElectoral() : "";
					datosColumnas[3] = registroReporte.getNitcli() != null ? registroReporte.getNitcli() : "";
					datosColumnas[4] = registroReporte.getNombreCompleto() != null ? registroReporte.getNombreCompleto() : "";
					datosColumnas[5] = registroReporte.getProfesion() != null ? registroReporte.getProfesion() : "";
					datosColumnas[6] = registroReporte.getEstadoHabilidad() != null ? registroReporte.getEstadoHabilidad() : "";
					datosColumnas[7] = registroReporte.getRegional() != null ? registroReporte.getRegional() : "";
					datosColumnas[8] = registroReporte.getCiudad() != null ? registroReporte.getCiudad() : "";
					datosColumnas[9] = registroReporte.getOficina() != null ? registroReporte.getOficina() : "";
					
					
					//Se ingresa cada fila o registro encontrado con sus respectivos datos en la coleccion.
					datosFilasExcel.add(datosColumnas);
					
					i++;
				}
				
				ReporteExcel reporteExcel = new ReporteExcel();
				reporteExcel.setNombreHoja("Registros Asociados "+tipoHabilidad);
				reporteExcel.setTituloReporte("Reporte Asociados "+tipoHabilidad+"es - Total Registros "+listAsociadosHabiles.size());
				
				DelegadoReportesToExcel  delegadoReportesToExcel = new DelegadoReportesToExcel();
				byteArrayOutputStream = delegadoReportesToExcel.generacionReportesExcelByteArrayOutputStream(encabezados, null, datosFilasExcel, reporteExcel);
				
				resp.setContentType("application/vnd.ms-excel");
				resp.setHeader("Content-disposition", "inline; filename=\""+reporte+".xls\"");													
			
			}else if (tipoReporte.equals(LoaderResourceElements.getInstance().getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "tipoReporteHabilidadPdf"))) {
				byteArrayOutputStream =  DelegadoReportes.getInstance().generarReporteAsociadosHabilesInhabilesPDF(listAsociadosHabiles, 
						PathRequest.getInstance().getPathServerContextPath(getServletContext(), "imagenes"));
				resp.setContentType("application/vnd.ms-pdf");
				resp.setHeader("Content-disposition", "inline; filename=\""+reporte+".pdf\"");
			}								
						
			resp.setCharacterEncoding("iso-8859-1");
			
			resp.setHeader("Refresh", "10");
			toClient = resp.getOutputStream();
			byteArrayOutputStream.writeTo(toClient);
			
			req.removeAttribute("reporteParam");
			req.removeAttribute("tipoReporte");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion generando el reporte :"+e.getMessage());
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			if (flag) {
				out.println("  <HEAD><TITLE>"+e.getMessage()+"</TITLE></HEAD>");
			}else
				out.println("  <HEAD><TITLE>Se generó un error generando el reporte</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("  <TABLE ALIGN='CENTER'>");
			out.println("  <TR>");
			out.println("  <TD>");
			if (!flag) {
				out.print("    Error -  ");
			}
			out.print(e.getMessage());
			out.println("  </TR>");
			out.println("  </TD>");			
			out.println("  </TABLE>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}finally{
			
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
