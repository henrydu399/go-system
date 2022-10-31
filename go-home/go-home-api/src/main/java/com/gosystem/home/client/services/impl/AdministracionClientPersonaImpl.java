package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
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
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
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
	//####
	

	public AdministracionClientPersonaImpl() {
		logger = UtilsLogs.getLogger(AdministracionClientPersonaImpl.class.getName());	
	}
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTES");
		this. urlGateway = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
		this. pathGoAdminUserPersona = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_PERSONA);
		logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
	}
	
	@Override
	public List<PersonaDTO> getAll() throws HomeException {
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
	public void create(PersonaDTO user) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edith(PersonaDTO user) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(PersonaDTO user) throws HomeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioDTO find(PersonaDTO user) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

}
