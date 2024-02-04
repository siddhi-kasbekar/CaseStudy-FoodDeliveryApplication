package com.hexaware.hotpot.dto;



public class CartDTO {
	
	private int cartId;
	
	private double price;
	
	private int quantity;
	
	private double total;
	
	private long menuId;
	
	private long customerId;
	

	public CartDTO() {
		super();
	}



	public CartDTO(int cartId, double price, int quantity, double total) {
		super();
		this.cartId = cartId;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}



	public int getCartId() {
		return cartId;
	}



	public void setCartId(int cartId) {
		this.cartId = cartId;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Long getMenuId() {
		return menuId;
	}



	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}



	public long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
	
	

}
