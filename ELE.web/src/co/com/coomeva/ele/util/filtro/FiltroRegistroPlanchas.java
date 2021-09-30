package co.com.coomeva.ele.util.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroRegistroPlanchas implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response,     
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		if(session==null){
			session = req.getSession(true);
			resp.sendRedirect("../inicioRegistroPlanchas.jspx");
		}else{
			if((session.getAttribute("asociado")== null) && (session.getAttribute("numeroDocAsociado")== null))          
				resp.sendRedirect("../inicioRegistroPlanchas.jspx");
		}
		chain.doFilter(request, response); // continue filtering
	}

	public void destroy() {
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
