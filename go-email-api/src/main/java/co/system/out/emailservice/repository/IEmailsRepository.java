package co.system.out.emailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.system.out.emailservice.model.Email;

public interface IEmailsRepository  extends JpaRepository<Email, String>{

}
