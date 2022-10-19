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
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tipo_identificacion")
@NamedQuery(name="TipoIdentificacion.findAll", query="SELECT t FROM TipoIdentificacion t")
public class TipoIdentificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String prefijo;

	private String nombre;
	
	private Date fechaCreacion;

	//bi-directional many-to-one association to Persona
	@JsonIgnore
	@OneToMany(mappedBy="tipoIdentificacion")
	private List<Persona> personas;
	


	

}