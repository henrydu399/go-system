package administradorUsers.services;

import java.util.Optional;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Usuario;
import administradorUsers.logic.IEntityDao;

public interface UserService extends IEntityDao<Usuario> {
	
	 Optional<Usuario>	findByEmail(String email);
	 
	 public UsuarioDTO getUserForLogin(Usuario usuario) throws AdministradorUserException ;
	 
	 
	 public void saveUserSystem(UsuarioDTO usuario) throws AdministradorUserException ;

}
