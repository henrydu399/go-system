package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.PersonaContactoDTO;

import administradorUsers.entitys.PersonaContacto;

public class PersonaContactoMapper {
	
	public static PersonaContactoDTO mapper( PersonaContacto in) {
		PersonaContactoDTO out = PersonaContactoDTO.builder()
				.id(PersonaContactoPKMapper.mapper(in.getId()))
				.activo(in.getActivo())
				.direccion(in.getDireccion())
				.fechaCreacion(in.getFechaCreacion())
				.idBarrio(in.getIdBarrio())
				.idCiudad(in.getIdCiudad())
				.idDepartamento(in.getIdDepartamento())
				.movil(in.getMovil())
				.build();
		
		return out;
		
	}
	
	public static PersonaContacto mapper( PersonaContactoDTO in) {
		PersonaContacto out = PersonaContacto.builder()
				.id(PersonaContactoPKMapper.mapper(in.getId()))
				.activo(in.getActivo())
				.direccion(in.getDireccion())
				.fechaCreacion(in.getFechaCreacion())
				.idBarrio(in.getIdBarrio())
				.idCiudad(in.getIdCiudad())
				.idDepartamento(in.getIdDepartamento())
				.movil(in.getMovil())
				.build();
		
		return out;
		
	}

}
