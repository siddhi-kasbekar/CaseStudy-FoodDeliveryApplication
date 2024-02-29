package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to cart , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_sequence")
    @SequenceGenerator(name = "cart_id_sequence", sequenceName = "CART_ID_SEQUENCE",initialValue = 101, allocationSize = 1)
	@Column(name = "CartID")
	private int cartId;

	private double total;
	
	@Column(name = "custid")
	private Long customerId;

//	@OneToOne
//	@JoinColumn(name = "custid")
//    @JsonManagedReference // Manages serialization of the relationship
//	private Customers customer;

	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

//	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("cart")
//	private Set<Payment> paymentSet = new HashSet<>();
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent infinite recursion
    private Set<CartDetails> cartItems = new HashSet<>();

	


	public Set<CartDetails> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartDetails> cartItems) {
		this.cartItems = cartItems;
	}

	public Cart() {
		super();
	}

	public Cart(int cartId, double total, Customers customer) {
		super();
		this.cartId = cartId;

		this.total = total;
//		this.customer = customer;
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

//	public Customers getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customers customer) {
//		this.customer = customer;
//	}

//	public Set<Payment> getPaymentSet() {
//		return paymentSet;
//	}
//
//	public void setPaymentSet(Set<Payment> paymentSet) {
//		this.paymentSet = paymentSet;
//	}



}
