package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 * Author: Nipurna Bandi
 * 
 * Entity description: contains properties related to restaurants , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity
public class Restaurants {

	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id_sequence")
    @SequenceGenerator(name = "restaurant_id_sequence", sequenceName = "RESTAURANT_ID_SEQUENCE",initialValue = 601, allocationSize = 1)
	@Column(name = "RestaurantID")
	private int restaurantId;

	@NotNull(message = "Name is required")
	private String name;

	@NotNull(message = "Location is required")
	private String location;

	@Pattern(regexp = "[0-9]{10}", message = "Contact number must be a 10-digit number")
	@NotNull(message = "contact number  is required")
	private String contactNumber;

	@NotNull(message = "Rating is required")

	private double rating = 0.0;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<MenuItems> menuItemSet = new HashSet<>(); // collections should be initialized to avoid
																	// nullPoitner Escep

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private Set<Orders> orderSet = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private Set<Discount> discountSet = new HashSet<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Administrator admin;
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<MenuCategory> menuCategories = new HashSet<>();

	public Restaurants() {
		super();
	}

	public Restaurants(int restaurantId, String name, String location, String contactNumber, double rating) {
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<MenuItems> getMenuItemSet() {
		return menuItemSet;
	}

	public void setMenuItemSet(Set<MenuItems> menuItemSet) {
		this.menuItemSet = menuItemSet;
	}

	public Set<Orders> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}

	public Set<Discount> getDiscountSet() {
		return discountSet;
	}

	public void setDiscountSet(Set<Discount> discountSet) {
		this.discountSet = discountSet;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public Set<MenuCategory> getMenuCategories() {
		return menuCategories;
	}

	public void setMenuCategories(Set<MenuCategory> menuCategories) {
		this.menuCategories = menuCategories;
	}

	@Override
	public String toString() {
		return "Restaurants [restaurant_id=" + restaurantId + ", name=" + name + ", location=" + location
				+ ", contactNumber=" + contactNumber + ", rating=" + rating + "]";
	}

}
