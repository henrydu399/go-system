package administradorUsers.entitys;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonaPK id;

	@Column(name="apellidos")
	private String apellidos;

	@Column(name="EDAD")
	private Integer edad;

	@Column(name="ESTADO_CIVIL")
	private String estadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@JsonIgnore
	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	private String nombres;

	@Column(name="NUMERO_HIJOS")
	private Integer numeroHijos;

	private String profesion;

	private String sexo;

	private String religion;
	
	private String nivelEscolaridad;
	
	//bi-directional many-to-one association to TipoIdentificacion
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="ID_TIPO_IDENTIFICACION" , insertable = false , updatable = false)
	private TipoIdentificacion tipoIdentificacion;

	//bi-directional many-to-one association to Usuario
	@JsonIgnore
	@OneToMany(mappedBy="persona")
	private List<Usuario> usuarios;
	
	



}