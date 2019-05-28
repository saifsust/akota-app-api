package com.hungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hungry.entities.AccessToken;
import com.hungry.entities.User;
import com.hungry.repositories.UserRepository;
import com.hungry.services.util.SecurityMaster;
import com.hungry.services.util.TokenStatus;
import com.hungry.services.util.Type;

/**
 * @email saiful.sust.cse@gmail.com
 * @author saif-sust
 * @phone 01686654727
 * 
 *        this class is for test user repository
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SecurityMaster securityMaster;

	private static final String phone = "1234567890";
	private int userId;

	@Before
	public void user_save_test() {
		User user = new User("Saiful", "Islam", phone, "test");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		AccessToken accessToken = new AccessToken(securityMaster.token(TokenStatus.CREATED, 10, Type.USER), 13135613,
				ts);
		user.setAccessToken(accessToken);
		User save = userRepository.save(user);
		this.userId = save.getUserId();
		assertNotNull("User save successfully", save);
	}

	@Test
	public void findUserByPhoneNumber() {
		User save = userRepository.findUserByPhoneNumber(phone);
		assertNotNull("User can not find by phone number", save);
		assertEquals("User phone number does not match", phone, save.getPhone());
	}

	@Test
	public void findUserByUserId() {
		User save = userRepository.findUserByUserId(userId);
		assertNotNull("User can not find by id", save);
		assertEquals("User id does not match", userId, save.getUserId());
	}

	@Test
	public void Id() {
		int lastId = userRepository.lastInsertedId();
		int maxId = userRepository.findMaxUserId();
		assertEquals("User id does not match", maxId, lastId);
	}

	@Test
	public void updateUserImage() {
		String imageUri = "image uri";
		String location = "store location";
		int check = userRepository.updateUserImage(imageUri, location, userId);
		assertEquals("user updateed check value must be 1 ", 1, check);
		String dbLocation = userRepository.findImageLocalAddresss(userId);
		String dbImageUri = userRepository.findImageUri(userId);
		assertEquals("user updateed check value must be 1 ", location, dbLocation);
		assertEquals("user updateed check value must be 1 ", imageUri, dbImageUri);
	}

	@After
	public void delete_user_test() {
		assertEquals("User delete successfully", 1, userRepository.deleteUserByPhoneNumber(phone));
	}

}
