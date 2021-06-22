package com.SpringAssignment.ClientApp.Enity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.SpringAssignment.ClientApp.Enity.Address;

public class Client {
	
	@Size(min=1,message="Please Enter FirstName: ")
	private String FirstName;
	
	
	@Size(min=1,message="Please Enter LastName: ")
	private String LastName;
	
	
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}",message="Mobile Number must have 10 digits")
	private String MobileNumber;
	
	
	@Size(min=1,message="IDNumber can not be empty")
	private String IDNumber;
	
	private Address address;
	
	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public Client( String firstName, String lastName, String mobileNumber,
			String iDNumber, Address physicalAddress) {
		System.out.println("*** Coming here in ClientEntity constuctor to create new entry....");
		FirstName = firstName;
		LastName = lastName;
		MobileNumber = mobileNumber;
		IDNumber = iDNumber;
		address = physicalAddress;
	}


	


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getMobileNumber() {
		return MobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}


	public String getIDNumber() {
		return IDNumber;
	}


	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Client [FirstName=" + FirstName + ", LastName=" + LastName + ", MobileNumber=" + MobileNumber
				+ ", IDNumber=" + IDNumber + ", address=" + address + "]";
	}


	

	


}
