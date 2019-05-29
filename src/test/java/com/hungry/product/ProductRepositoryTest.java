package com.hungry.product;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hungry.entities.Product;
import com.hungry.repositories.SaleRepository;

public final class ProductRepositoryTest extends BaseAbstract {

	@Test
	public void findProductSummaryByName() {
		List<Tuple> products = productRepository.findProductSummaryByName(mProduct.getSummary().getName());
		assertEquals("products size : ", 2, products.size());
		for (Tuple row : products) {
			assertEquals("product name : ", name, row.get("product_name"));
		}
	}

	@Test
	public void findProductSummaryById() {
		Tuple product = productRepository.findProductSummaryById(mProductWithDetail.getProductId());
		assertEquals("product name : ", name, product.get("product_name"));
		assertEquals("product type : ", type, product.get("product_type"));
	}

	@Test
	public void findProductsByType() {
		List<Product> products = productRepository.findProductsByType(mProduct.getSummary().getProductType());
		assertEquals("products size : ", 2, products.size());
		for (Product row : products) {
			assertEquals("product name : ", name, row.getSummary().getName());
		}
	}

	@Test
	public void findProductsByName() {
		List<Product> products = productRepository.findProductsByName(mProductWithDetail.getSummary().getName());
		assertEquals("products size : ", 2, products.size());
		for (Product row : products) {
			assertEquals("product name : ", name, row.getSummary().getName());
		}
	}

	@Test
	public void findProductById() {
		Product product = productRepository.findProductById(mProductWithDetail.getProductId());
		assertEquals("product name : ", name, product.getSummary().getName());
		assertEquals("product type : ", type, product.getSummary().getProductType());
	}

}
