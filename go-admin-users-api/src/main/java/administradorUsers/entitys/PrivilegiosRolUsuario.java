package administradorUsers.entitys;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="privilegios_rol_usuario")
public class PrivilegiosRolUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PrivilegiosRolUsuarioPK id;
	private Date fechaCreacion;
	private boolean crear;
	private boolean editar;
	private boolean buscar;
	
	@Column(name = "getAll")
	private boolean getAll;
	
	@Column(name = "getNormal")
	private boolean getNormal;
	
	private boolean desactivar;
	private boolean eliminar;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="ID_PRIVILEGIO" , insertable = false , updatable = false)
	private Privilegio privilegio;
	
	//bi-directional many-to-one association to Persona
		@JsonIgnore
		@ManyToOne()
		@JoinColumns({
			@JoinColumn(name="ID_USUARIO",             referencedColumnName="ID" , insertable = false , updatable = false),
			@JoinColumn(name="ID_TIPO_IDENTIFICACION", referencedColumnName="ID_TIPO_IDENTIFICACION" , insertable = false , updatable = false),
			@JoinColumn(name="NUMERO_IDENTIFICACION",  referencedColumnName="NUMERO_IDENTIFICACION" ,  insertable = false , updatable = false)
			})
		private Usuario usuario;
	
	
	
	
	

}
