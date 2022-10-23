package administradorUsers.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.utils.UtilsLogs;


import administradorUsers.entitys.TipoIdentificacion;
import administradorUsers.services.ITiposIdentificacionService;


@RestController
@RequestMapping("/TipoIdentificacion")
public class TipoIdentificacionController {
	
	
    private Logger logger;
	
	@Autowired
	private ITiposIdentificacionService tiposIdentificacionService;
	
	@Value("${spring.application.name}")
	private String nameApp;

	public TipoIdentificacionController() {
		logger = UtilsLogs.getLogger(PersonaController.class.getName());
	}
	
	
	
	//GET ALL 
	@GetMapping(value = "/")
	public ResponseEntity<Object> consultarAll() {
		logger.info(nameApp + " GET ALL :: INICIO ");	
		List<TipoIdentificacion> list =  tiposIdentificacionService.getAll();
		return new ResponseEntity<Object>(list, HttpStatus.OK);

	} 

}
