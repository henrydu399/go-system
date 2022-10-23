package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.adminUsers.dto.TipoIdentificacionDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IParametrizacionClientService;
import com.gosystem.home.client.services.ITipoIdentificacionClient;
import com.gosystem.home.controllers.TipoDocumentoController;
import com.gosystem.home.enums.EntityHomeEnum;


@Service
public class TipoIdentificacionClientImpl implements ITipoIdentificacionClient {

	
	private Logger logger;
	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
	//PATHS //
	private String urlGateway;
	private String pathTipoIdentificacion;
	
	//.
	private RestTemplate restTemplate;
	
	
	public TipoIdentificacionClientImpl() {
		this.logger = UtilsLogs.getLogger(TipoIdentificacionClientImpl.class.getName());
	}
	

	@PostConstruct
	public void init() throws HomeException {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTE ENVIO CORREOS");
		try {
			this. urlGateway       = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
			this. pathTipoIdentificacion = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_ADMIN_USER_API_TIPO_DOCUMENTO);
			logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
			if(Objects.isNull(urlGateway)  ||  Objects.isNull(pathTipoIdentificacion)) {
				this.throwReturn();
			}
		}catch (HomeException e) {
			   this.throwReturn();
		}	
	}
	
	private void throwReturn() throws ParametrizacionException{
		throw new ParametrizacionException(null, MethodsEnum.INIT_PARAMETERS, LayerEnum.SERVICE, "No se ha podico cargar los parametros :" 
				+ " URL_GATEWAY"
				+ "PATH_GO_PARAMETRICAS_API_TIPO_DOCUMENTO"
				);	
	}
	
	
	@Override
	public List<TipoIdentificacionDTO> getAll() throws HomeException {
		logger.info("METODO : getAll() ->Consultando departamentos");	
		try {
				this.restTemplate = new RestTemplate();
				URI uri = new URI(this.urlGateway + this. pathTipoIdentificacion);
				ResponseEntity<TipoIdentificacionDTO[]> response = restTemplate.getForEntity( uri, TipoIdentificacionDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					List<TipoIdentificacionDTO> list = Arrays.asList(response.getBody()) ;
					logger.info("METODO : getAll() ->Consultados correctamente");
					return list;
				} else {
					logger.info("METODO : getAll() ->Error consultando ");
					throw new ParametrizacionException( EntityEnum.TIPO_DOCUMENTO, MethodsEnum.GETALL, LayerEnum.CLIENT , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.TIPO_DOCUMENTO, MethodsEnum.GETALL, LayerEnum.CLIENT , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
		}
	}
	
	

}
