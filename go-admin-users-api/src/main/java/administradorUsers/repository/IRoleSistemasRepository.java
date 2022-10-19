package administradorUsers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import administradorUsers.entitys.RolesSistema;
import administradorUsers.entitys.RolesSistemaPK;

public interface IRoleSistemasRepository extends JpaRepository<RolesSistema, RolesSistemaPK> {
	
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = " SELECT rs.* FROM roles_sistema rs  "
			+ " INNER JOIN systema s ON ( s.id = rs.id_sistema) "
			+ " WHERE s.nombre = ?1  ")
	List<RolesSistema> findRolesBySistema(String sistemaName);

}
