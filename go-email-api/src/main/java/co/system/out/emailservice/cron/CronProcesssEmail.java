package co.system.out.emailservice.cron;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.gosystem.commons.constants.GeneralContstant;
import com.gosystem.commons.emailapi.dto.EmailDto;
import com.gosystem.commons.utils.Base64Util;
import com.gosystem.commons.utils.PasswordUtil;
import com.gosystem.commons.utils.UtilGson;

import co.system.out.emailservice.dto.juzDocumentoSendEmail;
import co.system.out.emailservice.enums.StatusEmailEnum;
import co.system.out.emailservice.model.Email;
import co.system.out.emailservice.model.Plantilla;
import co.system.out.emailservice.model.User;
import co.system.out.emailservice.repository.IEmailsRepository;
import co.system.out.emailservice.repository.IPlantillasRepository;
import co.system.out.emailservice.repository.IUsuariosRepository;
import co.system.out.emailservice.services.PlantillaService;
import co.system.out.emailservice.util.UtilFile;

@SpringBootApplication
@EnableScheduling
public class CronProcesssEmail {
	
	@Autowired
	IUsuariosRepository usuarioRepository;
	
	@Autowired
	PlantillaService planillaService;
	
	@Autowired
	IPlantillasRepository plantillasRepository;
	
	@Autowired
	IEmailsRepository emailsRepository;
	
	@Value("${spring.url.path}")
	private  String path;
	
	
	@Value("${cifrado.llave_maestra}")
	private String llaveMaestra;
	
	 @Scheduled(fixedRate = 20000)
	    public void run() {
		 
		 List<Email>  listEmails =  emailsRepository.findAll();
		 
		 if( listEmails != null) {
			 for (Email email : listEmails ) {
				 Gson gson = new Gson(); 
				 String tempDEstinatarios = email.getDestinatarios();
				 email.setDestinatarios(null);
				 String parametresString = email.getParameters();
				 email.setParameters(null);
				 try {
					 EmailDto emailDto = gson.fromJson(UtilGson.SerializeObjet(email) , EmailDto.class);
					 List<String> destinatarios = Arrays.asList(   gson.fromJson(tempDEstinatarios, String[].class)) ;
					 emailDto.setDestinatareos(destinatarios);
					 
					 if( Objects.nonNull(parametresString) ) {
						 emailDto.setParameters(   gson.fromJson(parametresString, Map.class) );
					 }
					 
					 this.sendEmail(emailDto);
					  emailsRepository.delete(email);
				} catch (Exception e) {
					email.setStatus(StatusEmailEnum.ERROR_AL_ENVIAR.name());
					email.setErrorTecnico(e.getMessage());
					e.printStackTrace();
				}finally {
					emailsRepository.flush();
				}
			 }
		 }
		 
	     
	    }
	 
	 
	 /**
	  * 
	  * @param emp
	  * @throws Exception
	  */
	 private void  sendEmail(EmailDto emp) throws Exception {	 
		
			User usuario = usuarioRepository.findByEmail(emp.getDe());
			for (String para : emp.getDestinatareos()) {
				if( para != null  &&  !para.isEmpty()) {
					try {			
						if( usuario != null) {
							
							String passwordDescript = PasswordUtil.encriptar(GeneralContstant.DECRYPT, PasswordUtil.strHashSeguridad(llaveMaestra),
									usuario.getPassword());
							JavaMailSenderImpl javaMailSender = 
									this.configEmail(String.valueOf(usuario.getPort())  , usuario.getServer() , usuario.getEmail()
									,passwordDescript, usuario.isSslEnable() , usuario.isTtlsEnable() , usuario.getProtocole() , usuario.isAuthEnable() 
									,usuario.isSocketFactoryFallback());			
							MimeMessage mimeMessage = null;
							if(Objects.nonNull(emp.getPlantilla())   ) {
								 mimeMessage = this.buildWithPlantilla(emp.getPlantilla(),emp.getDe(), para , emp.getAsunto(),  javaMailSender , null , emp.getParameters() );		
							}else {
								 mimeMessage = this.htmlOn(emp.getCuerpo(),emp.getDe(), para , emp.getAsunto(),  javaMailSender , null );		
							}
							
							
							
							javaMailSender.send(mimeMessage);						
						}

					}catch (Exception e) {
						e.printStackTrace();
						 throw e;
					}
				}
		
			}

		 
	 }
	 
	 
	 private MimeMessage htmlOn ( String htmlContent , String de , String para , String subject,  JavaMailSender  javaMailSender , List<juzDocumentoSendEmail> documentos ) throws Exception  {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
			helper.setText(UtilFile.getDecode(htmlContent) , true); // Use this or above line.
			helper.setTo(para);
			helper.setSubject(subject);
			helper.setFrom(de);
			if( documentos != null && documentos.size() > 0) {
				helper= addAdjuntos (helper , documentos);
			}

			return mimeMessage;
	}
	 
	 private MimeMessage buildWithPlantilla ( String plantillaName , String de , String para ,
			 String subject,  JavaMailSender  javaMailSender , List<juzDocumentoSendEmail> documentos ,
			 Map<String, String> parameters
			 ) throws Exception  {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
			
			Optional<Plantilla> p =  plantillasRepository.findByName(plantillaName);
			String PlantillaString = "";
			
			if( p.isPresent()) {
				 PlantillaString =  Base64Util.decode(p.get().getData()) ;
			}
			
			
			for(Map.Entry<String, String> entry : parameters.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    PlantillaString = PlantillaString.replace(key, value);

			}
			
			
			helper.setText(PlantillaString , true); // Use this or above line.
			helper.setTo(para);
			helper.setSubject(subject);
			helper.setFrom(de);
			if( documentos != null && documentos.size() > 0) {
				helper= addAdjuntos (helper , documentos);
			}

			return mimeMessage;
	}
	 
	 private MimeMessageHelper   addAdjuntos(MimeMessageHelper helper , List<juzDocumentoSendEmail> documentos) throws Exception  {
		 
		 for( juzDocumentoSendEmail archivo : documentos) {
	         try {
		         FileSystemResource file = new FileSystemResource(new File(path+ archivo.getTdpUrlDocumento()));
				helper.addAttachment(archivo.getTdpNombreTipoDocumento() +"."+ buscarExtencion(archivo.getTdpUrlDocumento()), file);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 throw e;
			}
		 }
		return helper;

	 }
	 
	 private  String buscarExtencion(String url) {
		 String ext = "";
		   String [] partes = url.split("\\\\");
		   String UltmParte = partes[partes.length -1];
		   String [] FileName = UltmParte.split("\\.");
		   ext = FileName[FileName.length - 1];
		   		
		 
		 return ext;
	 }
	 
	 
	private JavaMailSenderImpl configEmail(String puerto, String host, String user, String password,
				boolean ssl, boolean ttls , String protocolo, boolean auth , boolean fallback) {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(host);
			mailSender.setPort(Integer.parseInt(puerto));
			mailSender.setUsername(user);
			mailSender.setPassword(password);
			mailSender.setProtocol(protocolo);

			Properties props = mailSender.getJavaMailProperties();
			
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", puerto);
			
			
			if ( ssl) {
				props.put("mail.smtp.ssl.enable", "true");
			}
			
			if ( ttls) {
				props.put("mail.smtp.starttls.enable", "true");
			}
			
			if ( auth) {
				props.put("mail.smtp.auth", "true");
			}
			
			if ( fallback) {
				props.put("mail.smtp.socketFactory.fallback", "true");
			}
			
			
		
			
			/*
			 * props.put("mail.smtp.user", user); props.put("mail.smtp.password", password);
			 * props.put("mail.transport.protocol", "smtp"); props.put("mail.smtp.auth",
			 * "true"); props.put("mail.smtp.starttls.enable", "false");
			 * props.put("mail.smtp.starttls.required", "false");
			 * props.put("mail.smtp.ehlo", "true");
			 * props.put("mail.smtp.auth.mechanisms", "PLAIN");
			 * props.put("mail.smtp.auth.login.disable", "true");
			 * props.put("mail.smtp.auth.digest-md5.disable", "true");
			 * props.put("mail.smtp.auth.xoauth2.disable", "true");
			 * props.put("mail.smtp.auth.plain.disable", "false");
			 * props.put("mail.smtp.auth.ntlm.disable", "true");
			 * props.put("mail.smtp.allow8bitmime", "false");
			 * props.put("mail.smtps.allow8bitmime", "false");
			 */
			 //props.put("mail.debug", "true");
			mailSender.setJavaMailProperties(props);
			return mailSender;
		}
	
	

}
