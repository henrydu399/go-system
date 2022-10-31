package com.gosystem.home.services;

import java.util.Optional;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.home.logic.IEntityDao;



public interface IUserService extends IEntityDao<UsuarioDTO> {
	
	 Optional<UsuarioDTO>	findByEmail(String email) throws HomeException ;
	 
	 public void saveForSystemPublic(UsuarioDTO usuario) throws HomeException ;
	 
	 public void saveForSystem(UsuarioDTO usuario) throws HomeException ;
	 
	 public void edithForSystem(UsuarioDTO usuario) throws HomeException ;
	 
	 public void desactivate(UsuarioDTO p) throws HomeException ;
	 
	 public void delete(UsuarioDTO p) throws HomeException ;

}
