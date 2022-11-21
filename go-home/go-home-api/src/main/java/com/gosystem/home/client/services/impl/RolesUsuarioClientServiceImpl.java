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

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IParametrizacionClientService;
import com.gosystem.home.client.services.IRolesUsuarioClientService;
import com.gosystem.home.enums.EntityHomeEnum;


@Service
public class RolesUsuarioClientServiceImpl implements IRolesUsuarioClientService{
	
	
	private Logger logger;
	
	//PATHS //
	private String urlGateway;
	private String pathRolUsuario;
	private String pathRolUsuarioBySystema;
	private String pathRolUsuarioById;
	
	private RestTemplate restTemplate;
	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
	public RolesUsuarioClientServiceImpl() {
		logger = UtilsLogs.getLogger(RolesUsuarioClientServiceImpl.class.getName());
	}
	
	
	@PostConstruct
	public void init() throws HomeException {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTE ENVIO CORREOS");
		try {
			this. urlGateway       = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
			this. pathRolUsuario = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_ROL_USUARIO);
			this. pathRolUsuarioBySystema       = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_ROL_USUARIO_BY_SYSTEMA);
			this. pathRolUsuarioById       = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_ROL_USUARIO_BY_ID);
			
			logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
			if(Objects.isNull(urlGateway)  ||  Objects.isNull(pathRolUsuario) || Objects.isNull(pathRolUsuarioBySystema) || Objects.isNull(pathRolUsuarioById) ) {
				this.throwReturn();
			}
		}catch (HomeException e) {
			   this.throwReturn();
		}	
	}
	
	private void throwReturn() throws HomeException{
		throw new HomeException(null, MethodsEnum.INIT_PARAMETERS, LayerEnum.SERVICE, "No se ha podico cargar los parametros :" 
				+ " URL_GATEWAY"
				+ "PATH_GO_ADMIN_USER_ROL_USUARIO"
				+ "PATH_GO_ADMIN_USER_ROL_USUARIO_BY_SYSTEMA"
				);	
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
		logger.info("METODO : findById() -> BUSCANDO ROL USUARIO BY ID.... ");
		String urlFull = this.urlGateway + this.pathRolUsuarioById;
		URI url = null;
		try {
		 url =  new URI(urlFull);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			throw new HomeException( EntityEnum.ROL_USUARIO, MethodsEnum.FIND_BY_ID, LayerEnum.CLIENT ,ErrorConstantes.ERROR_CON_URL + urlFull );			
		}
		logger.info("METODO : findById() -> URL : " + urlFull);
		this.restTemplate  = new RestTemplate();
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<RolesUsuarioDTO> request =  new HttpEntity<RolesUsuarioDTO>(p, headers);	
			  ResponseEntity<RolesUsuarioDTO> responseEntityStr = restTemplate.postForEntity(url,request ,  RolesUsuarioDTO.class);
			  logger.info("METODO : findById() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if(     responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  RolesUsuarioDTO out =  responseEntityStr.getBody();
				  return out;
			  }	  
		}catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.FIND_BY_ID, LayerEnum.CLIENT , ErrorConstantes.ERROR_CONSULTANDO);
		}
	return null; 
		 
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
		try {
				this.restTemplate = new RestTemplate();
				URI uri = new URI(this.urlGateway + this.pathRolUsuarioBySystema + sistemaName);
				ResponseEntity<RolesUsuarioDTO[]> response = restTemplate.getForEntity( uri, RolesUsuarioDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					List<RolesUsuarioDTO> list = Arrays.asList(response.getBody()) ;
					logger.info("METODO : getAllSistemaByName() ->Consultados correctamente");
					return list;
				} else {
					logger.info("METODO : getAllSistemaByName() ->Error consultando ");
					throw new ParametrizacionException( EntityHomeEnum.DEPARTAMENTO, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
		}
	}

}
