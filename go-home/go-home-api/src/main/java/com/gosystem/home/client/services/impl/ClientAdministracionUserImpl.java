package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.AdministradorUserException.AdministradorUserExceptionDto;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.ParametroDTO;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientUsers;
import com.gosystem.home.client.services.IParametrizacionClientService;
import com.gosystem.home.streams.RolUsuariosStream;

	

@Service
public class ClientAdministracionUserImpl implements IAdministracionClientUsers {

	@Autowired
	private IParametrizacionClientService parametrizacionService;

	
	private RestTemplate restTemplate  ;
	
	private Logger logger;
	
	private String pathGoAdminUserUsuario;
	
	private String pathSaveUserSystemPublic;
	
	private String pathSaveUserSystem;
	private String pathEdithUserSystem;
	
	
	private String pathGetUserForLogin;
	private String pathGetRolesBySistemaNamen;
	private String urlGateway;
	private String pathUserConfirm;
	private String pathUSerFindCustom;
	
	
	
	
	
	
	
	public ClientAdministracionUserImpl() {
		logger = UtilsLogs.getLogger(ClientParametrizacionServiceImpl.class.getName());
		
	}
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTES");
		
		this. urlGateway = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
		this. pathGoAdminUserUsuario = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_USUARIO);
		
		this. pathSaveUserSystemPublic = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_USUARIO_SYSTEM_PUBLIC);
		this. pathSaveUserSystem = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_SAVE_FOR_SYSTEM);
		this. pathEdithUserSystem = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_EDITH_FOR_SYSTEM);
	
		this. pathGetUserForLogin = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GET_USER_FOR_LOGIN);
		this. pathGetRolesBySistemaNamen = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ROLES_USERS_BY_SISTEMA_NAME);
		this. pathUserConfirm = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_CONFIRM);
		this. pathUSerFindCustom = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_FIND);
		
	
		logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
	}
	

	public List<UsuarioDTO> getAll() throws HomeException {
		logger.info("METODO : v() -> BUSCANDO USUARIO POR EMAIL.... ");
		String urlFull = this.urlGateway + this.pathGoAdminUserUsuario;
		URI url = null;
		try {
		 url =  new URI(urlFull);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.CLIENT ,ErrorConstantes.ERROR_CON_URL + urlFull );			
		}
		logger.info("METODO : getAll() -> URL : " + urlFull);
		this.restTemplate  = new RestTemplate();
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>("Consultando usuarios URL : "+urlFull);
			  ResponseEntity<UsuarioDTO[]> responseEntityStr = restTemplate.getForEntity(url, UsuarioDTO[].class);
			  logger.info("METODO : getAll() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if(     responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  UsuarioDTO[] list =  responseEntityStr.getBody();
				  return Arrays.asList(list);
			  }	  
		}catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.CLIENT , ErrorConstantes.ERROR_CONSULTANDO);
		}
	return null;
		
	}
	
	@Override
	public void createUser(UsuarioDTO user) throws HomeException {
		logger.info("METODO : createUSer() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathGoAdminUserUsuario;
		logger.info("METODO : createUSer() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(user, headers);		    
			  ResponseEntity<String> responseEntityStr = restTemplate. postForEntity(urlFull, request, String.class);
			  logger.info("METODO : createUSer() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : createUSer() -> RESPUESTA OK: ");
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,responseEntityStr.getBody() );
				  }else {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
			  }	  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	   
	}

	@Override
	public void edithUser(UsuarioDTO user) throws AdministradorUserException {
		logger.info("METODO : edithUser() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathGoAdminUserUsuario;
		logger.info("METODO : edithUser() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(user, headers);		    
			  restTemplate.put(urlFull, request);   
			  logger.info("METODO : edithUser() -> Editado con exito OK: "); 
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	
		
	}

	/**
	 * Metodo que permite buscar un usuario para el realizar login
	 * @param UsuarioDTO user
	 *  @Req user.email, user.sistema
	 * @throws AdministradorUserException
	 */
	public UsuarioDTO findUserForLogin(UsuarioDTO user) throws AdministradorUserException {
		logger.info("METODO : findUserForLogin() ->  CLIENTE BUSCANDO  USUARIO.... ");
		String urlFull = this.urlGateway + this.pathGetUserForLogin;
		URI url = null;
		try {
		 url =  new URI(urlFull);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.CLIENT ,ErrorConstantes.ERROR_CON_URL + urlFull );			
		}
		
		logger.info("METODO : findUserForLogin() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();
		
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      logger.info("METODO : createUSer() -> REQUEST  : "+ UtilGson.SerializeObjet(entity));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(user, headers);		    
		      logger.info("METODO : findUserForLogin() -> REQUEST  : "+ UtilGson.SerializeObjet(request));	    
			  ResponseEntity<UsuarioDTO> responseEntityStr = restTemplate.postForEntity(url,request, UsuarioDTO.class);
			  logger.info("METODO : findUserForLogin() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if(     responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				 return responseEntityStr.getBody();
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.CLIENT ,ErrorConstantes.ERROR_CONSULTANDO_EL_REGISTROS  );
				  }else {
						throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.CLIENT ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }

			  }
			  
		}catch (RestClientException e) {
			HttpClientErrorException er = (HttpClientErrorException) e;
			if( er.getStatusCode() == HttpStatus.BAD_REQUEST &&
				Objects.nonNull(er.getResponseBodyAsString()) &&
				!er.getResponseBodyAsString().isEmpty()	 ) {
				AdministradorUserExceptionDto pa = UtilGson.getGson().fromJson( er.getResponseBodyAsString() ,AdministradorUserExceptionDto.class );
				AdministradorUserException ade =  new AdministradorUserException(pa);
				throw  ade;

		   }
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.CLIENT , ErrorConstantes.ERROR_GENERAL_GUARDANDO);


		}catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.CLIENT , ErrorConstantes.ERROR_GENERAL_GUARDANDO);

		}
	
		
	}
	
	

	
	/**
	 * Metodo que busca un usuario por las propiedades de un objeto del mismo tipo
	 * @param UsuarioDTO user
	*/
	public UsuarioDTO findUser(UsuarioDTO user) throws AdministradorUserException {
		logger.info("METODO : findUser() -> BUSCANDO USUARIO POR EMAIL.... ");
		String urlFull = this.urlGateway + this.pathUSerFindCustom;
		URI url = null;
		try {
		 url =  new URI(urlFull);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_CON_URL + urlFull );			
		}
		logger.info("METODO : findUser() -> URL : " + urlFull);
		this.restTemplate  = new RestTemplate();
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      logger.info("METODO : findUser() -> REQUEST  : "+ UtilGson.SerializeObjet(entity));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(user, headers);		    
		      logger.info("METODO : findUser() -> REQUEST  : "+ UtilGson.SerializeObjet(request));	    
			  ResponseEntity<UsuarioDTO> responseEntityStr = restTemplate.postForEntity(url,request, UsuarioDTO.class);
			  logger.info("METODO : findUser() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if(     responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				 return responseEntityStr.getBody();
			  }
			  
		}catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_CONSULTANDO);
		}
	return null;
		
	}

	@Override
	public void deleteUser(UsuarioDTO user) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desativateUser(UsuarioDTO user) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioDTO saveForSystemPublic(UsuarioDTO usuario) throws HomeException {
		logger.info("METODO : savePublic() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathSaveUserSystemPublic;
		logger.info("METODO : savePublic() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(usuario, headers);		    
			  ResponseEntity<UsuarioDTO> responseEntityStr = restTemplate. postForEntity(urlFull, request, UsuarioDTO.class);
			  logger.info("METODO : savePublic() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : savePublic() -> RESPUESTA OK: ");
				  return responseEntityStr.getBody();
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,UtilGson.SerializeObjet( responseEntityStr.getBody() ));
				  }else {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
			  }	  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	
		
	}
	
	
	@Override
	public UsuarioDTO saveForSystem(UsuarioDTO usuario) throws HomeException {
		logger.info("METODO : saveForSystem() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathSaveUserSystem;
		logger.info("METODO : saveForSystem() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(usuario, headers);	
              logger.info("METODO : savePublic() -> REQUEST   : "+ UtilGson.SerializeObjet(request));
			  ResponseEntity<UsuarioDTO> responseEntityStr = restTemplate. postForEntity(urlFull, request, UsuarioDTO.class);
			  logger.info("METODO : savePublic() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : savePublic() -> RESPUESTA OK: ");
				  return responseEntityStr.getBody();
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,UtilGson.SerializeObjet( responseEntityStr.getBody() ));
				  }else {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
			  }	  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	
		
	}
	
	
	@Override
	public UsuarioDTO edithForSystem(UsuarioDTO usuario) throws HomeException {
		logger.info("METODO : edithForSystem() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathEdithUserSystem;
		logger.info("METODO : edithForSystem() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(usuario, headers);	
              logger.info("METODO : edithForSystem() -> REQUEST   : "+ UtilGson.SerializeObjet(request));
			  ResponseEntity<UsuarioDTO> responseEntityStr = restTemplate. postForEntity(urlFull, request, UsuarioDTO.class);
			  logger.info("METODO : edithForSystem() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : edithForSystem() -> RESPUESTA OK: ");
				  return responseEntityStr.getBody();
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,UtilGson.SerializeObjet( responseEntityStr.getBody() ));
				  }else {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
			  }	  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	
		
	}
	
	
	
	
	
	

	/**
	 * Metodo que busca los Roles del systema GO-HOME
	 */
	public List<RolesSistemaDTO> getRolesBySystema(String systemaName) throws HomeException {
		logger.info("METODO : getRolesBySystema() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway  + this.pathGetRolesBySistemaNamen;
		URI url = null;
		try {
		 url =  new URI(urlFull+ systemaName);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_CON_URL + urlFull );			
		}
		
		logger.info("METODO : getRolesBySystema() -> URL : "+url.toString());
		this.restTemplate  = new RestTemplate();
		
		try {

			  ResponseEntity<RolesSistemaDTO []> responseEntityStr = restTemplate.getForEntity(url, RolesSistemaDTO[].class);
			  logger.info("METODO : getRolesBySystema() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  RolesSistemaDTO [] list =  responseEntityStr.getBody();
				 return Arrays.asList(list);
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_CONSULTANDO_EL_REGISTROS  );
				  }else {
						throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
 
			  }
			  
		}catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}
	}

	@Override
	public void confirmUser(UsuarioDTO usuario) throws HomeException {
		logger.info("METODO : confirmUser() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathUserConfirm;
		logger.info("METODO : confirmUser() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<UsuarioDTO> request =  new HttpEntity<UsuarioDTO>(usuario, headers);		    
			  ResponseEntity<String> responseEntityStr = restTemplate. postForEntity(urlFull, request, String.class);
			  logger.info("METODO : confirmUser() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : confirmUser() -> RESPUESTA OK: ");
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,responseEntityStr.getBody() );
				  }else {
						throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE ,ErrorConstantes.ERROR_GENERAL_GUARDANDO );
				  }
			  }	  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}	  
		
	}

}
