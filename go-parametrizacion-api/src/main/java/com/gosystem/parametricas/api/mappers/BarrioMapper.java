package com.gosystem.parametricas.api.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gosystem.commons.parametrizacion.dto.BarrioDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.parametricas.api.entitys.Barrio;
import com.gosystem.parametricas.api.entitys.Ciudad;

public class BarrioMapper {
	
	
	public static BarrioDTO mapper( Barrio in) {
		BarrioDTO out = BarrioDTO.builder()
				.id( BarrioPKMapper.mapper(in.getId()))
				.nombre(in.getNombre())
				.build();
		return out;
		
	}
	
	public static Barrio mapper( BarrioDTO in) {
		Barrio out = Barrio.builder()
				.id( BarrioPKMapper.mapper(in.getId()))
				.nombre(in.getNombre())
				.build();
		return out;
		
	}
	
	
	public static  List<BarrioDTO> mapperListAsDto( List<Barrio> in) {
		List<BarrioDTO> out = new ArrayList<BarrioDTO>();
		if( Objects.nonNull(in)) {
			for(Barrio inE : in ) {
				out.add( mapper(inE));
			}
		}
		return out;
	}

}
