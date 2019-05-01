package com.hungry.entities;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

@Embeddable
public class ProductSummary {
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

	public ProductSummary() {
		super();
	}

	public ProductSummary(String name, String productImgs, double price, String productType) {
		super();
		this.name = name;
		this.productImgs = productImgs;
		this.price = price;
		this.productType = productType;
	}

	public ProductSummary(String name, String productImgs, String detail, double price, String productType) {
		super();
		this.name = name;
		this.productImgs = productImgs;
		this.detail = detail;
		this.price = price;
		this.productType = productType;
	}

	@Transient
	public static ProductSummary converter(Tuple productSummary) {
		return new ProductSummary("" + productSummary.get("product_name"), "" + productSummary.get("product_imgs"),
				(double) productSummary.get("price"), "" + productSummary.get("product_type"));
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

	@Override
	public String toString() {
		return "ProductSummary [name=" + name + ", productImgs=" + productImgs + ", productLocalImgs="
				+ productLocalImgs + ", detail=" + detail + ", price=" + price + ", productType=" + productType + "]";
	}

}
