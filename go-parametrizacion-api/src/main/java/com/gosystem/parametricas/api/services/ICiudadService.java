package com.gosystem.parametricas.api.services;

import java.util.List;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadPKDTO;


public interface ICiudadService {
	
	void save( CiudadDTO departamento) throws ParametrizacionException ;
	void edith( CiudadDTO departamento) throws ParametrizacionException ;
	List<CiudadDTO> getAll( ) throws ParametrizacionException ;
	void delete( CiudadPKDTO id ) throws ParametrizacionException ;
	void desactivate( CiudadPKDTO id ) throws ParametrizacionException ;
	
	CiudadDTO getByid(CiudadPKDTO id ) throws ParametrizacionException ;
	
	List<CiudadDTO> getAllByIdDepartamento(long idDepartamento) throws ParametrizacionException ;

}
