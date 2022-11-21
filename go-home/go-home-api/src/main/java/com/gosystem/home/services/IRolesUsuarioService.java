package com.gosystem.home.services;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.home.logic.IEntityDao;



public interface IRolesUsuarioService extends IEntityDao<RolesUsuarioDTO>{
	
	 List<RolesUsuarioDTO> findByUsuario(UsuarioDTO usuario) throws HomeException ;
	 List<RolesUsuarioDTO> getAllSistemaByName(String sistemaName) throws HomeException ;
	 RolesUsuarioDTO findById(RolesUsuarioDTO p) throws HomeException ;

}
