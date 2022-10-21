package co.system.out.emailservice.client.services;

import com.gosystem.commons.exceptions.ParametrizacionException;
import com.gosystem.commons.parametrizacion.dto.ParametroDTO;

public interface IParametrizacionClientService {
	
	String getParametro(String key) throws ParametrizacionException;
	ParametroDTO getParametroDirect(String key) throws ParametrizacionException;
	

}
