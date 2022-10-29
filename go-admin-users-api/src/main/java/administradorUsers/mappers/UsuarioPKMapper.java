package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.UsuarioPKDTO;

import administradorUsers.entitys.UsuarioPK;

public class UsuarioPKMapper {
	
	public static UsuarioPKDTO mapper( UsuarioPK in ) {
		UsuarioPKDTO out = UsuarioPKDTO.builder()
				.id(in.getId())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}
	
	public static UsuarioPK mapper( UsuarioPKDTO in ) {
		UsuarioPK out = UsuarioPK.builder()
				.id(in.getId())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}

}
