package com.hungry.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hungry.models.Status;
import com.hungry.services.DbManagerService;
import com.hungry.services.HungryUserService;

@Controller("userController")
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public HungryUserService userService;
	
	@Autowired
	public DbManagerService service;

	private AccessToken accessToken;

	@PostMapping(value = "/registration")
	public @ResponseBody Status isRegistrationComplete(@RequestBody User user) {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		AccessToken accessToken = new AccessToken(AccessTokenGenerator.accessToken(), 84600, ts);

		accessToken.setStatus(UserController.class,"isRegistrationComplete","adhjdhad");
		// user.setAccessToken(accessToken);
		System.out.println(user);
		userService.isSave(user);

		return accessToken;
		// return new Status(HungryUserController.class,null);
	}

	@PostMapping(value = "/upload-profile-img", consumes = { "multipart/form-data" })
	public @ResponseBody Status profile_img_upload(@RequestParam("user_id") String userId,
			@RequestParam("user_img") MultipartFile mpf) {
		System.out.println(userId);
		System.out.println(mpf.getContentType());
		return new Status(UserController.class, "profile_img_upload",userId);
	}

	@RequestMapping(value = "/authorization")
	public @ResponseBody Status isAuthorizedUser(@RequestBody User user) {
		try {
		   
			
			
			service.execution();
			
	     return userService.isAuthorized(user);
		}catch (Exception e) {
			return new Status(UserController.class,"isAuthorizedUser",e.getMessage());
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public @ResponseBody Status profile(AccessToken accessToken) {

		return new User().getAccessToken();
	}

}
