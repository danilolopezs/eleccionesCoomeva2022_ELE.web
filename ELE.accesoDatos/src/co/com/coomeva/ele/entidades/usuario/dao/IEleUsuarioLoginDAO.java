package co.com.coomeva.ele.entidades.usuario.dao;

import java.util.List;

import co.com.coomeva.ele.entidades.usuario.EleUsuarioLogin;

public interface IEleUsuarioLoginDAO {
	
	public void save(EleUsuarioLogin entity);
	
	public void update(EleUsuarioLogin entity);
	
	public void merge(EleUsuarioLogin entity);
	
	public EleUsuarioLogin findByUserId(String userId);

	public List<EleUsuarioLogin> consultarUsuariosBloqueados(String userId);

}
