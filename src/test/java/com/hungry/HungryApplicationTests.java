package com.hungry;

import static org.assertj.core.api.Assertions.assertThat;

import javax.crypto.AEADBadTagException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hungry.controllers.UserController;
import com.hungry.models.Profile;
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
		
	ResponseEntity<Profile>	profile  = userService.profile("LQPG34894R8KAQsMIpBaPfwoYh/RKhPpt2sNxnF41vtXdTmu3l671ohCkkYKNSdBrfDkBM3QsOo/HAhDJIlEymfndvjl/cw5qyNjzb1BFNlSQwiADQU22mfc44wrZSlpMJP9NfR5bP3sRvHHsxIUkFhjfl1JhZwe4HD9rtLzTPjA1E0y0CcndOuYjN8hL51FAdugjIxjsn2VXC7LfcQlL2gn5vds8IuYlke/NgtlAmKbQJJY0yiwFsFnzDdH0uRY8Z8s7SioOhKLdnaqhxVWrPLLinw9BQQ==VMJ9431");
		System.out.println(profile);
	 assertThat(profile).isNotNull();
	}

}
