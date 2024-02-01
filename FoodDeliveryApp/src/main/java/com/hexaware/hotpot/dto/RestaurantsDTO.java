package com.hexaware.hotpot.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RestaurantsDTO {

	private int restaurantId;

	private String name;

	private String location;

	private String contactNumber;

	private Double rating = 0.0;

	public RestaurantsDTO() {
		super();
	}

	public RestaurantsDTO(int restaurantId, String name, String location, String contactNumber, Double rating) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.location = location;
		this.contactNumber = contactNumber;
		this.rating = rating;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	

}
