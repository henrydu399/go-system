package administradorUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.PersonaContacto;
import administradorUsers.entitys.PersonaContactoPK;

public interface IPersonaContactoRepository  extends JpaRepository<PersonaContacto, PersonaContactoPK>{

}
