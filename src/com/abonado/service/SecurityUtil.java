package com.abonado.service;

import org.jasypt.util.text.BasicTextEncryptor;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
	
	private final static BasicTextEncryptor pb = new BasicTextEncryptor();
	static {
		pb.setPassword("asdfasdfaer453453sfs452");
	}
	
	public static String generateToken(String userName, String password){
		String enStr = pb.encrypt(userName +"--"+password+"--"+System.currentTimeMillis());
		String token = Base64.encodeBase64URLSafeString(enStr.getBytes(StandardCharsets.UTF_8));
		System.out.println("Encrypted Str::::"+token);
		return token;
	}
	
	public static String decrypt(String str){
		byte[] dec = Base64.decodeBase64(str);
		String decrptedStr = pb.decrypt(new String(dec, StandardCharsets.UTF_8));
		System.out.println("Decrypted Str::::"+decrptedStr);
		return decrptedStr;
	}
	
	public static void main(String args[]){
		String token = generateToken("ramavenna","password123");
		decrypt(token);
	}

}
