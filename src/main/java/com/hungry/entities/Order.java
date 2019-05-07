package com.hungry.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hungry_orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;

	// @Column(name = "user_id", nullable = false)
	@JoinColumn(name = "delevery_user_id")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = User.class)
	private User user;

	@JoinColumn(name = "product_id")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Product.class)
	private Product product;

	public Order() {
		super();
	}

	public Order(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + "]";
	}

}
