package co.system.out.emailservice.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.utils.Base64Util;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;

import co.system.out.emailservice.client.services.IParametrizacionClientService;
import co.system.out.emailservice.client.services.dtos.Root;
import co.system.out.emailservice.client.services.dtos.Root.Sender;
import co.system.out.emailservice.client.services.dtos.Root.To;
import co.system.out.emailservice.model.Plantilla;
import co.system.out.emailservice.repository.IPlantillasRepository;
import co.system.out.emailservice.services.ISendBlueService;

@Service
public class SendBlueImpl  implements ISendBlueService{

	@Autowired
	IPlantillasRepository plantillasRepository;
	
	private RestTemplate restTemplate  ;
	
	private Logger logger;
	
	private String urlBlueSend;
	private String keyBlueSend;
	private String emailSend;
	private String nameSend;
	
	@Autowired
	private IParametrizacionClientService parametrizacionService;
	
	public SendBlueImpl() {
		logger = UtilsLogs.getLogger(SendBlueImpl.class.getName());	
	}
	
	@PostConstruct
	public void init() {
		logger.info("METODO : INIT() ->INCIALIZANDO PARAMETROS PARA CLIENTES");	
		this. urlBlueSend = this.parametrizacionService.getParametro(KeyParametrosConstantes.URL_SENDBLUEMAILS);
		this. keyBlueSend = this.parametrizacionService.getParametro(KeyParametrosConstantes.KEY_SENDBLUEMAILS);
		this. emailSend = this.parametrizacionService.getParametro(KeyParametrosConstantes.EMAIL_FROM_GO_HOME);
		this. nameSend = this.parametrizacionService.getParametro(KeyParametrosConstantes.NAME_FROM_GO_HOME);
		logger.info("METODO : INIT() ->INICIALIZADOS CORRECTAMENTE");
	}
	
	@Override
	public void sendEmail(EmailDto emp) throws Exception {
		logger.info("METODO : createUSer() -> CREANDO USUARIO.... ");
		String urlFull = this.urlBlueSend;
		
		
		
		logger.info("METODO : createUSer() -> URL : "+urlFull);
		this.restTemplate  = new RestTemplate();;
		try {
			
			Root dtoIn = this.buildDto(emp);
			
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      headers.set("api-key", this.keyBlueSend);
		      
              HttpEntity<Root> request =  new HttpEntity<Root>(dtoIn, headers);		    
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
	
	
	private Root buildDto(EmailDto emp) {
		
		Root root = null;
		
		///QUIEN ENVIA EL CORREO
		Sender sender = Sender.builder()
				.name(nameSend)
				.email(emailSend)
				.build();
		
		ArrayList<To> listTo = new ArrayList<To>();
		for( String destino : emp.getDestinatareos()) {
			listTo.add(new To(destino,destino));
		}
		
		
		root = Root.builder()
				.sender(sender)
				.myto(listTo)
				.subject(emp.getAsunto())
				.htmlContent(this.buildPantilla(emp))	
				.build();
		
		return root;
		
	}
	
	
	private String buildPantilla(EmailDto emp) {
	
		Optional<Plantilla> p =  plantillasRepository.findByName(emp.getPlantilla());
		String PlantillaString = "";
		
		if( p.isPresent()) {
			 PlantillaString =  Base64Util.decode(p.get().getData()) ;
		}
		
		
		for(Map.Entry<String, String> entry : emp.getParameters().entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    PlantillaString = PlantillaString.replace(key, value);

		}
		
		return PlantillaString;
		
	}

}
