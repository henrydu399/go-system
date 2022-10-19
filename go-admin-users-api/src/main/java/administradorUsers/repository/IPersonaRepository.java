package administradorUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaPK;

public interface IPersonaRepository extends JpaRepository<Persona, PersonaPK> {

}
