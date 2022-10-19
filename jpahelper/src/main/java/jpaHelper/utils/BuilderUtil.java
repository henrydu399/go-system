package jpaHelper.utils;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.ImportEnum;
import jpaHelper.enums.KeyClassEnum;

public class BuilderUtil {
	
	
	public static String getPakectName(String pakectName) {
		return KeyClassEnum.PACKAGE.getValue() +   FileCharacters.ESPACIO.getValue()  + pakectName + FileCharacters.PUNTOYCOMA.getValue() ;
	}
	
	public static String gerImport() {
		String out = "";
		out = out + ImportEnum.LOMBOK_ALLARGSCONSTRUCTOR.getValue() + FileCharacters.SALTO_LINEA.getValue();
		out = out + ImportEnum.LOMBOK_BUILDER.getValue() + FileCharacters.SALTO_LINEA.getValue();
		out = out + ImportEnum.LOMBOK_DATA.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out + ImportEnum.LOMBOK_NOARGSCONSTRUTOR.getValue()   + FileCharacters.SALTO_LINEA.getValue(); 
		out = out + ImportEnum.JAVA_UTIL_LIST.getValue()   + FileCharacters.SALTO_LINEA.getValue();		
		out = out + ImportEnum.JAVA_UTIL_DATE.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out + ImportEnum.JAVA_SQL_TIMESTAMP.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out + ImportEnum.JAVA_IO__SERIALIZABLE.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		return out;
	}

}
