package com.hungry.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class Sale {
	@Column(name = "total_sold_price")
	private double totalSoldPrices;
	@Column(name = "total_sold_peices")
	private int totalSales;
	@Column(name = "buyers")
	@JsonIgnore
	private String orderIds;

	public Sale() {
		super();
	}

	public Sale(double totalSoldPrices, int totalSales, String orderIds) {
		super();
		this.totalSoldPrices = totalSoldPrices;
		this.totalSales = totalSales;
		this.orderIds = orderIds;
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

	@Override
	public String toString() {
		return "Sale [totalSoldPrices=" + totalSoldPrices + ", totalSales=" + totalSales + ", orderIds=" + orderIds
				+ "]";
	}

}
