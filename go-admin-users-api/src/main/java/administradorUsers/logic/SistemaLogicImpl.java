package administradorUsers.logic;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.utils.UtilsLogs;

import administradorUsers.entitys.Systema;
import administradorUsers.repository.IRoleSistemasRepository;
import administradorUsers.repository.ISistemaRepository;



@Component
public class SistemaLogicImpl {

	@Autowired
	IRoleSistemasRepository repository;
	
	@Autowired
	ISistemaRepository sistemaRepository;
	
	private Logger logger;
	
	private final EntityEnum entity = EntityEnum.ROLES_SISTEMA ;
	
	
	public SistemaLogicImpl() {
		logger = UtilsLogs.getLogger(SistemaLogicImpl.class.getName());
	}
	
	
	public void  save( Systema obj) throws AdministradorUserException{
		//EL NOMBRE EN MAYUSCULA
		obj.setNombre(obj.getNombre().toUpperCase());
		
	}
	
	
}
