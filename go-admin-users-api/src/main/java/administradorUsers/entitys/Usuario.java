package administradorUsers.entitys;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioPK id;

	private String email;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	private String movil;

	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	private boolean activo;
	
	private boolean confirmado;
	
	private String tokenActivate;
	
	
	@Transient
	private String token;
	
	@Transient
	private String roles;
	
	@Transient
	private String sistema;
	@Transient
	private List<PrivilegiosRolUsuario> privilegios;
	
	@Transient
	private RolesSistemaDTO rol;
	
	@Transient
	private RolesUsuario rolUsuario;
	
	

	//bi-directional many-to-one association to Persona
	@ManyToOne()
	@JoinColumns({
		@JoinColumn(name="ID_TIPO_IDENTIFICACION", referencedColumnName="ID_TIPO_IDENTIFICACION" , insertable = false , updatable = false),
		@JoinColumn(name="NUMERO_IDENTIFICACION",  referencedColumnName="NUMERO_IDENTIFICACION" ,  insertable = false , updatable = false)
		})
	private Persona persona;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<RolesUsuario> listRolesUSuarios;

	
	
	
	

}