package administradorUsers.validations;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Usuario;




@Component
public class UserValidation {
	
	
	private final String ERROR_ENTIDAD_NULL_O_VACIA = "EL systema  esta null o vacia";
	
	private final String ERROR_CAMPO_SISTEMA = "El Campo sistema es requerido y obligatorio";
	
	private final String ERROR_CAMPO_EMAIL = "El Campo email es requerido y obligatorio";
	
	//private final String ERROR_CAMPO_EMAIL_FORMATO_INCORRECTO = "Formato incorrecto con el campo email ";
	
	private final String ERROR_CAMPO_PASSWORD = "El Campo password es requerido y obligatorio";
	
	//private final String ERROR_CAMPO_USERNAME = "El Campo username es requerido y obligatorio";
	
	private final EntityEnum entity = EntityEnum.USUARIO ;
	
	
	public void save(Usuario e) throws AdministradorUserException{
		if( Objects.isNull(e)) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_ENTIDAD_NULL_O_VACIA);
		}
		
		if(Objects.isNull(e.getEmail()) || e.getEmail().isEmpty() ) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_CAMPO_EMAIL);
		}
		
		if(Objects.isNull(e.getPassword()) || e.getPassword().isEmpty() ) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_CAMPO_PASSWORD);
		}
		
		if(Objects.isNull(e.getSistema()) || e.getSistema().isEmpty() ) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_CAMPO_SISTEMA);
		}
		
	}

}
