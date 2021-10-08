package co.com.coomeva.ele.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.coomeva.ele.delegado.generador.DelegadoGenerador;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.util.acceso.UtilAcceso;

public class ServletReporteDelegadosZona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String REPORTE_CUOCIENTE_ELECTORAL = "ReporteCuocienteElectoral";

	/**
	 * Constructor of the object.
	 */
	public ServletReporteDelegadosZona() {
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
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			executeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ejecuta el reporte depende del enviado por paramtro.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> -
	 *         Pragma S.A. <br>
	 * @date 6/11/2012
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = null;
		ServletOutputStream toClient = null;

		List<EleCuocienteDelegadosZona> delegadosZona = null;
		List<EleCuocienteRegional> delegadosRegional = null;
		EleCuocienteElectoral cuocienteElectoral = null;

		delegadosZona = (List<EleCuocienteDelegadosZona>) req.getSession().getAttribute("delegadosZona");
		delegadosRegional = (List<EleCuocienteRegional>) req.getSession().getAttribute("delegadosRegional");
		cuocienteElectoral = (EleCuocienteElectoral) req.getSession().getAttribute("cuociente");

		if (delegadosZona == null || delegadosZona.size() <= 0) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoDatosBusqueda"));
		}

		// List<ResumenCuocienteDTO> list =
		// DelegadoCuocienteElectoral.getInstance().generarInformacionResumenCuociente();

		byteArrayOutputStream = DelegadoGenerador.getInstance().generarCoucienteElectoral(delegadosRegional,
				delegadosZona, cuocienteElectoral);
		resp.setContentType("application/vnd.ms-excel");
		resp.setHeader("Content-disposition", "inline; filename=\"" + REPORTE_CUOCIENTE_ELECTORAL + ".xls\"");
		resp.setCharacterEncoding("iso-8859-1");
		toClient = resp.getOutputStream();
		byteArrayOutputStream.writeTo(toClient);

	}
	/*
	 * public void executeReport(HttpServletRequest req, HttpServletResponse resp)
	 * throws Exception {
	 * 
	 * String reporte = "ReporteDelegadozZona";
	 * 
	 * ByteArrayOutputStream byteArrayOutputStream = null; ServletOutputStream
	 * toClient = null; boolean flag = false;
	 * 
	 * try { List<EleCuocienteDelegadosZona> delegadosZona = null;
	 * List<EleCuocienteRegional> delegadosRegional = null; EleCuocienteElectoral
	 * cuocienteElectoral = null; String tipoHabilidad = "";
	 * 
	 * delegadosZona = (List<EleCuocienteDelegadosZona>) req.getSession()
	 * .getAttribute("delegadosZona");
	 * 
	 * delegadosRegional = (List<EleCuocienteRegional>) req.getSession()
	 * .getAttribute("delegadosRegional");
	 * 
	 * cuocienteElectoral = (EleCuocienteElectoral) req.getSession()
	 * .getAttribute("cuociente");
	 * 
	 * if (delegadosZona == null || delegadosZona.size() <= 0) { flag = true; throw
	 * new Exception(UtilAcceso.getParametroFuenteS("mensajes",
	 * "msgNoDatosBusqueda")); }
	 * 
	 * String encabezados = LoaderResourceElements.getInstance()
	 * .getKeyResourceValue("parametros",
	 * "encabezado_reporte_excel_delegados_zona");
	 * 
	 * if (encabezados == null || encabezados.equals("")) { throw new Exception(
	 * "No se encontr� el par�metro con los encabezados del reporte de asociados inh�biles"
	 * ); }
	 * 
	 * List<String[]> datosFilasExcel = new ArrayList<String[]>(); String[]
	 * datosColumnas = null;
	 * 
	 * if(cuocienteElectoral != null){ encabezados = " , , , "; datosColumnas = new
	 * String[9]; datosColumnas[2] = " "; datosColumnas[3] = " "; datosColumnas[4] =
	 * " "; datosColumnas[5] = " "; datosColumnas[6] = " "; datosColumnas[7] = " ";
	 * datosColumnas[8] = " "; datosColumnas[0] = "Total Asociados Habiles";
	 * datosColumnas[1] =
	 * UtilNumber.getInstance().getDecimalFormat(cuocienteElectoral.
	 * getTotalAsocHabiles(), "##,###").toString();
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * datosColumnas = datosColumnas.clone(); datosColumnas[0] =
	 * "Total Delegados a Elegir"; datosColumnas[1] =
	 * UtilNumber.getInstance().getDecimalFormat(cuocienteElectoral.
	 * getTotalDelegadosElegir(), "##,###").toString();
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * datosColumnas = datosColumnas.clone(); datosColumnas[0] =
	 * "Total Delegados Especiales (Promotores, Empleados)"; datosColumnas[1] =
	 * UtilNumber.getInstance().getDecimalFormat(cuocienteElectoral.
	 * getTotalDelegadosEspeciales(), "##,###").toString();
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * datosColumnas = datosColumnas.clone(); datosColumnas[0] =
	 * "Final Total Delegados a Elegir"; datosColumnas[1] =
	 * UtilNumber.getInstance().getDecimalFormat(cuocienteElectoral.
	 * getFinalTotalDelegadosElegir(), "##,###").toString();
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * datosColumnas = datosColumnas.clone(); datosColumnas[0] = "Cuociente";
	 * datosColumnas[1] =
	 * UtilNumber.getInstance().getDecimalFormat(cuocienteElectoral.
	 * getCuocienteElectoral(), "##,###.####").toString();
	 * datosFilasExcel.add(datosColumnas); } datosColumnas = new String[9];
	 * datosFilasExcel.add(datosColumnas.clone());
	 * datosFilasExcel.add(datosColumnas.clone());
	 * 
	 * 
	 * datosColumnas[0] = "Regional"; datosColumnas[1] = "Zona Electoral";
	 * datosColumnas[2] = "H�biles"; datosColumnas[3] = "Delegados";
	 * datosColumnas[4] = "Delegados Directos"; datosColumnas[5] = "Fracci�n";
	 * datosColumnas[6] = "Delegado Residuo"; datosColumnas[7] =
	 * "Distribucion Restantes"; datosColumnas[8] = "Total Delegados";
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * for (EleCuocienteDelegadosZona registroReporte : delegadosZona) {
	 * datosColumnas = new String[9]; if( registroReporte.getCodRegional() != null )
	 * datosColumnas[0] =
	 * validarDato(registroReporte.getDescCodRegional()).toString().trim();
	 * datosColumnas[1] = validarDato(registroReporte.getDescCodZona()).toString();
	 * datosColumnas[2] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getSumaHabiles(), "##,###")).toString(); datosColumnas[3] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getDelegados(), "##,###.####")).toString(); datosColumnas[4] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getDelegadosDirectos(), "##,###")).toString(); datosColumnas[5] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getFraccion(), "##,###.####")).toString(); datosColumnas[6] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getDelegadosResiduo(), "####")).toString(); datosColumnas[7] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getDistribuidosRestantes(), "####")).toString(); datosColumnas[8] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getTotalDelegadosZona(), "##,###")).toString(); // Se ingresa cada fila o
	 * registro encontrado con sus // respectivos datos en la coleccion.
	 * datosFilasExcel.add(datosColumnas); } datosColumnas = new String[9];
	 * datosFilasExcel.add(datosColumnas.clone());
	 * datosFilasExcel.add(datosColumnas.clone());
	 * 
	 * datosColumnas[0] = "Codigo Regional"; datosColumnas[1] = "Regional";
	 * datosColumnas[2] = "Total Delegados"; datosColumnas[3] =
	 * "Porcentaje Delegados"; datosColumnas[4] = " "; datosColumnas[5] = " ";
	 * datosColumnas[6] = " "; datosColumnas[7] = " "; datosColumnas[8] = " ";
	 * 
	 * datosFilasExcel.add(datosColumnas);
	 * 
	 * for (EleCuocienteRegional registroReporte : delegadosRegional) {
	 * datosColumnas = new String[9]; datosColumnas[0] =
	 * validarDato(registroReporte.getCodRegional()).toString(); datosColumnas[1] =
	 * validarDato(registroReporte.getDesRegional()).toString().trim();
	 * datosColumnas[2] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getTotalDelegados(), "##,###")).toString(); datosColumnas[3] =
	 * validarDato(UtilNumber.getInstance().getDecimalFormat(registroReporte.
	 * getRelacionDelegados(), "##,###.##")).toString().concat("%");
	 * datosColumnas[4] = " "; datosColumnas[5] = " "; datosColumnas[6] = " ";
	 * datosColumnas[7] = " "; datosColumnas[8] = " "; // Se ingresa cada fila o
	 * registro encontrado con sus // respectivos datos en la coleccion.
	 * datosFilasExcel.add(datosColumnas); }
	 * 
	 * ReporteExcel reporteExcel = new ReporteExcel();
	 * 
	 * reporteExcel.setNombreHoja("Registros Delegados por Zona Electoral " +
	 * tipoHabilidad); reporteExcel.setTituloReporte("Reporte Delegados " +
	 * tipoHabilidad + " - Total Registros " + delegadosZona.size());
	 * 
	 * DelegadoReportesToExcel delegadoReportesToExcel = new
	 * DelegadoReportesToExcel(); byteArrayOutputStream = delegadoReportesToExcel
	 * .generacionReportesExcelByteArrayOutputStream(encabezados, null,
	 * datosFilasExcel, reporteExcel);
	 * 
	 * resp.setContentType("application/vnd.ms-excel");
	 * resp.setHeader("Content-disposition", "inline; filename=\"" + reporte +
	 * ".xls\"");
	 * 
	 * resp.setCharacterEncoding("iso-8859-1");
	 * 
	 * resp.setHeader("Refresh", "10"); toClient = resp.getOutputStream();
	 * byteArrayOutputStream.writeTo(toClient);
	 * //req.removeAttribute("delegadosZona");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * resp.setContentType("text/html"); PrintWriter out = resp.getWriter(); out
	 * .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
	 * ); out.println("<HTML>"); if (flag) { out.println("  <HEAD><TITLE>" +
	 * e.getMessage() + "</TITLE></HEAD>"); } else out
	 * .println("  <HEAD><TITLE>Se gener� un error generando el reporte</TITLE></HEAD>"
	 * ); out.println("  <BODY>"); out.println("  <TABLE ALIGN='CENTER'>");
	 * out.println("  <TR>"); out.println("  <TD>"); if (!flag) {
	 * out.print("    Error -  "); } out.print(e.getMessage());
	 * out.println("  </TR>"); out.println("  </TD>"); out.println("  </TABLE>");
	 * out.println("  </BODY>"); out.println("</HTML>"); out.flush(); out.close(); }
	 * finally {
	 * 
	 * }
	 * 
	 * }
	 */

	public Object validarDato(Object dato) {
		if (dato != null && dato.toString().length() > 0) {
			return dato;
		} else {
			return "";
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
