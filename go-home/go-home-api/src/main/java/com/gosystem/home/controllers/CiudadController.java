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

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IUbicacionClienteService;




@RestController
@RequestMapping(path = "/ciudad")
public class CiudadController {

	
private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;
	

	@Autowired
	private IUbicacionClienteService ubicacionService;
	
	public CiudadController() {
		this.logger = UtilsLogs.getLogger(CiudadController.class.getName());
		
	}
	

    //=======================================================================
    // -----CRUD NORMAL -----
    //=======================================================================
	
	@PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody CiudadDTO json, HttpServletRequest req) {	
		logger.info(nameApp + " guardar :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			//ciudadService.save(json);
			logger.info(nameApp + " guardar :: FIN ");
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	} 	
	
	// EDITAR
	@PutMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editar(@RequestBody CiudadDTO json, HttpServletRequest req) {
		logger.info(nameApp + " Editar :: INICIO ");	
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			//ciudadService.edith(json);
		logger.info(nameApp + " Editar :: FIN ");	
			return new ResponseEntity<Object>(null, HttpStatus.OK);	
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_GATEWAY);
	    }
	
	} 
	
	
	//GET ALL 
	@GetMapping(value = "/")
	public ResponseEntity<Object> consultarAll() {
		logger.info(nameApp + " GET ALL :: INICIO ");	
		List<CiudadDTO> list =   this.ubicacionService.getAllCiudades();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	} 
	
	//GET ALL 
	@GetMapping(value = "/public/")
	public ResponseEntity<Object> consultarPublicAll() {
		logger.info(nameApp + " GET ALL :: INICIO ");	
		List<CiudadDTO> list =   this.ubicacionService.getAllCiudades();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
		
	//FIND CUSTUM 
	@PostMapping(value = "/find/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> find(@RequestBody DepartamentoDTO json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			//DepartamentoDTO e = departamentoService.find(json);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
			
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }	
	} 
	
	
	//FIND ALL CUSTUM 
	@PostMapping(value = "/findAll" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(@RequestBody DepartamentoDTO json) {
		logger.info(nameApp + " FIND CUSTUM :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet( json));
		try {
			//List<DepartamentoDTO> personas = service.findAll(json);
			return new ResponseEntity<Object>(null, HttpStatus.OK);		
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }

		
	} 
	
	//GET BY OD
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> consultarbyId(@PathVariable Long id) {
		logger.info(nameApp + " GET BY ID :: INICIO ");	
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(id));
		try {
			//
			//CiudadDTO d =  ciudadService.getByid(id);
			return new ResponseEntity<Object>(null, HttpStatus.OK);		
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
	

	//DELETE O DESACTIVAR
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable Long id) {
		logger.info(nameApp + " DELETE O DESACTIVAR :: INICIO ");
		logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(id));
		try {
			//ciudadService.delete(id);
			return new ResponseEntity<Object>(null, HttpStatus.OK);		
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	//DELETE O DESACTIVAR
	@GetMapping(value = "/getAllByIdDepartamento/{idDepartamento}")
		public ResponseEntity<Object> getAllByIdDepartamento(@PathVariable Long idDepartamento) {
			logger.info(nameApp + " getAllByIdDepartamento :: INICIO ");
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(idDepartamento));
			try {
				//List<CiudadDTO>  list = this.ubicacionService.getAllByIdDepartamento(idDepartamento);
				return new ResponseEntity<Object>(null, HttpStatus.OK);		
			}catch (ParametrizacionException e) {
				logger.severe(e.getMessage());
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		    }
			
		}
	
	
}
