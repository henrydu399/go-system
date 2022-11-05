package administradorUsers.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.exceptions.AdministradorUserException;

import administradorUsers.entitys.RolesUsuario;
import administradorUsers.entitys.Usuario;
import administradorUsers.repository.RolesUsuarioRepository;
import administradorUsers.services.IRolesUsuarioService;


@Service
public class RolesUsuarioServiceImpl implements IRolesUsuarioService {

	@Autowired
	RolesUsuarioRepository respository;
	
	@Override
	public List<RolesUsuario> getAll() throws AdministradorUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(RolesUsuario u) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edith(RolesUsuario p) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RolesUsuario find(RolesUsuario p) throws AdministradorUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolesUsuario> findAll(RolesUsuario p) throws AdministradorUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RolesUsuario p) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}
	

	public List<RolesUsuario> findByUsuario(Usuario usuario) throws AdministradorUserException {
		return respository.findByUsuario(usuario);

	}



	@Override
	public void desactivate(RolesUsuario usuario) throws AdministradorUserException {
		// TODO Auto-generated method stub
		
	}

}
