package co.com.coomeva.ele.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.com.coomeva.ele.delegado.generador.DelegadoGenerador;

public class servletArchivoXls extends HttpServlet {

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
		performTask(request, response);
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
		performTask(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public synchronized void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		{
			ByteArrayOutputStream byteArrayOutputStream = null;

			ServletOutputStream toClient = null;
//			HttpSession httpSession = request.getSession();
			boolean flag = false;

			try 
			{
				byteArrayOutputStream = DelegadoGenerador.getInstance().generarExcel();

				if (byteArrayOutputStream == null) {
					flag = true;
				}

				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding("iso-8859-1");

				toClient = response.getOutputStream();
				byteArrayOutputStream.writeTo(toClient);

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Excepcion generando XLS :"+e.getMessage());
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				if (flag) {
					out.println("  <HEAD><TITLE>"+e.getMessage()+"</TITLE></HEAD>");
				}else
					out.println("  <HEAD><TITLE>Se generó un error generando el archivo XLS</TITLE></HEAD>");
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
}
