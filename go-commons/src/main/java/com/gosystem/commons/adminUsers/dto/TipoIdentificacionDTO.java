package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

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
public class TipoIdentificacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int id;

	private String prefijo;

	private String nombre;


	private List<PersonaDTO> personas;

	

}