package com.gosystem.parametricas.api.services;

import java.util.List;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;

public interface IDepartamentoService {
	
	void save( DepartamentoDTO departamento) throws ParametrizacionException ;
	void edith( DepartamentoDTO departamento) throws ParametrizacionException ;
	List<DepartamentoDTO> getAll( ) throws ParametrizacionException ;
	void delete( long id ) throws ParametrizacionException ;
	void desactivate( long id ) throws ParametrizacionException ;
	
	DepartamentoDTO getByid(long id ) throws ParametrizacionException ;
	
	

}
