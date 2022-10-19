package administradorUsers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import administradorUsers.entitys.Usuario;
import administradorUsers.entitys.UsuarioPK;

public interface IUsuariosRepository extends JpaRepository<Usuario, UsuarioPK> {

   Optional<Usuario>	findByEmail(String email);
   
   
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = " SELECT  u.* "
			+ "from usuario u "
			+ "INNER JOIN persona p ON "
			+ "	( u.id_tipo_identificacion = p.id_tipo_identificacion AND u.numero_identificacion = p.numero_identificacion) "
			+ "INNER JOIN roles_usuario ru ON "
			+ "	(u.id = ru.id_usuario AND  u.id_tipo_identificacion = ru.id_tipo_identificacion AND u.numero_identificacion = ru.numero_identificacion ) "
			+ "INNER JOIN roles_sistema rs  ON "
			+ "( rs.id = ru.id_rol_sistema  AND rs.id_sistema =ru.id_sistema  AND rs.nombre_rol = ru.nombre_rol_sistema  )"
			+ "INNER JOIN systema s ON "
			+ "( rs.id_sistema = s.id) "
			+ "WHERE "
			+ "u.email = ?1 AND "
			+ "s.nombre =?2 "
			+ " ")
   Optional<Usuario>	findUserForLogin(String email ,String systemName);
	
	
	@Transactional(readOnly = false)
	@Query(nativeQuery = true, value = " SELECT "
			+ " MAX( CAST(p.NUMERO_IDENTIFICACION AS  UNSIGNED INT)) + 1 as NUMERO_IDENTIFICACION "
			+ " from persona p where p.ID_TIPO_IDENTIFICACION = (SELECT ti.id  FROM tipo_identificacion ti WHERE ti.prefijo = 'NAN') ")
	Long findMaxNumeroIdentificacion();
	
	@Transactional(readOnly = false)
	@Query(nativeQuery = true, value = " SELECT (MAX (u.ID) +1 ) as id FROM usuario u ;  ")
	long findUltimoId();
	
   
 
	
}
