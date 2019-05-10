package com.hungry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hungry.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM hungry_orders"
			+ " INNER JOIN hungry_users as buyer ON hungry_orders.user_id = buyer.user_id"
			+ " INNER JOIN  hungry_products ON hungry_orders.product_id = hungry_products.product_id", nativeQuery = true)
	public List<Order> findOrderByUserAndProduct();

	@Query(value = "SELECT * FROM hungry_orders"
			+ " INNER JOIN hungry_users as buyer ON hungry_orders.user_id = buyer.user_id"
			+ " INNER JOIN hungry_users as delever ON hungry_orders.delever_id = delever.user_id"
			+ " INNER JOIN  hungry_products ON hungry_orders.product_id = hungry_products.product_id", nativeQuery = true)
	public List<Order> findOrderByUserAndProductAndDelever();

	@Query(value = "SELECT * FROM hungry_orders INNER JOIN hungry_users ON hungry_orders.user_id = hungry_users.user_id", nativeQuery = true)
	public List<Order> findOrders();

}
