package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the INMUEBLE_SEGUIMIENTO_DETALLE database table.
 * 
 */
@Entity
@Table(name="INMUEBLE_SEGUIMIENTO_DETALLE")
@NamedQuery(name="InmuebleSeguimientoDetalle.findAll", query="SELECT i FROM InmuebleSeguimientoDetalle i")
public class InmuebleSeguimientoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

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

	public InmuebleSeguimientoDetalle() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaLimite() {
		return this.fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public InmueblesSeguimiento getInmueblesSeguimiento() {
		return this.inmueblesSeguimiento;
	}

	public void setInmueblesSeguimiento(InmueblesSeguimiento inmueblesSeguimiento) {
		this.inmueblesSeguimiento = inmueblesSeguimiento;
	}

	public TipoSeguimiento getTipoSeguimiento() {
		return this.tipoSeguimiento;
	}

	public void setTipoSeguimiento(TipoSeguimiento tipoSeguimiento) {
		this.tipoSeguimiento = tipoSeguimiento;
	}

}