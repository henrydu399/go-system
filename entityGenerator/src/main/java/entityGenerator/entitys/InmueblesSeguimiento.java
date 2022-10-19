package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the INMUEBLES_SEGUIMIENTOS database table.
 * 
 */
@Entity
@Table(name="INMUEBLES_SEGUIMIENTOS")
@NamedQuery(name="InmueblesSeguimiento.findAll", query="SELECT i FROM InmueblesSeguimiento i")
public class InmueblesSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_limite")
	private Date fechaLimite;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;

	@Column(name="id_tipo_identificacion_cliente")
	private int idTipoIdentificacionCliente;

	@Column(name="id_tipo_identificacion_usuario")
	private int idTipoIdentificacionUsuario;

	@Column(name="id_usuario")
	private BigInteger idUsuario;

	@Column(name="id_usuario_cliente")
	private BigInteger idUsuarioCliente;

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
	@OneToMany(mappedBy="inmueblesSeguimiento")
	private List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles;

	public InmueblesSeguimiento() {
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

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getIdTipoIdentificacionCliente() {
		return this.idTipoIdentificacionCliente;
	}

	public void setIdTipoIdentificacionCliente(int idTipoIdentificacionCliente) {
		this.idTipoIdentificacionCliente = idTipoIdentificacionCliente;
	}

	public int getIdTipoIdentificacionUsuario() {
		return this.idTipoIdentificacionUsuario;
	}

	public void setIdTipoIdentificacionUsuario(int idTipoIdentificacionUsuario) {
		this.idTipoIdentificacionUsuario = idTipoIdentificacionUsuario;
	}

	public BigInteger getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigInteger getIdUsuarioCliente() {
		return this.idUsuarioCliente;
	}

	public void setIdUsuarioCliente(BigInteger idUsuarioCliente) {
		this.idUsuarioCliente = idUsuarioCliente;
	}

	public String getNumeroIdentificacionCliente() {
		return this.numeroIdentificacionCliente;
	}

	public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	public String getNumeroIdentificacionUsuario() {
		return this.numeroIdentificacionUsuario;
	}

	public void setNumeroIdentificacionUsuario(String numeroIdentificacionUsuario) {
		this.numeroIdentificacionUsuario = numeroIdentificacionUsuario;
	}

	public Inmueble getInmueble() {
		return this.inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<InmuebleSeguimientoDetalle> getInmuebleSeguimientoDetalles() {
		return this.inmuebleSeguimientoDetalles;
	}

	public void setInmuebleSeguimientoDetalles(List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles) {
		this.inmuebleSeguimientoDetalles = inmuebleSeguimientoDetalles;
	}

	public InmuebleSeguimientoDetalle addInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().add(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setInmueblesSeguimiento(this);

		return inmuebleSeguimientoDetalle;
	}

	public InmuebleSeguimientoDetalle removeInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().remove(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setInmueblesSeguimiento(null);

		return inmuebleSeguimientoDetalle;
	}

}