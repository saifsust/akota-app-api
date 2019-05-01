package com.hungry.entities;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Tuple;

@Embeddable
public class Discount implements Serializable {
	@Column(name = "discount")
	private double discount;
	@Column(name = "discounted_sold_price")
	private double discountedSoldPrice;
	@Column(name = "discounted_sold_peices")
	private int discountedSoldPeices;
	@Column(name = "buyers_in_discount")
	private String buyersInDiscount;

	public Discount() {
		super();
	}

	public Discount(double discount, double discountedSoldPrice, int discountedSoldPeices, String buyersInDiscount) {
		super();
		this.discount = discount;
		this.discountedSoldPrice = discountedSoldPrice;
		this.discountedSoldPeices = discountedSoldPeices;
		this.buyersInDiscount = buyersInDiscount;
	}

	@Transient
	public static Discount converter(Tuple result) {
		return new Discount((double) result.get("discount"), (double) result.get("discounted_sold_price"),
				(int) result.get("discounted_sold_peices"), "" + result.get("buyers_in_discount"));
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

	@Override
	public String toString() {
		return "Discount [discount=" + discount + ", discountedSoldPrice=" + discountedSoldPrice
				+ ", discountedSoldPeices=" + discountedSoldPeices + ", buyersInDiscount=" + buyersInDiscount + "]";
	}

}
