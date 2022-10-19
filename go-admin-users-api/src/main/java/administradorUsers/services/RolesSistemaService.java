package administradorUsers.services;



import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.RolesSistema;
import administradorUsers.logic.IEntityDao;

public interface RolesSistemaService extends IEntityDao<RolesSistema> {
	
	  public List<RolesSistema> findBySystema(String sistemaName)throws AdministradorUserException;
	
	

}
