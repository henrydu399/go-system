package administradorUsers.controllers;

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

import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.Usuario;
import administradorUsers.services.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	private Logger logger;
	
	@Autowired
	private PersonaService personaService;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public PersonaController() {
		logger = UtilsLogs.getLogger(PersonaController.class.getName());
	}
	
	
	// METODO SAVE
	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody Persona json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			personaService.save(json);
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
	public ResponseEntity<Object> editar(@RequestBody Persona json, HttpServletRequest req) {
		logger.info(nameApp + " Editar :: INICIO ");	
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			personaService.edith(json);
			return new ResponseEntity<Object>(null, HttpStatus.OK);

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
		logger.info(nameApp + " GET ALL :: INICIO ");	
		List<Persona> listPersonas =  personaService.getAll();
		return new ResponseEntity<Object>(listPersonas, HttpStatus.OK);

	} 
	
	//GET ALL 
	@GetMapping(value = "/sistema/{sistemaName}")
	public ResponseEntity<Object> getAllBySistem(@PathVariable String sistemaName) {
		logger.info(nameApp + "  getAllBySistem :: INICIO ");	
		List<Persona> listPersonas =  personaService.getAllBySistem(sistemaName);
		return new ResponseEntity<Object>(listPersonas, HttpStatus.OK);

	} 
	
	//FIND CUSTUM 
	@PostMapping(value = "/find/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> find(@RequestBody Persona json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			Persona persona = personaService.find(json);
			return new ResponseEntity<Object>(persona, HttpStatus.OK);	
		}catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }catch (Exception e) {
	    	logger.severe(e.getMessage());
	    	return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		
	} 
	
	
	//FIND ALL CUSTUM 
	@PostMapping(value = "/findAll/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(@RequestBody Persona json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));

		try {
			List<Persona> personas = personaService.findAll(json);
			return new ResponseEntity<Object>(personas, HttpStatus.OK);
			
		}catch (AdministradorUserException e) {
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
	public ResponseEntity<Object> delete(@RequestBody Persona persona, HttpServletRequest req) {
		logger.info(nameApp + " DELETE  :: INICIO ");
		try {
			personaService.delete(persona);
			return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
		}catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }	
		
	}
	
	//DELETE O DESACTIVAR
	@PostMapping(value = "/desactivate/")
	public ResponseEntity<Object> desactivate(@RequestBody Persona persona, HttpServletRequest req) {
		logger.info(nameApp + "  O DESACTIVAR :: INICIO ");
		try {
			personaService.desactivate(persona);
			return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
		}catch (AdministradorUserException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }		
	}

}
