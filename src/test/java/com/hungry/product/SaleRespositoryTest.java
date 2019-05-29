package com.hungry.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hungry.repositories.SaleRepository;

public final class SaleRespositoryTest extends BaseAbstract {

	@Autowired
	private SaleRepository saleRepository;
	private final int soldPeices = 10;
	private final double soldPrice = 423143.1631543;

	@Before
	public void update() {
		int isUpdate = saleRepository.update(soldPrice, soldPeices, "[1,2,23]", mProduct.getProductId());
		assertEquals(1, isUpdate);

	}

	@Test
	public void update_test() {
		int isUpdate = saleRepository.update(soldPrice, soldPeices, "[1,2,23]", mProductWithDetail.getProductId());
		assertEquals(1, isUpdate);

	}

	@Test
	public void findProductPrice() {
		double price = saleRepository.findProductPrice(mProduct.getProductId());
		assertEquals("product price chceck : ", mProduct.getSummary().getPrice(), price, 0.000);

	}

	@Test
	public void findProductSoldPrice() {
		double price = saleRepository.findProductSoldPrice(mProduct.getProductId());
		assertEquals("sold price check : ", soldPrice, price, 0.000);

	}

	@Test
	public void findProductSoldPeices() {
		int peices = saleRepository.findProductSoldPeices(mProduct.getProductId());
		assertEquals("sold peices check : ", soldPeices, peices);
	}

	@Test
	public void findProductBuyers() {
		String buyer = saleRepository.findProductBuyers(mProduct.getProductId());
		assertNotEquals("sold buyer check : ", null, buyer);
	}

}
