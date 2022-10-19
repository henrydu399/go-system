package com.gosystem.parametricas.api.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
@Table (name = "CIUDADES") 
@Entity
public class Ciudad  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CiudadPK id;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="ID_DEPARTAMENTO" , insertable = false , updatable = false)
	private Departamento departamento;

}
