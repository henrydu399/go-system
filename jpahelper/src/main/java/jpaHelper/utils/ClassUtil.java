package jpaHelper.utils;

import java.util.ArrayList;
import java.util.List;

import jpaHelper.enums.FileCharacters;
import jpaHelper.enums.KeyClassEnum;
import jpaHelper.exceptions.models.ClassModel;
import jpaHelper.exceptions.models.PropertieModel;

public   class  ClassUtil {
	
	public static ClassModel getClass(List<String> fileLines) {
		
		 
		  
		 List<PropertieModel> listProperties =  new ArrayList<PropertieModel>();
		 List<String> listImplements =   new ArrayList<String>();
		 List<String> listImports =  new ArrayList<String>();
		 
		 ClassModel classModel =   ClassModel.builder()
				 .className("")
				 .listImplements(listImplements)
				 .listImports(listImports)
				 .listProperties(listProperties)
				 .build();
		
		 return filterClass(classModel , fileLines);
		
	
	}
	

	
	public static ClassModel   filterClass(ClassModel classModel, List<String> fileLines ) {

		for(String line : fileLines) {
			
			line =  line.trim();
			
			//BUSCANDO PAQUETE
			if (line.contains(KeyClassEnum.PACKAGE.getValue())) {
				classModel.getListImports().add(line);
				continue;
			}
			
			//BUSCANDO IMPORTS
			if (line.contains(KeyClassEnum.IMPORT.getValue())) {
				classModel.getListImports().add(line);
				continue;
			}
			
			if ( line.contains( KeyClassEnum.PUBLIC.getValue())) {
				/// BUSCANDO NOMBRE DE LA CLASE 
				if( line.contains( KeyClassEnum.CLASS.getValue())) {
					line = line.replace(KeyClassEnum.PUBLIC.getValue(),FileCharacters.VACIO.getValue());
					line = line.replace(KeyClassEnum.ABSTRACT.getValue(),FileCharacters.VACIO.getValue());
					line = line.replace(KeyClassEnum.CLASS.getValue(),FileCharacters.VACIO.getValue());
					line = line.trim();
					String [] words = line.split(FileCharacters.ESPACIO.getValue() );
					if( words != null && words.length > 0){
						classModel.setClassName(words[0]);
						continue;
					}
					
				}
			}
			
			//DESCARTANDO LINEAS 
			if( islinePropertieClassValid(line) == false) {
				continue;
			}
			//BUSCANDO PROPIEDADES
			
			if ( line.contains( KeyClassEnum.PUBLIC.getValue()) ||   line.contains( KeyClassEnum.PRIVATE.getValue())  ) {
				String [] properties = line.split(FileCharacters.ESPACIO.getValue() );
				if( properties.length == 3) {
					
					System.out.println("SE ENCONTRO PROPIEDAD  ....  "+properties[2]);
					
					PropertieModel  propertie = PropertieModel.builder()
							.acceso(properties[0])
							.tipo( properties[1])
							.nombre(properties[2])
							.build();
					classModel.getListProperties().add(propertie);
					
				}
				continue;
			}
			
			
			
			
			
			
			
		}
		
		return classModel;
	}
	

	

	
	
	/*
	 * Metodo limpia la clase de lineas inecesarias 
	 */
	public static boolean islinePropertieClassValid(String line) {	
		
		line =  line.replaceAll(FileCharacters.TABULADOR.getValue(), FileCharacters.VACIO.getValue());
	
		if( line.isEmpty() ||  line.equals( FileCharacters.VACIO.getValue())   ) {
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
			
		//LIMIA ATRIBUTOS BASE CLASE INECESARIOS
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
		// LIMPIA ATRIBUTOS DE TIPO LOMBOK
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
	
	

}
