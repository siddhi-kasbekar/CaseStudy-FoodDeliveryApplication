package com.hexaware.hotpot.dto;

import java.util.Set;

public class MenuItemsDTO {

	private Long menuitemId;
    private String itemName;
    private String description;
    private String category;
    private Double price;
    private String availabilityTime;
    private String specialDietaryInfo;
    private String tasteInfo;
    private String nutritionalInfo;
    private Integer cookingTime;
    private int restaurantId; 
    private Set<Integer> orderDetailsIds; 
    private int cartId;
    private Set<Integer> orderIds; 
  

    public MenuItemsDTO() {
    	super();
    }

    public MenuItemsDTO(Long menuitemId, String itemName, String description, String category, Double price,
            String availabilityTime, String specialDietaryInfo, String tasteInfo, String nutritionalInfo,
            Integer cookingTime, int restaurantId, Set<Integer> orderDetailsIds, int cartId, Set<Integer> orderIds) {
        
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
        this.restaurantId = restaurantId;
        this.orderDetailsIds = orderDetailsIds;
        this.cartId = cartId;
        this.orderIds = orderIds;
    }

    // Getter and Setter methods

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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Set<Integer> getOrderDetailsIds() {
        return orderDetailsIds;
    }

    public void setOrderDetailsIds(Set<Integer> orderDetailsIds) {
        this.orderDetailsIds = orderDetailsIds;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Set<Integer> getOrderIds() {
        return orderIds;
    }


	
	
	
}
