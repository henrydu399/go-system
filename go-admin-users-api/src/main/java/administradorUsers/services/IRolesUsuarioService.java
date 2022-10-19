package administradorUsers.services;


import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.Usuario;
import administradorUsers.logic.IEntityDao;



public interface IRolesUsuarioService extends IEntityDao<RolesUsuario>{
	
	 List<RolesUsuario> findByUsuario(Usuario usuario) throws AdministradorUserException ;

}
