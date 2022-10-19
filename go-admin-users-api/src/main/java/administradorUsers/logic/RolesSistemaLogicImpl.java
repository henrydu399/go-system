package administradorUsers.logic;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.RolesSistema;
import administradorUsers.entitys.Systema;
import administradorUsers.repository.IRoleSistemasRepository;
import administradorUsers.repository.ISistemaRepository;





@Component
public class RolesSistemaLogicImpl {

	@Autowired
	IRoleSistemasRepository repository;
	
	@Autowired
	ISistemaRepository sistemaRepository;
	
	private Logger logger;
	
	private final EntityEnum entity = EntityEnum.ROLES_SISTEMA ;
	
	
	public RolesSistemaLogicImpl() {
		logger = UtilsLogs.getLogger(RolesSistemaLogicImpl.class.getName());
	}
	
	
	public void  save( RolesSistema obj) throws AdministradorUserException{
		
		Optional<Systema> s = sistemaRepository.findById(obj.getId().getIdSistema());
		if( !s.isPresent()) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_SISTEMA_NO_EXISTE_EN_EL_SISTEMA);
		}
		
		Optional<RolesSistema>  e = repository.findById(obj.getId());
		if(e.isPresent()) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.DAO , ErrorConstantes.ERROR_ROL_EXISTE_EN_EL_SISTEMA);
		}
		
		
	}
	
	
}
