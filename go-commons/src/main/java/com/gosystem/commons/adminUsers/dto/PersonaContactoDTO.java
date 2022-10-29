package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonaContactoDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private PersonaContactoPKDTO id;
	
	
	private String movil;
	
	
	private String tel;
	
	
	private String direccion;
	
	
	private Date fechaCreacion;
	
	
	private Boolean activo;
	
	private Long idDepartamento;
	
	private Long idCiudad;
	
	private Long idBarrio;
	
	
	

}
