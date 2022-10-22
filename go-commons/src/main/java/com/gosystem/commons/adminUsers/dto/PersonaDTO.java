package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private PersonaPKDTO id;

	private String apellidos;

	private int edad;
	
	private String estadoCivil;
	
	private Date fechaNacimiento;


	private Timestamp fechaCreacion;

	private String nombres;

	
	private int numeroHijos;

	private String profesion;

	private String sexo;

	
	private TipoIdentificacionDTO tipoIdentificacion;

	
	private List<UsuarioDTO> usuarios;

	

}