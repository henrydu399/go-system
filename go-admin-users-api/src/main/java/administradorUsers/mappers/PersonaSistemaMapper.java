package administradorUsers.mappers;

import com.gosystem.commons.adminUsers.dto.PersonaSistemaDTO;

import administradorUsers.PersonaSistemaPKMapper;
import administradorUsers.entitys.PersonaSistema;

public class PersonaSistemaMapper {
	
	
	public static PersonaSistemaDTO mapper( PersonaSistema in) {
		PersonaSistemaDTO out =  PersonaSistemaDTO.builder()
				.id(PersonaSistemaPKMapper.mapper(in.getId()))
				.build();
		return out;
	}
	
	public static PersonaSistema mapper( PersonaSistemaDTO in) {
		PersonaSistema out =  PersonaSistema.builder()
				.id( PersonaSistemaPKMapper.mapper(in.getId()))
				.build();
		return out;
	}
	
	

}
