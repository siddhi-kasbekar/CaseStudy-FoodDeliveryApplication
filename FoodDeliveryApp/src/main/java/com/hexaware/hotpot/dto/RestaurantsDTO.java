package com.hexaware.hotpot.dto;

import java.util.Set;

public class RestaurantsDTO {

	private int restaurantId;
    private String name;
    private String location;
    private String contactNumber;
    private Double rating;
    private Set<Integer> menuItemIds; 
    private Set<Integer> orderIds; 
    private Set<Integer> discountIds;

    public RestaurantsDTO() {
    	super();
    }

    public RestaurantsDTO(int restaurantId, String name, String location, String contactNumber, Double rating,
            Set<Integer> menuItemIds, Set<Integer> orderIds, Set<Integer> discountIds) {
        
    	super();
    	this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.rating = rating;
        this.menuItemIds = menuItemIds;
        this.orderIds = orderIds;
        this.discountIds = discountIds;
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

    public Set<Integer> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(Set<Integer> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }

    public Set<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public Set<Integer> getDiscountIds() {
        return discountIds;
    }

    public void setDiscountIds(Set<Integer> discountIds) {
        this.discountIds = discountIds;
    }
	

}
