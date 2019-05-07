package com.hungry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.models.Profile;
import com.hungry.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void invalid_user_register() {
		User user = new User();
		user.setFirstName("Saiful");
		user.setLastName("Islam");
		user.setPhone("01686654728");
		user.setPassword("saiful");
		user.setEmail("saiful.sust.cse@gmail.com");
		ResponseEntity<AccessToken> token = userService.register(user);
		assertThat(token.getStatusCode(), is(HttpStatus.FOUND));
	}

	@Test
	public void valid_user_register() {
		User user = new User();
		user.setFirstName("Saiful");
		user.setLastName("Islam");
		user.setPhone("01686654729");
		user.setPassword("saiful");
		user.setEmail("saiful.sust.cse@gmail.com");
		ResponseEntity<AccessToken> token = userService.register(user);
		assertThat(token.getStatusCode(), is(HttpStatus.CREATED));
	}

	@Test
	public void profileTestNotNullAble() {
		ResponseEntity<Profile> profile = userService.profile(
				"LQPG34894R8KAQsMIpBaPfwoYh/RKhPpt2sNxnF41vtXdTmu3l671ohCkkYKNSdBrfDkBM3QsOo/HAhDJIlEymfndvjl/cw5qyNjzb1BFNlSQwiADQU22mfc44wrZSlpMJP9NfR5bP3sRvHHsxIUkFhjfl1JhZwe4HD9rtLzTPjA1E0y0CcndOuYjN8hL51FAdugjIxjsn2VXC7LfcQlL2gn5vds8IuYlke/NgtlAmKbQJJY0yiwFsFnzDdH0uRY8Z8s7SioOhKLdnaqhxVWrPLLinw9BQQ==VMJ9431");
		assertThat(profile).isNotNull();
	}

	@Test
	public void profileTestNullAble() {
		ResponseEntity<Profile> profile = userService.profile(
				"AQPG34894R8KAQsMIpBaPfwoYh/RKhPpt2sNxnF41vtXdTmu3l671ohCkkYKNSdBrfDkBM3QsOo/HAhDJIlEymfndvjl/cw5qyNjzb1BFNlSQwiADQU22mfc44wrZSlpMJP9NfR5bP3sRvHHsxIUkFhjfl1JhZwe4HD9rtLzTPjA1E0y0CcndOuYjN8hL51FAdugjIxjsngVXC7LfcQlL2gn5vds8IuYlke/NgtlAmKbQJJY0yiwFsFnzDdH0uRY8Z8s7SioOhKLdnaqhxVWrPLLinw9BQQ==VMJ9431");
		assertThat(profile.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

}
