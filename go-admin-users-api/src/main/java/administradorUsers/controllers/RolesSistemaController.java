package administradorUsers.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.RolesSistema;
import administradorUsers.services.RolesSistemaService;



@RestController
@RequestMapping("/rolSistema")
public class RolesSistemaController {
	
	private Logger logger;

	@Value("${spring.application.name}")
	private String nameApp;

	@Autowired
	RolesSistemaService rolesSistemaService;
	
	public RolesSistemaController() {
		logger = UtilsLogs.getLogger(RolesController.class.getName());
	}
	
	/**
	 * Metodo que obtiene los roles de un sistema
	 * @param sistemaName
	 * @return
	 */
	@GetMapping(value = "/findBySistemaName/{sistemaName}")
	public ResponseEntity<Object> consultarbyId(@PathVariable String sistemaName) {
		logger.info(nameApp + "GET BY ID :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(sistemaName));
		try {
			List<RolesSistema> list = rolesSistemaService.findBySystema(sistemaName);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
			}
	}

}
