package com.hungry.product;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hungry.entities.Product;
import com.hungry.entities.ProductSummary;
import com.hungry.repositories.ProductRepository;
import com.hungry.user.UserBaseAbstract;

public abstract class ProductBaseAbstract extends UserBaseAbstract {

	@Autowired
	protected ProductRepository productRepository;

	
	private ProductSummary mProductSummary;

	protected String name = "Test Product";
	protected String type = "TEST";

	protected Product mProduct, mProductWithDetail;

	@Before
	public final void set_up() {

		/**
		 * mock set up
		 */



		mProductSummary = new ProductSummary(name, 2222222, type);
		mProduct = new Product(mProductSummary);
		this.mProduct = productRepository.save(mProduct);
		mProductSummary.setDetail("[[\" Display \",\" 6.5' \"],[\"battery \",\"1800 amp\"]]");
		mProductWithDetail = new Product(mProductSummary);
		this.mProductWithDetail = productRepository.save(mProductWithDetail);

	}

	@After
	public final void clear_set_up() {
		productRepository.deleteProductById(mProduct.getProductId());
		productRepository.deleteProductById(mProductWithDetail.getProductId());
	}

}
