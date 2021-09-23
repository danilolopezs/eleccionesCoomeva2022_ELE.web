package co.com.coomeva.ele.util.filtro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.util.acceso.UtilAcceso;

public class FiltroAdm implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response,     
			FilterChain chain) throws IOException, ServletException {     

		HttpServletRequest req = (HttpServletRequest) request;     
		HttpServletResponse resp = (HttpServletResponse) response;     
		HttpSession session = req.getSession(false);         
		String pageRequested = req.getRequestURI().toString();
		int cambioAcceso = -1;

		//Inscripcion de Planchas
		if (pageRequested.contains("inicioAsociado.jspx"))cambioAcceso = 0;
		if (pageRequested.contains("crearPlancha.jspx"))cambioAcceso = 0;
		if (pageRequested.contains("modificarPlancha.jspx"))cambioAcceso = 0;
		if (pageRequested.contains("resumenPlancha.jspx")) cambioAcceso = 0;
		if (pageRequested.contains("verModPlancha.jspx")) cambioAcceso = 0;
		if (pageRequested.contains("verPlancha.jspx")) cambioAcceso = 0;
		if (pageRequested.contains("ConsultaCabezaPlancha.jspx")) cambioAcceso = 0;
		if (pageRequested.contains("ConsultaPlanchaEstado.jspx")) cambioAcceso = 0;

		//Administracion de Habilidades
		if (pageRequested.contains("inicioAdm.jspx"))cambioAcceso = 1;
		if (pageRequested.contains("admHabilidad.jspx"))cambioAcceso = 1;
		if (pageRequested.contains("inicioAdmHabilidad.jspx"))cambioAcceso = 1;
		if (pageRequested.contains("repLog.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("repHabilidad.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("cargarArchivo.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("ejecutarProcesoHabilidadAsos.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("generarReportesHabilidades.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("consultarNovedadesAplicadas.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("generarReportesResumenHabilidades.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("resumenNovedades.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("cargarArchivoOtrasEntElect.jspx")) cambioAcceso = 1;
		if (pageRequested.contains("cargarArchivoSancionadosAnnos.jspx")) cambioAcceso = 1;


		//Administracion de Planchas
		if (pageRequested.contains("inicioAdm.jspx"))cambioAcceso = 2;
		if (pageRequested.contains("consultarPlanchasCCEE.jspx"))cambioAcceso = 2;
		if (pageRequested.contains("evaluarPlanchasCCEE.jspx"))cambioAcceso = 2;
		if (pageRequested.contains("inicioAdmPlanchas.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("modificarPlanchasUGA.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("numerarPlanchasCCEE.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("recibirPlanchasUGA.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("sanearPlanchasUGA.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("sanearPlanchasOkUGA.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("verSanearPlanchasUGA.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("repPlanchasLog.jspx")) cambioAcceso = 2;
//		if (pageRequested.contains("generarArchivoXLS.jspx")) cambioAcceso = 2;
		if (pageRequested.contains("generarXLS.jspx")) cambioAcceso = 2;
		


		if(session==null){        
			session = req.getSession(true);

			if (cambioAcceso == 0) {
				resp.sendRedirect("../inicioAsociado.jspx");
			}else
				resp.sendRedirect("../inicioAdm.jspx");

		}else
//			if (cambioAcceso == 0) {
//				if((session.getAttribute("userPlancha")== null) && (!pageRequested.contains("inicioAsociado.jspx")))           
//					resp.sendRedirect("../inicioAsociado.jspx");
//
//			}else
				if((session.getAttribute("user")== null) && (!pageRequested.contains("inicioAdm.jspx")))           
					resp.sendRedirect("../inicioAdm.jspx");
				else{        
					HashMap<String, Section> secciones = (HashMap<String, Section>)session.getAttribute("userSeccions");
					if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad"))!=null) {
						if (cambioAcceso != 1) {
							session.removeAttribute("user");
							session.removeAttribute("userSeccions");
							resp.sendRedirect("../inicioAdm.jspx");
						}
					}else{
						if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secPlancha"))!=null) {
							if (cambioAcceso != 2) {
								session.removeAttribute("user");
								session.removeAttribute("userSeccions");
								resp.sendRedirect("../inicioAdm.jspx");
							}
						}

					}

				} 
		chain.doFilter(request, response); // continue filtering
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}       


}
