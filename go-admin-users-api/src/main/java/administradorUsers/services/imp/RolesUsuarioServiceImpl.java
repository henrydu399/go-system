package administradorUsers.services.imp;

import java.util.ArrayList;
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

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.Systema;
import administradorUsers.entitys.Usuario;
import administradorUsers.enums.MethodsAdminUSerEnum;
import administradorUsers.repository.ISistemaRepository;
import administradorUsers.repository.RolesUsuarioRepository;
import administradorUsers.services.IRolesUsuarioService;


@Service
public class RolesUsuarioServiceImpl implements IRolesUsuarioService {

	@Autowired
	private RolesUsuarioRepository respository;
	
	@Autowired
	private ISistemaRepository sistemaRepository;
	
	
    private Logger logger;
	
	public RolesUsuarioServiceImpl() {
		logger = UtilsLogs.getLogger(RolesSistemaServiceImpl.class.getName());
	}
	
	@Override
	public List<RolesUsuario> getAll() throws AdministradorUserException {
		try {
			return this.respository.findAll();
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}	
	}
	
	@Override
	public List<RolesUsuario> getAllSistemaByName(String sistemaName) throws AdministradorUserException {
		try {
			    logger.info(UtilsLogs.getInfo(MethodsAdminUSerEnum.GET_ALL_BY_SYSTEMA, EntityEnum.ROL_USUARIO ,null,"Buscando Roles Usuarios por el nombre de sistema : "+ sistemaName));
				Optional<Systema> sistemaExist =  this.sistemaRepository.findBynombre(sistemaName);
				if(!sistemaExist.isPresent()) {
					throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_SISTEMA_NO_EXISTE_EN_EL_SISTEMA);
				}
				return this.respository.findBysistema(sistemaName);
		    }catch (AdministradorUserException e) {
		    	logger.severe(e.getMessage());
		    	throw e;
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.severe(e.getMessage());
				throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
			}	
	}

	@Override
	public void save(RolesUsuario u) throws AdministradorUserException {
		try {
			 this.respository.save(u);
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}		
	}

	@Override
	public void edith(RolesUsuario u) throws AdministradorUserException {
		try {
			 this.respository.save(u);
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}		
	}

	@Override
	public RolesUsuario find(RolesUsuario p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_ALL, EntityEnum.ROL_USUARIO ,p,null));
		List<RolesUsuario> list = new  ArrayList<RolesUsuario>();
		try {
			RolesUsuario persona = (RolesUsuario) p;	
			Example<RolesUsuario> personaByFind =  Example.of(persona); 
			list = this.respository.findAll(personaByFind);
			if( Objects.nonNull(list) && list.size() > 0) {
				return list.get(0);
			}
			return null;
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.FIND, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
	}
	
	@Override
	public RolesUsuario findById(RolesUsuario p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_BY_ID, EntityEnum.ROL_USUARIO ,p,null));
		Optional<RolesUsuario> out = null;
		try {
			out = this.respository.findById(p.getId());
			if( out.isPresent()) {
				return out.get();
			}
			return null;
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.FIND, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
	}
	
	@Override
	public List<RolesUsuario> findAll(RolesUsuario p) throws AdministradorUserException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_ALL, EntityEnum.ROL_USUARIO ,p,null));
		List<RolesUsuario> list = new  ArrayList<RolesUsuario>();
		try {
			RolesUsuario persona = (RolesUsuario) p;	
			Example<RolesUsuario> personaByFind =  Example.of(persona); 
			list = this.respository.findAll(personaByFind);
			return list;		
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.FIND_ALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
	}



	@Override
	public void delete(RolesUsuario p) throws AdministradorUserException {
		try {
			if( Objects.isNull(p) || Objects.isNull(p.getId()) ){
				throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.ERROR_PARAMETROS_INSUFICIENTES);
			}
			Optional<RolesUsuario> rolUsuario =  this.respository.findById(p.getId());
			if( !rolUsuario.isPresent()) {
				throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.NO_SE_ECONTRARON_REGISTRO_PARA_OPERAR);
			}
			this.respository.delete(rolUsuario.get());
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		
	}
	

	public List<RolesUsuario> findByUsuario(Usuario usuario) throws AdministradorUserException {
		return respository.findByUsuario(usuario);

	}



	@Override
	public void desactivate(RolesUsuario p) throws AdministradorUserException {
		try {
			if( Objects.isNull(p) || Objects.isNull(p.getId()) ){
				throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.ERROR_PARAMETROS_INSUFICIENTES);
			}
			Optional<RolesUsuario> rolUsuario =  this.respository.findById(p.getId());
			if( !rolUsuario.isPresent()) {
				throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.NO_SE_ECONTRARON_REGISTRO_PARA_OPERAR);
			}
			rolUsuario.get().setEstado(false);
			this.respository.save(rolUsuario.get());
	    }catch (AdministradorUserException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.DELETE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		
	}

}
