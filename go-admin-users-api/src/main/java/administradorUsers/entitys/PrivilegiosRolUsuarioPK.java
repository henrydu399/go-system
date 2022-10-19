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
public class PrivilegiosRolUsuarioPK  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="ID_ROL_USUARIO" ,insertable=false, updatable=false)
	private long id;
	
	@Column(name="ID_TIPO_IDENTIFICACION" , insertable=false, updatable=false)
	private int idTipoIdentificacion;
	
	@Column(name="NUMERO_IDENTIFICACION" , insertable=false, updatable=false)
	private String numeroIdentificacion;
	
	@Column(name="ID_USUARIO" , insertable=false, updatable=false)
	private long idUsuario;
	
	@Column(name="ID_SISTEMA", insertable=false, updatable=false)
	private int idSistema;

	@Column(name="ID_ROL_SISTEMA" ,  insertable=false, updatable=false)
	private long idRolSistema;

	@Column(name="nombre_rol_sistema", insertable=false, updatable=false)
	private String nombreRol;
	
	@Column(name="ID_PRIVILEGIO", insertable=false, updatable=false)
	private String idPrivilegio;







}
