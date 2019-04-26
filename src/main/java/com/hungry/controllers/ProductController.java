package com.hungry.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hungry.entities.Product;
import com.hungry.services.ProductService;
import com.hungry.services.SecurityMaster;
import com.hungry.util.Contents;
import com.hungry.util.TokenStatus;
import com.hungry.util.Type;

@RestController
public class ProductController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private SecurityMaster utilService;

	@PostMapping(value = Contents.NEW_PRODUCT, consumes = { Contents.JSON })
	public ResponseEntity<Void> upload_new_product(@RequestBody Product newProduct) {

		try {
			log.info(newProduct.toString());
			productService.persist(newProduct);

			String token = utilService.token(TokenStatus.ACCEPTED, 123, Type.USER);

			System.out.println(token);
			log.error(token);

			Map<String, Object> map = utilService.decrypt(token);
			
			TokenStatus status = (TokenStatus) map.get("token_status");
			
			System.out.println(status);
			
			System.out.println(map.get("user_id"));
			System.out.println(map.get("user_type"));
			System.out.println(map.get("token_status"));
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
