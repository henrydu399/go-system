package co.system.out.emailservice.services.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.EmailException;
import com.gosystem.commons.utils.UtilsLogs;

import co.system.out.emailservice.api.PlantillaController;
import co.system.out.emailservice.enums.EmailEntityEnum;
import co.system.out.emailservice.model.Plantilla;
import co.system.out.emailservice.repository.IPlantillasRepository;
import co.system.out.emailservice.services.IPlantillaService;


@Service
public class PlantillaServiceimpl implements IPlantillaService{

	@Autowired
	IPlantillasRepository plantillaRepository;
	
	private Logger logger;
	
	public PlantillaServiceimpl() {
		logger = UtilsLogs.getLogger(PlantillaServiceimpl.class.getName());
	}
	/**
	 * Metodo que guarda las plantillas para los correos de todos los sitemas
	 * @apiNote El campo dataString debe estar en Base64
	 */
	@Override
	public void save(Plantilla p) throws EmailException {
		
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EmailEntityEnum.PLANTILLA ,p));
		logger.info("GUARDANDO PLANTILLA PARA EMAIL API....");
		try {		
			
			if(  Objects.isNull(p.getName()) ) {
				throw new EmailException(EmailEntityEnum.PLANTILLA, MethodsEnum.SAVE, LayerEnum.SERVICE, "El campo name nb puede estar vacio o nulo");
			}
			if(  Objects.isNull(p.getSistema()) ) {
				throw new EmailException(EmailEntityEnum.PLANTILLA, MethodsEnum.SAVE, LayerEnum.SERVICE, "El campo systema nb puede estar vacio o nulo");
			}
			
			if(  Objects.isNull(p.getDataString()) ) {
				throw new EmailException(EmailEntityEnum.PLANTILLA, MethodsEnum.SAVE, LayerEnum.SERVICE, "El campo data nb puede estar vacio o nulo");
			}
			
			Optional<Plantilla> plantilla = plantillaRepository.findByName(p.getName());
			
			if( plantilla.isPresent()) {
				throw new EmailException(EmailEntityEnum.PLANTILLA, MethodsEnum.SAVE, LayerEnum.SERVICE, "Ya existe una plantilla con ese nombre");
			}
			
			p.setData( p.getDataString());

			plantillaRepository.save(p);

			logger.info("PLANTILLA GUARDADA");
						
			
		}catch (EmailException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new EmailException( EmailEntityEnum.PLANTILLA, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}
		
		
		
	}

}
