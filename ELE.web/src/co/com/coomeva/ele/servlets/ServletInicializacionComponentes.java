package co.com.coomeva.ele.servlets;

/**
 * Esta  clase inicializa la conexión con la base de datos en el momento
 * en que se suba el servidor de aplicaciones.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.coomeva.ele.entidades.climae.HibernateSessionFactoryClimae;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;

public class ServletInicializacionComponentes extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ServletInicializacionComponentes() {
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

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException 
	{
		System.out.println("######################################################################");
		System.out.println("    INICIALIZANDO COMPONENTES");
		System.out.println("######################################################################");
		System.out.println("        INICIALIZANDO SESSION DE BD [HABILIDADES, CLIMAE, ELECDB...]  ");
		System.out.println("######################################################################");
		//HibernateSessionFactoryHab.getSession();
		HibernateSessionFactoryClimae.getSession();		
		HibernateSessionFactoryElecciones2012.getSession();
		System.out.println("######################################################################");
		System.out.println("        COMPONENTES INCIALIZADOS CORRECTAMENTE");
		System.out.println("######################################################################");
		//System.out.println("##########################################################");
		//System.out.println("         [INICIALIZANDO SESSION DE BD PLANCHAS....]");
		//HibernateSessionFactoryPlanchas.getSession();		
		//System.out.println("############################################################");
		//System.out.println("         [INICIALIZANDO SESSION DE BD LICO....]");
		//HibernateSessionFactoryLico.getSession();
		//System.out.println("############################################################");
		//System.out.println("         [INICIALIZANDO SESSION DE BD SALUD....]");
		//HibernateSessionFactorySalud.getSession();
		//System.out.println("############################################################");
		//System.out.println("         [INICIALIZANDO SESSION DE BD SIE....]");
		//HibernateSessionFactorySie.getSession();
		//System.out.println("############################################################");
		//System.out.println("         [INICIALIZANDO SESSION DE BD SRH....]");
		//HibernateSessionFactorySrh.getSession();
		//System.out.println("         [SESSION COMENTADA PORQUE LA CUENTA NO EXISTE, AHORA PEOPLEnet]");
		//HibernateSessionFactorySrh.getSession();	
	}
}
