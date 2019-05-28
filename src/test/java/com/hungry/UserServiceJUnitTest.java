package com.hungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceJUnitTest {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	private static final String phone = "1234567890";
	private static final int expires = 60;
	private int userId;
	User user = new User("Saiful", "Islam", phone, "saiful.sust.cse@gmail.com", "test");

	@Before
	public void preprocessor() {
		ResponseEntity<AccessToken> token = userService.register(user);
		assertNotNull("Not null token", token);
		assertEquals(HttpStatus.CREATED, token.getStatusCode());
		assertNotEquals("is token \"\"?", "", token.getBody().getAccessToken());
		assertNotEquals("is token null?", null, token.getBody().getAccessToken());
		assertEquals("token expires ", expires, token.getBody().getExpire());

	}

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

	@After
	public void postprocessor() {
		int isDelete = userRepository.deleteUserByPhoneNumber(phone);
		assertEquals("is user deleted?", 1, isDelete);
	}

}
