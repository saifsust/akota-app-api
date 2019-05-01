package com.hungry.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hungry.entities.Product;
import com.hungry.entities.ProductSummary;
import com.hungry.repositories.ProductRepository;
import com.hungry.services.ProductService;
import com.hungry.services.util.Contents;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@PostMapping(value = Contents.NEW_PRODUCT, consumes = { Contents.JSON })
	public @ResponseBody ResponseEntity<Void> upload(@RequestBody HashMap<String, Object> summary) {
		LOG.debug("upload : " + summary);
		System.out.println(summary.get("productImgs"));

		List<String> map = new ArrayList<String>();

		map.add("SAIFUL ISLAM");
		map.add("LITON");
		ProductSummary s = new ProductSummary("babu", map, 113.0, "ami");

		s.setProductLocalImgs(map);
		Product pp = new Product(s);

		productRepository.save(pp);

		return null;
		// return productService.upload(summary);
	}

	/**
	 * all get request
	 * 
	 */

	@GetMapping(value = Contents.NAME_RETREIVER, produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retrieveByName(@PathVariable("name") String name) {
		return productService.retrieveByName(name);
	}

	@GetMapping(value = Contents.BEST_NAME_RETREIVER, produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retrieveBest(@PathVariable("name") String name) {
		return productService.retrieveByBestName(name);
	}

	@GetMapping(value = Contents.TYPE_RETREIVER, produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retreiveByType(@PathVariable("type") String type) {
		return productService.retrieveByType(type);
	}

	@GetMapping(value = Contents.BEST_TYPE_RETREIVER, produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retreiveByBestType(@PathVariable("type") String type) {
		return productService.retrieveByBestType(type);
	}

	/*
	 * @GetMapping(value = Contents.SUMMARIES_NAME_RETREIVER, produces =
	 * Contents.JSON) public @ResponseBody ResponseEntity<List<ProductSummary>>
	 * retreiveSummariesByName(
	 * 
	 * @PathVariable("name") String name) { return
	 * productService.retrieveSummariesByName(name); }
	 */
}
