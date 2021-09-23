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
import javax.servlet.http.HttpSession;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoReportes;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class ServletReportesHabilidadesWeb extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletReportesHabilidadesWeb() {
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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> - Pragma S.A. <br>
	 * @date 6/11/2012
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		
		ByteArrayOutputStream byteArrayOutputStream = null;
		ServletOutputStream toClient=  null;
		HttpSession session = req.getSession();
		boolean flag = false;
		
		Long zona = (session.getAttribute("zonaAsociado")==null)?null:(Long)session.getAttribute("zonaAsociado");
		Long profesion = (session.getAttribute("profesionAsociado")==null)?null:(Long)session.getAttribute("profesionAsociado");
		Long numeroDocumento = (session.getAttribute("numIdAsociado")==null)?null:(Long)session.getAttribute("numIdAsociado");
		
		if (zona < 0) {
			zona = null;
		}
		
		String reporte = (String)req.getSession().getAttribute("reporteParam");
		
		try {
			List<EleAsociadoDatosDTO> listAsociadosHabiles = DelegadoAsociado.getInstance().consultarAsociadosHabilesAsociado(profesion, numeroDocumento, zona);
			
			String tipoReporte = (String)req.getSession().getAttribute("tipoReporte");
			
			if (listAsociadosHabiles == null || listAsociadosHabiles.size() <= 0) {
				flag = true;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoDatosBusqueda"));
			}
			byteArrayOutputStream =  DelegadoReportes.getInstance().generarReporteAsociadosHabilesWeb(listAsociadosHabiles, 
					PathRequest.getInstance().getPathServerContextPath(getServletContext(), "imagenes"));
			session.removeAttribute("zonaAsociado");
			session.removeAttribute("profesionAsociado");
			session.removeAttribute("numIdAsociado");
			
			
			if (tipoReporte.equals(LoaderResourceElements.getInstance().getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "tipoReporteHabilidadExcel"))) {
				resp.setContentType("application/vnd.ms-excel");
				resp.setHeader("Content-disposition", "inline; filename=\""+reporte+".xls\"");							
			}else if (tipoReporte.equals(LoaderResourceElements.getInstance().getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "tipoReporteHabilidadPdf"))) {
				resp.setContentType("application/vnd.ms-pdf");
				resp.setHeader("Content-disposition", "inline; filename=\""+reporte+".pdf\"");
			}		
			
			resp.setCharacterEncoding("iso-8859-1");
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
				out.println("  <HEAD><TITLE>Se gener� un error generando el reporte</TITLE></HEAD>");
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
