package com.gosystem.parametricas.api.mappers;

import com.gosystem.commons.parametrizacion.dto.CiudadPKDTO;
import com.gosystem.parametricas.api.entitys.CiudadPK;

public class CiudadPKMapper {
	
	
	public static CiudadPKDTO mapper(CiudadPK in) {
		CiudadPKDTO out =  CiudadPKDTO.builder()
				.id( in.getId())
				.idDepartamento( in.getIdDepartamento())
				.build();
		return out;
		
	}
	
	public static CiudadPK mapper(CiudadPKDTO in) {
		CiudadPK out =  CiudadPK.builder()
				.id( in.getId())
				.idDepartamento( in.getIdDepartamento())
				.build();
		return out;
		
	}

}
