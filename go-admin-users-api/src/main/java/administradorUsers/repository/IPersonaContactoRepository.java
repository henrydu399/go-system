package administradorUsers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaContacto;
import administradorUsers.entitys.PersonaContactoPK;

public interface IPersonaContactoRepository  extends JpaRepository<PersonaContacto, PersonaContactoPK>{
	
	List<PersonaContacto> findByPersona(Persona persona);

}
