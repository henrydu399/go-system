package com.gosystem.home.services.imp;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gosystem.commons.adminUsers.dto.PersonaDTO;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.home.client.services.IAdministracionClientPersona;
import com.gosystem.home.services.IPersonaService;

@Service
public class PersonaServiceImpl  implements IPersonaService{
	
	@Autowired
	private IAdministracionClientPersona administracionClientPersona;

	@Override
	public List<PersonaDTO> getAll() throws HomeException {
		return administracionClientPersona.getAll();
	}

	@Override
	public void save(PersonaDTO u) throws HomeException {
		 
		if( Objects.nonNull(  u.getListPersonaContacto() ) && u.getListPersonaContacto().size() >0  ) {
			u.getListPersonaContacto().get(0).setActivo(true);
		}
		
		this.administracionClientPersona.save(u);
		
	}

	@Override
	public void edith(PersonaDTO p) throws HomeException {
		
		if( Objects.nonNull(  p.getListPersonaContacto() ) && p.getListPersonaContacto().size() >0  ) {
			p.getListPersonaContacto().get(0).setActivo(true);
		}
		this.administracionClientPersona.edith(p);
		
	}

	@Override
	public PersonaDTO find(PersonaDTO p) throws HomeException {
		return this.administracionClientPersona.find(p);
	
	}

	@Override
	public List<PersonaDTO> findAll(PersonaDTO p) throws HomeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PersonaDTO p) throws HomeException {
		 this.administracionClientPersona.delete(p);
		
	}

	@Override
	public void desactivate(PersonaDTO p) throws HomeException {
		// TODO Auto-generated method stub
		
	}

}
