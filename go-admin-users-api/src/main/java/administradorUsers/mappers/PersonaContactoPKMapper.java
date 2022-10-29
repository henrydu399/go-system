package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.PersonaContactoPKDTO;

import administradorUsers.entitys.PersonaContactoPK;

public class PersonaContactoPKMapper {
	
	
	public static PersonaContactoPKDTO mapper( PersonaContactoPK in) {
		PersonaContactoPKDTO out =  PersonaContactoPKDTO.builder()
				.id( in.getId())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}
	
	public static PersonaContactoPK mapper( PersonaContactoPKDTO in) {
		PersonaContactoPK out =  PersonaContactoPK.builder()
				.id( in.getId())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}

}
