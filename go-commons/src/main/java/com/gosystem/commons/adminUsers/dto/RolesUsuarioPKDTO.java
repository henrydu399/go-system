package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesUsuarioPKDTO implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


	private long id;

	
	private long idRolSistema;

	
	private int idSistema;

	
	private String nombreRol;


	private long idUsuario;


	private int idTipoIdentificacion;

	
	private String numeroIdentificacion;

	

}