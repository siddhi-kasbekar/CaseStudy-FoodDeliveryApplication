package com.hexaware.hotpot.dto;

import java.time.LocalDateTime;

public class OrderHistoryDTO {
	
	private long orderDetailId;
    private int quantity;
    private long menuId;
    private long orderId;
    private LocalDateTime orderDate;
    private String status;
    private double totalCost;
    private int customerId;
    private Long restaurantId;
    private String menuName;
    
    
    
    
    public OrderHistoryDTO() {
		super();
	}
    
    
    
    
	public OrderHistoryDTO(long orderDetailId, int quantity, long menuId, long orderId, LocalDateTime orderDate,
			String status, double totalCost, int customerId, Long restaurantId, String menuName) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.menuId = menuId;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.totalCost = totalCost;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.menuName = menuName;
	}




	public long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	

}
