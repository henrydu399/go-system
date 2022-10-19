package com.gosystem.parametricas.api.mappers;

import com.gosystem.commons.parametrizacion.dto.BarrioPKDTO;
import com.gosystem.parametricas.api.entitys.BarrioPK;

public class BarrioPKMapper {
	
	
	public static BarrioPKDTO mapper( BarrioPK in) {
		BarrioPKDTO out =  BarrioPKDTO.builder()
				.id(in.getId())
				.idCiudad(in.getIdCiudad())
				.idDepartamento( in.getIdDepartamento())
				.build();
		return out;				
	}
	
	public static BarrioPK mapper( BarrioPKDTO in) {
		BarrioPK out =  BarrioPK.builder()
				.id(in.getId())
				.idCiudad(in.getIdCiudad())
				.idDepartamento( in.getIdDepartamento())
				.build();
		return out;				
	}
	

}
