package com.gosystem.home.client.services;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.HomeException;

public interface IAdministracionClientPersona {
	
    List<PersonaDTO> getAll() throws HomeException ;
	
	void save(PersonaDTO user) throws HomeException;
	
	void edith(PersonaDTO user) throws HomeException;
	
	void delete(PersonaDTO user) throws HomeException;
		
	PersonaDTO find(PersonaDTO user) throws HomeException;
	

}
