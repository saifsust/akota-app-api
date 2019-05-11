package com.hungry.services.util;

import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CryptoMaster {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CryptoMaster.class);

	private Random rand = new Random();
	private final int MAX_RANGE = 999999999;
	private final int MIN_RANGE = 100000000;
	private static final int KEY_LENGTH = 16;
	private static final int PLAINTEXT_LEN = 200;

	private char randomChar() {
		return (char) (64 + rand.nextInt(26));
	}

	protected String randomString(int length) {
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

	public String encrypt(String plainText) {
		try {

			String key = key_generator();
			Key aesKey = screct_key_generator(key);
			Cipher cipher;
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(plainText.getBytes());
			String encrypt = Base64.getEncoder().encodeToString(encrypted);
			return screct_key_adder(encrypt, key);
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("encrypt : " + e.getMessage());
			LOG.error("encrypt : " + e.getCause().toString());
			LOG.error("encrypt : " + e.getLocalizedMessage());
		}

		return null;
	}

	public String decrypt(String cipherText) {

		try {
			LOG.info("status : token -> " + cipherText);
			Map<String, String> map = saparator(cipherText);
			Key aesKey = screct_key_generator(map.get("KEY"));
			Cipher cipher;
			cipher = Cipher.getInstance("AES");
			String CipherText = map.get("CIPHER");
			byte[] byteToken = Base64.getDecoder().decode(CipherText);
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(byteToken));
			LOG.info("status : decrypt  -> " + decrypted);
			return decrypted;
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("decrypt : " + e.getMessage());
			LOG.error("decrypt : " + e.getCause().toString());
			LOG.error("decrypt : " + e.getLocalizedMessage());
		}

		return null;
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
