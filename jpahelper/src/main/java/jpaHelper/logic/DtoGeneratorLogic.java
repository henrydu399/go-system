package jpaHelper.logic;

import java.io.File;
import java.util.List;
import java.util.Properties;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.KeyClassEnum;
import jpaHelper.enums.PropertiesEnum;
import jpaHelper.exceptions.JpaHelperException;
import jpaHelper.exceptions.enums.ErrorEnum;
import jpaHelper.services.FileService;
import jpaHelper.utils.DtoUtil;
import jpaHelper.utils.DtoUtilv2;
import jpaHelper.utils.PropertiesUtil;

public class DtoGeneratorLogic {
	
	
	private Properties properties;


	public DtoGeneratorLogic(Properties properties) {
		super();
		this.properties = properties;
	}


	public void run() throws JpaHelperException {
			
		File filesEntitys = new File( properties.getProperty(PropertiesEnum.pathJavaEntitys.name()) );
		List<String> listNames =  FileService.findAllFilesInFolder(filesEntitys  );
		
		for( String fileNameTemp : listNames ) {
			this.createDto(fileNameTemp);
		}
		
	}
	
	
	public void createDto(String nameFile ) throws JpaHelperException {
		
		try {
			System.out.println("CREANDO DTO.... +"+nameFile);
			
			String nameclass= nameFile.replace(FileCharacters.EXTENSION_JAVA.getValue(), FileCharacters.VACIO.getValue());
			
			File fileTemp = new File(properties.getProperty(PropertiesEnum.pathJavaEntitys.name()) + FileCharacters.CHARACTER_BARRA_INVERTIDA.getValue() + nameFile);
			List<String> lines =  FileService.readFile(fileTemp);
			
			String tempOutDTO = "";
			
			
			DtoUtilv2 dtoUtilv2 = new DtoUtilv2(lines);
			
			//1.CREACION LINEA PAQUETE
			System.out.println("CREANDO LINE PAQUETE .... +"+nameFile);
			tempOutDTO = tempOutDTO +    dtoUtilv2.getPakectName(properties.getProperty( PropertiesEnum.linepaqueteDtos.name()) )    + FileCharacters.SALTO_LINEA.getValue();
			tempOutDTO = tempOutDTO   + FileCharacters.SALTO_LINEA.getValue();
			
			// IMPORTS
			System.out.println("CREANDO LINES IMPORTS .... +"+nameFile);
			tempOutDTO = tempOutDTO +    dtoUtilv2.gerImport()    + FileCharacters.SALTO_LINEA.getValue();
			tempOutDTO = tempOutDTO   + FileCharacters.SALTO_LINEA.getValue();
			
			//1. CREACION DE LOOBOOK
			System.out.println("CREANDO LINES LOMBOOK .... +"+nameFile);
			tempOutDTO = tempOutDTO + dtoUtilv2.Writelombok() + FileCharacters.SALTO_LINEA.getValue();
			
			//3.CREACION DE CLASS NAME
			System.out.println("CREANDO LINES CLASS NAME .... +"+nameFile);
			tempOutDTO = tempOutDTO +  
					dtoUtilv2.WriteLineNameClass(nameclass, properties.getProperty( PropertiesEnum.prfijoClassNameDtos.name()) ,PropertiesUtil.isboolean(PropertiesEnum.classImplentSerializableDtos.name())  )  + 
							FileCharacters.SALTO_LINEA.getValue();
			tempOutDTO = tempOutDTO   + FileCharacters.SALTO_LINEA.getValue();
		
			// CREACION DE PROPIEDADES
			System.out.println("CREANDO LINES LOMBOOK .... +"+nameFile);
			tempOutDTO = tempOutDTO + dtoUtilv2.WritelinePropertie(nameclass, properties.getProperty( PropertiesEnum.prfijoPropertieClassNameDtos.name() ) );
			

			
			tempOutDTO = tempOutDTO   + FileCharacters.SALTO_LINEA.getValue();
			
			tempOutDTO = tempOutDTO   + KeyClassEnum.SIMBOLOS_TRES.getValue();
			
			String nameFileFull  = properties.getProperty( PropertiesEnum.pathJavaOutDtos.name() ) + 
					FileCharacters.CHARACTER_BARRA_INVERTIDA.getValue()+
					nameclass +
					properties.getProperty( PropertiesEnum.prfijoPropertieClassNameDtos.name() )+
					FileCharacters.EXTENSION_JAVA.getValue()
					;
					
			File fileSave =  new File( nameFileFull   );
			FileService.save(fileSave, tempOutDTO);
			
			
		} catch (JpaHelperException e) {	
			e.printStackTrace();
			throw  new JpaHelperException(e.getMessage(), ErrorEnum.ERROR_CREANDO_DTO);		
		}
		
		
		
	}
	
	
	
	

}
