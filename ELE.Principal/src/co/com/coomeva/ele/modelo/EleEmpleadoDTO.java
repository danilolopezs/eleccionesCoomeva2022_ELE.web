
/*
 * Created on 16/12/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.coomeva.ele.modelo;


/**
 * @author dnxapr06
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EleEmpleadoDTO  {

	/* (non-Javadoc)
	 * @see co.com.coomeva.gs.model.ValueObject#toString()
	 */
	 private int id;
	 private String empresa;
	 
	 
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
