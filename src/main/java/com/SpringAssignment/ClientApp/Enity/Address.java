package com.SpringAssignment.ClientApp.Enity;


public class Address {

	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", streetLine1=" + streetLine1 + ", streetLine2="
				+ streetLine2 + "]";
	}
	private String city;
	private String country;
	private String streetLine1;
	private String streetLine2;
	public Address(String city, String country, String streetLine1, String streetLine2) {
		super();
		this.city = city;
		this.country = country;
		this.streetLine1 = streetLine1;
		this.streetLine2 = streetLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetLine1() {
		return streetLine1;
	}
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}
	public String getStreetLine2() {
		return streetLine2;
	}
	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}
	
}
