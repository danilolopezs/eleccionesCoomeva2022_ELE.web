package co.com.coomeva.ele.backbeans.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.DelegadoUsuarioLogin;
import co.com.coomeva.ele.entidades.usuario.EleUsuarioLogin;

public class DesbloqueoUsuarioVista extends BaseVista
{
	private String codigoUsuario;
	private List<EleUsuarioLogin> listaUsuarioBloqueado;
		
	public DesbloqueoUsuarioVista() {
		
		codigoUsuario = "";
		listaUsuarioBloqueado= new ArrayList<EleUsuarioLogin>();
		cargarParametros();
	}
	
	public String consultarUsuariosBloqueados(ActionEvent accion)
	{
		try
		{
			listaUsuarioBloqueado = DelegadoUsuarioLogin.getInstance().consultarUsuariosBloqueados(codigoUsuario);
			
			if(listaUsuarioBloqueado == null)
			{
				getMensaje().setMensaje("No se encontraron usuarios bloqueados que concuerden con el filtro ingresado");
				getMensaje().setVisible(true);
			}
		}
		catch (Exception e) {
			getMensaje().setMensaje("Se presento un error Inesperado al realizar la consulta");
			getMensaje().setVisible(true);
		}
		
		return "";
	}
	
	public String desbloquearUsuarios(ActionEvent accion)
	{
		if(listaUsuarioBloqueado == null || listaUsuarioBloqueado.isEmpty())
		{
			getMensaje().setMensaje("Debe Seleccionar el usuario o los usuario que se desea desbloquear.");
			getMensaje().setVisible(true);
		}
		else
		{
			List<EleUsuarioLogin> usuariosSeleccionados = new ArrayList<EleUsuarioLogin>();
			
			for (EleUsuarioLogin eleUsuarioLogin : listaUsuarioBloqueado) {
				
				if(eleUsuarioLogin.isSeleccionado())
				{
					usuariosSeleccionados.add(eleUsuarioLogin);
				}
			}
			
			if(usuariosSeleccionados.isEmpty())
			{
				getMensaje().setMensaje("Debe Seleccionar el usuario o los usuario que se desea desbloquear.");
				getMensaje().setVisible(true);
			}
			else
			{
				DelegadoUsuarioLogin.getInstance().desbloquearUsuarios(usuariosSeleccionados);
				
				listaUsuarioBloqueado = null;
				getMensaje().setMensaje("Se desbloquearon exitosamente los usuarios seleccionados");
				getMensaje().setVisible(true);
			}
		}
		
		return "";
	}
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public List<EleUsuarioLogin> getListaUsuarioBloqueado() {
		return listaUsuarioBloqueado;
	}
	public void setListaUsuarioBloqueado(List<EleUsuarioLogin> listaUsuarioBloqueado) {
		this.listaUsuarioBloqueado = listaUsuarioBloqueado;
	}	
}
