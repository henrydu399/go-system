package com.gosystem.home.entitys;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="INMUEBLE_SEGUIMIENTO_DETALLE")
@NamedQuery(name="InmuebleSeguimientoDetalle.findAll", query="SELECT i FROM InmuebleSeguimientoDetalle i")
public class InmuebleSeguimientoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_limite")
	private Date fechaLimite;

	private String observaciones;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado_seguimiento")
	private Estado estado;

	//bi-directional many-to-one association to InmueblesSeguimiento
	@ManyToOne
	@JoinColumn(name="id_inmueble_seguimiento")
	private InmueblesSeguimiento inmueblesSeguimiento;

	//bi-directional many-to-one association to TipoSeguimiento
	@ManyToOne
	@JoinColumn(name="id_tipo_seguimiento")
	private TipoSeguimiento tipoSeguimiento;

	
}