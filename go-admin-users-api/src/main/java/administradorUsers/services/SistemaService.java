package administradorUsers.services;

import java.util.Optional;

import administradorUsers.entitys.Systema;
import administradorUsers.logic.IEntityDao;

public interface SistemaService extends IEntityDao<Systema> {
	
	Optional<Systema> findBynombre(String name);

}
