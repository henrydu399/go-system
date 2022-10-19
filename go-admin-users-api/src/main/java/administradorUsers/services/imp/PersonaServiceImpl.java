package administradorUsers.services.imp;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.Persona;
import administradorUsers.repository.IPersonaRepository;
import administradorUsers.services.PersonaService;





@Service
public  class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	IPersonaRepository repository;


	private Logger logger;
	
	
	public PersonaServiceImpl() {
		logger = UtilsLogs.getLogger(PersonaServiceImpl.class.getName());
	}
	

	@Override
	public List<Persona> getAll() throws AdministradorUserException {
		return repository.findAll();
		
	}

	@Override
	public void save(Persona p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,p));
		try {
			
			Optional<Persona> personaFind = this.repository.findById(p.getId());
			
			if( personaFind.isPresent()) {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.PERSONA_YA_EXISTE);
			}else {
				this.repository.save(p);
			}			
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.SAVE, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

	@Override
	public void edith(Persona p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.PERSONA ,p));
		
		try {
			Optional<Persona> personaFind = this.repository.findById(p.getId());	
			if( personaFind.isPresent()) {
				this.repository.save(p);	
			}else {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.PERSONA_NO_EXISTE_EN_EL_SISTEMA);
			}			
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_MODIFICAR);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

	@Override
	public List<Persona> findAll(Persona p) throws AdministradorUserException {
		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.PERSONA ,p));
		try {
			Persona persona = (Persona) p;	
			Example<Persona> personaByFind =  Example.of(persona); 
			List<Persona> listPersonas = this.repository.findAll(personaByFind);	
			
			
			if( listPersonas != null && listPersonas.size() > 0) {
				return listPersonas;
			}else {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO , ErrorConstantes.NO_EXISTEN_REGISTROS);
			}			
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, ErrorConstantes.ERROR_CONSULTANDO);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}
	}
	
	
	@Override
	public Persona find(Persona p) throws AdministradorUserException {
		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.PERSONA ,p));
		try {
			Persona persona = (Persona) p;	
			Example<Persona> personaByFind =  Example.of(persona); 
			List<Persona> listPersonas = this.repository.findAll(personaByFind);	
			
			
			if( listPersonas != null && listPersonas.size() > 0) {
				return listPersonas.get(0);
			}else {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO , ErrorConstantes.NO_SE_ECONTRARON_REGISTRO);
			}			
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, null);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , null);
		}
	}

	@Override
	public void delete(Persona p) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}


	

}
