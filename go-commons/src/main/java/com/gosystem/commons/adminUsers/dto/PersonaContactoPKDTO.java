package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaContactoPKDTO  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String numeroIdentificacion;

	private int idTipoIdentificacion;
	
	

}
