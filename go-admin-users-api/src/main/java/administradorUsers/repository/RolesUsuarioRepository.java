package administradorUsers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.RolesUsuarioPK;
import administradorUsers.entitys.Usuario;



public interface RolesUsuarioRepository extends JpaRepository<RolesUsuario, RolesUsuarioPK> {
	
	List<RolesUsuario> findByUsuario(Usuario usuario);

}
