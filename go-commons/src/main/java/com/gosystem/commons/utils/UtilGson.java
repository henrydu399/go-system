package com.gosystem.commons.utils;

import java.util.Objects;

import com.google.gson.Gson;

public class UtilGson {
	
	private static  Gson gson ;
	
	public static String SerializeObjet (Object obj) {
		gson = new Gson();
		return  gson.toJson(obj);
		
	}
	
	public static Gson getGson() {
		if( Objects.isNull(gson)) {
			return new Gson();
		}else {
			return gson;
		}
	}
	
	

}
