package com.hungry.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hungry_deleveries")
public class Delevery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delevery_id")
	private int deleveryId;
	@Column(name = "delevery_type")
	private String deleveryType;

	public Delevery() {
		super();
	}

	public Delevery(String deleveryType) {
		super();
		this.deleveryType = deleveryType;
	}

	public int getDeleveryId() {
		return deleveryId;
	}

	public void setDeleveryId(int deleveryId) {
		this.deleveryId = deleveryId;
	}

	public String getDeleveryType() {
		return deleveryType;
	}

	public void setDeleveryType(String deleveryType) {
		this.deleveryType = deleveryType;
	}

	@Override
	public String toString() {
		return "Delevery [deleveryId=" + deleveryId + ", deleveryType=" + deleveryType + "]";
	}

}
