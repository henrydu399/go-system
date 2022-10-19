package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the INMUEBLES database table.
 * 
 */
@Entity
@Table(name="INMUEBLES")
@NamedQuery(name="Inmueble.findAll", query="SELECT i FROM Inmueble i")
public class Inmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

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
	private BigInteger idBarrio;

	@Column(name="id_ciudad")
	private BigInteger idCiudad;

	@Column(name="id_departamento")
	private BigInteger idDepartamento;

	@Column(name="id_tipo_identificacion")
	private int idTipoIdentificacion;

	@Column(name="id_tipo_identificacion_propietario")
	private BigInteger idTipoIdentificacionPropietario;

	@Column(name="id_usuario")
	private BigInteger idUsuario;

	@Column(name="id_usuario_propietario")
	private BigInteger idUsuarioPropietario;

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
	private List<InmueblesSeguimiento> inmueblesSeguimientos;

	public Inmueble() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getArea() {
		return this.area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getBaños() {
		return this.baños;
	}

	public void setBaños(int baños) {
		this.baños = baños;
	}

	public int getCantParqueaderos() {
		return this.cantParqueaderos;
	}

	public void setCantParqueaderos(int cantParqueaderos) {
		this.cantParqueaderos = cantParqueaderos;
	}

	public String getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstrato() {
		return this.estrato;
	}

	public void setEstrato(int estrato) {
		this.estrato = estrato;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public BigInteger getIdBarrio() {
		return this.idBarrio;
	}

	public void setIdBarrio(BigInteger idBarrio) {
		this.idBarrio = idBarrio;
	}

	public BigInteger getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(BigInteger idCiudad) {
		this.idCiudad = idCiudad;
	}

	public BigInteger getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(BigInteger idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public int getIdTipoIdentificacion() {
		return this.idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public BigInteger getIdTipoIdentificacionPropietario() {
		return this.idTipoIdentificacionPropietario;
	}

	public void setIdTipoIdentificacionPropietario(BigInteger idTipoIdentificacionPropietario) {
		this.idTipoIdentificacionPropietario = idTipoIdentificacionPropietario;
	}

	public BigInteger getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigInteger getIdUsuarioPropietario() {
		return this.idUsuarioPropietario;
	}

	public void setIdUsuarioPropietario(BigInteger idUsuarioPropietario) {
		this.idUsuarioPropietario = idUsuarioPropietario;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacionPropietario() {
		return this.numeroIdentificacionPropietario;
	}

	public void setNumeroIdentificacionPropietario(String numeroIdentificacionPropietario) {
		this.numeroIdentificacionPropietario = numeroIdentificacionPropietario;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public TiposInmueble getTiposInmueble() {
		return this.tiposInmueble;
	}

	public void setTiposInmueble(TiposInmueble tiposInmueble) {
		this.tiposInmueble = tiposInmueble;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public UsoInmueble getUsoInmueble() {
		return this.usoInmueble;
	}

	public void setUsoInmueble(UsoInmueble usoInmueble) {
		this.usoInmueble = usoInmueble;
	}

	public List<InmueblesSeguimiento> getInmueblesSeguimientos() {
		return this.inmueblesSeguimientos;
	}

	public void setInmueblesSeguimientos(List<InmueblesSeguimiento> inmueblesSeguimientos) {
		this.inmueblesSeguimientos = inmueblesSeguimientos;
	}

	public InmueblesSeguimiento addInmueblesSeguimiento(InmueblesSeguimiento inmueblesSeguimiento) {
		getInmueblesSeguimientos().add(inmueblesSeguimiento);
		inmueblesSeguimiento.setInmueble(this);

		return inmueblesSeguimiento;
	}

	public InmueblesSeguimiento removeInmueblesSeguimiento(InmueblesSeguimiento inmueblesSeguimiento) {
		getInmueblesSeguimientos().remove(inmueblesSeguimiento);
		inmueblesSeguimiento.setInmueble(null);

		return inmueblesSeguimiento;
	}

}