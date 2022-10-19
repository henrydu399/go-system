package com.gosystem.parametricas.api.services;

import java.util.List;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.BarrioDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.parametricas.api.entitys.BarrioPK;

public interface IBarrioService {
	
	void save( BarrioDTO departamento) throws ParametrizacionException ;
	void edith( BarrioDTO departamento) throws ParametrizacionException ;
	List<BarrioDTO> getAll( ) throws ParametrizacionException ;
	void delete( BarrioPK id ) throws ParametrizacionException ;
	void desactivate( BarrioPK id ) throws ParametrizacionException ;
	
	CiudadDTO getByid(BarrioPK id ) throws ParametrizacionException ;
	
	List<BarrioDTO> getAllByIdDepartamento(long idDepartamento) throws ParametrizacionException ;
	
	List<BarrioDTO> getAllByIdCiudad(long idDepartamento) throws ParametrizacionException ;
	
	/**
	 * 
	 * @param idDepartamento
	 * @return List<BarrioDTO>
	 * @throws ParametrizacionException
	 */
	List<BarrioDTO> getAllByCustom(long idDepartamento , long idCiudad) throws ParametrizacionException ;

}
