package com.gosystem.commons.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESGCMHelper {

	 private static final int GCM_IV_LENGTH = 12;
	    private static final int GCM_TAG_LENGTH = 16;

	    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

	    public static SecretKeySpec getSecretKey(String myKey) throws NoSuchAlgorithmException {
	        MessageDigest sha;
	        byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
	        sha = MessageDigest.getInstance("SHA-1");
	        key = sha.digest(key);
	        key = Arrays.copyOf(key, GCM_TAG_LENGTH);
	        return new SecretKeySpec(key, "AES");
	    }

	    public static String encrypt(String privateString, String key) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
	        SecretKeySpec sKey = getSecretKey(key);
	        byte[] iv = new byte[GCM_IV_LENGTH];
	        (new SecureRandom()).nextBytes(iv);

	        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
	        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
	        cipher.init(Cipher.ENCRYPT_MODE, sKey, ivSpec);

	        byte[] cipherText = cipher.doFinal(privateString.getBytes(StandardCharsets.UTF_8));
	        byte[] encrypted = new byte[iv.length + cipherText.length];
	        System.arraycopy(iv, 0, encrypted, 0, iv.length);
	        System.arraycopy(cipherText, 0, encrypted, iv.length, cipherText.length);

	        return Base64.getEncoder().encodeToString(encrypted);
	    }

	    public static String decrypt(String encrypted, String key) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
	        SecretKeySpec sKey = getSecretKey(key);

	        byte[] decoded = Base64.getDecoder().decode(encrypted);
	        byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

	        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
	        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
	        cipher.init(Cipher.DECRYPT_MODE, sKey, ivSpec);

	        byte[] cipherText = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);
	        return new String(cipherText, StandardCharsets.UTF_8);
	    }
	
}
