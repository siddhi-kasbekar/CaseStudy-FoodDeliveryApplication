package com.hexaware.hotpot.dto;

public class OrderDetailsDTO {

	private int orderDetailId;
	private int orderId;
	private int menuItemId;
	private int quantity;

	public OrderDetailsDTO() {
		super();
	}

	public OrderDetailsDTO(int orderDetailId, int orderId, int menuItemId, int quantity) {
		
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
	}

	

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
