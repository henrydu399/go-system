package jpaHelper.modules;

import java.io.File;
import java.util.Properties;

import jpaHelper.enums.PropertiesEnum;
import jpaHelper.exceptions.JpaHelperException;
import jpaHelper.logic.DtoGeneratorLogic;
import jpaHelper.services.FileService;
import jpaHelper.utils.PropertiesUtil;

public class Principal {
	
	
	private Properties properties;
	
	public void run() {
		try {
			this.properties =   PropertiesUtil.get();
			

			DtoGeneratorLogic dtoLogic =  new DtoGeneratorLogic(this.properties);
			dtoLogic.run();
			
			
		}catch (JpaHelperException e) {
			System.out.println(e.getMsj().toString());
		}
		
		
	}
	
	
	private void generateDtos() {
		
	}

}
