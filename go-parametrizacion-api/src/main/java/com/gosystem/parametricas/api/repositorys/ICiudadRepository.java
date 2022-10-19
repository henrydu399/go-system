package com.gosystem.parametricas.api.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gosystem.parametricas.api.entitys.Ciudad;
import com.gosystem.parametricas.api.entitys.CiudadPK;
import com.gosystem.parametricas.api.entitys.Departamento;

public interface ICiudadRepository  extends JpaRepository<Ciudad, CiudadPK>{
	
	Optional<Ciudad>  findByNombre(String nombre);
	
	List<Ciudad> findByDepartamento(Departamento departamento);
	
	

}
