package com.hexaware.hotpot.dto;



public class DeliveryAddressDTO {
	
	private int  addressId;
	
	private String houseNo;
	
	private String area;
	
	private String landmark;
	 
	private String city;
	
	private int pincode;

	public DeliveryAddressDTO() {
		super();
	}

	public DeliveryAddressDTO(int addressId, String houseNo, String area, String landmark, String city, int pincode) {
		super();
		this.addressId = addressId;
		this.houseNo = houseNo;
		this.area = area;
		this.landmark = landmark;
		this.city = city;
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	

}
