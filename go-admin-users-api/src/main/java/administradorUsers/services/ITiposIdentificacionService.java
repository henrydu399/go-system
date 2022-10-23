package administradorUsers.services;

import java.util.List;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.TipoIdentificacion;

public interface ITiposIdentificacionService {
	
	List<TipoIdentificacion> getAll()  throws AdministradorUserException ;

}
