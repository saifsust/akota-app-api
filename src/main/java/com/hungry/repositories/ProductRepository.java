package com.hungry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hungry.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM hungry_products WHERE product_name = :product_name", nativeQuery = true)
	public List<Product> findProductsByName(@Param("product_name") String productName);

	@Query(value = "SELECT * FROM hungry_products WHERE product_id = :product_id", nativeQuery = true)
	public Product findProductById(@Param("product_id") int productId);

	@Query(value = "SELECT * FROM hungry_products WHERE product_type = :product_type", nativeQuery = true)
	public List<Product> findProductsByType(@Param("product_type") String productType);

	@Query(value = "SELECT * FROM hungry_products WHERE product_type = :product_type and rating >= :rating", nativeQuery = true)
	public List<Product> findProductsByRatingAndType(@Param("product_type") String productType,
			@Param("rating") double rating);

	@Query(value = "SELECT * FROM hungry_products WHERE product_name = :product_name and rating >= :rating", nativeQuery = true)
	public List<Product> findProductsByRatingAndName(@Param("product_name") String productName,
			@Param("rating") double rating);
}
