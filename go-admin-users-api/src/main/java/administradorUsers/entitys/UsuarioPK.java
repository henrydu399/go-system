package administradorUsers.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


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
public class UsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	
	
	private static final long serialVersionUID = 1L;

	@Column(name="id")
	private Long id;

	@Column(name="ID_TIPO_IDENTIFICACION")
	private Integer idTipoIdentificacion;

	@Column(name="NUMERO_IDENTIFICACION")
	private String numeroIdentificacion;

	
}