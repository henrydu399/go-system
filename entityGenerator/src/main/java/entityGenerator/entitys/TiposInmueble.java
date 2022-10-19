package entityGenerator.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPOS_INMUEBLES database table.
 * 
 */
@Entity
@Table(name="TIPOS_INMUEBLES")
@NamedQuery(name="TiposInmueble.findAll", query="SELECT t FROM TiposInmueble t")
public class TiposInmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Inmueble
	@OneToMany(mappedBy="tiposInmueble")
	private List<Inmueble> inmuebles;

	public TiposInmueble() {
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
		inmueble.setTiposInmueble(this);

		return inmueble;
	}

	public Inmueble removeInmueble(Inmueble inmueble) {
		getInmuebles().remove(inmueble);
		inmueble.setTiposInmueble(null);

		return inmueble;
	}

}