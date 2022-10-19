package com.gosystem.home.client.services;

import java.util.List;

import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.parametrizacion.dto.BarrioDTO;
import com.gosystem.commons.parametrizacion.dto.CiudadDTO;
import com.gosystem.commons.parametrizacion.dto.DepartamentoDTO;

public interface IUbicacionClienteService {
	
	List<DepartamentoDTO> getAllDepartamentos() throws HomeException;
	List<CiudadDTO>  getAllCiudades()throws HomeException;
	List<BarrioDTO>  getAllBarrios()throws HomeException;
	
	
	BarrioDTO findBarrioByName(String barrioName)throws HomeException;
	List<CiudadDTO>  getAllCiudadesByIdDepartamento(long idDepartamento)throws HomeException;
	List<CiudadDTO>  getAllCiudadesByNameDepartamento(String nameDepartamento)throws HomeException;
	List<BarrioDTO>  getAllBarriosByIdCiudad(long idCiudad)throws HomeException;
	
	
	
	

}
