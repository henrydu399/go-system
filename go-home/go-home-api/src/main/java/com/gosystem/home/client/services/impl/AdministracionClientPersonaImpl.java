package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientPersona;
import com.gosystem.home.client.services.IParametrizacionClientService;

@Service
public class AdministracionClientPersonaImpl implements IAdministracionClientPersona {
	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
    private RestTemplate restTemplate  ;
	
	private Logger logger;
	
	//URLS
	private String urlGateway;
	private String pathGoAdminUserPersona;
	private String pathDeletePersona;
	private String pathFindPersona;
	//####
	

	public AdministracionClientPersonaImpl() {
		logger = UtilsLogs.getLogger(AdministracionClientPersonaImpl.class.getName());	
	}
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTES");
		this. urlGateway = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
		this. pathGoAdminUserPersona = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_PERSONA);
		this. pathDeletePersona = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_PERSONA_DELETE);
		this. pathFindPersona = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_PERSONA_FIND);
		
		logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
	}
	
	@Override
	public List<PersonaDTO> getAll() throws HomeException {
		logger.info("METODO : v() -> BUSCANDO USUARIO POR EMAIL.... ");
		String urlFull = this.urlGateway + this.pathGoAdminUserPersona;
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
			  ResponseEntity<PersonaDTO[]> responseEntityStr = restTemplate.getForEntity(url, PersonaDTO[].class);
			  logger.info("METODO : getAll() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if(     responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  PersonaDTO[] list =  responseEntityStr.getBody();
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
	public void save(PersonaDTO user) throws HomeException {
		logger.info("METODO : save() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway + pathGoAdminUserPersona;
		logger.info("METODO : save() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<PersonaDTO> request =  new HttpEntity<PersonaDTO>(user, headers);		    
			  ResponseEntity<String> responseEntityStr = restTemplate. postForEntity(urlFull, request, String.class);
			  logger.info("METODO : save() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : save() -> RESPUESTA OK: ");
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
	public void edith(PersonaDTO user) throws HomeException {
		logger.info("METODO : save() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway + pathGoAdminUserPersona;
		logger.info("METODO : save() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<PersonaDTO> request =  new HttpEntity<PersonaDTO>(user, headers);		    
			  restTemplate.put(urlFull, request);  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL_GUARDANDO);
		}
		
	}

	@Override
	public void delete(PersonaDTO user) throws HomeException {
		logger.info("METODO : deleteUser() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ this.pathDeletePersona;
		logger.info("METODO : deleteUser() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<PersonaDTO> request =  new HttpEntity<PersonaDTO>(user, headers);		    
			  ResponseEntity<PersonaDTO> responseEntityStr = restTemplate. postForEntity(urlFull, request, PersonaDTO.class);
			  logger.info("METODO : deleteUser() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : deleteUser() -> RESPUESTA OK: ");
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
	public PersonaDTO find(PersonaDTO user) throws HomeException {
		logger.info("METODO : findUser() -> BUSCANDO USUARIO POR EMAIL.... ");
		String urlFull = this.urlGateway + this.pathFindPersona;
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
              HttpEntity<PersonaDTO> request =  new HttpEntity<PersonaDTO>(user, headers);		    
		      logger.info("METODO : findUser() -> REQUEST  : "+ UtilGson.SerializeObjet(request));	    
			  ResponseEntity<PersonaDTO> responseEntityStr = restTemplate.postForEntity(url,request, PersonaDTO.class);
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

}
