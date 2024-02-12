package com.hexaware.hotpot.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 * Author: Nipurna Bandi
 * 
 * Entity description: contains properties related to payment , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */


@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private int paymentId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    @JsonIgnore
    private Customers customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CartID")
    @JsonIgnore
    private Cart cart;

    private LocalDateTime paymentDate;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "^(upi|net banking|cod|credit card|debit card)$", message = "Invalid payment method")
    private String paymentMethod;

    @NotBlank(message = "Transaction ID is required")
    @Pattern(regexp = "\\d{10}", message = "Transaction ID must be 10 digits")
    private String transactionID;

	public Payment() {
		super();
	}

	public Payment(int paymentId, Customers customer, Cart cart, LocalDateTime paymentDate, BigDecimal amount,
			String paymentMethod, String transactionID) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
		this.cart = cart;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionID = transactionID;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", customer=" + customer + ", cart=" + cart + ", paymentDate="
				+ paymentDate + ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", transactionID="
				+ transactionID + "]";
	}

    
    
}
