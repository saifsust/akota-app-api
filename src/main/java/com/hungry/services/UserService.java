package com.hungry.services;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Tuple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.util.TokenStatus;
import com.hungry.util.Type;

@Service("hungryUserService")
public class UserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserService.class);

	/**
	 * Autowired properties
	 */
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SecurityMaster securityMaster;
	@Autowired
	private CryptoMaster cryptoMaster;
	@Autowired
	public DbManagerService service;

	private static final int MAX_EXPIRES = 60;

	private static final int MIN_EXPIRES = 60;
	private static final int INVALID_USER_ID = 0;

	public ResponseEntity<AccessToken> user_register(User user) {

		Tuple result = userRepository.findUserByPhone(user.getPhone());

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		AccessToken accessToken = null;

		/**
		 * user all ready registered
		 */

		if (result != null)
			return new ResponseEntity<AccessToken>(accessToken, HttpStatus.NOT_ACCEPTABLE);

		/**
		 * 
		 * phone number invalidation test future work
		 */

		user.setPassword(cryptoMaster.encrypt(user.getPassword()));
		user.setRegistrationDate(ts.toString());
		int maxUserId = userRepository.findMaxUserId();
		++maxUserId;
		accessToken = new AccessToken(securityMaster.token(TokenStatus.CREATED, maxUserId, Type.USER), MAX_EXPIRES, ts);
		user.setAccessToken(accessToken);
		userRepository.persist(user);
		return new ResponseEntity<AccessToken>(accessToken, HttpStatus.CREATED);
	}

	/*
	 * String accessToken = (String) result.get("access_token"); int expires = (int)
	 * result.get("expires"); Timestamp timestamp = (Timestamp)
	 * result.get("access_date");
	 * 
	 */

	/*
	 * public AccessToken isAuthorized(Authentication user) throws Exception {
	 * return userRepository.findHungryUserByPhone(user.getPhone()); }
	 */

}
