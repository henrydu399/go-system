package com.gosystem.parametricas.api.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;
import com.gosystem.parametricas.api.entitys.Departamento;

public class DepartamentoMapper {
	
	public static DepartamentoDTO map(Departamento in) {
		DepartamentoDTO out = DepartamentoDTO.builder()
				.id(in.getId())
				.nombre(in.getNombre())
				.build();
		return out;
	}
	
	public static Departamento map(DepartamentoDTO in) {
		Departamento out = Departamento.builder()
				.id(in.getId())
				.nombre(in.getNombre())
				.build();
		return out;
	}
	
	
	public static List<DepartamentoDTO> mapList( List<Departamento>  in){
		List<DepartamentoDTO> out = new ArrayList<>();
		if( Objects.nonNull(in)) {
			for(Departamento inE : in ) {
				out.add( map(inE));
			}
		}
		return out;
	}

}
