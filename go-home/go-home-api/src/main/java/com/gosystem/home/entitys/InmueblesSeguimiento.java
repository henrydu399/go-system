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
@Table(name="INMUEBLES_SEGUIMIENTOS")
@NamedQuery(name="InmueblesSeguimiento.findAll", query="SELECT i FROM InmueblesSeguimiento i")
public class InmueblesSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_limite")
	private Date fechaLimite;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	@Column(name="id_tipo_identificacion_cliente")
	private Integer idTipoIdentificacionCliente;

	@Column(name="id_tipo_identificacion_usuario")
	private Integer idTipoIdentificacionUsuario;

	@Column(name="id_usuario")
	private Long idUsuario;

	@Column(name="id_usuario_cliente")
	private Long idUsuarioCliente;

	@Column(name="numero_identificacion_cliente")
	private String numeroIdentificacionCliente;

	@Column(name="numero_identificacion_usuario")
	private String numeroIdentificacionUsuario;

	//bi-directional many-to-one association to Inmueble
	@ManyToOne
	@JoinColumn(name="id_inmueble")
	private Inmueble inmueble;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//bi-directional many-to-one association to InmuebleSeguimientoDetalle
	@JsonIgnore
	@OneToMany(mappedBy="inmueblesSeguimiento")
	private List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles;

	

}