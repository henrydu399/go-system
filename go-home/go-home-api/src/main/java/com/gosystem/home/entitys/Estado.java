package com.gosystem.home.entitys;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to Inmueble
	@OneToMany(mappedBy="estado")
	private List<Inmueble> inmuebles;

	//bi-directional many-to-one association to InmueblesSeguimiento
	@OneToMany(mappedBy="estado")
	private List<InmueblesSeguimiento> inmueblesSeguimientos;

	//bi-directional many-to-one association to InmuebleSeguimientoDetalle
	@OneToMany(mappedBy="estado")
	private List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles;

	

}