package co.system.out.emailservice.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.emailapi.dto.EmailDto;
import com.gosystem.commons.exceptions.EmailException;

import co.system.out.emailservice.model.User;
import co.system.out.emailservice.services.EnvioEmail;
import co.system.out.emailservice.services.IUserService;

@RestController
@RequestMapping(name = "/")
public class ServiceEmailController {

	@Autowired
	private EnvioEmail emailService;
	
	@Autowired
	private IUserService userSerivce;

	@PostMapping("send/")
	public ResponseEntity<Object> EmailFull(@RequestBody EmailDto emp) {
		try {
			emailService.sendSave(emp);
			return ResponseEntity.status(200).build();
		} catch (EmailException e2) {
			return ResponseEntity.status(500).body(e2.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(ErrorConstantes.ERROR_GENERAL.toString());
		}

	}
	
	@PostMapping("create/user/")
	public ResponseEntity<Object> EmailFull(@RequestBody User emp) {
		try {
			userSerivce.createUSer(emp);
			return ResponseEntity.status(200).build();
		} catch (EmailException e2) {
			return ResponseEntity.status(500).body(e2.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(ErrorConstantes.ERROR_GENERAL.toString());
		}

	}

}
