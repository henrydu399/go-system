package co.system.out.emailservice.services;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.emailapi.dto.EmailDto;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.exceptions.EmailException;
import com.gosystem.commons.utils.UtilGson;

import co.system.out.emailservice.enums.StatusEmailEnum;
import co.system.out.emailservice.model.Email;
import co.system.out.emailservice.repository.IEmailsRepository;
import co.system.out.emailservice.repository.IUsuariosRepository;


@Service
public class EnvioEmail {


	@Autowired
	IUsuariosRepository usuarioRepository;
	
	@Autowired
	PlantillaService planillaService;

	
	@Autowired
	IEmailsRepository emailsRepository;
	
	

	public void sendSave(EmailDto emp) throws EmailException {	
		
		try {
			if( Objects.isNull(emp.getCuerpo())) {
				throw new EmailException(null,null,LayerEnum.SERVICE  ,"El campo 'Contenido' no puede ser null o estar vacio");
			}
			
			if( Objects.isNull(emp.getDe())) {
				throw new EmailException(null,null,LayerEnum.SERVICE  ,"El campo 'DE' no puede ser null o estar vacio");
			}
			
			
	
			
			if( Objects.isNull(emp.getAsunto())) {
				throw new EmailException(null,null,LayerEnum.SERVICE  ,"El campo 'Subject' no puede ser null o estar vacio");
			}
			

			
		
			
			Email email = Email.builder()
			.status(StatusEmailEnum.PROCESS.name())
			.asunto(emp.getAsunto())
			.cuerpo(emp.getCuerpo())
			.fechaCreacion(new Date())
			.de(emp.getDe())
			.sistema(emp.getSistema())
			.para(emp.getPara())
			.plantilla(emp.getPlantilla())
			.isHtml(emp.isHtml())
			.destinatarios(  UtilGson.SerializeObjet(emp.getDestinatareos())   )
			.build();
			if( Objects.nonNull(emp.getParametersString()) ) {
				email.setParameters(emp.getParametersString());	
			}
			
		
			
			emailsRepository.save(email);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		


	}
	
	
	




}