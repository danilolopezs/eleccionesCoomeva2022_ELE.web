package co.com.coomeva.util.resources.log4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class AppContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent contextEvent) { }
 
    public void contextInitialized(ServletContextEvent contextEvent) {
        try {
             AppRepositorySelector.init(contextEvent.getServletContext());
        } catch (Exception ex) {
             System.err.println(ex);
        }
    }
    public static void main(String[] args) {
		System.out.println(); 
	}
}
