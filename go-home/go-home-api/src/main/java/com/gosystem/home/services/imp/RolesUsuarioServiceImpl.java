package com.gosystem.home.services.imp;


import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;

import com.gosystem.commons.utils.UtilsLogs;

import com.gosystem.home.client.services.IRolesUsuarioClientService;

import com.gosystem.home.services.IRolesUsuarioService;


@Service
public class RolesUsuarioServiceImpl implements IRolesUsuarioService{
	
	
	private Logger logger;
		
	
	@Autowired
	private IRolesUsuarioClientService rolesUsuarioClientService;
	
	public RolesUsuarioServiceImpl() {
		logger = UtilsLogs.getLogger(RolesUsuarioServiceImpl.class.getName());
	}
	


	@Override
	public List<RolesUsuarioDTO> getAll() throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RolesUsuarioDTO u) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edith(RolesUsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RolesUsuarioDTO find(RolesUsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RolesUsuarioDTO findById(RolesUsuarioDTO p) throws HomeException{
		logger.info("METODO : findById() ->Consultando roles usuarios by id");
		
		try {
			RolesUsuarioDTO out = rolesUsuarioClientService.findById(p);
			if(  Objects.nonNull(out)  && Objects.nonNull( out.getUsuario())) {
				out.getUsuario().setPassword(null);
			}
			return out;
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.FIND_BY_ID, LayerEnum.CLIENT , ErrorConstantes.ERROR_CONSULTANDO);
		}	
	 }

	@Override
	public List<RolesUsuarioDTO> findAll(RolesUsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RolesUsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desactivate(RolesUsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RolesUsuarioDTO> findByUsuario(UsuarioDTO usuario) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolesUsuarioDTO> getAllSistemaByName(String sistemaName) throws HomeException {
		logger.info("METODO : getAllSistemaByName() ->Consultando roles usuarios");	
		return rolesUsuarioClientService.getAllSistemaByName(sistemaName);
	}

}
