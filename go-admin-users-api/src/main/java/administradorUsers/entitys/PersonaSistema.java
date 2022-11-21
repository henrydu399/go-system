package administradorUsers.entitys;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
public class PersonaSistema  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonaSistemaPK id;
	
	
	
	/*
	 * @JsonIgnore
	 * 
	 * @OneToOne()
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name="ID_TIPO_IDENTIFICACION",
	 * referencedColumnName="ID_TIPO_IDENTIFICACION" , insertable = false ,
	 * updatable = false),
	 * 
	 * @JoinColumn(name="NUMERO_IDENTIFICACION",
	 * referencedColumnName="NUMERO_IDENTIFICACION" , insertable = false , updatable
	 * = false) }) private Persona persona;
	 */
	
	
	  @JsonIgnore
	  @ManyToOne()
	  @JoinColumn(name="id_sistema" , insertable = false , updatable = false)
	private Systema systema;
	
	

}
