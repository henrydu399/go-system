package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADOS database table.
 * 
 */
@Entity
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

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

	public Estado() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Inmueble addInmueble(Inmueble inmueble) {
		getInmuebles().add(inmueble);
		inmueble.setEstado(this);

		return inmueble;
	}

	public Inmueble removeInmueble(Inmueble inmueble) {
		getInmuebles().remove(inmueble);
		inmueble.setEstado(null);

		return inmueble;
	}

	public List<InmueblesSeguimiento> getInmueblesSeguimientos() {
		return this.inmueblesSeguimientos;
	}

	public void setInmueblesSeguimientos(List<InmueblesSeguimiento> inmueblesSeguimientos) {
		this.inmueblesSeguimientos = inmueblesSeguimientos;
	}

	public InmueblesSeguimiento addInmueblesSeguimiento(InmueblesSeguimiento inmueblesSeguimiento) {
		getInmueblesSeguimientos().add(inmueblesSeguimiento);
		inmueblesSeguimiento.setEstado(this);

		return inmueblesSeguimiento;
	}

	public InmueblesSeguimiento removeInmueblesSeguimiento(InmueblesSeguimiento inmueblesSeguimiento) {
		getInmueblesSeguimientos().remove(inmueblesSeguimiento);
		inmueblesSeguimiento.setEstado(null);

		return inmueblesSeguimiento;
	}

	public List<InmuebleSeguimientoDetalle> getInmuebleSeguimientoDetalles() {
		return this.inmuebleSeguimientoDetalles;
	}

	public void setInmuebleSeguimientoDetalles(List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles) {
		this.inmuebleSeguimientoDetalles = inmuebleSeguimientoDetalles;
	}

	public InmuebleSeguimientoDetalle addInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().add(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setEstado(this);

		return inmuebleSeguimientoDetalle;
	}

	public InmuebleSeguimientoDetalle removeInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().remove(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setEstado(null);

		return inmuebleSeguimientoDetalle;
	}

}