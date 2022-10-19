package com.gosystem.parametricas.api.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.parametricas.api.entitys.Parametro;
import com.gosystem.parametricas.api.services.IParametrosService;





@RestController
@RequestMapping("/parametrica")
public class ParametricasController {
	
	
	private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;
	
	@Autowired
	private IParametrosService parametrosService;
	
	

	public ParametricasController() {
		logger = this.logger.getLogger(ParametricasController.class.getName());
	}
	
	//GET ket
		@GetMapping(value = "/{key}" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> consultarAll(@PathVariable String key) {
			logger.info(nameApp + " GET BY ID :: INICIO ");	
			logger.info(nameApp + " Request ::  " + UtilGson.SerializeObjet(key));
			try {
				Parametro p = this.parametrosService.getParametro(key);	
				return new ResponseEntity<Object>(p, HttpStatus.OK);
			}catch (ParametrizacionException e) {
		    	return new ResponseEntity<Object>(e.getMensaje(), HttpStatus.BAD_REQUEST);
			}
		} 
		
		@GetMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> consultarAll() {
			logger.info(nameApp + " GET ALL ID :: INICIO ");	
			try {
				List<Parametro> p = this.parametrosService.getAll();	
				return new ResponseEntity<Object>(p, HttpStatus.OK);
			}catch (ParametrizacionException e) {
		    	return new ResponseEntity<Object>(e.getMensaje(), HttpStatus.BAD_REQUEST);
			}
		} 	

}
