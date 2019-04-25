package com.hungry.services;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Authentication;
import com.hungry.models.Status;
import com.hungry.repositories.UserRepository;

@Service("hungryUserService")
@Transactional
public class UserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public void persist(User user)throws Exception {
		userRepository.persist(user);
	}

	public AccessToken isAuthorized(Authentication user)throws Exception {
		return userRepository.findHungryUserByPhone(user.getPhone());
	}

}
