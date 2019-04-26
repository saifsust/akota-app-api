package com.hungry.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hungry.entities.Product;

@Repository
public class ProductRepository {

	
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public void persist(Product product) throws Exception {
		em.persist(product);
	}
	
	
}
