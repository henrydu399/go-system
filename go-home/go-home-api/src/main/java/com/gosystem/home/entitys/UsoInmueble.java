package com.gosystem.home.entitys;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="USO_INMUEBLE")
@NamedQuery(name="UsoInmueble.findAll", query="SELECT u FROM UsoInmueble u")
public class UsoInmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to Inmueble
	@JsonIgnore
	@OneToMany(mappedBy="usoInmueble")
	private List<Inmueble> inmuebles;

	

}