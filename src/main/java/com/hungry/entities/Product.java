package com.hungry.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name="hungry_products")
public class Product {

	@Id
	@Column(name="product_id")
	private int productId;
	@Column(name="product_name")
	private String name;
	@Column(name="product_img")
	private String img;
	@Column(name="price")
	private double price;
	@Column(name="discount")
	private double discount;
	@Column(name="rating")
	private double rating;
	@Column(name="total_raters")
	private int totalRaters;
	@Transient
	private JSONArray raters;
	
	@Column(name="total_reviewers")
	private int totalReviewers;
	@Transient
	private JSONArray reviewers;
	@Column(name="discounted_sold_peices")
	private int discountSalesPeices;
	@Transient
	private JSONArray buyerInDiscount;
	@Column(name="total_sold_peices")
	private int totalSales;
	@Column(name="launch")
	private String launch;
	@Transient
	private JSONArray orderIds;
	@Transient
	private JSONObject productTypes;
	
	
	
	
	public Product() {
		super();
	}


	public Product(String name, String img, double price, JSONObject productTypes) {
		super();
		this.name = name;
		this.img = img;
		this.price = price;
		this.productTypes = productTypes;
	}


	public Product(int productId, String name, double price, double discount, double rating, int totalRaters,
			JSONArray raters, int totalReviewers, JSONArray reviewers, int discountSalesPeices,
			JSONArray buyerInDiscount, int totalSales, String launch, JSONArray orderIds, JSONObject productTypes) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.rating = rating;
		this.totalRaters = totalRaters;
		this.raters = raters;
		this.totalReviewers = totalReviewers;
		this.reviewers = reviewers;
		this.discountSalesPeices = discountSalesPeices;
		this.buyerInDiscount = buyerInDiscount;
		this.totalSales = totalSales;
		this.launch = launch;
		this.orderIds = orderIds;
		this.productTypes = productTypes;
	}




	public int getProductId() {
		return productId;
	}




	public void setProductId(int productId) {
		this.productId = productId;
	}




	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public double getDiscount() {
		return discount;
	}




	public void setDiscount(double discount) {
		this.discount = discount;
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




	public JSONArray getRaters() {
		return raters;
	}




	public void setRaters(JSONArray raters) {
		this.raters = raters;
	}




	public int getTotalReviewers() {
		return totalReviewers;
	}




	public void setTotalReviewers(int totalReviewers) {
		this.totalReviewers = totalReviewers;
	}




	public JSONArray getReviewers() {
		return reviewers;
	}




	public void setReviewers(JSONArray reviewers) {
		this.reviewers = reviewers;
	}




	public int getDiscountSalesPeices() {
		return discountSalesPeices;
	}




	public void setDiscountSalesPeices(int discountSalesPeices) {
		this.discountSalesPeices = discountSalesPeices;
	}




	public JSONArray getBuyerInDiscount() {
		return buyerInDiscount;
	}




	public void setBuyerInDiscount(JSONArray buyerInDiscount) {
		this.buyerInDiscount = buyerInDiscount;
	}




	public int getTotalSales() {
		return totalSales;
	}




	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}




	public String getLaunch() {
		return launch;
	}




	public void setLaunch(String launch) {
		this.launch = launch;
	}




	public JSONArray getOrderIds() {
		return orderIds;
	}




	public void setOrderIds(JSONArray orderIds) {
		this.orderIds = orderIds;
	}




	public JSONObject getProductTypes() {
		return productTypes;
	}




	public void setProductTypes(JSONObject productTypes) {
		this.productTypes = productTypes;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", img=" + img + ", price=" + price
				+ ", discount=" + discount + ", rating=" + rating + ", totalRaters=" + totalRaters + ", raters="
				+ raters + ", totalReviewers=" + totalReviewers + ", reviewers=" + reviewers + ", discountSalesPeices="
				+ discountSalesPeices + ", buyerInDiscount=" + buyerInDiscount + ", totalSales=" + totalSales
				+ ", launch=" + launch + ", orderIds=" + orderIds + ", productTypes=" + productTypes + "]";
	}




}
