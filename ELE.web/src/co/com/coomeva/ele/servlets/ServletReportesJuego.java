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
import co.com.coomeva.ele.delegado.DelegadoJuego;
import co.com.coomeva.ele.delegado.DelegadoReportes;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleParticipanteJuegoDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.modelo.reportesToExcel.ReporteExcel;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class ServletReportesJuego extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletReportesJuego() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 6/11/2012
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		String reporte = LoaderResourceElements.getInstance()
				.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
						"reporte_juego");

		ByteArrayOutputStream byteArrayOutputStream = null;
		ServletOutputStream toClient = null;
		boolean flag = false;
		String zona = (String) req.getSession().getAttribute(
				"eleccionesJuegoZonaElectoral");
		String fase = (String) req.getSession().getAttribute(
				"eleccionesJuegoFase");

		try {
			List<EleParticipanteJuegoDTO> listaJuegos = null;
			listaJuegos = DelegadoJuego.getInstance().consultarJuegos(zona,
					fase);

			if (listaJuegos == null || listaJuegos.size() <= 0) {
				flag = true;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
						"msgNoDatosJuegos"));
			}

			String encabezados = LoaderResourceElements.getInstance()
					.getKeyResourceValue("parametros",
							"encabezado_reporte_excel_juegos");
			// String fase =
			// LoaderResourceElements.getInstance().getKeyResourceValue("parametros",
			// "periodoelectoral");

			if (encabezados == null || encabezados.equals("")) {
				throw new Exception(
						"No se encontró el parámetro con los encabezados del reporte de juegos medios visuales");
			}

			List<String[]> datosFilasExcel = new ArrayList<String[]>();
			String[] datosColumnas = null;
			Integer i = 1;
			for (EleParticipanteJuegoDTO registroReporte : listaJuegos) {
				datosColumnas = new String[6];

				datosColumnas[0] = registroReporte.getNumeroDocumento() != null ? String
						.valueOf(registroReporte.getNumeroDocumento())
						: "";
						
				datosColumnas[1] = registroReporte.getNombreAsociado() != null ? String
								.valueOf(registroReporte.getNombreAsociado())
								: "";
						
				datosColumnas[2] = registroReporte.getFaseJuego() != null ? String
						.valueOf(registroReporte.getFaseJuego())
						: "";
						
				datosColumnas[3] = registroReporte.getDesRegional() != null ? registroReporte
								.getDesRegional()
								: "";
						
				datosColumnas[4] = registroReporte.getZonaJuego() != null ? registroReporte
						.getZonaJuego()
						: "";
				datosColumnas[5] = registroReporte.getHoraTrans() != null ? String
						.valueOf(registroReporte.getHoraTrans())
						: "";

				// Se ingresa cada fila o registro encontrado con sus
				// respectivos datos en la coleccion.
				datosFilasExcel.add(datosColumnas);

				i++;
			}

			ReporteExcel reporteExcel = new ReporteExcel();
			reporteExcel.setNombreHoja("Juegos");
			reporteExcel
					.setTituloReporte("Reporte de juegos medios visuales - Total Registros "
							+ listaJuegos.size());

			DelegadoReportesToExcel delegadoReportesToExcel = new DelegadoReportesToExcel();
			byteArrayOutputStream = delegadoReportesToExcel
					.generacionReportesExcelByteArrayOutputStream(encabezados,
							null, datosFilasExcel, reporteExcel);

			resp.setContentType("application/vnd.ms-excel");
			resp.setHeader("Content-disposition", "inline; filename=\""
					+ reporte + ".xls\"");

			resp.setCharacterEncoding("iso-8859-1");

			resp.setHeader("Refresh", "10");
			toClient = resp.getOutputStream();
			byteArrayOutputStream.writeTo(toClient);

			req.removeAttribute("reporteParam");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion generando el reporte :"
					+ e.getMessage());
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out
					.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			if (flag) {
				out.println("  <HEAD><TITLE>" + e.getMessage()
						+ "</TITLE></HEAD>");
			} else
				out
						.println("  <HEAD><TITLE>Se generó un error generando el reporte</TITLE></HEAD>");
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
		} finally {

		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
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
	 * Returns information about the servlet, such as author, version, and
	 * copyright.
	 * 
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
