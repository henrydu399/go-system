package jpaHelper.logic;

import java.io.File;
import java.util.List;
import java.util.Properties;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.PropertiesEnum;
import jpaHelper.exceptions.JpaHelperException;
import jpaHelper.services.FileService;
import jpaHelper.utils.BuilderUtil;

public class BuilderLogic {

	private Properties properties;

	public BuilderLogic(Properties properties) {
		super();
		this.properties = properties;
	}

	public void run() throws JpaHelperException {

		File filesEntitys = new File(properties.getProperty(PropertiesEnum.pathJavaOutDtos.name()));
		List<String> listNames = FileService.findAllFilesInFolder(filesEntitys);

		for (String fileNameTemp : listNames) {
			this.createBuilder(fileNameTemp);
		}

	}

	private void  createBuilder(String nameFile) {
	
		try {
		//CREAMOS EL NOMBRE DE LA CLASE A CREAR
		String nameclass = "";
		///LE QUITAMOS EL .JAVA
		 nameclass = nameFile.replace(FileCharacters.EXTENSION_JAVA.getValue(), FileCharacters.VACIO.getValue());
		///LE QUITAMOS EL PREFIJO'DTO'
		 nameclass= nameFile.replace(properties.getProperty(PropertiesEnum.prfijoPropertieClassNameDtos.name()), FileCharacters.VACIO.getValue());
		///////////////////////////////////
		
		//ABRIMOS EL ARCHIVO DTO 
		File fileTemp = new File(properties.getProperty(PropertiesEnum.pathJavaEntitys.name()) + FileCharacters.CHARACTER_BARRA_INVERTIDA.getValue() + nameFile);
		/// LO SEPARAMOS EN LINEAS 
		List<String> lines =  FileService.readFile(fileTemp);
		
		String tempOutDTO = "";
		
		////CREACION LINEA PAQUETE
		tempOutDTO = tempOutDTO +    BuilderUtil.getPakectName(properties.getProperty( PropertiesEnum.linepaqueteBuilder.name()) )    + FileCharacters.SALTO_LINEA.getValue();
		tempOutDTO = tempOutDTO +    FileCharacters.SALTO_LINEA.getValue();
		
		////IMPORTS
		tempOutDTO = tempOutDTO +    BuilderUtil.gerImport()    + FileCharacters.SALTO_LINEA.getValue();
		tempOutDTO = tempOutDTO +    FileCharacters.SALTO_LINEA.getValue();
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
