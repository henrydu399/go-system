package co.system.out.filemanager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    
    public static String convertBse64(String in) {
    	return Base64.getEncoder().encodeToString(in.getBytes());
    	
    }
    
    public static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
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
    

}
