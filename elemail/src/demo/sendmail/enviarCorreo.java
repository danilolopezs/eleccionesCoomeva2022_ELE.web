package demo.sendmail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class enviarCorreo
 */
public class enviarCorreo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SendMail sendMail;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enviarCorreo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		sendMail=new SendMail();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			sendMail.enviar();
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Correo enviado correctamente</h1>");
			out.println("</body>");
			out.println("</html>");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al enviar el correo: "+e.getMessage());
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Error al enviar correo: </h1>");
			out.println("<h1>+"+e.getMessage()+"+ </h1>");
			out.println("</body>");
			out.println("</html>");				
			
		}
	}

}
