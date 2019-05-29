package com.hungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hungry.entities.User;

public final class UserControllerJUnitTest extends UserAbstractSetUp {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserControllerJUnitTest.class);

	@Test
	@WithMockUser(username = phone, password = password, roles = "USER")
	public void profile() {
		try {
			mMvcResult = mMockMvc
					.perform(MockMvcRequestBuilders.get("/user/profile").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn();
			MockHttpServletResponse response = mMvcResult.getResponse();
			assertEquals("content-type", MediaType.APPLICATION_JSON_VALUE, response.getContentType());
			assertNotNull(response.getContentType());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	@WithMockUser(username = phone, password = password, roles = "USER")
	public void login() {
		try {
			mMvcResult = mMockMvc
					.perform(MockMvcRequestBuilders.get("/user/login").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isFound()).andReturn();
			MockHttpServletResponse response = mMvcResult.getResponse();
			assertEquals("content-type", MediaType.APPLICATION_JSON_VALUE, response.getContentType());
			assertNotNull(response.getContentType());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	public void register() {
		try {

			String phone = "99999999999999";
			user = new User("Saiful ", "Islam", phone, "saiful.sust.cse@gmail.com", "saiful");
			mMvcResult = mMockMvc.perform(
					post("/user/registration").contentType(MediaType.APPLICATION_JSON_VALUE).content(user.toJson()))
					.andExpect(status().isCreated()).andReturn();
			MockHttpServletResponse response = mMvcResult.getResponse();
			assertEquals("content-type", MediaType.APPLICATION_JSON_VALUE, response.getHeader("content-type"));
			assertEquals("new registered user deleted : ", 1, userRepository.deleteUserByPhoneNumber(phone));

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

}
