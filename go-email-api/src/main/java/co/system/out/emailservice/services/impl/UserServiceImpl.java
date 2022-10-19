package co.system.out.emailservice.services.impl;

import java.util.Objects;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.constants.GeneralContstant;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.EmailException;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.PasswordUtil;
import com.gosystem.commons.utils.UtilsLogs;
import com.netflix.discovery.provider.Serializer;

import co.system.out.emailservice.model.User;
import co.system.out.emailservice.repository.IUsuariosRepository;
import co.system.out.emailservice.services.IUserService;
import co.system.out.emailservice.util.BCryptPasswordEncoder;


@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	IUsuariosRepository usuarioREpository;
	
	private Logger logger;
	

	
	@Value("${cifrado.llave_maestra}")
	private String llaveMaestra;
	
	
	public UserServiceImpl() {
		logger = UtilsLogs.getLogger(UserServiceImpl.class.getName());
	
	}
	
	@Override
	public void createUSer(User u) throws EmailException {
		logger.info(UtilsLogs.getInfo(MethodsEnum.SAVE, EntityEnum.USUARIO ,u));
		logger.info( "CREANDO USUARIO PARA ENVIO DE EMAILS");
		try {					

			if( Objects.isNull(u.getEmail())  ) {
				throw new EmailException(null,null,LayerEnum.SERVICE,"Error con el parametro email no puede ser null o vacio !");
			}
			if( Objects.isNull(u.getPassword())  ) {
				throw new EmailException(null,null,LayerEnum.SERVICE,"Error con el parametro password no puede ser null o vacio !");
			}
			if( Objects.isNull(u.getServer())  ) {
				throw new EmailException(null,null,LayerEnum.SERVICE,"Error con el parametro server no puede ser null o vacio !");
			}
			if( Objects.isNull(u.getPort())  ) {
				throw new EmailException(null,null,LayerEnum.SERVICE,"Error con el parametro port no puede ser null o vacio !");
			}
			

			String passwordEmail = PasswordUtil.encriptar(GeneralContstant.ENCRYPT, PasswordUtil.strHashSeguridad(llaveMaestra),
					u.getPassword());
			
			u.setPassword(passwordEmail);
			
			
			usuarioREpository.save(u);

			logger.info( "CREADO CON EXITO");
						
			
		}catch (EmailException e) {
	    	logger.severe(e.getMessage());
	    	throw e;
		}
		catch (Exception e) {
			logger.severe(e.getMessage());
			throw new HomeException( EntityEnum.USUARIO, MethodsEnum.SAVE, LayerEnum.LOGIC , ErrorConstantes.ERROR_GENERAL);
		}

		
	}

}
