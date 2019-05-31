package com.hungry.controllers;

import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.hungry.services.util.MultipartFileStoreService;

@Controller("userController")
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;
	@Autowired
	private MultipartFileStoreService MultipartFileStoreService;

	@GetMapping(path = "/reply")
	public @ResponseBody ResponseEntity<?> reply(Principal principal) {
		log.info(principal.getName());
		return userService.reply(principal);

	}

	@PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessToken> isRegistrationComplete(@RequestBody User user) {
		log.info("recieve : isRegistrationComplete : " + user.toString());
		LocalDate localDate = LocalDate.now();
		user.setRegistrationDate(localDate.toString());
		return userService.register(user);
	}

	@PutMapping(value = "/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody ResponseEntity<Void> upload_multipartfile(
			@RequestParam(required = true, value = "token") String token,
			@RequestParam(value = "image") MultipartFile mpf, HttpServletRequest httpServletRequest) {
		log.debug(
				"recieve : upload_multipartfile : accesstoken : " + token + " multipartfile : " + mpf.getContentType());
		return MultipartFileStoreService.store(token, mpf, httpServletRequest);
	}

	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessToken> isAuthorizedUser(Principal principal) {
		log.info(principal.getName());
		return userService.authorizer(principal.getName());

	}

	@RequestMapping(value = { "/public/profile", "/admin/profile", "/dealer/profile",
			"/rider/profile" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Profile> profile(Principal principal) {
		log.debug(principal.getName());
		return userService.profile(principal);
	}
}
