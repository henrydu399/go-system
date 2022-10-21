package co.system.out.emailservice.services;

import com.gosystem.commons.emailapi.dto.EmailDto;

public interface ISendBlueService {

	  void  sendEmail(EmailDto emp) throws Exception ;
	
}
