package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.PrivilegiosRolUsuarioPKDTO;

import administradorUsers.entitys.PrivilegiosRolUsuarioPK;

public class PrivilegioRolUsuarioPKMapper {
	
	public static PrivilegiosRolUsuarioPKDTO mapper (PrivilegiosRolUsuarioPK in) {
		PrivilegiosRolUsuarioPKDTO out = PrivilegiosRolUsuarioPKDTO.builder()
				.id(in.getId())
				.idPrivilegio(in.getIdPrivilegio())
				.idRolSistema(in.getIdRolSistema())
				.idSistema(in.getIdSistema())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.idUsuario(in.getIdUsuario())
				.nombreRol(in.getNombreRol())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}
	
	public static PrivilegiosRolUsuarioPK  mapper ( PrivilegiosRolUsuarioPKDTO in) {
		PrivilegiosRolUsuarioPK out = PrivilegiosRolUsuarioPK.builder()
				.id(in.getId())
				.idPrivilegio(in.getIdPrivilegio())
				.idRolSistema(in.getIdRolSistema())
				.idSistema(in.getIdSistema())
				.idTipoIdentificacion(in.getIdTipoIdentificacion())
				.idUsuario(in.getIdUsuario())
				.nombreRol(in.getNombreRol())
				.numeroIdentificacion(in.getNumeroIdentificacion())
				.build();
		return out;
	}

}
