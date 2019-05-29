package com.hungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

}
