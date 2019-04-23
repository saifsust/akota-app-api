package com.hungry.authentication;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AccessTokenGenerator {
	
	private static Random rand = new Random();
	
	
	public static String accessToken() {
		return Integer.toString(rand.nextInt(100000000));
	}

	private void cryption() throws Exception{
		String text = "Hello World SAiful ";
        String key = "Bar12345Bar12345"; // 128 bit key
        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher;
		
		cipher = Cipher.getInstance("AES");
		
        // encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        System.err.println(new String(encrypted));
        // decrypt the text
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));
        System.err.println(decrypted);
	}
	
}
