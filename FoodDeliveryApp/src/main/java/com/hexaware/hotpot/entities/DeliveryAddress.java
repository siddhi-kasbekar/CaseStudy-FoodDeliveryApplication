package com.hexaware.hotpot.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to deliverAddress , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */


@Entity
public class DeliveryAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
    @SequenceGenerator(name = "address_id_sequence", sequenceName = "ADDRESS_ID_SEQUENCE",initialValue = 1001, allocationSize = 1)
	@Column(name = "AddressID")
	private int  addressId;
	
	@NotBlank(message = "House number is required")
	private String houseNo;
	
	@NotBlank(message = "Area is required")
	private String area;
	
	 @NotBlank(message = "Landmark is required")
	private String landmark;
	 
	 @NotBlank(message = "City is required")
	 @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City should contain only alphabets")
	private String city;
	
	@NotNull(message = "Pincode is required")
    @Min(value = 100000)
    @Max(value = 999999)
	private int pincode;
	

	
	public DeliveryAddress() {
		super();
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
	
	

	@Override
	public String toString() {
		return "DeliveryAddress [addressId=" + addressId + ", houseNo=" + houseNo + ", area=" + area + ", landmark="
				+ landmark + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	
	
	
	
	

}
