package com.gosystem.home.streams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.home.client.services.IAdministracionClientUsers;


@Component
public class RolUsuariosStream {
	
	
	@Value("${application.name}")
	private String aplicationName;
	
	@Autowired
	private IAdministracionClientUsers clientAdministracionUsers;
	
	
	public  RolesSistemaDTO findBySistemName(String name) {
		List <RolesSistemaDTO> list =  this.clientAdministracionUsers.getRolesBySystema(aplicationName);	
		RolesSistemaDTO out = list.stream()
				  .filter(customer -> name.equals(customer.getId().getNombreRol()))
				  .findAny()
				  .orElse(null);
		return out;
	}

}
