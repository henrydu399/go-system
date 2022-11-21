package administradorUsers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaPK;
import administradorUsers.entitys.PersonaSistema;


public interface IPersonaRepository extends JpaRepository<Persona, PersonaPK> {
	
	
	//List<Persona> findByPersonaSistema(PersonaSistema personaSistema);
	
	
	@Transactional(readOnly = true)
	@Query(nativeQuery = true, value = " SELECT * FROM persona p  "
			+ " INNER JOIN persona_sistema ps ON (p.NUMERO_IDENTIFICACION= ps.numero_identificacion AND p.ID_TIPO_IDENTIFICACION = ps.id_tipo_identificacion) "
			+ " where ps.id_sistema = ?1 ")
   List<Persona>	findBySistemaId(int sistemaId);

}
