package com.hungry.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Profile;
import com.hungry.services.UserService;
import com.hungry.services.util.ConsumeType;
import com.hungry.services.util.MultipartFileStoreService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller("userController")
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;
	@Autowired
	private MultipartFileStoreService MultipartFileStoreService;

	@PostMapping(value = "/registration", consumes = { ConsumeType.JOSN })
	public @ResponseBody ResponseEntity<AccessToken> isRegistrationComplete(@RequestBody User user) {
		log.info("recieve : isRegistrationComplete : " + user.toString());
		
		System.out.println(user);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		user.setRegistrationDate(localDate.toString());
		return userService.register(user);
	}

	@PutMapping(value = "/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody ResponseEntity<Void> upload_multipartfile(
			@RequestParam(required = true, value = "token") String token,
			@RequestParam(value = "image") MultipartFile mpf, HttpServletRequest httpServletRequest) {
		log.debug(
				"recieve : upload_multipartfile : accesstoken : " + token + " multipartfile : " + mpf.getContentType());
		return MultipartFileStoreService.store(token, mpf, httpServletRequest);
	}

	@PostMapping(value = "/login", consumes = { ConsumeType.JOSN })
	public @ResponseBody ResponseEntity<AccessToken> isAuthorizedUser(@RequestBody User user) {
		log.info(user.toString());
		return userService.authorizer(user);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = ConsumeType.JOSN)
	public @ResponseBody ResponseEntity<Profile> profile(@RequestParam("token") String token) {
		return userService.profile(token);
	}
}
