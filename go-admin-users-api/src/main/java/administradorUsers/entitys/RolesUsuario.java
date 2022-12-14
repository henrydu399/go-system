package administradorUsers.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="roles_usuario")
public class RolesUsuario implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolesUsuarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	//bi-directional many-to-one association to RolesSistema
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="id_rol_sistema", referencedColumnName="id" ,  insertable = false , updatable = false ),
		@JoinColumn(name="id_sistema", referencedColumnName="id_sistema" ,  insertable = false , updatable = false),
		@JoinColumn(name="nombre_rol_sistema", referencedColumnName="NOMBRE_ROL" ,  insertable = false , updatable = false)
		})
	private RolesSistema rolesSistema;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="id_sistema", referencedColumnName="id" ,  insertable = false , updatable = false),
		})
	private Systema systema;

	
	//bi-directional many-to-one association to RolesSistema
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="ID_USUARIO", referencedColumnName="ID" ,  insertable = false , updatable = false  ),
		@JoinColumn(name="ID_TIPO_IDENTIFICACION", referencedColumnName="ID_TIPO_IDENTIFICACION" ,  insertable = false , updatable = false ),
		@JoinColumn(name="NUMERO_IDENTIFICACION", referencedColumnName="NUMERO_IDENTIFICACION" ,  insertable = false , updatable = false )
		})
	private Usuario usuario;
	
	private boolean estado;


	
}
	