package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_id;
	
	
	private double total;
	
	
	 @OneToOne
	    @JoinColumn(name = "custid")
	    private Customers customer;


	 @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<CartMenuItems> cartMenuItems = new HashSet<>();

	    
	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	    private Set<Payment> paymentSet = new HashSet<Payment>();
	    
	    
	    

		public Cart() {
			super();
		}

		public Cart(int cart_id, double total, Customers customer) {
			super();
			this.cart_id = cart_id;
			
			this.total = total;
			this.customer = customer;
		}

		public int getCartId() {
			return cart_id;
		}

		public void setCartId(int cart_id) {
			this.cart_id = cart_id;
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
