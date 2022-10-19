package administradorUsers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import administradorUsers.entitys.Systema;

public interface ISistemaRepository  extends JpaRepository<Systema, Long> {
	
	List<Systema> findByNombre(String name);
	
	Optional<Systema> findBynombre(String name);

}
