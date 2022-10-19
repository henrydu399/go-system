package com.gosystem.parametricas.api.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gosystem.parametricas.api.entitys.Departamento;

public interface IDepartamentoRepository  extends JpaRepository<Departamento, Long>{
	
	Optional<Departamento> findByNombre(String nombre);

}
