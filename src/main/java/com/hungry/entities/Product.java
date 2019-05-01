package com.hungry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hungry_products")
public class Product implements Serializable {

	// default properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;
	@Embedded
	private ProductSummary summary;
	@Embedded
	private Discount discount;
	@Embedded
	private Rating rating;
	@Embedded
	private Reviewer reviewer;
	@Embedded
	private Sale sale;
	@Column(name = "launch")
	private String launch;

	public Product() {
		super();
	}

	public Product(ProductSummary summary) {
		super();
		this.summary = summary;
		this.discount = new Discount();
		this.rating = new Rating();
		this.reviewer = new Reviewer();
		this.sale = new Sale();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ProductSummary getSummary() {
		return summary;
	}

	public void setSummary(ProductSummary summary) {
		this.summary = summary;
		this.discount = new Discount();
		this.rating = new Rating();
		this.reviewer = new Reviewer();
		this.sale = new Sale();
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", summary=" + summary + ", discount=" + discount + ", rating="
				+ rating + ", reviewer=" + reviewer + ", sale=" + sale + ", launch=" + launch + "]";
	}

}
