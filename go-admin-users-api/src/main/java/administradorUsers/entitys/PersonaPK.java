package administradorUsers.entitys;

import java.io.Serializable;
import java.util.Objects;

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
public class PersonaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NUMERO_IDENTIFICACION" )
	private String numeroIdentificacion;

	@Column(name="ID_TIPO_IDENTIFICACION")
	private int idTipoIdentificacion;

	@Override
	public int hashCode() {
		return Objects.hash(idTipoIdentificacion, numeroIdentificacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaPK other = (PersonaPK) obj;
		return idTipoIdentificacion == other.idTipoIdentificacion
				&& Objects.equals(numeroIdentificacion, other.numeroIdentificacion);
	}

	
	
	
}