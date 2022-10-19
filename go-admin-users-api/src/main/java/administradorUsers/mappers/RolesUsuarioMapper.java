package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.RolesUsuarioPKDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.RolesUsuarioPK;

public class RolesUsuarioMapper {

	public static  RolesUsuario mapper(RolesUsuarioDTO in) {
		RolesUsuario out = null;
		RolesUsuarioPK rolUsuarioPk = null;
		try {	
			rolUsuarioPk = RolesUsuarioPK.builder()
							.id(in.getId().getId())
							.idRolSistema(in.getId().getIdRolSistema())
							.idSistema(in.getId().getIdSistema())
							.idTipoIdentificacion(in.getId().getIdTipoIdentificacion())
							.idUsuario(in.getId().getIdUsuario())
							.nombreRol(in.getId().getNombreRol())
							.numeroIdentificacion(in.getId().getNumeroIdentificacion())
							.build();
							
							
			out = RolesUsuario.builder()
					.idPk(rolUsuarioPk)
					.fechaCreacion(in.getFechaCreacion())
					.build();
		
		}catch (Exception e) {
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.MAPPER, LayerEnum.SERVICE , ErrorConstantes.ERROR_MAPPER_OBJECT);
		}	
		return out;
	}
	
	public static  RolesUsuarioDTO  mapper(RolesUsuario in) {
		RolesUsuarioDTO out = null;
		RolesUsuarioPKDTO pk = null;
		try {	
			pk = RolesUsuarioPKDTO.builder()
							.id(in.getIdPk().getId())
							.idRolSistema(in.getIdPk().getIdRolSistema())
							.idSistema(in.getIdPk().getIdSistema())
							.idTipoIdentificacion(in.getIdPk().getIdTipoIdentificacion())
							.idUsuario(in.getIdPk().getIdUsuario())
							.nombreRol(in.getIdPk().getNombreRol())
							.numeroIdentificacion(in.getIdPk().getNumeroIdentificacion())
							.build();
							
							
			out = RolesUsuarioDTO.builder()
					.id(pk)
					.fechaCreacion(in.getFechaCreacion())
					.build();
		
		}catch (Exception e) {
			throw new AdministradorUserException( EntityEnum.ROL_USUARIO, MethodsEnum.MAPPER, LayerEnum.SERVICE , ErrorConstantes.ERROR_MAPPER_OBJECT);
		}	
		return out;
	}
	
}
