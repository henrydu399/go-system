package com.gosystem.home.client.services.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.ParametroDTO;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IParametrizacionClientService;




@Service
public class ClientParametrizacionServiceImpl implements IParametrizacionClientService{

	
	private Logger logger;
	
	@Value("${url.parametrizacion}")
	private String url;
	
	
	HashMap<String, String> parametros;
	
	RestTemplate restTemplate;
	
	public ClientParametrizacionServiceImpl() {
		logger = UtilsLogs.getLogger(ClientParametrizacionServiceImpl.class.getName());

	}
	
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTES");	
		try {
				restTemplate = new RestTemplate();
				URI uri = new URI(this.url);
				ResponseEntity<ParametroDTO[]> response = restTemplate.getForEntity( uri, ParametroDTO[].class);				
				if ( Objects.nonNull(response)  &&   Objects.nonNull(response.getBody()) ) {
					this.parametros = new HashMap<>();
					List<ParametroDTO> list = Arrays.asList(response.getBody()) ;
					for(ParametroDTO p : list  ) {
						this.parametros.put(p.getKey(), p.getValue());
					}
					logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
				} else {
					logger.info("METODO : INIT() ->ERROR INICIALIZANDO ");
					throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
				}
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETALL, LayerEnum.SERVICE , ErrorConstantes.ERROR_NO_CARGARON_PARAMETRICAS);
		}	
	}
	
	
	@Override
	public ParametroDTO getParametroDirect(String key) throws ParametrizacionException {
		logger.info(" [ METODO : getParametro() ] PROCESO : consulta parametrica por key  :" + key );
		ParametroDTO p= null;
		
		try {
				restTemplate = new RestTemplate();
				URI uri = new URI(this.url+ key);
				ResponseEntity<ParametroDTO> response = restTemplate.getForEntity( uri, ParametroDTO.class);
				logger.info("[ METODO : ConConexionDb() ] Response : :" + UtilGson.SerializeObjet(response));
				if (response != null && response.getBody() != null) {
					 p = response.getBody();
					return p;
				} else {
					throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.NO_EXISTE_PARAMETRICA);
				}

		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		
		
	}
	
	@Override
	public String getParametro(String key) throws ParametrizacionException {
		logger.info(" [ METODO : getParametro() ] PROCESO : consulta parametrica por key  :" + key );
		String value = null;
		try {	
			if ( Objects.isNull(this.parametros) ) {
				this.init();
			}
			value =   this.parametros.get(key);	
		}catch (ParametrizacionException e) {
			logger.severe(e.getMessage());
			throw e;		
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new ParametrizacionException( EntityEnum.PARAMETRICA, MethodsEnum.GETBYID, LayerEnum.SERVICE , ErrorConstantes.ERROR_GENERAL);
		}
		return value;	
	}


	
  
	
}
