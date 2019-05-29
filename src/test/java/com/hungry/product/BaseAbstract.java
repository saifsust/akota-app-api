package com.hungry.product;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hungry.entities.Product;
import com.hungry.entities.ProductSummary;
import com.hungry.entities.Sale;
import com.hungry.repositories.ProductRepository;
import com.hungry.repositories.SaleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class BaseAbstract {

	@Autowired
	protected ProductRepository productRepository;

	private ProductSummary mProductSummary;

	protected String name = "Test Product";
	protected String type = "TEST";

	protected Product mProduct, mProductWithDetail;

	@Before
	public final void set_up() {
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
