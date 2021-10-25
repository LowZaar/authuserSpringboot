package com.lorenzozaar.authuser.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;


public class md5Factory {
	
	public static String mdfy(String text) {
		try {
			byte[] digestArray = text.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(digestArray);
			String md5hash = DatatypeConverter.printHexBinary(digest);			
			return md5hash;
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
