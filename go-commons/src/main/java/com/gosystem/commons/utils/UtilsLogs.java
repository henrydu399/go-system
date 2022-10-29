package com.gosystem.commons.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;





public class UtilsLogs {

	private static String pattern = "######.###";
	private static DecimalFormat decimalFormatBD = new DecimalFormat(pattern);
	private static SimpleDateFormat formatHoraSql = new SimpleDateFormat("HH:mm:ss");

	public static Logger getLogger(String className) {
		Logger logger = Logger.getLogger(className);
		logger.setLevel(Level.FINE);
		return logger;
	}
	
	@Deprecated
	public static String getInfo(Enum<?> metodo , Enum<?> entity ,  Object obj ) {
		String out = "";
		String metodoName = "";
		String entityName = "";
		
		if( Objects.nonNull( metodo)) {
			metodoName = metodo.name();
		}
		
		if( Objects.nonNull( entity)) {
			entityName = entity.name();
		}
		
		try {
			out = " :: METHODO :: "+ metodoName +" ::ENTIDAD:: "+ entityName +" ::INPUT:: "+ UtilGson.SerializeObjet( obj);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return out;
	}
	
	public static String getInfo(Enum<?> metodo , Enum<?> entity ,  Object obj , String _menssage) {
		String out = "";
		String metodoName = "";
		String entityName = "";
		String menssage = "";
		
		if( Objects.nonNull( metodo)) {
			metodoName = metodo.name();
		}
		if( Objects.nonNull( entity)) {
			entityName = entity.name();
		}
		if( Objects.nonNull(_menssage)) {
			menssage = _menssage;
		}
		
		
		try {
			out = " :: METHODO :: "+ metodoName +   " :: MENSSAGE ::"+ menssage+ "   ::ENTIDAD:: "+ entityName +" ::INPUT:: "+ UtilGson.SerializeObjet( obj);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return out;
	}


}
