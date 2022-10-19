package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.PrivilegiosDTO;

import administradorUsers.entitys.Privilegio;

public class PrivilegioMapper {
	
	public static PrivilegiosDTO mapper( Privilegio in) {
		PrivilegiosDTO out = PrivilegiosDTO.builder()
				.activo(in.isActivo())
				.descripcion(in.getDescripcion())
				.fechaCreacion(in.getFechaCreacion())
				.fkIdPrivilegio(in.getFkIdPrivilegio())
				.iconoClass(in.getIconoClass())
				.id(in.getId())
				.idSistema(in.getIdSistema())
				.isVisibleMenu(in.isVisibleMenu())
				.level(in.getLevel())
				.nombre(in.getNombre())
				.url(in.getUrl())
				.build();
		return out;
	}
	
	public static Privilegio mapper( PrivilegiosDTO in) {
		Privilegio out = Privilegio.builder()
				.activo(in.isActivo())
				.descripcion(in.getDescripcion())
				.fechaCreacion(in.getFechaCreacion())
				.fkIdPrivilegio(in.getFkIdPrivilegio())
				.iconoClass(in.getIconoClass())
				.id(in.getId())
				.idSistema(in.getIdSistema())
				.isVisibleMenu(in.isVisibleMenu())
				.level(in.getLevel())
				.nombre(in.getNombre())
				.url(in.getUrl())
				.build();
		return out;
	}

}
