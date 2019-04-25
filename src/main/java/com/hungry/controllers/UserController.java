package com.hungry.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hungry.authentication.AccessTokenGenerator;
import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Authentication;
import com.hungry.models.Status;
import com.hungry.services.DbManagerService;
import com.hungry.services.SecurityMaster;
import com.hungry.services.UserService;

@Controller("userController")
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@Autowired
	public DbManagerService service;

	@Autowired
	private SecurityMaster securityMaster;

	private AccessToken accessToken;

	@PostMapping(value = "/registration", consumes = { "application/json" })
	public @ResponseBody ResponseEntity<AccessToken> isRegistrationComplete(@RequestBody User user) {

		try {

			log.info("recieve : isRegistrationComplete : " + user.toString());
			Date date = new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			AccessToken accessToken = new AccessToken(securityMaster.token() , 84600, ts);

			user.setAccessToken(accessToken);

			userService.persist(user);
			return new ResponseEntity<AccessToken>(accessToken, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<AccessToken>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(value = "/upload", consumes = { "multipart/form-data" })
	public @ResponseBody ResponseEntity<Void> upload_multipartfile(@RequestParam("user_id") String userId,
			@RequestParam("user_img") MultipartFile mpf) {
		log.debug("recieve : upload_multipartfile : user id : " + userId + " multipartfile : " + mpf.getContentType());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/authorization", consumes = { "application/json" })
	public @ResponseBody ResponseEntity<AccessToken> isAuthorizedUser(@RequestBody Authentication authen) {
		try {
			System.out.println(authen);

			service.execution();

			AccessToken token = userService.isAuthorized(authen);
			// log.info(token.toString());
			return new ResponseEntity<AccessToken>(token, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getCause().toString());
			return new ResponseEntity<AccessToken>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> profile(AccessToken accessToken) {
		return null;
	}

}
