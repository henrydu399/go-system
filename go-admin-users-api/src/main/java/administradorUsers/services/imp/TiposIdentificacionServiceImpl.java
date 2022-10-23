package administradorUsers.services.imp;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.controllers.PersonaController;
import administradorUsers.entitys.TipoIdentificacion;
import administradorUsers.repository.ITipoIdentificacionRepository;
import administradorUsers.services.ITiposIdentificacionService;
import administradorUsers.services.PersonaService;

@Service
public class TiposIdentificacionServiceImpl implements ITiposIdentificacionService{
	
	
	   private Logger logger;
		
		@Autowired
		private PersonaService personaService;
		
		@Value("${spring.application.name}")
		private String nameApp;

		public TiposIdentificacionServiceImpl() {
			logger = UtilsLogs.getLogger(PersonaController.class.getName());
		}
		

	@Autowired
	ITipoIdentificacionRepository tipoIdentificacionRepository;
	
	@Override
	public List<TipoIdentificacion> getAll() throws AdministradorUserException {
		return tipoIdentificacionRepository.findAll();
	}

}
