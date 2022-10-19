package com.gosystem.home.entitys;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="INMUEBLES")
@NamedQuery(name="Inmueble.findAll", query="SELECT i FROM Inmueble i")
public class Inmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private int area;

	private int baños;

	@Column(name="cant_parqueaderos")
	private int cantParqueaderos;

	private String caracteristicas;

	private String direccion;

	private int estrato;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	private int habitaciones;

	@Column(name="id_barrio")
	private Long idBarrio;

	@Column(name="id_ciudad")
	private Long idCiudad;

	@Column(name="id_departamento")
	private BigInteger idDepartamento;

	@Column(name="id_tipo_identificacion")
	private Integer idTipoIdentificacion;

	@Column(name="id_tipo_identificacion_propietario")
	private Long idTipoIdentificacionPropietario;

	@Column(name="id_usuario")
	private Long idUsuario;

	@Column(name="id_usuario_propietario")
	private Long idUsuarioPropietario;

	@Column(name="numero_identificacion")
	private String numeroIdentificacion;

	@Column(name="numero_identificacion_propietario")
	private String numeroIdentificacionPropietario;

	private int precio;

	private String ubicacion;

	//bi-directional many-to-one association to TiposInmueble
	@ManyToOne
	@JoinColumn(name="id_tipo_inmueble")
	private TiposInmueble tiposInmueble;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to UsoInmueble
	@ManyToOne
	@JoinColumn(name="id_uso_inmueble")
	private UsoInmueble usoInmueble;

	//bi-directional many-to-one association to InmueblesSeguimiento
	@OneToMany(mappedBy="inmueble")
	@JsonIgnore
	private List<InmueblesSeguimiento> inmueblesSeguimientos;

	

}