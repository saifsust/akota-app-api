package com.hungry.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

public final class ProductControllerTest extends ProductBaseAbstract {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ProductControllerTest.class);

	/**
	 * product/retreive/name/{name} url test
	 */

	@Test
	@WithMockUser(username = phone, password = password, roles = "USER")
	public void user_product_retreive_by_name() {

		try {
			mMockMvc.perform(get("/product/retreive/name/product_name").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneAdmin, password = password, roles = "ADMIN")
	public void admin_product_retreive_by_name() {

		try {
			mMockMvc.perform(get("/product/retreive/name/product_name").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	@WithMockUser(username = phoneRider, password = password, roles = "RIDER")
	public void rider_product_retreive_by_name() {

		try {
			mMockMvc.perform(get("/product/retreive/name/product_name").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneDealer, password = password, roles = "DEALER")
	public void dealer_product_retreive_by_name() {

		try {
			mMockMvc.perform(get("/product/retreive/name/product_name").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	/**
	 * product/retreive/name/{name} url test
	 */

	/**
	 * product/retreive/id/{id} url test
	 */

	@Test
	@WithMockUser(username = phone, password = password, roles = "USER")
	public void user_product_retreive_by_id() {

		try {
			mMockMvc.perform(get("/product/retreive/id/1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneAdmin, password = password, roles = "ADMIN")
	public void admin_product_retreive_by_id() {

		try {
			mMockMvc.perform(get("/product/retreive/id/1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneRider, password = password, roles = "RIDER")
	public void rider_product_retreive_by_id() {

		try {
			mMockMvc.perform(get("/product/retreive/id/1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneDealer, password = password, roles = "DEALER")
	public void dealer_product_retreive_by_id() {

		try {
			mMockMvc.perform(get("/product/retreive/id/1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	/**
	 * product/retreive/id/{id} url test
	 */

	/**
	 * Product upload check
	 */

	@Test
	@WithMockUser(username = phoneDealer, password = password, roles = "DEALER")
	public void dealer_product_upload() {

		try {
			mMockMvc.perform(post("/product/dealer/upload").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mProduct.toJson())).andExpect(status().isAccepted());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Test
	@WithMockUser(username = phoneAdmin, password = password, roles = "ADMIN")
	public void admin_product_upload() {

		try {
			mMockMvc.perform(post("/product/dealer/upload").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mProduct.toJson())).andExpect(status().isForbidden());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	@WithMockUser(username = phoneRider, password = password, roles = "RIDER")
	public void rider_product_upload() {

		try {
			mMockMvc.perform(post("/product/dealer/upload").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mProduct.toJson())).andExpect(status().isForbidden());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	@WithMockUser(username = phone, password = password, roles = "USER")
	public void user_product_upload() {

		try {
			mMockMvc.perform(post("/product/dealer/upload").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mProduct.toJson())).andExpect(status().isForbidden());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Product upload check
	 */

}
