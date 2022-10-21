package co.system.out.emailservice.services;



import com.gosystem.commons.exceptions.EmailException;

import co.system.out.emailservice.model.User;

public interface IUserService {
	
	void createUSer ( User u) throws EmailException;

}
