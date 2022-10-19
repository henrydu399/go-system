package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


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
public class PrivilegiosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	private int level;
	private boolean activo;
	private Long fkIdPrivilegio;
	private String url;
	private boolean isVisibleMenu;
	private String iconoClass;
	private int idSistema;
	private Date fechaCreacion;

}
