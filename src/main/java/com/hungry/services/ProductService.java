package com.hungry.services;

import java.util.List;

import javax.persistence.Tuple;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hungry.entities.Discount;
import com.hungry.entities.Product;
import com.hungry.entities.ProductSummary;
import com.hungry.entities.Rating;
import com.hungry.entities.Reviewer;
import com.hungry.entities.Sale;
import com.hungry.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<List<Product>> all() {

		ProductSummary summary = new ProductSummary("MyPro1", new JSONArray("[{ imgs: kadkahdkahd }]").toString(),
				120.5, "SHABAN");

		Product pro = new Product(summary);
		productRepository.save(pro);
		Discount dis = new Discount();
		// dis.setBuyersInDiscount("");

		List<Product> product = productRepository.findProductsByName("MyPro1");

		Tuple result = productRepository.findDiscountProductById(2);
		System.out.println(Discount.converter(result));

		System.out.println(Rating.converter(productRepository.findRatingProductById(1)));
		System.out.println(Reviewer.converter(productRepository.findReviewerProductById(1)));

		System.out.println(Sale.converter(productRepository.findSaleProductById(1)));

		System.out.println(ProductSummary.converter(productRepository.findProductSummaryById(1)));

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(product);
	}

}
