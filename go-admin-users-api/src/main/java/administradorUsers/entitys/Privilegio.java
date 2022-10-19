package administradorUsers.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="privilegios")
public class Privilegio  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String nombre;
	private String descripcion;
	private int level;
	private boolean activo;
	private Long fkIdPrivilegio;
	private String url;
	private boolean isVisibleMenu;
	private String iconoClass;
	private int idSistema;
	private Date fechaCreacion;
	
	@JsonIgnore
	@OneToMany(mappedBy="privilegio")
	private List<PrivilegiosRolUsuario> listPrivilegios;
	
	

}
