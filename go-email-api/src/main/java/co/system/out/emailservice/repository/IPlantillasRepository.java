package co.system.out.emailservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.system.out.emailservice.model.Plantilla;

public interface IPlantillasRepository  extends JpaRepository<Plantilla, Integer>{
	
	
	Optional<Plantilla> findByName(String name);

}
