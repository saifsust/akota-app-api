package com.hungry.services;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Profile;
import com.hungry.repositories.Debugger;
import com.hungry.repositories.UserRepository;
import com.hungry.services.util.CryptoMaster;
import com.hungry.services.util.SecurityMaster;
import com.hungry.services.util.TokenStatus;
import com.hungry.services.util.Type;

@Service
public class UserService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserService.class);

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

	@Autowired
	private Debugger debugger;

	@Autowired
	private AmqpAdmin mAmqpAdmin;
	@Autowired
	private RabbitTemplate mRabbitTemplate;

	private static final int MAX_EXPIRES = 60;
	private static final int MIN_EXPIRES = 60;
	private static final int INVALID_USER_ID = 0;
	private static final String regExpr = "";

	private AccessToken accessToken;

	public ResponseEntity<?> reply(Principal principal) {

		System.out.println(principal.getName());
		String username = "ndd";

		mAmqpAdmin.declareExchange(new Exchange() {

			@Override
			public boolean shouldDeclare() { // TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isIgnoreDeclarationExceptions() { // TODO
				return false;
			}

			@Override
			public Collection<?> getDeclaringAdmins() { // TODO Auto-generated
				return null;
			}

			@Override
			public boolean isInternal() { 
				return false;
			}

			@Override
			public boolean isDurable() { 
				return false;
			}

			@Override
			public boolean isDelayed() { 
				return false;
			}

			@Override
			public boolean isAutoDelete() { 
				return true;
			}

			@Override
			public String getType() {
				return "direct";
			}

			@Override
			public String getName() {
				return "driver_hailing";
			}

			@Override
			public Map<String, Object> getArguments() {
				return null;
			}
		});
		
		
		
		mAmqpAdmin.declareExchange(new Exchange() {

			@Override
			public boolean shouldDeclare() { // TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isIgnoreDeclarationExceptions() { // TODO
				return false;
			}

			@Override
			public Collection<?> getDeclaringAdmins() { // TODO Auto-generated
				return null;
			}

			@Override
			public boolean isInternal() { 
				return false;
			}

			@Override
			public boolean isDurable() { 
				return false;
			}

			@Override
			public boolean isDelayed() { 
				return false;
			}

			@Override
			public boolean isAutoDelete() { 
				return true;
			}

			@Override
			public String getType() {
				return "topic";
			}

			@Override
			public String getName() {
				return "reply_exchange";
			}

			@Override
			public Map<String, Object> getArguments() {
				return null;
			}
		});
		

		mAmqpAdmin.declareQueue(new Queue("reply:ndd"));
		
		try {
			mRabbitTemplate.convertAndSend("driver_hailing", "driver_hailing:ndd", principal.getName());
			long a=0;
			while(a<1000000000) ++a;
			
		  Message msg =	mRabbitTemplate.receive("reply:ndd");
		  
		  System.out.println(new String(msg.getBody()));
			

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		return ResponseEntity.ok(null);

	}

	@Transactional
	public ResponseEntity<AccessToken> register(User user) {

		try {
			LOG.debug("register : user " + user.toString());

			// System.out.println(user);

			User result = userRepository.findUserByPhoneNumber(user.getPhone());
			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);

			accessToken = null;

			if (result != null || user.getPassword().equals(null) || user.getPassword().equals("")) {
				LOG.debug("register : findUserByPhoneNumber " + result.toString());
				return new ResponseEntity<AccessToken>(accessToken, HttpStatus.NOT_ACCEPTABLE);
			}

			// System.out.println(user);

			/**
			 * 
			 * phone number verification test future work
			 */

			user.setPassword(cryptoMaster.encrypt(user.getPassword()));
			user.setRegistrationDate(ts.toString());

			int maxUserId = userRepository.findMaxUserId();
			++maxUserId;

			LOG.debug("register  maxUserId : " + maxUserId);
			accessToken = new AccessToken(securityMaster.token(TokenStatus.CREATED, maxUserId, Type.USER), MAX_EXPIRES,
					ts);
			user.setAccessToken(accessToken);
			userRepository.save(user);

		} catch (Exception e) {
			LOG.debug("register  : " + e.getMessage());
			return new ResponseEntity<AccessToken>(accessToken, HttpStatus.NOT_ACCEPTABLE);
			// TODO: handle exception
		}

		return new ResponseEntity<AccessToken>(accessToken, HttpStatus.CREATED);
	}

	public ResponseEntity<AccessToken> authorizer(String phone) {

		User result = userRepository.findUserByPhoneNumber(phone);
		accessToken = null;
		if (result == null)
			return new ResponseEntity<AccessToken>(accessToken, HttpStatus.NOT_FOUND);

		String token = result.getAccessToken().getAccessToken();
		int expires = (int) result.getAccessToken().getExpire();
		Timestamp timestamp = (Timestamp) result.getAccessToken().getAccessDate();

		Date date = new Date();
		long time = date.getTime();
		// Timestamp timestamp = new Timestamp(time);
		accessToken = new AccessToken(token, expires, timestamp);

		return new ResponseEntity<AccessToken>(accessToken, HttpStatus.FOUND);
	}

	public ResponseEntity<Profile> profile(String token) {

		Profile profile = null;
		try {
			Map<String, Object> info = securityMaster.decrypt(token);
			long userId = (long) info.get("user_id");
			User user = null;
			user = userRepository.findUserByUserId((int) userId);
			if (user == null)
				return new ResponseEntity<Profile>(profile, HttpStatus.NOT_FOUND);
			profile = new Profile(user);
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("profile : " + e.getMessage());
			return new ResponseEntity<Profile>(profile, HttpStatus.NOT_FOUND);
		}
	}

}
