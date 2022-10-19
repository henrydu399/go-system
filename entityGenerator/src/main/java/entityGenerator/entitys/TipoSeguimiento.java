package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_SEGUIMIENTO database table.
 * 
 */
@Entity
@Table(name="TIPO_SEGUIMIENTO")
@NamedQuery(name="TipoSeguimiento.findAll", query="SELECT t FROM TipoSeguimiento t")
public class TipoSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to InmuebleSeguimientoDetalle
	@OneToMany(mappedBy="tipoSeguimiento")
	private List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles;

	public TipoSeguimiento() {
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

	public List<InmuebleSeguimientoDetalle> getInmuebleSeguimientoDetalles() {
		return this.inmuebleSeguimientoDetalles;
	}

	public void setInmuebleSeguimientoDetalles(List<InmuebleSeguimientoDetalle> inmuebleSeguimientoDetalles) {
		this.inmuebleSeguimientoDetalles = inmuebleSeguimientoDetalles;
	}

	public InmuebleSeguimientoDetalle addInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().add(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setTipoSeguimiento(this);

		return inmuebleSeguimientoDetalle;
	}

	public InmuebleSeguimientoDetalle removeInmuebleSeguimientoDetalle(InmuebleSeguimientoDetalle inmuebleSeguimientoDetalle) {
		getInmuebleSeguimientoDetalles().remove(inmuebleSeguimientoDetalle);
		inmuebleSeguimientoDetalle.setTipoSeguimiento(null);

		return inmuebleSeguimientoDetalle;
	}

}