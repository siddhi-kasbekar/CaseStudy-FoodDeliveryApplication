package com.hexaware.hotpot.dto;

public class CartMenuItemsDTO {
    private long cartMenuItemId;
    private long cartId;
    private long menuItemId;
    private int quantity;

    public CartMenuItemsDTO() {
        // Default constructor
    }

    // Constructor with parameters
    public CartMenuItemsDTO(long cartMenuItemId, long cartId, long menuItemId, int quantity) {
        this.cartMenuItemId = cartMenuItemId;
        this.cartId = cartId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    // Getters and setters
    public long getCartMenuItemId() {
        return cartMenuItemId;
    }

    public void setCartMenuItemId(long cartMenuItemId) {
        this.cartMenuItemId = cartMenuItemId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

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
}
