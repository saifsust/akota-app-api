package com.hungry.entities;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Tuple;

@Embeddable
public class Rating {
	@Column(name = "rating")
	private double rating;
	@Column(name = "total_raters")
	private int totalRaters;
	@Column(name = "raters")
	private String raters;

	public Rating() {
		super();
	}

	public Rating(double rating, int totalRaters, String raters) {
		super();
		this.rating = rating;
		this.totalRaters = totalRaters;
		this.raters = raters;
	}

	@Transient
	public static Rating converter(Tuple rating) {
		return new Rating((double) rating.get("rating"), (int) rating.get("total_raters"), "" + rating.get("raters"));
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

	@Override
	public String toString() {
		return "Rating [rating=" + rating + ", totalRaters=" + totalRaters + ", raters=" + raters + "]";
	}

}
