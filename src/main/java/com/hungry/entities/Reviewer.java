package com.hungry.entities;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Tuple;

@Embeddable
public class Reviewer {
	@Column(name = "total_reviewers")
	private int totalReviewers;
	@Column(name = "reviewers_ids")
	private String reviewers;

	public Reviewer() {
		super();
	}

	public Reviewer(int totalReviewers, String reviewers) {
		super();
		this.totalReviewers = totalReviewers;
		this.reviewers = reviewers;
	}

	@Transient
	public static Reviewer converter(Tuple reviwer) {
		return new Reviewer((int) reviwer.get("total_reviewers"), "" + reviwer.get("reviewers_ids"));
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

	@Override
	public String toString() {
		return "Reviewer [totalReviewers=" + totalReviewers + ", reviewers=" + reviewers + "]";
	}

}
