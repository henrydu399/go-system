package administradorUsers.entitys;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.adminUsers.dto.PersonaPKDTO;
import com.gosystem.commons.adminUsers.dto.TipoIdentificacionDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonaContactoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column ( name = "ID")
	private Long id;
	
	
	@Column(name="NUMERO_IDENTIFICACION" , insertable=false, updatable=false)
	private String numeroIdentificacion;

	@Column(name="ID_TIPO_IDENTIFICACION", insertable=false, updatable=false)
	private int idTipoIdentificacion;
	
	
	

	
	
	
	
	
	
 
}
