package co.system.out.emailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.system.out.emailservice.model.User;





public interface IUsuariosRepository  extends JpaRepository<User, Long> {
	

	
	public User findByEmail (String uEmailSendEmail);
}
