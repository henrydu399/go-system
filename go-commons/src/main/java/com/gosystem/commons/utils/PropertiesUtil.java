package com.gosystem.commons.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static final String PATH = "path/to/config.properties";
	private static final String KEY = "url.parametrizacion";

	public static String getUrlParametrica() {
		
		try (InputStream input = new FileInputStream(PATH)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            return prop.getProperty(KEY );

      

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
}
