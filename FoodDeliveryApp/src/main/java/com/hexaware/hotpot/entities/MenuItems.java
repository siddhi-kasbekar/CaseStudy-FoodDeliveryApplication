package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Author: Nipurna Bandi
 * 
 * Entity description: contains properties related to menuItems , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity
public class MenuItems {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menuItemId;

	@NotBlank(message = "Item name is required")
	private String itemName;

	private String description;

	@NotBlank(message = "Category is required")
	private String category;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
	private Double price;

	private String availabilityTime;

	private String specialDietaryInfo;

	private String tasteInfo;

	private String nutritionalInfo;

	private int cookingTime;
	
	private int quantity;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RestaurantID")
	@JsonIgnore
	private Restaurants restaurant;

	@OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
	private Set<OrderDetails> orderDetailsSet = new HashSet<>();

	
	@OneToMany(mappedBy = "menuItem")
    @JsonIgnore // Prevent infinite recursion
    private Set<CartDetails> cartMenuItems = new HashSet<>();

	
	


	public MenuItems() {
		super();
	}

	public MenuItems(long menuItemId, String itemName, String description, String category, double price,
			String availabilityTime, String specialDietaryInfo, String tasteInfo, String nutritionalInfo,
			int cookingTime, int quantity) {
		super();

		this.menuItemId = menuItemId;

		this.itemName = itemName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.availabilityTime = availabilityTime;
		this.specialDietaryInfo = specialDietaryInfo;
		this.tasteInfo = tasteInfo;
		this.nutritionalInfo = nutritionalInfo;
		this.cookingTime = cookingTime;
		this.quantity=quantity;
	}

	public long getMenuitemId() {

		return menuItemId;
	}

	public void setMenuitemId(long menuItemId) {
		this.menuItemId = menuItemId;

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAvailabilityTime() {
		return availabilityTime;
	}

	public void setAvailabilityTime(String availabilityTime) {
		this.availabilityTime = availabilityTime;
	}

	public String getSpecialDietaryInfo() {
		return specialDietaryInfo;
	}

	public void setSpecialDietaryInfo(String specialDietaryInfo) {
		this.specialDietaryInfo = specialDietaryInfo;
	}

	public String getTasteInfo() {
		return tasteInfo;
	}

	public void setTasteInfo(String tasteInfo) {
		this.tasteInfo = tasteInfo;
	}

	public String getNutritionalInfo() {
		return nutritionalInfo;
	}

	public void setNutritionalInfo(String nutritionalInfo) {
		this.nutritionalInfo = nutritionalInfo;
	}

	public Integer getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public Set<OrderDetails> getOrderDetailsSet() {
		return orderDetailsSet;
	}

	public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
		this.orderDetailsSet = orderDetailsSet;
	}

	


	
	
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {

		return "MenuItems [menuitemId=" + menuItemId + ", itemName=" + itemName + ", description=" + description

				+ ", category=" + category + ", price=" + price + ", availabilityTime=" + availabilityTime
				+ ", specialDietaryInfo=" + specialDietaryInfo + ", tasteInfo=" + tasteInfo + ", nutritionalInfo="
				+ nutritionalInfo + ", cookingTime=" + cookingTime + ", restaurant=" + restaurant + ", orderDetailsSet="
				+ orderDetailsSet +   "]";
	}

}
