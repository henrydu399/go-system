package co.system.out.emailservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.system.out.emailservice.model.Email;

public interface IEmailsRepository  extends JpaRepository<Email, String>{
	
	@Query("SELECT u FROM Email u WHERE u.intentos < 3")
	List<Email> getAllIntentos();

}
