package com.gosystem.home.client.services.impl;

import java.util.Arrays;
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

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.emailapi.dto.EmailDto;

import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;

import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IEmailClientService;
import com.gosystem.home.client.services.IParametrizacionClientService;


@Service
public class ClienteEmailServiceImpl  implements  IEmailClientService{

	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
	private RestTemplate restTemplate;
	
	private String pathSendEmail;
	private String pathEmailApi;
	private String urlGateway;
	
	private Logger logger;
	
	public ClienteEmailServiceImpl() {
		logger = UtilsLogs.getLogger(ClienteEmailServiceImpl.class.getName());
		
	}
	
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTE ENVIO CORREOS");
		
		this. urlGateway = parametrizacionService.getParametro(KeyParametrosConstantes.URL_GATEWAY);
		this. pathEmailApi = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_GO_EMAIL_API);
		this. pathSendEmail = parametrizacionService.getParametro(KeyParametrosConstantes.PATH_SEND_EMAIL);
	
		
	
		logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
	}
	
	@Override
	public void send(EmailDto dto) throws HomeException {
		logger.info("METODO : send() -> CREANDO USUARIO.... ");
		String urlFull = this.urlGateway+ pathEmailApi+ pathSendEmail;
		logger.info("METODO : send() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
              HttpEntity<EmailDto> request =  new HttpEntity<EmailDto>(dto, headers);	
              
              logger.info("METODO : send() -> REQUEST  : "+ UtilGson.SerializeObjet(request));
			  ResponseEntity<String> responseEntityStr = restTemplate. postForEntity(urlFull, request, String.class);
			  logger.info("METODO : send() -> RESPONSE  : "+ UtilGson.SerializeObjet(responseEntityStr));
			  if( responseEntityStr.getStatusCode() == HttpStatus.ACCEPTED ||  
					  responseEntityStr.getStatusCode() == HttpStatus.CREATED ||
					  responseEntityStr.getStatusCode() == HttpStatus.OK ) {
				  logger.info("METODO : send() -> RESPUESTA OK: ");
			  }else {
				  if( Objects.nonNull(responseEntityStr.getBody())  ) {
						throw new HomeException( null, MethodsEnum.SEND_EMAIL, LayerEnum.CLIENT ,responseEntityStr.getBody() );
				  }else {
						throw new HomeException( null, MethodsEnum.SEND_EMAIL, LayerEnum.CLIENT ,ErrorConstantes.ERROR_GENERAL_ENVIADO_EMAIL );
				  }
 
			  }
			  
		}catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( null, MethodsEnum.SEND_EMAIL, LayerEnum.CLIENT , ErrorConstantes.ERROR_GENERAL_ENVIADO_EMAIL);
		}
		
	}
	
	

}
