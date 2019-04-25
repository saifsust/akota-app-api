package com.hungry.services;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecurityMaster {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(SecurityMaster.class);

	private Random rand = new Random();
	private final int MAX_RANGE = 999999999;
	private final int MIN_RANGE = 100000000;
	private static final int KEY_LENGTH = 16;
	private static final int PLAINTEXT_LEN = 100;

	private String plaintext_generator() {
		String plaintext = "";
		for (int i = 0; i <= PLAINTEXT_LEN; ++i)
			plaintext += randomChar();

		return plaintext;
	}

	private char randomChar() {
		return (char) (64 + rand.nextInt(26));
	}

	private String randomString(int length) {
		String temp = "";
		for (int i = 1; i <= length; ++i)
			temp += randomChar();
		return temp;
	}

	private String key_generator() {
		return randomString(4) + (MIN_RANGE + rand.nextInt(MAX_RANGE - MIN_RANGE)) + randomString(3);

	}

	private Key screct_key_generator(String key) {
		return new SecretKeySpec(key.getBytes(), "AES");
	}

	public String token() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		String key = key_generator();
		Key aesKey = screct_key_generator(key);
		Cipher cipher;
		cipher = Cipher.getInstance("AES");

		String plainText = plaintext_generator();

		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(plainText.getBytes());
		String token = Base64.getEncoder().encodeToString(encrypted);// new String(encrypted);

		return screct_key_adder(token, key);
	}

	public String decript_token(String cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		Map<String, String> data = saparator(cipherText);
		Key aesKey = screct_key_generator(data.get("KEY"));
		Cipher cipher;
		cipher = Cipher.getInstance("AES");
		String CipherText = data.get("CIPHER");
		byte[] token = Base64.getDecoder().decode(CipherText);
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(token));
		return decrypted;
	}

	private Map<String, String> saparator(String cipherText) {
		int mid = KEY_LENGTH >> 1;
		String start = "";
		String end = "";
		String plainText = "";

		LOG.debug("recieve in  saparator cipher text :  " + cipherText);
		for (int i = 0; i <= mid; ++i)
			start += cipherText.charAt(i);

		int due = KEY_LENGTH - mid - 1;

		for (int i = mid + 1; i < cipherText.length() - due; ++i)
			plainText += cipherText.charAt(i);

		for (int i = cipherText.length() - 1, j = 0; j < KEY_LENGTH - mid - 1; --i, ++j)
			end += cipherText.charAt(i);

		LOG.debug("return : plainText : " + plainText + " secrect key : " + (start + end));

		Map<String, String> map = new HashMap<String, String>();
		map.put("KEY", start + end);
		map.put("CIPHER", plainText);
		return map;
	}

	private String screct_key_adder(String chiperText, String screctKey) {

		LOG.debug(screctKey);

		int mid = KEY_LENGTH >> 1;
		String text = "";
		for (int i = 0; i <= mid; ++i) {
			text += screctKey.charAt(i);
		}
		++mid;
		chiperText = text + chiperText;
		for (int i = screctKey.length() - 1; i >= mid; --i) {
			chiperText += screctKey.charAt(i);
		}
		return chiperText;
	}

}
