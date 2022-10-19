package com.gosystem.home.validations;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;

@Component
public class LoginValidation {
	
	private final String ERROR_PASSWORD = "El password no puede estar vacio";
	private final String ERROR_CORREO = "El email no puede estar vacio";
	private final String ERROR_SISTEMA = "El sistema no puede estar vacio";
	
	
	public void login(UsuarioDTO e) throws AdministradorUserException{
		
		if( Objects.isNull(e.getEmail())) {
			throw new AdministradorUserException( null, MethodsEnum.LOGIN, LayerEnum.VALIDATE ,this.ERROR_CORREO );
		}
		if( Objects.isNull(e.getPassword())) {
			throw new AdministradorUserException( null, MethodsEnum.LOGIN, LayerEnum.VALIDATE ,this.ERROR_PASSWORD );
		}
		if( Objects.isNull(e.getSistema())) {
			throw new AdministradorUserException( null, MethodsEnum.LOGIN, LayerEnum.VALIDATE ,this.ERROR_SISTEMA );
		}
	}

}
