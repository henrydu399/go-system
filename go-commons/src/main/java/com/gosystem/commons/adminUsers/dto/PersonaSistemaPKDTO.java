package com.gosystem.commons.adminUsers.dto;

import java.io.Serializable;

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
public class PersonaSistemaPKDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Integer idSistema;
	

	private String numeroIdentificacion;

	
	private int idTipoIdentificacion;

}
