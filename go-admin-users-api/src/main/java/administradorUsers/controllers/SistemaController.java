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

import administradorUsers.entitys.Systema;
import administradorUsers.services.SistemaService;


@RestController
@RequestMapping("/sistema")
public class SistemaController {

	private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;
	
	@Autowired
	private SistemaService service;

	public SistemaController() {
		logger = UtilsLogs.getLogger(SistemaController.class.getName());
	}
	
	
	// METODO SAVE
		@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> guardar(@RequestBody Systema json, HttpServletRequest req) {
			logger.info(nameApp + "guardar :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(json));
			try {
				service.save(json);
				return new ResponseEntity<Object>(null, HttpStatus.CREATED);
			} catch (AdministradorUserException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}
		}

		// EDITAR
		@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> editar(@RequestBody Systema json, HttpServletRequest req) {
			logger.info(nameApp + "Editar :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(json));
			try {
				service.edith(json);
				return new ResponseEntity<Object>(null, HttpStatus.OK);
			} catch (AdministradorUserException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}
		}

		// GET ALL
		@GetMapping(value = "/")
		public ResponseEntity<Object> consultarAll() {
			logger.info(nameApp + "GET ALL :: INICIO ");
			List<Systema> list = service.getAll();
			return new ResponseEntity<Object>(list, HttpStatus.OK);

		}

		// FIND CUSTUM
		@PostMapping(value = "/findCustom" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> findCustom(@RequestBody Systema json) {
			logger.info(nameApp + "FIND CUSTUM :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(json));
			try {
				Systema persona = service.find(json);
				return new ResponseEntity<Object>(persona, HttpStatus.OK);
			} catch (AdministradorUserException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}
		}

		// GET BY OD
		@GetMapping(value = "/{id}")
		public ResponseEntity<Object> consultarbyId(@PathVariable Long id) {
			logger.info(nameApp + "GET BY ID :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(id));
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}

		// DELETE O DESACTIVAR
		@PostMapping(value = "/delete" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> eliminar(@RequestBody Systema json) {
			logger.info(nameApp + "DELETE O DESACTIVAR :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(json));
			try {
				 service.delete(json);
				return new ResponseEntity<Object>(null, HttpStatus.OK);
			} catch (AdministradorUserException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
			}

		}

		// DELETE O DESACTIVAR
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Object> desactivar(@PathVariable Long id) {
			logger.info(nameApp + "DELETE O DESACTIVAR :: INICIO ");
			logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(id));
			return new ResponseEntity<Object>(null, HttpStatus.OK);

		}
	
}
