/**
 * 
 */
package co.com.coomeva.smb.modelo;

import jcifs.smb.NtlmAuthenticator;
import jcifs.smb.NtlmPasswordAuthentication;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * @author clmasw02
 *
 */
public class SmbUsuario extends NtlmAuthenticator {

	
	private String dominio;
	private String userName;
	private String password;
	/**
	 * 
	 */
	public SmbUsuario() {
		// TODO Auto-generated constructor stub
		NtlmAuthenticator.setDefault( this );
		this.dominio = "";
		this.userName = "";
		this.password = "";
	}
	public SmbUsuario(String dominio,String userName,String password) {
		// TODO Auto-generated constructor stub
		NtlmAuthenticator.setDefault( this );
		this.dominio = "";
		this.userName = "";
		this.password = "";		
		setDominio(dominio);
		setUserName(userName);
		setPassword(password);		
	}
	public SmbUsuario(boolean solicitandoDatos) {
		// TODO Auto-generated constructor stub
		NtlmAuthenticator.setDefault( this );
		this.dominio = "";
		this.userName = "";
		this.password = "";		
		if(solicitandoDatos){
			try {
				System.out.print( "Dominio: " );
				setDominio(UtilAcceso.readLine());        	
				
				System.out.print( "Username: " );
				setUserName(UtilAcceso.readLine());

				System.out.print( "Password: " );
				setPassword(UtilAcceso.readLine());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				UtilAcceso.printStackTrace(e, true);
			}			
		}
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    protected NtlmPasswordAuthentication getNtlmPasswordAuthentication() {
        System.out.println( getRequestingException().getMessage() + " for " + getRequestingURL() );
        return new NtlmPasswordAuthentication(getDominio(),getUserName(),getPassword() );
    }	
	
}
