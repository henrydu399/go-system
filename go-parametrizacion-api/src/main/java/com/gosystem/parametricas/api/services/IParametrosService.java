package com.gosystem.parametricas.api.services;

import java.util.List;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.parametricas.api.entitys.Parametro;



public interface IParametrosService {
	
	Parametro getParametro(String key) throws ParametrizacionException;
	
	List<Parametro> getAll () throws ParametrizacionException;
	

}
