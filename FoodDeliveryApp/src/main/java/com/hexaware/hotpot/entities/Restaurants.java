package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
public class Restaurants {





	 @Id
    @Column(name="RestaurantID")
    private int restaurant_id;

    
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Location is required")
    private String location;

    @Pattern(regexp = "[0-9]{10}", message = "Contact number must be a 10-digit number")
    @NotNull(message = "contact number  is required")
    private String contactNumber;

    @NotNull(message = "Rating is required")

    private double rating =0.0;

   




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<MenuItems> menuItemSet = new HashSet<MenuItems>(); // collections should be initialized to avoid nullPoitner Escep

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Orders> orderSet = new HashSet<Orders>();
    
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Discount> discountSet = new HashSet<Discount>() ;

	public Restaurants() {
		super();
	}

	public Restaurants(int restaurantId, String name, String location, String contactNumber, double rating) {
		super();
		this.restaurant_id = restaurantId;
		this.name = name;
		this.location = location;
		this.contactNumber = contactNumber;
		this.rating = rating;
	}

	public int getRestaurantId() {
		return restaurant_id;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurant_id = restaurantId;
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

	@Override
	public String toString() {
		return "Restaurants [restaurant_id=" + restaurant_id + ", name=" + name + ", location=" + location
				+ ", contactNumber=" + contactNumber + ", rating=" + rating + "]";
	}

	
    



}
