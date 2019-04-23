package com.hungry.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.support.StandardScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hungry.authentication.AccessTokenGenerator;
import com.hungry.entities.AccessToken;
import com.hungry.entities.HungryUser;
import com.hungry.models.Status;
import com.hungry.models.User;
import com.hungry.services.HungryUserService;

@Controller("hungryUserController")
@RequestMapping(value = "/user")
public class HungryUserController {

	@Autowired
	public HungryUserService userService;

	private AccessToken accessToken;

	@PostMapping(value = "/registration")
	public @ResponseBody Status isRegistrationComplete(@RequestBody HungryUser user) {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		AccessToken accessToken = new AccessToken(AccessTokenGenerator.accessToken(), 84600, ts);

		accessToken.setStatus(HungryUserController.class,"isRegistrationComplete","adhjdhad");
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
		return new Status(HungryUserController.class, "profile_img_upload",userId);
	}

	@RequestMapping(value = "/authorization")
	public @ResponseBody Status isAuthorizedUser(@RequestBody User user) {
		try {
	     return userService.isAuthorized(user);
		}catch (Exception e) {
			return new Status(HungryUserController.class,"isAuthorizedUser",e.getMessage());
		}
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public @ResponseBody Status profile(AccessToken accessToken) {

		return new HungryUser().getAccessToken();
	}

}
