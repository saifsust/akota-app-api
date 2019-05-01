package com.hungry.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hungry.entities.converters.JsonArrayConverter;

@Embeddable
public class ProductSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_imgs", columnDefinition = "json")
	@Convert(converter = JsonArrayConverter.class)
	private List<String> productImgs;
	@Column(name = "product_local_imgs", columnDefinition = "json")
	@JsonIgnore
	@Convert(converter = JsonArrayConverter.class)
	private List<String> productLocalImgs;
	@Column(name = "product_detail")
	@JsonIgnore
	private String detail;
	@Column(name = "price")
	private double price;
	@Column(name = "product_type")
	private String productType;

	public ProductSummary() {
		super();
	}

	public ProductSummary(String name, List<String> productImgs, String detail, double price, String productType) {
		super();
		this.name = name;
		this.productImgs = productImgs;
		this.detail = detail;
		this.price = price;
		this.productType = productType;
	}

	public ProductSummary(String name, List<String> productImgs, double price, String productType) {
		super();
		this.name = name;
		this.productImgs = productImgs;
		this.price = price;
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(List<String> productImgs) {
		this.productImgs = productImgs;
	}

	public List<String> getProductLocalImgs() {
		return productLocalImgs;
	}

	public void setProductLocalImgs(List<String> productLocalImgs) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductSummary [name=" + name + ", productImgs=" + productImgs + ", productLocalImgs="
				+ productLocalImgs + ", detail=" + detail + ", price=" + price + ", productType=" + productType + "]";
	}

}
