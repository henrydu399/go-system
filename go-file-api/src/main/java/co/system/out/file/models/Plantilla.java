package co.system.out.file.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table( name = "juz_plantillas")
public class Plantilla implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private String id;
	
	

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoPlantilla", nullable=false)
	private TiposPlantilla tipoPlantilla;
	
	
	@Column(name="nombre_plantilla" )
	private String nombrePlantilla;
	
	@Column(name="fechaCreacion" )
	private Timestamp fechaCreacion;
	
	@Column(name="data" )
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TiposPlantilla getTipoPlantilla() {
		return tipoPlantilla;
	}

	public void setTipoPlantilla(TiposPlantilla tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}

	public String getNombrePlantilla() {
		return nombrePlantilla;
	}

	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	

}
