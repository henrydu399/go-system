package com.gosystem.commons.adminUsers.dto;

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
public class PrivilegiosRolUsuarioPKDTO  implements Serializable{

	private static final long serialVersionUID = 1L;


	private long id;
	
	
	private int idTipoIdentificacion;
	

	private String numeroIdentificacion;
	
	
	private long idUsuario;
	
	
	private int idSistema;


	private long idRolSistema;

	
	private String nombreRol;
	
	
	private String idPrivilegio;
}
