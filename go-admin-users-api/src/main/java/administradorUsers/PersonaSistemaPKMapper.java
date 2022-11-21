package administradorUsers;

import com.gosystem.commons.adminUsers.dto.PersonaSistemaPKDTO;

import administradorUsers.entitys.PersonaSistemaPK;

public  class PersonaSistemaPKMapper {
	
	public static PersonaSistemaPKDTO mapper( PersonaSistemaPK in) {
		PersonaSistemaPKDTO out = PersonaSistemaPKDTO.builder()
				.idSistema(in.getIdSistema())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}
	
	public static PersonaSistemaPK mapper( PersonaSistemaPKDTO in) {
		PersonaSistemaPK out = PersonaSistemaPK.builder()
				.idSistema(in.getIdSistema())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}
	
	

}
