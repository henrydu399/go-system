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
public class PersonaSistemaPK implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Column(name="id_sistema" )
	private int idSistema;
	
	@Column(name="NUMERO_IDENTIFICACION" )
	private String numeroIdentificacion;

	@Column(name="ID_TIPO_IDENTIFICACION")
	private int idTipoIdentificacion;

}
