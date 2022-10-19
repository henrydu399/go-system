package administradorUsers.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

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

import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.Persona;



@RestController
@RequestMapping("/rolesUsuarios")
public class RolesUsuariosController {
	
	
	private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public RolesUsuariosController() {
		logger = UtilsLogs.getLogger(RolesUsuariosController.class.getName());
	}
	
	
	// METODO SAVE
	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody Persona json, HttpServletRequest req) {	
		logger.info(nameApp + "guardar :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
		
	} 	
		
	
	// EDITAR
	@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editar(@RequestBody String json, HttpServletRequest req) {
		logger.info(nameApp + "Editar :: INICIO ");	
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	
	} 
	
	
	//GET ALL 
	@GetMapping(value = "/")
	public ResponseEntity<Object> consultarAll() {
		logger.info(nameApp + "GET ALL :: INICIO ");		
		return new ResponseEntity<Object>(null, HttpStatus.OK);

	} 
	
	//FIND CUSTUM 
	@PostMapping(value = "/findCustom" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findCustom(@RequestBody Persona json) {
		logger.info(nameApp + "FIND CUSTUM :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet( json));
		return new ResponseEntity<Object>(null, HttpStatus.OK);
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
