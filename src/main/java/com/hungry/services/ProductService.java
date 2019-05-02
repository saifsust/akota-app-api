package com.hungry.services;

import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hungry.entities.Product;
import com.hungry.entities.ProductSummary;
import com.hungry.repositories.ProductRepository;

@Service
public class ProductService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private DbManagerService dbManagerService;

	private static final double RATING = 0.0;

	public ResponseEntity<Product> productById(int productId) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findProductById(productId));
	}

	public ResponseEntity<List<Product>> retrieveByName(String name) {

		// dbManagerService.execution();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findProductsByName(name));
	}

	public ResponseEntity<List<Product>> retrieveByType(String type) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findProductsByType(type));
	}

	public ResponseEntity<List<Product>> retrieveByBestName(String name) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findProductsByRatingAndName(name, RATING));
	}

	public ResponseEntity<List<Product>> retrieveByBestType(String type) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findProductsByRatingAndType(type, RATING));
	}


	public ResponseEntity<Void> upload(ProductSummary summary) {
		LOG.debug("upload" + summary);
		if (summary == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		if (summary.getName() == null || summary.getProductType() == null || summary.getPrice() == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

		// System.out.println(summary);

		productRepository.save(new Product(summary));
		LOG.debug("upload : successfully upload");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
