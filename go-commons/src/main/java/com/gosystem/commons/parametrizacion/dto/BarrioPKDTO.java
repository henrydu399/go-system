package com.gosystem.commons.parametrizacion.dto;

import java.io.Serializable;



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
public class BarrioPKDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long idDepartamento;
	
	private long idCiudad;

}
