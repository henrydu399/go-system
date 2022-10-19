package administradorUsers.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.adminUsers.dto.PersonaPKDTO;
import com.gosystem.commons.adminUsers.dto.PrivilegiosRolUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.TipoIdentificacionDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioPKDTO;
import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.Persona;
import administradorUsers.entitys.PersonaPK;
import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.TipoIdentificacion;
import administradorUsers.entitys.Usuario;
import administradorUsers.entitys.UsuarioPK;


public class UsuarioMapper {
	
	public static Usuario Mapper(UsuarioDTO in) throws AdministradorUserException {

		TipoIdentificacion t = null;
		PersonaPK ppk= null;
		Persona  p = null;
		UsuarioPK upk= null;
		Usuario  u= null;
		try {		
			if(Objects.nonNull(in.getPersona()) ) {
				if(Objects.nonNull (in.getPersona().getTipoIdentificacion())) {
					
					 t =  TipoIdentificacion.builder()
							.id(in.getPersona().getTipoIdentificacion().getId())
							.nombre(  in.getPersona().getTipoIdentificacion().getNombre())
					         .build();
					 
					 ppk = PersonaPK.builder()
							 .idTipoIdentificacion(in.getPersona().getId().getIdTipoIdentificacion())
							 .numeroIdentificacion(in.getPersona().getId().getNumeroIdentificacion() )
							 .build();
					 
					 p = Persona.builder()
							 .id(ppk)
							 .tipoIdentificacion(t)
							 .apellidos(in.getPersona().getApellidos())
							 .nombres(in.getPersona().getNombres())
							 .fechaNacimiento(in.getPersona().getFechaNacimiento())
							 .sexo(in.getPersona().getSexo())
							.build();
				}
				
						
			}	
			
			upk = UsuarioPK.builder()
					.id( in.getId().getId())
					.idTipoIdentificacion(in.getId().getIdTipoIdentificacion())
					.numeroIdentificacion( in.getId().getNumeroIdentificacion())
					.build();
			
			List<RolesUsuario> roles = null;
			if( in.getListRolesUSuarios() != null && in.getListRolesUSuarios().size() > 0) {
				roles =  new ArrayList<RolesUsuario>();
				for( RolesUsuarioDTO rol  : in.getListRolesUSuarios()) {
					roles.add( RolesUsuarioMapper.mapper(rol));
				}
				
			}
			
			u = Usuario.builder()
					.id(upk)
					.persona(p)
					.email(in.getEmail())
					.movil(in.getMovil())
					.password(in.getPassword())
					.sistema(in.getSistema())
					.username(in.getUsername())
					.listRolesUSuarios(roles)
					.build();		

			
		}catch (Exception e) {
			throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.MAPPER, LayerEnum.DAO , ErrorConstantes.ERROR_MAPPER_OBJECT);

		}	
		return u;
		
	
		
	}
	
	public static UsuarioDTO  Mapper(Usuario in, boolean isLogin) throws AdministradorUserException {
		TipoIdentificacionDTO t = null;
		PersonaPKDTO ppk= null;
		PersonaDTO  p = null;
		UsuarioPKDTO upk= null;
		UsuarioDTO  u= null;
		try {		
			if(Objects.nonNull(in.getPersona()) ) {
				if(Objects.nonNull (in.getPersona().getTipoIdentificacion())) {
					
					 t =  TipoIdentificacionDTO.builder()
							.id(in.getPersona().getTipoIdentificacion().getId())
							.nombre(  in.getPersona().getTipoIdentificacion().getNombre())
					         .build();
					 
					 ppk = PersonaPKDTO.builder()
							 .idTipoIdentificacion(in.getPersona().getId().getIdTipoIdentificacion())
							 .numeroIdentificacion(in.getPersona().getId().getNumeroIdentificacion() )
							 .build();
					 
					 p = PersonaDTO.builder()
							 .id(ppk)
							 .tipoIdentificacion(t)
							 .apellidos(in.getPersona().getApellidos())
							 .nombres(in.getPersona().getNombres())
							 .fechaNacimiento(in.getPersona().getFechaNacimiento())
							 .sexo(in.getPersona().getSexo())
							.build();
				}
				
						
			}	
			
			upk = UsuarioPKDTO.builder()
					.id( in.getId().getId())
					.idTipoIdentificacion(in.getId().getIdTipoIdentificacion())
					.numeroIdentificacion( in.getId().getNumeroIdentificacion())
					.build();
			
			List<RolesUsuarioDTO> roles = null;
			if( in.getListRolesUSuarios() != null && in.getListRolesUSuarios().size() > 0) {
				roles =  new ArrayList<RolesUsuarioDTO>();
				for( RolesUsuario rol  : in.getListRolesUSuarios()) {
					
					roles.add( RolesUsuarioMapper.mapper(rol));
				}
				
			}
			
			List<PrivilegiosRolUsuarioDTO> listPrivilegios = null;
			if( Objects.nonNull(in.getPrivilegios() ) && in.getPrivilegios().size() >0 ) {
				listPrivilegios = PrivilegioRolUsuarioMapper.mapperAsListDto(in.getPrivilegios() , isLogin);
			}
			
			u = UsuarioDTO.builder()
					.id(upk)
					.persona(p)
					.email(in.getEmail())
					.movil(in.getMovil())
					.password(in.getPassword())
					.sistema(in.getSistema())
					.username(in.getUsername())
					.listRolesUSuarios(roles)
					.privilegios(listPrivilegios)
					.build();		

			
		}catch (Exception e) {
			throw new AdministradorUserException( EntityEnum.USUARIO, MethodsEnum.MAPPER, LayerEnum.DAO , ErrorConstantes.ERROR_MAPPER_OBJECT);

		}	
		return u;
		
	}

}
