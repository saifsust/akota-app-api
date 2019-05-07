package com.hungry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hungry.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM hungry_orders"
			+ " INNER JOIN hungry_users ON hungry_orders.delevery_user_id = hungry_users.user_id"
			+ " INNER JOIN  hungry_products ON hungry_orders.product_id = hungry_products.product_id", nativeQuery = true)
	public List<Order> findOrderByUserAndProductId();

	@Query(value = "SELECT * FROM hungry_orders INNER JOIN hungry_users ON hungry_orders.delevery_user_id = hungry_users.user_id", nativeQuery = true)
	public List<Order> findOrders();

}
