package com.gosystem.commons.parametrizacion.dto;

import java.io.Serializable;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoDTO  implements Serializable{
	

	private static final long serialVersionUID = 1L;


	private long id;

	
	private String nombre;
	

	private List<CiudadDTO> ciudades;
	
	
	

}
