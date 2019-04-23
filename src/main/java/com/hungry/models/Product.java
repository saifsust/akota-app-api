package com.hungry.models;

public class Product {
 private String type;
 private double price;
 
 public Product() {
	super();
}
public Product(String type, double price) {
	super();
	this.type = type;
	this.price = price;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
@Override
public String toString() {
	return "Product [type=" + type + ", price=" + price + "]";
}
 
 
}
