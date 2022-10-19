package administradorUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import administradorUsers.entitys.TipoIdentificacion;

public interface ITipoIdentificacionRepository  extends JpaRepository<TipoIdentificacion, Integer>{

}
