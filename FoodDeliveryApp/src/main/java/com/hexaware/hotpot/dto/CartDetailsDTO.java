package com.hexaware.hotpot.dto;


public class CartDetailsDTO {

//    private long cartMenuItemId;
//    private long cartId;
    private long menuItemId;
    private int quantity;
    private double price;


    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CartDetailsDTO() {

        // Default constructor
    }

    // Constructor with parameters

    public CartDetailsDTO( long menuItemId, int quantity) {

//        this.cartMenuItemId = cartMenuItemId;
//        this.cartId = cartId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    // Getters and setters
//    public long getCartMenuItemId() {
//        return cartMenuItemId;
//    }
//
//    public void setCartMenuItemId(long cartMenuItemId) {
//        this.cartMenuItemId = cartMenuItemId;
//    }
//
//    public long getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(long cartId) {
//        this.cartId = cartId;
//    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public String toString() {
		return "CartDetailsDTO [menuItemId=" + menuItemId + ", quantity=" + quantity + ", price=" + price + "]";
	}


}
