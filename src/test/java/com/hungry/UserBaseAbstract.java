package com.hungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.services.util.CryptoMaster;
import com.hungry.services.util.SecurityMaster;
import com.hungry.services.util.TokenStatus;
import com.hungry.services.util.Type;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class UserBaseAbstract {

	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected SecurityMaster securityMaster;
	@Autowired
	protected CryptoMaster cryptoMaster;

	protected MockMvc mMockMvc;
	protected MvcResult mMvcResult;

	@Autowired
	private WebApplicationContext mWebApplicationContext;

	protected static final String phone = "12345678902462";
	protected static final String password = "saiful";
	protected int userId;
	protected User user;
	protected static final String phoneAdmin = "017274574542";
	protected static final String phoneRider = "256452654625";
	protected static final String phoneDealer = "25645265462523";

	@Before
	public final void user_save_test() {

		/**
		 * MockMvc set up for mvc test
		 */
		mMockMvc = MockMvcBuilders.webAppContextSetup(mWebApplicationContext).apply(springSecurity()).build();

		/**
		 * user set up for test
		 */

		Date date = new Date();

		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		AccessToken accessToken = new AccessToken(securityMaster.token(TokenStatus.CREATED, 10, Type.USER), 13135613,
				ts);

		user = new User("Saiful", "Islam", phoneAdmin, "saiful.sust.cse@gmail.com", "ADMIN",
				cryptoMaster.encrypt(password));
		user.setAccessToken(accessToken);
		User save = userRepository.save(user);
		assertNotNull("User save successfully", save);

		user = new User("Saiful", "Islam", phoneRider, "saiful.sust.cse@gmail.com", "RIDER",
				cryptoMaster.encrypt(password));
		user.setAccessToken(accessToken);
		save = userRepository.save(user);
		assertNotNull("User save successfully", save);

		user = new User("Saiful", "Islam", phoneDealer, "saiful.sust.cse@gmail.com", "DEALER",
				cryptoMaster.encrypt(password));
		user.setAccessToken(accessToken);
		save = userRepository.save(user);
		assertNotNull("User save successfully", save);

		user = new User("Saiful", "Islam", phone, "saiful.sust.cse@gmail.com", "USER", cryptoMaster.encrypt(password));
		user.setAccessToken(accessToken);
		save = userRepository.save(user);
		this.userId = save.getUserId();
		assertNotNull("User save successfully", save);
	}

	@After
	public final void delete_user_test() {
		assertEquals("User delete successfully", 1, userRepository.deleteUserByPhoneNumber(phoneDealer));
		assertEquals("User delete successfully", 1, userRepository.deleteUserByPhoneNumber(phoneRider));
		assertEquals("User delete successfully", 1, userRepository.deleteUserByPhoneNumber(phoneAdmin));
		assertEquals("User delete successfully", 1, userRepository.deleteUserByPhoneNumber(phone));
	}

}
