package administradorUsers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.RolesUsuarioPK;
import administradorUsers.entitys.Usuario;



public interface RolesUsuarioRepository extends JpaRepository<RolesUsuario, RolesUsuarioPK> {
	
	List<RolesUsuario> findByUsuario(Usuario usuario);
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = " SELECT ru.* from roles_usuario ru "
			+ " INNER JOIN roles_sistema rs   ON ( "
			+ "									rs.id  = ru.id_rol_sistema AND "
			+ "									rs.id_sistema  = ru.id_sistema AND "
			+ "									rs.NOMBRE_ROL  = ru.nombre_rol_sistema "
			+ "								 )"
			+ " INNER JOIN systema s          ON(  s.id = rs.id_sistema)"
			+ " INNER JOIN usuario u          ON ( "
			+ "									u.ID = ru.id_usuario AND "
			+ "									u.ID_TIPO_IDENTIFICACION  = ru.id_tipo_identificacion AND "
			+ "									u.NUMERO_IDENTIFICACION   = ru.numero_identificacion "
			+ "							     )						"
			+ " INNER JOIN persona p          ON ( u.ID_TIPO_IDENTIFICACION = p.ID_TIPO_IDENTIFICACION  AND"
			+ "								   u.NUMERO_IDENTIFICACION  = p.NUMERO_IDENTIFICACION "
			+ "								 )"
			+ " INNER JOIN tipo_identificacion ti  ON ( p.ID_TIPO_IDENTIFICACION = ti.id)"
			+ " WHERE s.nombre = ?1 ")
	List<RolesUsuario> findBysistema(String sistemaName );

}
