package com.hungry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungry.entities.Product;
import com.hungry.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void persist(Product product) throws Exception{
		productRepository.persist(product);
	}
	
	
	
	
}
