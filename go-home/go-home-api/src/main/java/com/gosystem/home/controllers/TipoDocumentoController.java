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

import com.gosystem.commons.adminUsers.dto.TipoIdentificacionDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.ITipoIdentificacionClient;


@RestController
@RequestMapping(path = "/tipoDocumento")
public class TipoDocumentoController {

	
private Logger logger;
	
	@Value("${spring.application.name}")
	private String nameApp;
	
	@Autowired
	private ITipoIdentificacionClient tipoIdentificacionClient;
	
	
	public TipoDocumentoController() {
		this.logger = UtilsLogs.getLogger(TipoDocumentoController.class.getName());
	}
	
	//GET ALL 
	@GetMapping(value = "/")
	public ResponseEntity<Object> consultarAll() {
		logger.info(nameApp + " GET ALL :: INICIO ");	
		List<TipoIdentificacionDTO> list =   this.tipoIdentificacionClient.getAll();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	} 
	
	
	

	
}
