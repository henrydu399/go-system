package administradorUsers.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
@Embeddable
public class RolesSistemaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID" )
	private Long id;

	@Column(name="ID_SISTEMA")
	private Long idSistema;

	@Column(name="NOMBRE_ROL" )
	private String nombreRol;




}