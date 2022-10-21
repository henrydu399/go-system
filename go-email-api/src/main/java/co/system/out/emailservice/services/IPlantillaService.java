package co.system.out.emailservice.services;


import com.gosystem.commons.exceptions.EmailException;

import co.system.out.emailservice.model.Plantilla;

public interface IPlantillaService {
	
	void save(Plantilla p)throws EmailException;

}
