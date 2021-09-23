package co.com.coomeva.ele.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.com.coomeva.ele.delegado.habilidad.DelegadoProcesoVerificacionHabilidad;
import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;

public class ServletProcesoVerificacionHabilidadAsos extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config); // always!
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ejecutarProceso(request, response);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ejecutarProceso(req, res);
	}

	private void ejecutarProceso(HttpServletRequest req,
			HttpServletResponse response) {
		HttpSession session = req.getSession();
		Object objetoSesion = session
				.getAttribute("procesoVerificacionHabilidad");

		if (objetoSesion != null) {
			
			UserVo user = (UserVo)session.getAttribute("user");
			String nombreUsuario = user.getName() != null
					&& user.getName().length() > 15 ? user.getName().substring(
					0, 14) : user.getName() != null ? user.getName() : null;
			
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("iso-8859-1");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE>Inicio Proceso de verificación de habilidad de asociados</TITLE></HEAD>");
				out.println("  <BODY>");
				out.println("  <TABLE ALIGN='CENTER'>");
				out.println("  <TR>");
				out.println("  <TD>");
				out.print("   SE HA INICIADO EL PROCESO DE VERIFICACIÓN DE HABILIDAD DE ASOCIADOS.  ESTE PROCESO PUEDE TOMAR VARIAS HORAS  ");
				out.println("  </TD>");			
				out.println("  </TR>");
				out.println("  </TABLE>");
				out.println("  </BODY>");
				out.println("</HTML>");
				
				DTOProcesoVerificacionHabilidad procesoVerificacionHabilidad = (DTOProcesoVerificacionHabilidad) objetoSesion;

				DelegadoProcesoVerificacionHabilidad.getInstance()
						.ejecutarProcesoVerificacion(
								procesoVerificacionHabilidad, nombreUsuario);
			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void destroy() {
	}
}
