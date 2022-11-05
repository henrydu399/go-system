package com.gosystem.home.controllers;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gosystem.commons.adminUsers.dto.PrivilegiosRolUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;

import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientUsers;

import com.gosystem.home.util.BCryptPasswordEncoder;
import com.gosystem.home.validations.LoginValidation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
public class LoginController {

	
	@Value("${jwt.key}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long timeExpiration;
	
	@Autowired
	IAdministracionClientUsers usuarioRepository;
	
	@Autowired
	LoginValidation validation;
	
	private Logger logger;
	
	//@Autowired
	//RolesUsuarioServiceImpl rolesUsuarioServiceImpl;
	public LoginController() {
		logger = UtilsLogs.getLogger(LoginController.class.getName());
	
	}

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsuarioDTO userIn)  { 
    	
    	try {
    		
    		validation.login(userIn);
		  	
        	UsuarioDTO usuarioOpt = usuarioRepository.findUserForLogin(userIn);   	
        	
    		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        		
        	if( Objects.nonNull(usuarioOpt) ) { 
        		
				if(!usuarioOpt.isConfirmado()) {
	  				  throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.USUARIO_NO_CONFIRMADO);
				}
        		
        		UsuarioDTO usuario = usuarioOpt;
        		// ** verificar password *** 
        		boolean isPasswordMatch = passwordEncoder.matches(userIn.getPassword(), usuario.getPassword());  		
            	if ( ! isPasswordMatch) {
  				  throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.NO_EXISTE_EL_USUARIO_O_CREDECIALES_INVALIDAS);

            	}
        		// ***********
        		
        		//buscamos los roles del usuarios x sistema
    			
    			  List<RolesUsuarioDTO> roles =  usuario.getListRolesUSuarios();
    			  if( roles == null ||  roles.size() == 0) { 
    				  throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.NO_EXISTE_ROLES_USUARIOS);
    				  }
    			  
    			  List<PrivilegiosRolUsuarioDTO> privilegios = usuario.getPrivilegios();
    			  
    			  if(  Objects.isNull(privilegios) || privilegios.size() == 0) {
    				  throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.NO_EXISTE_PRIVILEGIOS_USUARIOS);
    			  }
    			 
        		
        		
        		String rolesIn = "" ;
    			
    			  for( RolesUsuarioDTO rol : roles)
    			  { rolesIn = rolesIn +rol.getId().getNombreRol() +",";
    			  }
    			  
    			  rolesIn = rolesIn.substring(0, rolesIn.length()-1);
    			 
        		

          
            	String token = getJWTToken(usuario.getEmail(), rolesIn);        	
            	usuario.setToken(token);
            	usuario.setPassword(null);
        		//usuario.get().builderDto()
        		return ResponseEntity.ok(usuario);
    		
    		
        	}else {
        		throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.USUARIO_NO_EXISTE);
        	}
        	
    	}catch (HomeException e2) {
    		this.logger.info(e2.getMessage());
    		return new ResponseEntity<Object>(e2.getMessage(), HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			this.logger.info(e.getMessage());
			HomeException nu = new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
			return new ResponseEntity<Object>(nu.getMessage(), HttpStatus.BAD_REQUEST);
		}
    	
    	
    	


    }
    
    
   
    
	private String getJWTToken(String username , String roles) {
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(roles);
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
				.signWith(SignatureAlgorithm.HS512,
						this.secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	
}