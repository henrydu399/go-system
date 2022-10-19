package com.gosystem.parametricas.api.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


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
@Embeddable
public class CiudadPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="ID" )
	private long id;

	@Column(name = "ID_DEPARTAMENTO")
	private long idDepartamento;

}
