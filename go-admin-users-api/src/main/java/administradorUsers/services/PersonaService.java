package administradorUsers.services;

import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Persona;
import administradorUsers.logic.IEntityDao;

public interface PersonaService extends IEntityDao<Persona> {
	
	public List<Persona> getAllBySistem(String nameSistem) throws AdministradorUserException ;

}
