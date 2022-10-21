package co.system.out.emailservice.api;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.exceptions.EmailException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import co.system.out.emailservice.model.Plantilla;
import co.system.out.emailservice.services.IPlantillaService;




@RestController
@RequestMapping(name = "/")
public class PlantillaController {
	
	private Logger logger;
	
	
	@Value("${spring.application.name}")
	private String nameApp;
	
	@Autowired
	private IPlantillaService plantillaService;
	
	public PlantillaController() {
		logger = UtilsLogs.getLogger(PlantillaController.class.getName());
	}
	
	// METODO SAVE
	@PostMapping(value = "plantilla/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody Plantilla json, HttpServletRequest req) {
		logger.info(nameApp + "guardar :: INICIO ");
		logger.info(nameApp + "Request ::  " + UtilGson.SerializeObjet(json));
		try {
			plantillaService.save(json);
			return new ResponseEntity<Object>(null, HttpStatus.CREATED);
		} catch (EmailException e) {
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
