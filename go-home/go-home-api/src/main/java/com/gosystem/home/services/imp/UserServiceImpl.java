package com.gosystem.home.services.imp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.KeyParametrosConstantes;
import com.gosystem.commons.emailapi.dto.EmailDto;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.Base64Util;
import com.gosystem.commons.utils.UtilGson;
import com.gosystem.commons.utils.UtilsLogs;
import com.gosystem.home.client.services.IAdministracionClientUsers;
import com.gosystem.home.client.services.IEmailClientService;
import com.gosystem.home.client.services.IParametrizacionClientService;
import com.gosystem.home.services.IUserService;
import com.gosystem.home.streams.RolUsuariosStream;
import com.gosystem.home.util.BCryptPasswordEncoder;
import com.gosystem.home.validations.UserValidation;



@Service
public class UserServiceImpl  implements IUserService {
	
	

	@Autowired
	private IAdministracionClientUsers clientAdministracionUsers;
	
	@Autowired
	private IEmailClientService emailClientService;
	
	
	
	@Autowired
	private UserValidation userValidation;
	
	
	@Autowired
	private RolUsuariosStream rolUsuariosStream;
	
	@Autowired 
	private IParametrizacionClientService parametrizacionClientService;
    
	
	
	@Value("${rol.user.normal}")
	private String rolUSerNormal;
	
	@Value("${application.name}")
	private String sistemaName;
	
	private Logger logger;
	
	private BCryptPasswordEncoder passwordEncoder ;
	

	
	public UserServiceImpl() {
		logger = UtilsLogs.getLogger(UserServiceImpl.class.getName());
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	
	@Override
	public List<UsuarioDTO> getAll() throws HomeException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.GETALL, EntityEnum.USUARIO , null));
		try {
			return clientAdministracionUsers.getAll();			
		}catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}
		
	}

	@Override
	public void save(UsuarioDTO usuario) throws HomeException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO ,usuario));
		try {					

			userValidation.save(usuario);

			
						
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_GUARDAR);
		
	    }catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

	@Override
	public void edith(UsuarioDTO usuario) throws HomeException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.EDITH, EntityEnum.USUARIO , usuario));
		
		try {	
			
			
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.DAO, ErrorConstantes.ERROR_INTENTAR_MODIFICAR);
		
	    }catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

	@Override
	public List<UsuarioDTO> findAll(UsuarioDTO usuario) throws HomeException {
		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.USUARIO , usuario));
		try {
			return null;	
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, ErrorConstantes.ERROR_CONSULTANDO);
		
	    }catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}
	}
	
	
	@Override
	public UsuarioDTO find(UsuarioDTO obj) throws HomeException {
		
		logger.info(UtilsLogs.getInfo(MethodsEnum.FIND_CUSTOM, EntityEnum.USUARIO ,obj));
		try {
			return null;	
			
		}catch (PersistenceException e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.DAO, ErrorConstantes.ERROR_CONSULTANDO_EL_REGISTROS);
		
	    }catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.FIND_CUSTOM, LayerEnum.LOGIC , null);
		}
	}

	@Override
	public void delete(UsuarioDTO p) throws HomeException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Optional<UsuarioDTO> findByEmail(String email) {
		return null;
	}
	
	
	 //=======================================================================
    // ----- PUBLIC  -----
    //=======================================================================
	
	
	/**
	 * Metodo para registrar Usuarios de rol USER_NORMAL
	 */
	@Override
	public void savePublic(UsuarioDTO usuario) throws HomeException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO ,usuario));
		try {					
			 logger.info("METODO : savePublic() : REGISTRANDO USUARIO ....");
			 logger.info("METODO : savePublic() : VALIDANDO ....");
			 this.userValidation.save(usuario);
			 logger.info("METODO : savePublic() : VALIDADO CORRECTAMENTE ....");
			 UsuarioDTO userForFind = UsuarioDTO.builder()
					.email(usuario.getEmail())
					.sistema(this.sistemaName)
					.build();
			userForFind = this.clientAdministracionUsers.findUser(userForFind);
			
			if( Objects.isNull(userForFind) ) {
				//ENCODE PASSWORD 
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				usuario.setPassword( passwordEncoder.encode(usuario.getPassword() ) );
				///
				
				RolesSistemaDTO rol = rolUsuariosStream.findBySistemName(rolUSerNormal);
				usuario.setRol(rol);
					
				
				this.clientAdministracionUsers.savePublic(usuario);
				
				this.sendEmail(usuario);
				
				logger.info("METODO : savePublic() : USUARIO CREADO CORRECTAMENTE ....");
			}else {
				logger.warning("METODO : savePublic() : MENSSAGE" + ErrorConstantes.USUARIO_EMAIL_YA_EXISTE );
				throw new HomeException( null, MethodsEnum.SAVE, LayerEnum.SERVICE , ErrorConstantes.USUARIO_EMAIL_YA_EXISTE);
			}
					
			
		}catch (HomeException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}
	
	private void sendEmail(UsuarioDTO usuario) throws HomeException {
		String[] destinos =  {usuario.getEmail()};
		
		
		String emailSystema =  parametrizacionClientService.getParametro(KeyParametrosConstantes.EMAIL_FROM_GO_HOME);
		String body = "<P>Bienvenido "+usuario.getPersona().getNombres()+" Tu proceso esta casi listo, revisa tu correo y confirma tu cuenta, gracias</P>";
		String plantillaName = parametrizacionClientService.getParametro(KeyParametrosConstantes.PLANTILLA_CONFIRM_USER_GO_HOME);
		
		HashMap<String, String> parametros = new HashMap<>();
		parametros.put("{nombres}", usuario.getPersona().getNombres());
				
		EmailDto mail = EmailDto.builder()
				.asunto("REGISTRO DE USUARIO GO-HOME")
				.cuerpo(Base64Util.encode(body))
				.destinatareos(Arrays.asList(destinos) )
				.de(emailSystema)
				.plantilla(plantillaName)
				.parametersString( UtilGson.SerializeObjet(parametros))
				//.parameters(parametros)
				.sistema(this.sistemaName)
				.isHtml(true)
				.build();
		
		emailClientService.send(mail);
	}

}
