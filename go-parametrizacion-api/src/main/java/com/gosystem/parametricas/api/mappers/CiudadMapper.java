package com.gosystem.parametricas.api.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.parametricas.api.entitys.Ciudad;
import com.gosystem.parametricas.api.entitys.Departamento;


public class CiudadMapper {
	
	public static CiudadDTO mapper( Ciudad in) {
		
		CiudadDTO out = CiudadDTO.builder()
				.id(CiudadPKMapper.mapper(in.getId()))
				//.departamentoDTO(DepartamentoMapper.map(in.getDepartamento()))
				.nombre(in.getNombre())
				.build();
		return out;
				
	}
	
	
	public static Ciudad mapper( CiudadDTO in) {
		
		Ciudad out = Ciudad.builder()
				.id(CiudadPKMapper.mapper(in.getId()))
				//.departamento(DepartamentoMapper.map(in.getDepartamentoDTO()))
				.nombre(in.getNombre())
				.build();
		return out;
				
	}
	
	
	public static List<CiudadDTO> mapList( List<Ciudad>  in){
		List<CiudadDTO> out = new ArrayList<>();
		if( Objects.nonNull(in)) {
			for(Ciudad inE : in ) {
				out.add( mapper(inE));
			}
		}
		return out;
	}

}
