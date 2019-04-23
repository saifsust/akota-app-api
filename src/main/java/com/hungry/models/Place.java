package com.hungry.models;

public class Place {

	private String address;
	private double lat;
	private double lng;
	
	
	
	public Place() {
		super();
	}


	public Place(String address, double lat, double lng) {
		super();
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLng() {
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}


	@Override
	public String toString() {
		return "Place [address=" + address + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
}
