package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
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

	
	private String profesion;

	private String sexo;

	private String religion;
	
	private String nivelEscolaridad;

	
	private TipoIdentificacionDTO tipoIdentificacion;

	
	private List<PersonaContactoDTO> listPersonaContacto;
	
	private List<PersonaSistemaDTO> listPersonaSistema;
	
	
	private List<UsuarioDTO> usuarios;

	

}