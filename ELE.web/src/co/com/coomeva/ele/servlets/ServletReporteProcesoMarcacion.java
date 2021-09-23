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
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoProcesoMarcacion;
import co.com.coomeva.ele.logica.inscripcion.plancha.DTOAsociadoMarcado;
import co.com.coomeva.modelo.reportesToExcel.ReporteExcel;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class ServletReporteProcesoMarcacion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ServletReporteProcesoMarcacion() {
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
	
	public void executeReport(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		ServletOutputStream toClient=  null;
		boolean flag = false;
		
		try {
			List<DTOAsociadoMarcado> listAsociadosMarcados = null;
			String tipoHabilidad = "";

			String encabezados = LoaderResourceElements.getInstance()
					.getKeyResourceValue("parametros",
							"encabezado_reporte_excel_marcacion_asociados");

			if (encabezados == null || encabezados.equals("")) {
				throw new Exception(
						"No se encontró el parámetro con los encabezados del reporte de marcación de asociados");
			}
			
			listAsociadosMarcados = DelegadoProcesoMarcacion.getInstance().generarReporteAsociadosMarcados();

			List<String[]> datosFilasExcel = new ArrayList<String[]>();
			String[] datosColumnas = null;
			Integer i = 1;
			for (DTOAsociadoMarcado registroReporte : listAsociadosMarcados) {
				datosColumnas = new String[12];
				datosColumnas[0] = i.toString();
				datosColumnas[1] = registroReporte.getCodigoZonaElectoral() != null ? registroReporte.getCodigoZonaElectoral(): "";
				datosColumnas[2] = registroReporte.getConsecutivoPlancha() != null ? registroReporte.getConsecutivoPlancha().toString(): "";
				datosColumnas[3] = registroReporte.getNumeroZona() != null ? registroReporte.getNumeroZona(): "";
				datosColumnas[4] = registroReporte.getNombreZonaElectoral() != null ? registroReporte.getNombreZonaElectoral(): "";
				datosColumnas[5] = registroReporte.getNumeroPlancha() != null ? registroReporte.getNumeroPlancha().toString(): "";
				datosColumnas[6] = registroReporte.getNumeroDocumento() != null ? registroReporte.getNumeroDocumento().toString(): "";
				datosColumnas[7] = registroReporte.getNombreAsociado() != null ? registroReporte.getNombreAsociado(): "";
				datosColumnas[8] = registroReporte.getTipo() != null ? registroReporte.getTipo(): "";
				datosColumnas[9] = registroReporte.getPosicion() != null ? registroReporte.getPosicion().toString(): "";
				datosColumnas[10] = registroReporte.getCabezaPlancha() != null ? registroReporte.getCabezaPlancha(): "";
				datosColumnas[11] = registroReporte.getObservacionViolaciones() != null ? registroReporte.getObservacionViolaciones(): "";

				// Se ingresa cada fila o registro encontrado con sus
				// respectivos datos en la coleccion.
				datosFilasExcel.add(datosColumnas);
				i++;
			}

			ReporteExcel reporteExcel = new ReporteExcel();
			reporteExcel.setNombreHoja("Registros Asociados " + tipoHabilidad);
			reporteExcel.setTituloReporte("Reporte Asociados " + tipoHabilidad
					+ " - Total Registros " + listAsociadosMarcados.size());

			DelegadoReportesToExcel delegadoReportesToExcel = new DelegadoReportesToExcel();
			byteArrayOutputStream = delegadoReportesToExcel
					.generacionReportesExcelByteArrayOutputStream(encabezados,
							null, datosFilasExcel, reporteExcel);

			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("Content-disposition", "inline; filename=\"reporteMarcacionAsociados.xls\"");

			resp.setCharacterEncoding("iso-8859-1");

			resp.setHeader("Refresh", "10");
			toClient = resp.getOutputStream();
			byteArrayOutputStream.writeTo(toClient);

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

