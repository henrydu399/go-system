package jpaHelper.utils;

import java.util.List;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.ImportEnum;
import jpaHelper.enums.KeyClassEnum;
import jpaHelper.exceptions.models.ClassModel;
import jpaHelper.exceptions.models.PropertieModel;



public class DtoUtilv2 {
	
	private ClassModel classModel;
	
	
	
	
	public DtoUtilv2(List<String>  lines) {
		super();
		this.classModel = ClassUtil.getClass(lines);
	}

	public  String getPakectName(String pakectName) {
		return KeyClassEnum.PACKAGE.getValue() +   FileCharacters.ESPACIO.getValue()  + pakectName + FileCharacters.PUNTOYCOMA.getValue() ;
	}
	
	public  String gerImport() {
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

	
	
	/*
	 * Return String line class name
	 */
	public  String WriteLineNameClass(String classNameEntity , String prfijoClassNameDtos , boolean classImplentSerializable) {	
		if( classImplentSerializable) 
			return  KeyClassEnum.PUBLIC.getValue() +
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.CLASS.getValue()+ 
					FileCharacters.ESPACIO.getValue()+
					this.classModel.getClassName()+ 
					prfijoClassNameDtos+
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.SIMBOLOS_CUATRO.getValue()
					;
		else 

			return  KeyClassEnum.PUBLIC.getValue() +
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.CLASS.getValue()+ 
					FileCharacters.ESPACIO.getValue()+
					this.classModel.getClassName()+ 
					prfijoClassNameDtos+
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.IMPLEMENTS.getValue()+
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.SERIALIZABLE.getValue()+
					FileCharacters.ESPACIO.getValue()+
					KeyClassEnum.SIMBOLOS_CUATRO.getValue()
					;
	}
	
	
	
	public static String Writelombok() {
		String out = "";
		out = out +  KeyClassEnum.LOMBOK_DATA.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out +  KeyClassEnum.LOMBOK_BUILDER.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out +  KeyClassEnum.LOMBOK_ALLARGSCONSTRUCTOR.getValue()   + FileCharacters.SALTO_LINEA.getValue();
		out = out +  KeyClassEnum.LOMBOK_NOARGSCONSTRUTOR.getValue()   ;
		return out;
	}
	
	

	public  String WritelinePropertie(String line , String prefijoProperties) {
		String out = "";
			
		for ( PropertieModel p : this.classModel.getListProperties()) {
			out = out + p.getAcceso() + 
					FileCharacters.ESPACIO.getValue() +
					writeType(p.getTipo(), prefijoProperties)+
					FileCharacters.ESPACIO.getValue() +
					p.getNombre()+
					FileCharacters.SALTO_LINEA.getValue()
					;
					
					
		}
		return out;
		
	}
	
	private String writeType(String type , String prefijoProperties ) {
		type = type.trim();
		if( type.contains(  KeyClassEnum.TYPE_List.getValue() )) {
			type = type.replace(KeyClassEnum.TYPE_List.getValue(),FileCharacters.VACIO.getValue());
			type = type.replace(FileCharacters.FLECHADERECHA.getValue(),FileCharacters.VACIO.getValue());
			type = type.replace(FileCharacters.FLECHAIZQUIERDA.getValue(),FileCharacters.VACIO.getValue());
			type = type + prefijoProperties;
			
			return KeyClassEnum.TYPE_List.getValue()+
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					type +
					FileCharacters.FLECHADERECHA.getValue()
					
					;
		}else {
			return type +prefijoProperties;
		}
		


		
	}
	
	
	
   
	
	
	
	

	
}
