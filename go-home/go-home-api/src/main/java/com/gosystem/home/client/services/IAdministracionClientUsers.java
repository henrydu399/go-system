package com.gosystem.home.client.services;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;

import com.gosystem.commons.exceptions.HomeException;


public interface IAdministracionClientUsers {
	
	public List<UsuarioDTO> getAll() throws HomeException ;
	
	void createUser(UsuarioDTO user) throws HomeException;
	
	UsuarioDTO saveForSystemPublic(UsuarioDTO user) throws HomeException;
	
	UsuarioDTO saveForSystem(UsuarioDTO user) throws HomeException;
	
	UsuarioDTO edithForSystem(UsuarioDTO user) throws HomeException;
	


	
	void edithUser(UsuarioDTO user) throws HomeException;
		
	UsuarioDTO findUser(UsuarioDTO user) throws HomeException;
	
	void deleteUser(UsuarioDTO user) throws HomeException;
	
	void desativateUser(UsuarioDTO user) throws HomeException;
	
	UsuarioDTO findUserForLogin(UsuarioDTO user) throws HomeException ;
	
	List<RolesSistemaDTO>getRolesBySystema (String systemaName) throws HomeException ;
	
	void confirmUser(UsuarioDTO usuario) throws HomeException ;
	

}
