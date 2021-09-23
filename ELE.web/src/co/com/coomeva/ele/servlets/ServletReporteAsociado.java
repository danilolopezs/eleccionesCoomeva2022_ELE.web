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
import javax.servlet.http.HttpSession;

import co.com.coomeva.ele.backbeans.ReportesInhabilidades;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoReportes;
import co.com.coomeva.ele.modelo.AsociadoDTO;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PathRequest;
import co.com.coomeva.util.acceso.UtilAcceso;


public class ServletReporteAsociado extends HttpServlet{

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		performTask(request,response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		performTask(request,response);

		
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

	public synchronized  void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		ByteArrayOutputStream byteArrayOutputStream = null;

		ServletOutputStream toClient=  null; 
		HttpSession session = request.getSession();
		//Obtiene parámetros consulta
		String zona = (String)session.getAttribute("zona");
		String oficina = (String)session.getAttribute("oficina");
		String regional = (String)session.getAttribute("regional");
		String estado = (String)session.getAttribute("estado");
		
		boolean flag = false;
		
		try {
			
			List<AsociadoReporteDTO> listaAsociados = DelegadoAsociado.getInstance().findAsoHabFinNative(zona,oficina,regional,estado);
			if (listaAsociados.size()==0) {
				flag = true;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgNoDatosBusqueda"));
			}
			byteArrayOutputStream = DelegadoReportes.getInstance().generarAsociadosByte(listaAsociados,PathRequest.getInstance().getPathServerContextPath(getServletContext(), "imagenes"));
			session.removeAttribute("zona");
			session.removeAttribute("oficina");
			session.removeAttribute("regional");
			session.removeAttribute("estado");
			
			response.setContentType("application/pdf");
			response.setCharacterEncoding("iso-8859-1");
			
			toClient = response.getOutputStream();
			byteArrayOutputStream.writeTo(toClient);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion generando pdf :"+e.getMessage());
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			if (flag) {
				out.println("  <HEAD><TITLE>"+e.getMessage()+"</TITLE></HEAD>");
			}else
				out.println("  <HEAD><TITLE>Se generó un error generando el archivo pdf</TITLE></HEAD>");
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
		}
	}
}
