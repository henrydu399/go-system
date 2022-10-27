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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.enums.PrivilegioOperacion;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientUsers;
import com.gosystem.home.services.IUserService;
import com.gosystem.home.util.UtilPrivilegioMetodo;
import com.gosystem.home.util.UtilToken;




@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/usuario")
public class UsuarioController {
	
private Logger logger;
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private IAdministracionClientUsers administracionClientUsers;
	
	@Autowired
	private UtilToken utilToken;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public UsuarioController() {
		logger = UtilsLogs.getLogger(UsuarioController.class.getName());
	}
	
	
	 //=======================================================================
    // -----CRUD NORMAL SEGURIDAD ON -----
    //=======================================================================
	
	// METODO SAVE
	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody UsuarioDTO json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			service.save(json);
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
	public ResponseEntity<Object> editar(@RequestBody UsuarioDTO json, HttpServletRequest req) {
		logger.info(nameApp + " Editar :: INICIO ");	
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			service.edith(json);
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
			List<UsuarioDTO> list =   service.getAll();
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
	@PostMapping(value = "/find" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> find(@RequestBody UsuarioDTO json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));

		try {
			UsuarioDTO e = service.find(json);
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
	@PostMapping(value = "/findAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(@RequestBody UsuarioDTO json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			List<UsuarioDTO> personas = service.findAll(json);
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
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable Long id) {
		logger.info(nameApp + " DELETE O DESACTIVAR :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(id));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
		
	}
	
	 //=======================================================================
    // ----- END CRUD NORMAL SEGURIDAD ON -----
    //=======================================================================
	
	/**
	 * Metodo que permite crear Usuarios desde la paguina publica
	 * @SEGURIDAD NO 
	 */
	@PostMapping(value = "/public/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveForSystemPublic(@RequestBody UsuarioDTO json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			service.saveForSystemPublic(json);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (HomeException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

	} 	
	
	/**
	 * Metodo que permite crear Usuarios + persona + persona contacto + rol Usuariio
	 * @SEGURIDAD SI 
	 */
	@PostMapping(value = "/saveForSystem/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveForSystem(@RequestBody UsuarioDTO json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			service.saveForSystem(json);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (HomeException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@PostMapping(value = "/confirm/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> confirmUser(@RequestBody UsuarioDTO json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			this.administracionClientUsers.confirmUser(json);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	logger.severe(e.getMessage());
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

	} 
	
	 //=======================================================================
    // ----- PUBLIC  -----
    //=======================================================================
	

}
