package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cart {
	@Id
	private int cartId;
	
	@NotNull(message = "Price is required")
	private double price;
	
	@NotNull(message = "Quantity is required")
	private int quantity;
	
	private double total;
	
	
	 @OneToOne
	    @JoinColumn(name = "custid")
	    private Customers customer;


	 @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	    private Set<MenuItems> menuItemSet =  new HashSet<MenuItems>();;

//	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//	    private Set<Orders> orderSet = new HashSet<Orders>();
	    
	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	    private Set<Payment> paymentSet = new HashSet<Payment>();

		public Cart() {
			super();
		}

		public Cart(int cartId, double price, int quantity, double total, Customers customer) {
			super();
			this.cartId = cartId;
			this.price = price;
			this.quantity = quantity;
			this.total = total;
			this.customer = customer;
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

		public Customers getCustomer() {
			return customer;
		}

		public void setCustomer(Customers customer) {
			this.customer = customer;
		}

		public Set<MenuItems> getMenuItemSet() {
			return menuItemSet;
		}

		public void setMenuItemSet(Set<MenuItems> menuItemSet) {
			this.menuItemSet = menuItemSet;
		}
//
//		public Set<Orders> getOrderSet() {
//			return orderSet;
//		}
//
//		public void setOrderSet(Set<Orders> orderSet) {
//			this.orderSet = orderSet;
//		}

		public Set<Payment> getPaymentSet() {
			return paymentSet;
		}

		public void setPaymentSet(Set<Payment> paymentSet) {
			this.paymentSet = paymentSet;
		}
	    
	    
	    
	    
	 
    
    

}
