package com.gosystem.commons.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {

	public static String encode(String value) {
		String basicEncoded = null;
		try {
			 basicEncoded = Base64.getEncoder().encodeToString(value.getBytes("utf-8"));	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return basicEncoded;	
		//String encodedString = Base64.getEncoder().encodeToString(value.getBytes());	
	}
	
	public static String decode ( String base64) {
		String decodedString = null;
		byte[] decodedBytes;
		try {
			decodedBytes = Base64.getDecoder().decode(base64.getBytes("utf-8"));
			 decodedString = new String(decodedBytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return decodedString;
	}
	
	
	
	
	
}
