package com.gosystem.home.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientUsers;


@RestController
@RequestMapping("/rolesSistema")
public class RolesSistemaController {

	

	private Logger logger;
	
	@Value("${application.name}")
	private String nameApp;
	

	
	@Autowired
	private IAdministracionClientUsers administracionClientUsers;
	
	public RolesSistemaController() {
		this.logger = UtilsLogs.getLogger(RolesSistemaController.class.getName());
	}
	
	
	//GET ALL 
		@GetMapping(value = "/")
		public ResponseEntity<Object> consultarAll() {
			logger.info(nameApp + " GET ALL :: INICIO ");	
			List<RolesSistemaDTO> list =   this.administracionClientUsers.getRolesBySystema(nameApp);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} 
	
}
