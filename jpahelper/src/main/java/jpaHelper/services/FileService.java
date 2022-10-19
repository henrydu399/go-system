package jpaHelper.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jpaHelper.exceptions.JpaHelperException;
import jpaHelper.exceptions.enums.ErrorEnum;

public class FileService {
	
	
	public static List<String> findAllFilesInFolder(File folder) {	
		List<String> listNames =  new ArrayList<String>();
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				listNames.add( file.getName()  );
				//System.out.println(file.getName());
			} else {
				findAllFilesInFolder(file);
			}
		}
		return listNames;
	}
	
	
	
	public static List<String>  readFile(File file) throws JpaHelperException 
    {  
        Scanner obj;
        List<String> listLine =  new ArrayList<String>();
		try {
			obj = new Scanner(file);
	        while (obj.hasNextLine()) {
	        	
	        	listLine.add(obj.nextLine());

	        }	      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw  new JpaHelperException(e.getMessage(), ErrorEnum.ERROR_LEYENDO_ARCHIVO);		
		}
		return listLine;
		
    }
	
	
    public static void save(File file, String contenido) throws JpaHelperException{
        try {
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new JpaHelperException(e.getMessage(), ErrorEnum.ERROR_GUARDANDO_ARCHIVO);		
        }
    }


	

}
