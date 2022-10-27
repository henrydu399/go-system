package administradorUsers.entitys;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "persona_contacto")
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
	
	private Long idDepartamento;
	
	private Long idCiudad;
	
	private Long idBarrio;
	
	
	//bi-directional many-to-one association to Persona
		@JsonIgnore
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumns({
			@JoinColumn(name="ID_TIPO_IDENTIFICACION", referencedColumnName="ID_TIPO_IDENTIFICACION" , insertable = false , updatable = false),
			@JoinColumn(name="NUMERO_IDENTIFICACION", referencedColumnName="NUMERO_IDENTIFICACION" ,  insertable = false , updatable = false)
			})
		private Persona persona;
		
		
		
		
		
	
	

}
