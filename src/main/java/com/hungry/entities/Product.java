package com.hungry.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.aspectj.lang.annotation.RequiredTypes;
import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "hungry_products")
public class Product {

	// default properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_imgs")
	private String productImgs;
	@Column(name = "product_local_imgs")
	private String productLocalImgs;
	@Column(name = "product_detail")
	private String detail;
	@Column(name = "price")
	private double price;
	@Column(name = "product_type")
	private String productType;

	// discount properties
	@Column(name = "discount")
	private double discount;
	@Column(name = "discounted_sold_price")
	private double discountedSoldPrice;
	@Column(name = "discounted_sold_peices")
	private int discountedSoldPeices;
	@Column(name = "buyers_in_discount")
	private String buyersInDiscount;

	// rating properties
	@Column(name = "rating")
	private double rating;
	@Column(name = "total_raters")
	private int totalRaters;
	@Column(name = "raters")
	private String raters;

	// reviewer properties
	@Column(name = "total_reviewers")
	private int totalReviewers;
	@Column(name = "reviewers_ids")
	private String reviewers;

	// sales properties
	@Column(name = "total_sold_price")
	private double totalSoldPrices;
	@Column(name = "total_sold_peices")
	private int totalSales;

	// order properties
	@Column(name = "order_ids")
	private String orderIds;
	@Column(name = "total_orders")
	private int totalOrders;

	//launch properties
	@Column(name = "launch")
	private String launch;
	
	
	public Product() {
		super();
	}
	
	

	public Product(String name, double price, String productType) {
		super();
		this.name = name;
		this.price = price;
		this.productType = productType;
	}



	public Product(String name, String productImgs, String detail, double price, String productType) {
		super();
		this.name = name;
		this.productImgs = productImgs;
		this.detail = detail;
		this.price = price;
		this.productType = productType;
	}



	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(String productImgs) {
		this.productImgs = productImgs;
	}

	public String getProductLocalImgs() {
		return productLocalImgs;
	}

	public void setProductLocalImgs(String productLocalImgs) {
		this.productLocalImgs = productLocalImgs;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscountedSoldPrice() {
		return discountedSoldPrice;
	}

	public void setDiscountedSoldPrice(double discountedSoldPrice) {
		this.discountedSoldPrice = discountedSoldPrice;
	}

	public int getDiscountedSoldPeices() {
		return discountedSoldPeices;
	}

	public void setDiscountedSoldPeices(int discountedSoldPeices) {
		this.discountedSoldPeices = discountedSoldPeices;
	}

	public String getBuyersInDiscount() {
		return buyersInDiscount;
	}

	public void setBuyersInDiscount(String buyersInDiscount) {
		this.buyersInDiscount = buyersInDiscount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getTotalRaters() {
		return totalRaters;
	}

	public void setTotalRaters(int totalRaters) {
		this.totalRaters = totalRaters;
	}

	public String getRaters() {
		return raters;
	}

	public void setRaters(String raters) {
		this.raters = raters;
	}

	public int getTotalReviewers() {
		return totalReviewers;
	}

	public void setTotalReviewers(int totalReviewers) {
		this.totalReviewers = totalReviewers;
	}

	public String getReviewers() {
		return reviewers;
	}

	public void setReviewers(String reviewers) {
		this.reviewers = reviewers;
	}

	public double getTotalSoldPrices() {
		return totalSoldPrices;
	}

	public void setTotalSoldPrices(double totalSoldPrices) {
		this.totalSoldPrices = totalSoldPrices;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", productImgs=" + productImgs
				+ ", productLocalImgs=" + productLocalImgs + ", detail=" + detail + ", price=" + price
				+ ", productType=" + productType + ", discount=" + discount + ", discountedSoldPrice="
				+ discountedSoldPrice + ", discountedSoldPeices=" + discountedSoldPeices + ", buyersInDiscount="
				+ buyersInDiscount + ", rating=" + rating + ", totalRaters=" + totalRaters + ", raters=" + raters
				+ ", totalReviewers=" + totalReviewers + ", reviewers=" + reviewers + ", totalSoldPrices="
				+ totalSoldPrices + ", totalSales=" + totalSales + ", orderIds=" + orderIds + ", totalOrders="
				+ totalOrders + ", launch=" + launch + "]";
	}

	
	
	
}
