package com.hexaware.hotpot.dto;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public class OrdersDTO {

	private int orderId;
	private LocalDateTime orderDate;
	private BigDecimal totalCost;
	private String status;
	private int cartId;
	private int customerId;
	private int restaurantId;
	private Set<Integer> menuItemIds;

	public OrdersDTO() {
		super();
	}

	public OrdersDTO(int orderId, LocalDateTime orderDate, BigDecimal totalCost, String status, int cartId, int customerId,
			int restaurantId, Set<Integer> menuItemIds) {
		
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
		this.cartId = cartId;
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

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Set<Integer> getMenuItemIds() {
		return menuItemIds;
	}

	public void setMenuItemIds(Set<Integer> menuItemIds) {
		this.menuItemIds = menuItemIds;
	}

}
