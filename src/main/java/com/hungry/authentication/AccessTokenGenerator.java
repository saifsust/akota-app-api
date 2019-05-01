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
		
	}
	
}
