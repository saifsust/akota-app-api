package com.hungry.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hungry.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT product_name,product_imgs,product_local_imgs,product_detail,price,product_type FROM hungry_products WHERE product_name = :product_name", nativeQuery = true)
	public List<Tuple> findProductSummaryByName(@Param("product_name") String name);

	@Query(value = "SELECT product_name,product_imgs,product_local_imgs,product_detail,price,product_type FROM hungry_products WHERE product_id = :product_id", nativeQuery = true)
	public Tuple findProductSummaryById(@Param("product_id") int productId);

	@Query(value = "SELECT total_reviewers,reviewers_ids FROM hungry_products WHERE product_id = :product_id", nativeQuery = true)
	public Tuple findReviewerProductById(@Param("product_id") int productId);

	@Query(value = "SELECT total_sold_price,total_sold_peices,buyers FROM hungry_products  WHERE product_id = :product_id", nativeQuery = true)
	public Tuple findSaleProductById(@Param("product_id") int productId);

	@Query(value = "SELECT rating,total_raters,raters FROM hungry_products WHERE product_id = :product_id", nativeQuery = true)
	public Tuple findRatingProductById(@Param("product_id") int productId);

	@Query(value = "SELECT d.discount,d.discounted_sold_price,d.discounted_sold_peices,d.buyers_in_discount FROM hungry_products d WHERE d.product_id = :product_id", nativeQuery = true)
	public Tuple findDiscountProductById(@Param("product_id") int productId);

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
