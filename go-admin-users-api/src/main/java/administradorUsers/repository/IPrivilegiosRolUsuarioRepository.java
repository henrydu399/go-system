package administradorUsers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.PrivilegiosRolUsuario;
import administradorUsers.entitys.PrivilegiosRolUsuarioPK;
import administradorUsers.entitys.Usuario;

public interface IPrivilegiosRolUsuarioRepository  extends JpaRepository<PrivilegiosRolUsuario, PrivilegiosRolUsuarioPK>{
	
	List<PrivilegiosRolUsuario> findByUsuario(Usuario usuario);

}
