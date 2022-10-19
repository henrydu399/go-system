package com.gosystem.parametricas.api.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table (name = "DEPARTAMENTOS") 
@Entity
public class Departamento  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "NOMBRE")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy="departamento")
	private List<Ciudad> ciudades;
	
	
	

}
