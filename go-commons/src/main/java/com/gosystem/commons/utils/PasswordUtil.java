package com.gosystem.commons.utils;

import java.util.logging.Logger;

import com.gosystem.commons.constants.GeneralContstant;

public class PasswordUtil {

	public static String encriptar(String tipoEncriptado, String llaveMaestra, String texto) {
		
		Logger logger = UtilsLogs.getLogger(PasswordUtil.class.getName());
		logger.info("Utils :: encriptar :: INICIO :: INFO");
		
		String resultado = null;
		try {

			switch (tipoEncriptado) {
			case GeneralContstant.ENCRYPT:
				resultado = AESGCMHelper.encrypt(texto, llaveMaestra);
				break;
			case GeneralContstant.DECRYPT:
				resultado = AESGCMHelper.decrypt(texto, llaveMaestra);
				break;
			default:
				resultado = "Tipo de encriptado desconocido";
				break;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Utils :: encriptar :: FIN :: INFO");
		return resultado;
	}
	
	
	public static String strHashSeguridad(String texto) {
		int code = Math.abs(texto.hashCode());
		return String.valueOf(code);
	}
	
	
	
}
