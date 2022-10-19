package administradorUsers.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonaContactoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column ( name = "ID")
	private Long id;
	
	
	@Column(name="NUMERO_IDENTIFICACION" , insertable=false, updatable=false)
	private String numeroIdentificacion;

	@Column(name="ID_TIPO_IDENTIFICACION", insertable=false, updatable=false)
	private int idTipoIdentificacion;
	
	
	
	
	

	public PersonaContactoPK(Long id, String numeroIdentificacion, int idTipoIdentificacion) {
		super();
		this.id = id;
		this.numeroIdentificacion = numeroIdentificacion;
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public PersonaContactoPK() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public int getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	
	
	
	
	
	
	
 
}
