package com.gosystem.home.client.services.impl;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.HomeException;

public interface IAdministracionClientPersona {
	
    List<PersonaDTO> getAll() throws HomeException ;
	
	void create(PersonaDTO user) throws HomeException;
	
	void edith(PersonaDTO user) throws HomeException;
	
	void deleteUser(PersonaDTO user) throws HomeException;
		
	UsuarioDTO find(PersonaDTO user) throws HomeException;
	

}
