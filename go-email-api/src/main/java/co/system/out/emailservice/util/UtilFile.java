package co.system.out.emailservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class UtilFile {
	
	
	   /**
     * Method to <b>copy</b> a file from a source origin (<code>fromFile</code>)
     * to a destination(<code>toFile</code>). M�todo para <b>copiar</b> un
     * fichero desde un origen (<code>fromFile</code>) a un destino
     * (<code>toFile</code>).
     *
     * @param fromFile <code>String</code> source file path (ruta del fichero
     * origen).
     * @param toFile <code>String</code> destination file path (ruta del fichero
     * destino).
     * @return <code>boolean</code> It returns true if they could copy the file
     * false on error (devuelve true si se pude copiar el fichero false en caso
     * de error).
     */
    public static boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
    
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    
    public static  String getStringImage(File file){
        try {
            FileInputStream fin = new FileInputStream(file);
            byte[] imageBytes = new byte[(int)file.length()];
            fin.read(imageBytes, 0, imageBytes.length);
            fin.close();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception ex) {
           
           ex.printStackTrace();
        }
        return null;
    }
    
    
    public static String convertBse64(String in) {
    	return Base64.getEncoder().encodeToString(in.getBytes());
    	
    }
    
    public static String getDecode(String in) {
    	String out = ""; 	
    	try {
			out = new String(Base64.getDecoder().decode(in), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return out;
    	
    }
    

}
