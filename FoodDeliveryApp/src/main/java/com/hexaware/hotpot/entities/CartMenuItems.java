package com.hexaware.hotpot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name = "cart_menu_items")

	public class CartMenuItems{

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long cartMenuItemId;

	    
	    @ManyToOne
	    @JoinColumn(name = "cart_id")
	    @JsonIgnore // Prevent infinite recursion
	    private Cart cart;

	    
	    @ManyToOne
	    @JoinColumn(name = "menuitem_id")
	    @JsonIgnore // Prevent infinite recursion
	    private MenuItems menuItem;

	    private int quantity;

		public CartMenuItems() {
			super();
		}

		public CartMenuItems(Cart cart, MenuItems menuItems, int quantity) {
			super();
		
			this.cart = cart;
			this.menuItem = menuItems;
			this.quantity = quantity;
		}

		public Long getId() {
			return cartMenuItemId;
		}

		public void setId(Long cartMenuItemId) {
			this.cartMenuItemId = cartMenuItemId;
		}

		public Cart getCart() {
			return cart;
		}

		public void setCart(Cart cart) {
			this.cart = cart;
		}

		public MenuItems getMenuItems() {
			return menuItem;
		}

		public void setMenuItems(MenuItems menuItems) {
			this.menuItem = menuItems;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	    
	    


	}


