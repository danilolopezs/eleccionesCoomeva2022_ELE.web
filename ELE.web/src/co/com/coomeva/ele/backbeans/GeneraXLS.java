package co.com.coomeva.ele.backbeans;

import javax.faces.context.FacesContext;

import com.icesoft.faces.context.effects.JavascriptContext;

public class GeneraXLS {
	
	
	public String action_generarXLS()
	{
		try {
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "servletInvokerXLS();");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
