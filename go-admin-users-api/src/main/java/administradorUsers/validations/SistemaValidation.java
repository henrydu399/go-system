package administradorUsers.validations;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Systema;




@Component
public class SistemaValidation {
	
	private final String ERROR_ENTIDAD_NULL_O_VACIA = "EL systema  esta null o vacia";
	private final String ERROR_ENTIDAD_NOMBRE_O_VACIA = "El nombre del systema esta null o vacia";
	
	private final EntityEnum entity = EntityEnum.SYSTEMA ;
	
	
	public void save(Systema e) throws AdministradorUserException{
		if( Objects.isNull(e)) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_ENTIDAD_NULL_O_VACIA);
		}
		
		if(Objects.isNull(e.getNombre()) || e.getNombre().isEmpty() ) {
			throw new AdministradorUserException( entity, MethodsEnum.SAVE, LayerEnum.VALIDATE , ERROR_ENTIDAD_NOMBRE_O_VACIA);
		}
	}

}
