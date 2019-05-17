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
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;
	@JoinColumn(name = "user_id")
	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, targetEntity = User.class)
	private User user;
	@JoinColumn(name = "delever_id")
	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, targetEntity = User.class)
	private User delever;
	@JoinColumn(name = "product_id")
	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, targetEntity = Product.class)
	private Product product;
	@Column(name = "peices")
	private int peices;
	@Column(name = "total_price")
	private double prices;
	@Column(name = "promo_code")
	private String promoCode;
	@Column(name = "delevery_type")
	private String deleveryType;
	@Column(name = "pickup")
	private String pickup;
	@Column(name = "destinatio")
	private String destination;
	@Column(name = "order_date")
	private String now;

	public Order() {
		super();
	}

	public Order(User user, Product product, int peices, double prices, String deleveryType, String now) {
		super();
		this.user = user;
		this.product = product;
		this.peices = peices;
		this.prices = prices;
		this.deleveryType = deleveryType;
		this.now = now;
	}

	public Order(User user, User delever, Product product, int peices, double prices, String promoCode,
			String deleveryType, String pickup, String destination, String now) {
		super();
		this.user = user;
		this.delever = delever;
		this.product = product;
		this.peices = peices;
		this.prices = prices;
		this.promoCode = promoCode;
		this.deleveryType = deleveryType;
		this.pickup = pickup;
		this.destination = destination;
		this.now = now;
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

	public User getDelever() {
		return delever;
	}

	public void setDelever(User delever) {
		this.delever = delever;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getPeices() {
		return peices;
	}

	public void setPeices(int peices) {
		this.peices = peices;
	}

	public double getPrices() {
		return prices;
	}

	public void setPrices(double prices) {
		this.prices = prices;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getDeleveryType() {
		return deleveryType;
	}

	public void setDeleveryType(String deleveryType) {
		this.deleveryType = deleveryType;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", delever=" + delever + ", product=" + product
				+ ", peices=" + peices + ", prices=" + prices + ", promoCode=" + promoCode + ", deleveryType="
				+ deleveryType + ", pickup=" + pickup + ", destination=" + destination + ", now=" + now + "]";
	}

}
