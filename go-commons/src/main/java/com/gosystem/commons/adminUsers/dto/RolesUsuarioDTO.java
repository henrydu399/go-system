package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesUsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private RolesUsuarioPKDTO id;


	private Date fechaCreacion;

	
	private SystemaDTO systema;

	private RolesSistemaDTO rolesSistema1;


	private RolesSistemaDTO rolesSistema2;
	
	private UsuarioDTO usuario;

	private boolean estado;

}