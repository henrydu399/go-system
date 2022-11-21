package administradorUsers.entitys;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="roles_sistema")
public class RolesSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId 
	private RolesSistemaPK id;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

	//bi-directional many-to-one association to RolesUsuario
	@JsonIgnore
	@OneToMany(mappedBy="rolesSistema")
	private List<RolesUsuario> rolesUsuarios;



}