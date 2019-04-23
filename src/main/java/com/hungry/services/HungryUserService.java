package com.hungry.services;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungry.entities.User;
import com.hungry.models.Status;
import com.hungry.repositories.HungryUserRepository;

@Service("hungryUserService")
@Transactional
public class HungryUserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(HungryUserService.class);

	@Autowired
	private HungryUserRepository userRepository;

	public boolean isSave(User user) {
		return userRepository.isSaved(user);
	}

	public Status isAuthorized(User user) {
		return userRepository.findHungryUserByPhone(user.getPhone());
	}

}
