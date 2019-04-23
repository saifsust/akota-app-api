package com.hungry.models;

import java.util.List;

public class Order {

	private String orderDate;
	private List<Product> products;
	private int numberOfProduct;
	private double totalPrice;
	private double discount;
	private String deleveryType;
	private Place destination;
	private Place pickup;
	
	
	
	public Order() {
		super();
	}
	public Order(String orderDate, List<Product> products, int numberOfProduct, double totalPrice, double discount,
			String deleveryType, Place destination, Place pickup) {
		super();
		this.orderDate = orderDate;
		this.products = products;
		this.numberOfProduct = numberOfProduct;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.deleveryType = deleveryType;
		this.destination = destination;
		this.pickup = pickup;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getNumberOfProduct() {
		return numberOfProduct;
	}
	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getDeleveryType() {
		return deleveryType;
	}
	public void setDeleveryType(String deleveryType) {
		this.deleveryType = deleveryType;
	}
	public Place getDestination() {
		return destination;
	}
	public void setDestination(Place destination) {
		this.destination = destination;
	}
	public Place getPickup() {
		return pickup;
	}
	public void setPickup(Place pickup) {
		this.pickup = pickup;
	}
	@Override
	public String toString() {
		return "Order [orderDate=" + orderDate + ", products=" + products + ", numberOfProduct=" + numberOfProduct
				+ ", totalPrice=" + totalPrice + ", discount=" + discount + ", deleveryType=" + deleveryType
				+ ", destination=" + destination + ", pickup=" + pickup + "]";
	}
	
}
