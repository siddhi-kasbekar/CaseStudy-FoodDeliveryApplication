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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	private double total;

	@OneToOne
	@JoinColumn(name = "custid")
	private Customers customer;

	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonIgnore // Prevent infinite recursion
	private Set<Payment> paymentSet = new HashSet<>();
	


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



}
