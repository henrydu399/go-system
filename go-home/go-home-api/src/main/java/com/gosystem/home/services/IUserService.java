package com.gosystem.home.services;

import java.util.Optional;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.home.logic.IEntityDao;



public interface IUserService extends IEntityDao<UsuarioDTO> {
	
	 Optional<UsuarioDTO>	findByEmail(String email) throws HomeException ;
	 
	 public void savePublic(UsuarioDTO usuario) throws HomeException ;

}
