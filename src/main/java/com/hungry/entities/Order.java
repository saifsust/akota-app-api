package com.hungry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hungry_orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;

	//@Column(name = "product_id")
	 @JoinColumn(name = "product_id", insertable = true, updatable = true)
	//@ToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
	private Product products;

	@Column(name = "user_id")
	// @JoinColumn(name = "user_id", insertable = true, updatable = true)
	// @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
	private User users;
	@Column(name = "ordet_times")
	private String orderDate;

	public Order() {
		super();
	}

	

	public Order(Product products, User users) {
		super();
		this.products = products;
		this.users = users;
	}



	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	

	public Product getProducts() {
		return products;
	}



	public void setProducts(Product products) {
		this.products = products;
	}



	public User getUsers() {
		return users;
	}



	public void setUsers(User users) {
		this.users = users;
	}



	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", products=" + products + ", users=" + users + ", orderDate=" + orderDate
				+ "]";
	}

}
