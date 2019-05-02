package com.hungry.controllers;

import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.Media;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> upload(RequestEntity<HashMap<String, Object>> summary) {
		LOG.debug("upload : " + summary);
		ProductSummary productSummary = ProductSummary.converter(new JSONObject(summary.getBody()));
		return productService.upload(productSummary);
	}

	@GetMapping(value = "/retreive/name/{name}", produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retrieveByName(@PathVariable("name") String name) {

		return productService.retrieveByName(name);
	}

	@GetMapping(value = "/retreive/best/name/{name}", produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retrieveBest(@PathVariable("name") String name) {
		return productService.retrieveByBestName(name);
	}

	@GetMapping(value = "/retreive/type/{type}", produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retreiveByType(@PathVariable("type") String type) {
		return productService.retrieveByType(type);
	}

	@GetMapping(value = "retreive/best/type/{type}", produces = Contents.JSON)
	public @ResponseBody ResponseEntity<List<Product>> retreiveByBestType(@PathVariable("type") String type) {
		return productService.retrieveByBestType(type);
	}

	@GetMapping(value = "retreive/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Product> productById(@PathVariable("productId") int productId) {
		return productService.productById(productId);
	}

}
