package com.hungry.services.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityMaster {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(SecurityMaster.class);
	@Autowired
	private StatusMaster statusMaster;
	@Autowired
	private StringProessor processor;
	@Autowired
	private CryptoMaster cryptoMaster;

	private static final int PLAINTEXT_LEN = 200;

	private String plaintext_generator() {
		return cryptoMaster.randomString(PLAINTEXT_LEN);
	}

	public String token(TokenStatus status, long userId, Type type) {

		try {
			String plainText = statusMaster.push(plaintext_generator(), status);
			plainText = processor.push_user_identification(plainText, userId);
			plainText = processor.push_user_type(plainText, type);
			LOG.info("token : status -> " + status + " | plainText -> " + plainText);
			return cryptoMaster.encrypt(plainText);

		} catch (Exception e) {
			LOG.error("token");
			return null;
		}

	}
	
	/**
	 * 
	 * @param token recieve user token
	 * @return user_id,user_type,token_status decrypt as token info
	 */

	public Map<String, Object> decrypt(String token){

		try {

			LOG.info("status : token -> " + token);
			String decrypted = cryptoMaster.decrypt(token);
			LOG.info("status : decrypt  -> " + decrypted);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("token_status", statusMaster.seek(decrypted));
			map.put("user_id", processor.seek_user_id(decrypted));
			map.put("user_type", processor.seek_user_type(decrypted));
			return map;
		} catch (Exception e) {
			LOG.error("status : " + e.getMessage());
			return null;
		}

	}
}