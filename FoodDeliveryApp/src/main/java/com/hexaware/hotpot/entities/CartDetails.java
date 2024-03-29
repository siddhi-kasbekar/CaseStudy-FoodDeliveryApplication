package com.hexaware.hotpot.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to cartMenuItems , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity


	public class CartDetails{


	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_details_id_sequence")
	    @SequenceGenerator(name = "cart_details_id_sequence", sequenceName = "CART_DETAILS_ID_SEQUENCE",initialValue = 201, allocationSize = 1)
	    @Column(name = "CartMenuItemID")
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
	    private double price;


		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public CartDetails() {
			super();
		}

		public CartDetails(Cart cart, MenuItems menuItems, int quantity) {

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