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
public class BarrioDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private BarrioPKDTO id;
	
	private String nombre;
	
	private DepartamentoDTO ciudadDTO;

}
