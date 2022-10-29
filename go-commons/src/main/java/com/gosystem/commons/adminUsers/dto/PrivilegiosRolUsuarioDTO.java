package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class PrivilegiosRolUsuarioDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private PrivilegiosRolUsuarioPKDTO id;
	private Date fechaCreacion;
	private boolean crear;
	private boolean editar;
	private boolean buscar;
	private boolean getAll;
	private boolean getNormal;
	private boolean desactivar;
	private boolean eliminar;
	

	private PrivilegiosDTO privilegio;
	
}
