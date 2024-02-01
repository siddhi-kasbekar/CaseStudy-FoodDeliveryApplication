package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class MenuItems {
	

	    @Id
	    private Long menuitemId;

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

	    private Integer cookingTime;

	    
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "RestaurantID")
	    private Restaurants restaurant;
	    
	    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
	    private Set<OrderDetails> orderDetailsSet = new HashSet<OrderDetails>();
	    
		@ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "CartID")
	    private Cart cart;
		
		@ManyToMany(mappedBy = "menuItems")
	    private Set<Orders> orders = new HashSet<>();

		public MenuItems() {
			super();
		}

		public MenuItems(Long menuitemId, String itemName, String description, String category, Double price,
				String availabilityTime, String specialDietaryInfo, String tasteInfo, String nutritionalInfo,
				Integer cookingTime) {
			super();
			this.menuitemId = menuitemId;
			this.itemName = itemName;
			this.description = description;
			this.category = category;
			this.price = price;
			this.availabilityTime = availabilityTime;
			this.specialDietaryInfo = specialDietaryInfo;
			this.tasteInfo = tasteInfo;
			this.nutritionalInfo = nutritionalInfo;
			this.cookingTime = cookingTime;
		}

		public Long getMenuitemId() {
			return menuitemId;
		}

		public void setMenuitemId(Long menuitemId) {
			this.menuitemId = menuitemId;
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

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
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

		public void setCookingTime(Integer cookingTime) {
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

		public Cart getCart() {
			return cart;
		}

		public void setCart(Cart cart) {
			this.cart = cart;
		}

		public Set<Orders> getOrders() {
			return orders;
		}

		public void setOrders(Set<Orders> orders) {
			this.orders = orders;
		}

		@Override
		public String toString() {
			return "MenuItems [menuitemId=" + menuitemId + ", itemName=" + itemName + ", description=" + description
					+ ", category=" + category + ", price=" + price + ", availabilityTime=" + availabilityTime
					+ ", specialDietaryInfo=" + specialDietaryInfo + ", tasteInfo=" + tasteInfo + ", nutritionalInfo="
					+ nutritionalInfo + ", cookingTime=" + cookingTime + ", restaurant=" + restaurant
					+ ", orderDetailsSet=" + orderDetailsSet + ", cart=" + cart + ", orders=" + orders + "]";
		}

		
		


}