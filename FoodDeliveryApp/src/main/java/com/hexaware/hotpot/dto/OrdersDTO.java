package com.hexaware.hotpot.dto;

import java.time.LocalDateTime;
import java.util.Set;


import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Restaurants;

public class OrdersDTO {

	private int orderId;
	private LocalDateTime orderDate;
	private double totalCost;
	private String status;
	private Customers customerId;
	private Restaurants restaurantId;
	private Set<Integer> menuItemIds;

	public OrdersDTO() {
		super();
	}

	public OrdersDTO(int orderId, LocalDateTime orderDate, double totalCost, String status, Customers customerId,
			Restaurants restaurantId, Set<Integer> menuItemIds) {
		
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
		
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.menuItemIds = menuItemIds;
	}

	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Customers getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}

	public Restaurants getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Restaurants restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Set<Integer> getMenuItemIds() {
		return menuItemIds;
	}

	public void setMenuItemIds(Set<Integer> menuItemIds) {
		this.menuItemIds = menuItemIds;
	}

}
