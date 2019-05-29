package com.hungry.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.services.UserService;

public final class UserServiceJUnitTest extends UserAbstractSetUp {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserServiceJUnitTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void re_register() {
		ResponseEntity<AccessToken> token = userService.register(user);
		assertNotNull("Not null token", token);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, token.getStatusCode());
		assertEquals("token must null ", null, token.getBody());

	}

	@Test
	public void authorizer() {
		ResponseEntity<AccessToken> token = userService.authorizer(phone);
		assertNotNull("Not null token", token);
		assertEquals(HttpStatus.FOUND, token.getStatusCode());
		assertNotEquals("token must null ", "", token.getBody());

	}

	@Test
	public void not_found() {
		ResponseEntity<AccessToken> token = userService.authorizer(phone + "5");
		assertNotNull("Not null token", token);
		assertEquals(HttpStatus.NOT_FOUND, token.getStatusCode());
		assertEquals("token must null ", null, token.getBody());

	}

}
