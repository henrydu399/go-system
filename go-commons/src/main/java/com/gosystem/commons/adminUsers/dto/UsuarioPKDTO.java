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
public class UsuarioPKDTO implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long id;


	private int idTipoIdentificacion;


	private String numeroIdentificacion;

	
}