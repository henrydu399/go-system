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

import administradorUsers.entitys.RolesSistema;
import administradorUsers.entitys.Usuario;
import administradorUsers.enums.MethodsAdminUSerEnum;
import administradorUsers.logic.RolesSistemaLogicImpl;
import administradorUsers.repository.IRoleSistemasRepository;
import administradorUsers.services.RolesSistemaService;




@Service
public class RolesSistemaServiceImpl implements RolesSistemaService{

	@Autowired
	IRoleSistemasRepository repository;
	
	@Autowired
	RolesSistemaLogicImpl logic;


	private Logger logger;
	
	private final EntityEnum entity = EntityEnum.ROLES_SISTEMA ;
	
	
	public RolesSistemaServiceImpl() {
		logger = UtilsLogs.getLogger(RolesSistemaServiceImpl.class.getName());
	}
	

	
	public List<RolesSistema> getAll() throws AdministradorUserException {
		return repository.findAll();
		
	}
	


	
	public void save(RolesSistema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, entity ,obj));
		try {			
			logic.save(obj);			
			Optional<RolesSistema> personaFind = this.repository.findById(obj.getId());		
			if( personaFind.isPresent()) {
				throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_ROL_EXISTE_EN_EL_SISTEMA);
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
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}	
	}

	
	public void edith(RolesSistema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, entity ,obj));	
		try {	
			Optional<RolesSistema> find = this.repository.findById(obj.getId());	
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

	
	public List<RolesSistema> findAll(RolesSistema obj) throws AdministradorUserException {	
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, entity ,obj));
		try {
			Example<RolesSistema>  entByFind =  Example.of(obj); 
			List<RolesSistema> list = this.repository.findAll(entByFind);	
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
	
	
	
	@SuppressWarnings("null")
	public RolesSistema  find(RolesSistema obj) throws AdministradorUserException {		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, entity ,obj));
		try {
			Example<RolesSistema>  entByFind =  Example.of(obj); 
			List<RolesSistema> list = this.repository.findAll(entByFind);			
			if( list == null && list.size() == 0) {
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



	@Override
	public void delete(RolesSistema obj) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.DELETE, entity ,obj));
		try {
			Optional<RolesSistema> find = this.repository.findById(obj.getId());	
			if( ! find.isPresent()) {
				throw new AdministradorUserException( entity, MethodsEnum.EDITH, LayerEnum.LOGIC , ErrorConstantes.ERROR_ROL_NO_EXISTE_EN_EL_SISTEMA);	
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

	
    public List<RolesSistema> findBySystema(String sistemaName)throws AdministradorUserException{
		logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.FIND_ROL_SISTEM_BY_SYSTEMA_NAME, entity ,sistemaName));
		try {
		 
			List<RolesSistema> list = this.repository.findRolesBySistema(sistemaName);			
			if( list == null && list.size() == 0) {
				throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO , ErrorConstantes.NO_SE_ECONTRARON_REGISTRO);	
			}			
			return list;
		}catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( entity, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , ErrorConstantes.ERROR_CONSULTANDO);
		}
    }



	@Override
	public void desactivate(Usuario usuario) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
