package jpaHelper.utils;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.ImportEnum;
import jpaHelper.enums.KeyClassEnum;



public class DtoUtil {
	
	
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
	
	public static boolean ispublicclassheader(String line) {
		//boolean containspublic = false;
		//boolean contaisclass = false;	
		if(line.contains(KeyClassEnum.PUBLIC.getValue()) && line.contains(KeyClassEnum.CLASS.getValue())) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Return String line class name
	 */
	public static String WriteLineNameClass(String classNameEntity , String prfijoClassNameDtos , boolean classImplentSerializable) {		
		return 
				KeyClassEnum.PUBLIC.getValue()+
				FileCharacters.ESPACIO.getValue() +
				KeyClassEnum.CLASS.getValue()+
				FileCharacters.ESPACIO.getValue() +
				classNameEntity +
				prfijoClassNameDtos +
				FileCharacters.ESPACIO.getValue() +
				FileCharacters.ESPACIO.getValue() +
				 (classImplentSerializable == true ?  KeyClassEnum.IMPLEMENTS.getValue() + FileCharacters.ESPACIO.getValue() + KeyClassEnum.SERIALIZABLE.getValue()   :
					 FileCharacters.ESPACIO.getValue()  )+
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
	
	

	public static String WritelinePropertie(String line , String prefijoProperties) {
		String out = "";
			if( line.contains(KeyClassEnum.TYPE_List.getValue())) {
				
				out = WriteList( line ,  prefijoProperties);
			}else {
				out = Write( line ,  prefijoProperties);
			}
		return out;
		
	}
	
	private static String WriteList(String line , String prefijoProperties) {
		String out = "";
		String typePropertieClass  = saveTypeList(line , prefijoProperties);
		out = deleteWordResevadasList(line,typePropertieClass );
		out =  out.replaceAll(FileCharacters.TABULADOR.getValue(), FileCharacters.VACIO.getValue());
		//CONVIERTE LA PRIMERA LETRA EN MENUSCULA		
		char k = out.toLowerCase().charAt(0);
		out = k + out.substring(1, out.length()) ;
		//ELIMINA PUNTO Y COMA AL FINAL
		out = out.replace(FileCharacters.PUNTOYCOMA.getValue(), FileCharacters.VACIO.getValue());
		
		out = KeyClassEnum.PRIVATE.getValue() + 
				FileCharacters.ESPACIO.getValue()+
				typePropertieClass+
				FileCharacters.ESPACIO.getValue()+
				out + prefijoProperties + FileCharacters.PUNTOYCOMA.getValue();	
		return out;
	}
	
    private static String Write(String line , String prefijoProperties) {
    	String out = "";
		String typePropertieClass  = saveType(line , prefijoProperties);
		out = deleteWordResevadas(line);
		//QUITAMOS ESPACIOS EN BLANCOS
		out =  out.replaceAll(FileCharacters.TABULADOR.getValue(), FileCharacters.VACIO.getValue());
		//CONVIERTE LA PRIMERA LETRA EN MENUSCULA		
		char k = out.toLowerCase().charAt(0);
		out = k + out.substring(1, out.length()) ;
		//ELIMINA PUNTO Y COMA AL FINAL
		out = out.replace(FileCharacters.PUNTOYCOMA.getValue(), FileCharacters.VACIO.getValue());
		
		out = KeyClassEnum.PRIVATE.getValue() + 
				FileCharacters.ESPACIO.getValue()+
				typePropertieClass+
				FileCharacters.ESPACIO.getValue()+
				out + prefijoProperties + FileCharacters.PUNTOYCOMA.getValue();	
		return out;
	}
	
	

	
	/*
	 * 
	 */
	public static boolean islinePropertieClassValid(String line) {	
		
		line =  line.replaceAll(FileCharacters.TABULADOR.getValue(), FileCharacters.VACIO.getValue());

		
		if( line.isEmpty()   ) {
			return false;
		}
		
		if( line.equals( FileCharacters.VACIO.getValue())  ) {
			return false;
		}

		if(         line.contains(KeyClassEnum.COMENTARIO_A.getValue())  
				||  line.contains(KeyClassEnum.COMENTARIO_B.getValue())  
				||  line.contains(KeyClassEnum.COMENTARIO_C.getValue())  
				||  line.contains(KeyClassEnum.COMENTARIO_D.getValue())
				) {
			return false;
		}
		
		if(         line.contains(KeyClassEnum.SIMBOLOS_UNO.getValue())  
				||  line.contains(KeyClassEnum.SIMBOLOS_DOS.getValue())  
				||  line.contains(KeyClassEnum.SIMBOLOS_TRES.getValue())  
				||  line.contains(KeyClassEnum.SIMBOLOS_CUATRO.getValue())
				) {
			return false;
		}
		
		
		
		if(     line.contains(KeyClassEnum.PACKAGE.getValue())   ||  
				line.contains(KeyClassEnum.IMPORT.getValue())  || 
				line.contains(KeyClassEnum.IMPLEMENTS.getValue()) ||
				line.contains(KeyClassEnum.SERIALIZABLE.getValue())  ||
				line.contains(KeyClassEnum.SERIALVERSIONUID.getValue()) ||
				line.contains(KeyClassEnum.CLASS.getValue()) ||
				line.contains(KeyClassEnum.THIS.getValue()) ||
				line.contains(KeyClassEnum.RETURN.getValue()) 
				
				
				) {
			return false;
		}
		
		if(     line.contains(KeyClassEnum.LOMBOK_ALLARGSCONSTRUCTOR.getValue())  || 
				line.contains(KeyClassEnum.LOMBOK_BUILDER.getValue()) ||
				line.contains(KeyClassEnum.LOMBOK_DATA.getValue()) ||
				line.contains(KeyClassEnum.LOMBOK_GETTER.getValue()) ||
				line.contains(KeyClassEnum.LOMBOK_NOARGSCONSTRUTOR.getValue()) ||
				line.contains(KeyClassEnum.LOMBOK_SETTER.getValue()) 
				
				) {
			return false;
		}
		
		if(     line.contains(KeyClassEnum.ENTITY.getValue())  || 
				line.contains(KeyClassEnum.TABLE.getValue()) ||
				line.contains(KeyClassEnum.ID.getValue()) ||
				line.contains(KeyClassEnum.NAMEDQUERY.getValue()) ||
				line.contains(KeyClassEnum.MANYTOONE.getValue()) ||
				line.contains(KeyClassEnum.JOINCOLUMNS.getValue()) ||
				line.contains(KeyClassEnum.JOINCOLUMN.getValue()) ||
				line.contains(KeyClassEnum.EMBEDDEDID.getValue()) ||
				line.contains(KeyClassEnum.EMBEDDABLE.getValue()) ||
				line.contains(KeyClassEnum.COLUMN.getValue()) ||
				line.contains(KeyClassEnum.JSON.getValue()) ||
				line.contains(KeyClassEnum.TEMPORAL.getValue()) ||
				line.contains(KeyClassEnum.ONETOMANY.getValue()) 
				
				) {
			return false;
		}
		
		if( line.contains(KeyClassEnum.GET.getValue())    ||   line.contains(KeyClassEnum.SET.getValue())  ) {
			return false;
		}
		
		
		if( line.contains(KeyClassEnum.PUBLIC.getValue())    &&   line.contains(KeyClassEnum.CLASS.getValue())  ) {
			return false;
		}
		
	
		
		return true;
	}
	
	private static String  deleteWordResevadas(String line) {
		line = line.replace( KeyClassEnum.PRIVATE.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.PUBLIC.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_boolean.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_byte.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_char.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_double.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_float.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_int.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_string.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_long.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_short.getValue() , FileCharacters.VACIO.getValue());	
		
		line =line.replace( KeyClassEnum.type_date.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.type_Timestamp.getValue() , FileCharacters.VACIO.getValue());	
		
		line =line.replace( KeyClassEnum.TYPE_Boolean.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Byte.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Char.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Double.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Float.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Integer.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Long.getValue() , FileCharacters.VACIO.getValue());
		line =line.replace( KeyClassEnum.TYPE_Short.getValue() , FileCharacters.VACIO.getValue());
		
		
		
		
		return line ;
	}
	
	
	
	private static String deleteWordResevadasList(String line , String nameClassList) {
		line = deleteWordResevadas(line);
		line = line.replace( KeyClassEnum.TYPE_List.getValue() , FileCharacters.VACIO.getValue());
		line = line.replace( FileCharacters.FLECHADERECHA.getValue() , FileCharacters.VACIO.getValue());
		line = line.replace( FileCharacters.FLECHAIZQUIERDA.getValue() , FileCharacters.VACIO.getValue());
		line = line.replace( nameClassList , FileCharacters.VACIO.getValue());
		return  line;
	}
	
	private static String saveType(String line , String prefijoProperties) {	
		if( line.contains(KeyClassEnum.TYPE_string.getValue())) {
			return KeyClassEnum.TYPE_string.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_boolean.getValue())) {
			return KeyClassEnum.TYPE_boolean.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_byte.getValue())) {
			return KeyClassEnum.TYPE_byte.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_char.getValue())) {
			return KeyClassEnum.TYPE_char.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_double.getValue())) {
			return KeyClassEnum.TYPE_double.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_float.getValue())) {
			return KeyClassEnum.TYPE_float.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_int.getValue())) {
			return KeyClassEnum.TYPE_int.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_long.getValue())) {
			return KeyClassEnum.TYPE_long.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_short.getValue())) {
			return KeyClassEnum.TYPE_short.getValue();
		}	
		
		if( line.contains(KeyClassEnum.TYPE_Boolean.getValue())) {
			return KeyClassEnum.TYPE_Boolean.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Byte.getValue())) {
			return KeyClassEnum.TYPE_Byte.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Char.getValue())) {
			return KeyClassEnum.TYPE_Char.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Double.getValue())) {
			return KeyClassEnum.TYPE_Double.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Float.getValue())) {
			return KeyClassEnum.TYPE_Float.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Integer.getValue())) {
			return KeyClassEnum.TYPE_Integer.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Long.getValue())) {
			return KeyClassEnum.TYPE_Long.getValue();
		}
		if( line.contains(KeyClassEnum.TYPE_Short.getValue())) {
			return KeyClassEnum.TYPE_Short.getValue();
		}	
		
		if( line.contains(KeyClassEnum.type_date.getValue())) {
			return KeyClassEnum.type_date.getValue();
		}	
		
		if( line.contains(KeyClassEnum.type_Timestamp.getValue())) {
			return KeyClassEnum.type_Timestamp.getValue();
		}	
		
		
		
		return getTypeCompuesto(line , prefijoProperties);	
	}
	
	private static String saveTypeList(String line , String prefijoProperties) {	
		if( line.contains(KeyClassEnum.TYPE_string.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_string.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_boolean.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_boolean.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_byte.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_byte.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_char.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_char.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_double.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_double.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_float.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_float.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_int.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_int.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_long.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_long.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_short.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_short.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}		
		
		if( line.contains(KeyClassEnum.TYPE_Boolean.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Boolean.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Byte.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Byte.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Char.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Char.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Double.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Double.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Float.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Float.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Integer.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Integer.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Long.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Long.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}
		if( line.contains(KeyClassEnum.TYPE_Short.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.TYPE_Short.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}	
		
		
		
		
		if( line.contains(KeyClassEnum.type_date.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.type_date.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}	
		
		if( line.contains(KeyClassEnum.type_Timestamp.getValue())) {
			return KeyClassEnum.TYPE_List.getValue() +
					FileCharacters.FLECHAIZQUIERDA.getValue() +
					KeyClassEnum.type_Timestamp.getValue() +
					FileCharacters.FLECHADERECHA.getValue() ;
		}	
		return getTypeCompuestoList(line , prefijoProperties);	
	}
	
	private static String getTypeCompuesto(String line , String prefijoProperties) {
		String out = "";
		out = deleteWordResevadas(line);

		
		out = out.replace( FileCharacters.PUNTOYCOMA.getValue(), FileCharacters.VACIO.getValue());
		out = out.trim();
		String [] temp = out.split(FileCharacters.ESPACIO.getValue() );
		
		return temp[0] +prefijoProperties ;
	}
	
	private static String getTypeCompuestoList(String line , String prefijoProperties) {
		String out = "";
		out = deleteWordResevadas(line);
		
		out = out.replace( FileCharacters.PUNTOYCOMA.getValue(), FileCharacters.VACIO.getValue());
		
		out = out.trim();
		
		String [] temp = out.split(FileCharacters.ESPACIO.getValue() );
		
		
		out = temp[0].replace(FileCharacters.FLECHADERECHA.getValue(), FileCharacters.VACIO.getValue());
				
		out = out + prefijoProperties + FileCharacters.FLECHADERECHA.getValue();
		
		return out ;

	}

	
	
	public static void main(String[] args) {
		
		
		
		System.out.println( FileCharacters.PUNTO()   );

	}
	
}
