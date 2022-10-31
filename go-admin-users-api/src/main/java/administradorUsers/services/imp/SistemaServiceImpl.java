package administradorUsers.services.imp;

import java.util.List;
import java.util.Objects;
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

import administradorUsers.entitys.Systema;
import administradorUsers.entitys.Usuario;
import administradorUsers.logic.SistemaLogicImpl;
import administradorUsers.repository.ISistemaRepository;
import administradorUsers.services.SistemaService;
import administradorUsers.validations.SistemaValidation;




@Service
public class SistemaServiceImpl implements SistemaService{
	
	
	@Autowired
	private ISistemaRepository repository;
	
	@Autowired
	private SistemaValidation validation;
	
	@Autowired
	SistemaLogicImpl logic;

	private Logger logger;
	
	private final EntityEnum entity = EntityEnum.SYSTEMA ;
	
	
	
	
	public SistemaServiceImpl() {
		logger = UtilsLogs.getLogger(RolesSistemaServiceImpl.class.getName());
	}
	

	
	public List<Systema> getAll() throws AdministradorUserException {
		return repository.findAll();
		
	}
	


	
	public void save(Systema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, entity ,obj));
		try {
			logic.save(obj);
			validation.save(obj);
			if(obj.getId() != null) {
				Optional<Systema> personaFind = this.repository.findById(obj.getId());		
				if( personaFind.isPresent()) {
					throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_SISTEMA_YA_EXISTE_EN_EL_SISTEMA);
				}	
			}
			List<Systema> list = this.repository.findByNombre(obj.getNombre());
			if(list != null && list.size() > 0 ) {
				throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_SISTEMA_YA_EXISTE_EN_EL_SISTEMA);
			}
			this.repository.save(obj);
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_GUARDAR);
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}	
	}

	
	public void edith(Systema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, entity ,obj));	
		try {	
			Optional<Systema> find = this.repository.findById(obj.getId());	
			if( ! find.isPresent()) {
				throw new AdministradorUserException( entity, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_ROL_NO_EXISTE_EN_EL_SISTEMA);	
			}	
			this.repository.save(obj);			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.EDITH, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_MODIFICAR);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

	
	public List<Systema> findAll(Systema obj) throws AdministradorUserException {	
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, entity ,obj));
		try {
			Example<Systema>  entByFind =  Example.of(obj); 
			List<Systema> list = this.repository.findAll(entByFind);	
			if( list != null && list.size() > 0) {
				return list;
			}else {
				throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO , ErrorConstantes.NO_EXISTEN_REGISTROS);
			}					
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, ErrorConstantes.ERROR_CONSULTANDO);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}
	}
	
	
	

	public Systema  find(Systema obj) throws AdministradorUserException {		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, entity ,obj));
		List<Systema> list = null;
		try {
			Example<Systema>  entByFind =  Example.of(obj); 
			list = this.repository.findAll(entByFind);			
			if( Objects.isNull(list)  && list.size() == 0) {
				throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO , ErrorConstantes.NO_SE_ECONTRARON_REGISTRO);	
			}			
			return list.get(0);
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, null);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , null);
		}
	}




	public void delete(Systema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, entity ,obj));
		try {
			Optional<Systema> find = this.repository.findById(obj.getId());	
			if( ! find.isPresent()) {
				throw new AdministradorUserException( entity, MethodsEnum.DELETE, LayerEnum.LOGIC , ErrorConstantes.ERROR_SISTEMA_NO_EXISTE_EN_EL_SISTEMA);	
			}				
			this.repository.delete(find.get());
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.DELETE, LayerEnum.DAO, null);
		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , null);
		}
		
	}


	public Optional<Systema> findBynombre(String name) {
		return this.repository.findBynombre(name);
	}



	@Override
	public void desactivate(Usuario usuario) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

	



	

}
