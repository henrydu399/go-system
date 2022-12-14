package administradorUsers.services;

import java.util.Optional;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Usuario;
import administradorUsers.logic.IEntityDao;

public interface UserService extends IEntityDao<Usuario> {
	
	 Optional<Usuario>	findByEmail(String email);
	 
	 public UsuarioDTO getUserForLogin(Usuario usuario) throws AdministradorUserException ;
	  
	 public UsuarioDTO saveUserSystemPublic(UsuarioDTO usuario) throws AdministradorUserException ;
	 
	 public UsuarioDTO saveUserSystem(UsuarioDTO usuario) throws AdministradorUserException ;
	 
	 public UsuarioDTO edithUserSystem(UsuarioDTO usuario) throws AdministradorUserException ;
	 
	 public void confirmUser(UsuarioDTO usuario) throws AdministradorUserException ;

}
