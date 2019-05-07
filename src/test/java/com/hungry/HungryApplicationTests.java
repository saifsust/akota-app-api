package com.hungry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hungry.controllers.UserController;
import com.hungry.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HungryApplicationTests {

	@Autowired
	private UserController UserController;

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		assertThat(UserController).isNotNull();
	}
	
	
	@Test
	public void insertUser() {
		

	}

}
