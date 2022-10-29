package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private UsuarioPKDTO id;

	private String email;

	
	private Timestamp fechaCreacion;

	private String movil;

	private String username;
	
	private String password;
	
	private String token;
	
    private String sistema;
    
	private boolean activo;
	
	private boolean confirmado;
	
	private String tokenActivate;
	

	private RolesUsuarioDTO rolUsuario;
	
	private PersonaContactoDTO personaContacto;


	private PersonaDTO persona;
	

	private String roles;
	
	private List<RolesUsuarioDTO> listRolesUSuarios;
	
	private RolesSistemaDTO rol;
	
	private List<PrivilegiosRolUsuarioDTO> privilegios;



}