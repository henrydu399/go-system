package com.gosystem.home.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;

import com.gosystem.commons.enums.PrivilegioOperacion;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.services.IPersonaService;
import com.gosystem.home.util.UtilPrivilegioMetodo;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public PersonaController() {
		logger = UtilsLogs.getLogger(PersonaController.class.getName());
	}
	
	
	// METODO SAVE
		@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> guardar(@RequestBody PersonaDTO json, HttpServletRequest req) {	
			logger.info(nameApp + " guardar :: INICIO ");
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
			try {
				personaService.save(json);
				return new ResponseEntity<Object>(null, HttpStatus.CREATED);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}

		} 	
			
		
		// EDITAR
		@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> editar(@RequestBody PersonaDTO json, HttpServletRequest req) {
			logger.info(nameApp + " Editar :: INICIO ");	
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
			try {
				personaService.edith(json);
				return new ResponseEntity<Object>(null, HttpStatus.OK);

			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}	
		} 
		
		
		//GET ALL 
		@GetMapping(value = "/")
		public ResponseEntity<Object> consultarAll(HttpServletRequest req ) {
			logger.info(nameApp + " GET ALL :: INICIO ");	
			try {
				UtilPrivilegioMetodo.isPrivilegioOk(req, PrivilegioOperacion.getAll);
				List<PersonaDTO> list =   personaService.getAll();
				return new ResponseEntity<Object>(list, HttpStatus.OK);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}


		} 
		
		//FIND CUSTUM 
		@PostMapping(value = "/find/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> find(@RequestBody PersonaDTO json) {
			logger.info(nameApp + " FIND CUSTUM :: INICIO ");
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
			try {
				PersonaDTO e = personaService.find(json);
				return new ResponseEntity<Object>(e, HttpStatus.OK);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}

			
		} 
		
		
		//FIND ALL CUSTUM 
		@PostMapping(value = "/findAll/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> findAll(@RequestBody PersonaDTO json) {
			logger.info(nameApp + " FIND CUSTUM :: INICIO ");
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
			try {
				List<PersonaDTO> personas = personaService.findAll(json);
				return new ResponseEntity<Object>(personas, HttpStatus.OK);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }catch (Exception e) {
		    	logger.severe(e.getMessage());
		    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}	
		} 
		
		//GET BY OD
		@GetMapping(value = "/{id}")
		public ResponseEntity<Object> consultarbyId(@PathVariable Long id) {
			logger.info(nameApp + " GET BY ID :: INICIO ");	
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(id));
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		

		//DELETE O DESACTIVAR
		@PostMapping(value = "/delete/")
		public ResponseEntity<Object> delete(@RequestBody PersonaDTO usuario, HttpServletRequest req) {
			logger.info(nameApp + " DELETE  :: INICIO ");
			try {
				personaService.delete(usuario);
				return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }	
			
		}
		
		//DELETE O DESACTIVAR
		@PostMapping(value = "/desactivate/")
		public ResponseEntity<Object> desactivate(@RequestBody PersonaDTO usuario, HttpServletRequest req) {
			logger.info(nameApp + "  O DESACTIVAR :: INICIO ");
			try {
				personaService.desactivate(usuario);
				return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
			}catch (HomeException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }		
		}
		
	

}
