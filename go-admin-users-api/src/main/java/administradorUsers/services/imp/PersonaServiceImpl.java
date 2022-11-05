package administradorUsers.services.imp;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaContacto;
import administradorUsers.entitys.Systema;
import administradorUsers.entitys.Usuario;
import administradorUsers.enums.MethodsAdminUSerEnum;
import administradorUsers.mappers.PersonaContactoMapper;
import administradorUsers.repository.IPersonaContactoRepository;
import administradorUsers.repository.IPersonaRepository;
import administradorUsers.repository.IPrivilegiosRolUsuarioRepository;
import administradorUsers.repository.ISistemaRepository;
import administradorUsers.repository.IUsuariosRepository;
import administradorUsers.repository.RolesUsuarioRepository;
import administradorUsers.services.PersonaService;





@Service
public  class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private ISistemaRepository sistemaRepository;
	
	@Autowired
	private IPersonaRepository repository;
	
	@Autowired
	private IUsuariosRepository usuarioRepository;
	
	@Autowired
	private IPrivilegiosRolUsuarioRepository privilegiosRolUsuarioRepository;
	
	@Autowired
	private RolesUsuarioRepository rolesUsuarioRepository;
	
	
	@Autowired
	private IPersonaContactoRepository personaContactoRepository;


	private Logger logger;
	
	
	public PersonaServiceImpl() {
		logger = UtilsLogs.getLogger(PersonaServiceImpl.class.getName());
	}
	

	@Override
	public List<Persona> getAll() throws AdministradorUserException {
		return repository.findAll();
	}
	
	@Override
	public List<Persona> getAllBySistem(String nameSistem) throws AdministradorUserException {
		
		Optional<Systema> sistemaFind = this.sistemaRepository.findBynombre(nameSistem);
		if(! sistemaFind.isPresent()) {
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_SISTEMA_NO_EXISTE_EN_EL_SISTEMA);	
		}
		return repository.findBySistemaId(sistemaFind.get().getId().intValue());
	}
	

	@Override
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public void save(Persona p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,p, "Guardando persona..."));
		PersonaContacto pcTemp = null;
		try {	
			if( Objects.nonNull(p.getListPersonaContacto())  && p.getListPersonaContacto().size() > 0 ) {
				 pcTemp = p.getListPersonaContacto().get(0);
				 p.setListPersonaContacto(null);
			}
			
			Optional<Persona> personaFind = this.repository.findById(p.getId());
			if( personaFind.isPresent()) {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.PERSONA_YA_EXISTE);
			}else {
				this.repository.saveAndFlush(p);
				logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,null, "persona guardada"));
				
				if( Objects.nonNull(pcTemp) ) {
					this.savePersonaContacto(pcTemp,p);
				}
				logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,p, "proceso finalizado con exito..."));
			}			
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
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public void edith(Persona p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.PERSONA ,p, "Editando persona..."));
		PersonaContacto pcTemp = null;
		try {
			if( Objects.nonNull(p.getListPersonaContacto())  && p.getListPersonaContacto().size() > 0 ) {
				 pcTemp = p.getListPersonaContacto().get(0);
				 p.setListPersonaContacto(null);
			}
			
			Optional<Persona> personaFind = this.repository.findById(p.getId());	
			if( personaFind.isPresent()) {
				this.repository.saveAndFlush(p);
				logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.PERSONA ,null, "persona Editada"));
				if( Objects.nonNull(pcTemp) ) {
					this.savePersonaContacto(pcTemp,p);
				}
				logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.PERSONA ,p, "proceso finalizado con exito..."));
			}else {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.PERSONA_NO_EXISTE_EN_EL_SISTEMA);
			}			
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
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public void delete(Persona p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, EntityEnum.PERSONA, null, "Eliminando persona..."));
		try {
			Optional<Persona> personaFind = this.repository.findById(p.getId());	
			if( personaFind.isPresent()) {
				List<PersonaContacto> listPersonaContacto = this.personaContactoRepository.findByPersona(p);
				if( Objects.nonNull(listPersonaContacto) && listPersonaContacto.size() > 0 ) {
					for( PersonaContacto pc : listPersonaContacto) {
						this.personaContactoRepository.delete(pc);
					}
				}
				
				this.repository.delete(p);
				logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,null, "persona Eliminada"));
				logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA ,p, "proceso finalizado con exito..."));
			}else {
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.PERSONA_NO_EXISTE_EN_EL_SISTEMA);
			}			
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			if( e instanceof SQLIntegrityConstraintViolationException) {
				logger.severe(e.getMessage());
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_ELIMINANDO_EXISTE_DEPENDECIA);
			}else {
				logger.severe(e.getMessage());
				throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
			}

		}		
	}


	@Override
	@Transactional(rollbackFor = { PersistenceException.class, AdministradorUserException.class, Exception.class })
	public void desactivate(Persona persona) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, EntityEnum.PERSONA, null, "Desactivando persona..."));
		try {
				
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.PERSONA, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}	
		
	}
	
	
	private void saveAllPersonaContacto(List<PersonaContacto> list) throws AdministradorUserException{
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA, null, "GUARDANDO DATOS CONTACTO !"));
		for( PersonaContacto pc : list) {
			personaContactoRepository.save(pc);	
		}		
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO, null, "GUARDANDO  EXITOSO !"));	
	}
	
	private void savePersonaContacto(PersonaContacto pc, Persona p) throws AdministradorUserException{
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.PERSONA, null, "GUARDANDO DATOS CONTACTO !"));
		List<PersonaContacto> listPersona = this.personaContactoRepository.findByPersona(p);
		if( Objects.nonNull(listPersona) && listPersona.size() > 0 ) {
			for( PersonaContacto subpc :listPersona) {
				subpc.setActivo(false);
				this.personaContactoRepository.save(subpc);
			}
		}
		this.personaContactoRepository.save(pc);			
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO, null, "GUARDANDO  EXITOSO !"));	
	}


	

}
