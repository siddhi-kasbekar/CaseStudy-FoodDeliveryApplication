package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	
	
	private double total;
	
	
	 @OneToOne
	    @JoinColumn(name = "custid")
	    private Customers customer;


	 @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<CartMenuItems> cartMenuItems = new HashSet<CartMenuItems>();

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	    @JsonIgnore // Prevent infinite recursion
	    private Set<Payment> paymentSet = new HashSet<Payment>();
	    

		public Cart() {
			super();
		}

		public Cart(int cartId, double total, Customers customer) {
			super();
			this.cartId = cartId;
			
			this.total = total;
			this.customer = customer;
		}

		public int getCartId() {
			return cartId;
		}

		public void setCartId(int cartId) {
			this.cartId = cartId;
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

		


		public Set<Payment> getPaymentSet() {
			return paymentSet;
		}

		public void setPaymentSet(Set<Payment> paymentSet) {
			this.paymentSet = paymentSet;
		}

	

		public Set<CartMenuItems> getCartMenuItems() {
			return cartMenuItems;
		}

		public void setCartMenuItems(Set<CartMenuItems> cartMenuItems) {
			this.cartMenuItems = cartMenuItems;
		}

		

		public void addCartItem(CartMenuItems cartItem) {
	        cartMenuItems.add(cartItem);
	        cartItem.setCart(this);
	    }

	    public void removeCartItem(CartMenuItems cartItem) {
	        cartMenuItems.remove(cartItem);
	        cartItem.setCart(null);
	    }

	    
	    
	    
	    
	 
    
    


}
