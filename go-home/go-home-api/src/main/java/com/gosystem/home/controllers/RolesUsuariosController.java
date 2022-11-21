package com.gosystem.home.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IRolesUsuarioClientService;
import com.gosystem.home.services.IRolesUsuarioService;







@RestController
@RequestMapping("/rolesUsuarios")
public class RolesUsuariosController {
	
	
	private Logger logger;
	
	@Autowired
	private IRolesUsuarioService rolesUsuarioService;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public RolesUsuariosController() {
		logger = UtilsLogs.getLogger(RolesUsuariosController.class.getName());
	}
	
	
	// METODO SAVE
	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody RolesUsuarioDTO json, HttpServletRequest req) {	
		logger.info(nameApp + "guardar :: INICIO ");
			try {
				rolesUsuarioService.save(json);
				return new ResponseEntity<Object>(null, HttpStatus.CREATED);
			}catch (AdministradorUserException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}		
	} 	
		
	
	// EDITAR
	@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editar(@RequestBody RolesUsuarioDTO json, HttpServletRequest req) {
		logger.info(nameApp + "Editar :: INICIO ");	
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		try {
			rolesUsuarioService.edith(json);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	logger.severe(e.getMessage());
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	
	} 
	
	
	//GET ALL 
	@GetMapping(value = "/")
	public ResponseEntity<Object> consultarAll() {
		logger.info(nameApp + "GET ALL :: INICIO ");		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	} 
	
	//GET ALL 
		@GetMapping(value = "/sistema/{sistemaName}")
		public ResponseEntity<Object> getAllBySistem(@PathVariable String sistemaName) {
			logger.info(nameApp + "  getAllBySistem :: INICIO ");	
			List<RolesUsuarioDTO> list =  rolesUsuarioService.getAllSistemaByName(sistemaName);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} 
	
	//FIND CUSTUM 
	@PostMapping(value = "/findCustom" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findCustom(@RequestBody RolesUsuarioDTO json) {
		logger.info(nameApp + "FIND CUSTUM :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	} 
	
	//FIND BY ID  
	@PostMapping(value = "/findById/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findById(@RequestBody RolesUsuarioDTO json) {
		logger.info(nameApp + "FIND BY ID :: INICIO ");
		RolesUsuarioDTO out = this.rolesUsuarioService.findById(json);
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		return new ResponseEntity<Object>(out, HttpStatus.OK);
	} 
	
	
	//GET BY OD
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> consultarbyId(@PathVariable Long id) {
		logger.info(nameApp + "GET BY ID :: INICIO ");	
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(id));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

	//DELETE O DESACTIVAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable Long id) {
		logger.info(nameApp + "DELETE O DESACTIVAR :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(id));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
		
	}

}
