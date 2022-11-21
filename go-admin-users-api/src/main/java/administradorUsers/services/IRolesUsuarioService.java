package administradorUsers.services;


import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.Usuario;
import administradorUsers.logic.IEntityDao;



public interface IRolesUsuarioService extends IEntityDao<RolesUsuario>{
	
	 List<RolesUsuario> findByUsuario(Usuario usuario) throws AdministradorUserException ;
	 List<RolesUsuario> getAllSistemaByName(String sistemaName) throws AdministradorUserException ;
	 RolesUsuario findById(RolesUsuario p) throws AdministradorUserException ;

}
