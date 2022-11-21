package administradorUsers.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(name="Systema.findAll", query="SELECT s FROM Systema s")
public class Systema implements Serializable {


	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String nombre;

	private Date fechaCreacion;
	
	

}