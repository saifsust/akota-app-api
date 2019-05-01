package com.hungry.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hungry.entities.Product;
import com.hungry.repositories.ProductRepository;
import com.hungry.services.DbManagerService;
import com.hungry.services.ProductService;
import com.hungry.services.util.Contents;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private DbManagerService dbManagerService;

	@GetMapping(value = Contents.PRODUCTS, produces = Contents.JSON)
	public ResponseEntity<List<Product>> upload_new_product() {
		 //dbManagerService.execution();
		return productService.all();
	}

}
