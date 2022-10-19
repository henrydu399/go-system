package administradorUsers.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "personas_contacto")
public class PersonaContacto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private PersonaContactoPK id;
	
	@Column( name = "MOVIL")
	private String movil;
	
	@Column( name = "TEL")
	private String tel;
	
	@Column( name = "DIRECCION")
	private String direccion;
	
	@Column( name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column( name = "ACTIVO")
	private Boolean activo;
	
	
	//bi-directional many-to-one association to Persona
		@JsonIgnore
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumns({
			@JoinColumn(name="ID_TIPO_IDENTIFICACION", referencedColumnName="ID_TIPO_IDENTIFICACION" , insertable = false , updatable = false),
			@JoinColumn(name="NUMERO_IDENTIFICACION", referencedColumnName="NUMERO_IDENTIFICACION" ,  insertable = false , updatable = false)
			})
		private Persona persona;
		
		
		
		
		public PersonaContacto() {
		
		}
		


		public PersonaContacto(PersonaContactoPK id, String movil, String tel, String direccion, Date fechaCreacion,
				Boolean activo, Persona persona) {
			super();
			this.id = id;
			this.movil = movil;
			this.tel = tel;
			this.direccion = direccion;
			this.fechaCreacion = fechaCreacion;
			this.activo = activo;
			this.persona = persona;
		}


		public PersonaContactoPK getId() {
			return id;
		}


		public void setId(PersonaContactoPK id) {
			this.id = id;
		}


		public String getMovil() {
			return movil;
		}


		public void setMovil(String movil) {
			this.movil = movil;
		}


		public String getTel() {
			return tel;
		}


		public void setTel(String tel) {
			this.tel = tel;
		}


		public String getDireccion() {
			return direccion;
		}


		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}


		public Date getFechaCreacion() {
			return fechaCreacion;
		}


		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}


		public Boolean getActivo() {
			return activo;
		}


		public void setActivo(Boolean activo) {
			this.activo = activo;
		}


		public Persona getPersona() {
			return persona;
		}


		public void setPersona(Persona persona) {
			this.persona = persona;
		}
		
		
	
	
	
	

}
