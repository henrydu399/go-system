package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

import java.sql.Timestamp;
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
public class RolesSistemaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private RolesSistemaPKDTO id;

	
	private Timestamp fechaCreacion;


	private List<RolesUsuarioDTO> rolesUsuarios1;


	private List<RolesUsuarioDTO> rolesUsuarios2;



}