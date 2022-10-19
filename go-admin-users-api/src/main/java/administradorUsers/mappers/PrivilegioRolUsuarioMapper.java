package administradorUsers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.gosystem.commons.adminUsers.dto.PrivilegiosRolUsuarioDTO;

import administradorUsers.entitys.PrivilegiosRolUsuario;

public class PrivilegioRolUsuarioMapper {
	
	public static PrivilegiosRolUsuarioDTO mapper( PrivilegiosRolUsuario in ,boolean isLogin) {
		PrivilegiosRolUsuarioDTO out = PrivilegiosRolUsuarioDTO.builder()
				.id( PrivilegioRolUsuarioPKMapper.mapper(in.getId()))
				.buscar(in.isBuscar())
				.crear(in.isCrear())
				.desactivar(in.isDesactivar())
				.editar(in.isEditar())
				.eliminar(in.isEliminar())
				.fechaCreacion(in.getFechaCreacion())
				.getAll(in.isGetAll())
				.getNormal(in.isGetNormal())
				.privilegio( PrivilegioMapper.mapper(in.getPrivilegio()) )
				.build();
		
		if(isLogin) {
			out.setId(null);
		}
		return out;
		
	}

	
	public static PrivilegiosRolUsuario  mapper( PrivilegiosRolUsuarioDTO in) {
		PrivilegiosRolUsuario out = PrivilegiosRolUsuario.builder()
				.id( PrivilegioRolUsuarioPKMapper.mapper(in.getId()))
				.buscar(in.isBuscar())
				.crear(in.isCrear())
				.desactivar(in.isDesactivar())
				.editar(in.isEditar())
				.eliminar(in.isEliminar())
				.fechaCreacion(in.getFechaCreacion())
				.getAll(in.isGetAll())
				.getNormal(in.isGetNormal())
				.build();
		return out;
		
	}
	
	public static List< PrivilegiosRolUsuarioDTO> mapperAsListDto( List<PrivilegiosRolUsuario> in  ,boolean isLogin) {
		List< PrivilegiosRolUsuarioDTO> out = new ArrayList<PrivilegiosRolUsuarioDTO>();
			for( PrivilegiosRolUsuario e : in) {
				out.add( mapper(e , isLogin));
			}
		return out;
	}
	

	

	public static List< PrivilegiosRolUsuario > mapperAsListEntity( List<PrivilegiosRolUsuarioDTO> in) {
		List< PrivilegiosRolUsuario> out = new ArrayList<PrivilegiosRolUsuario>();
			for( PrivilegiosRolUsuarioDTO e : in) {
				out.add( mapper(e));
			}
		return out;
		
	}

}
