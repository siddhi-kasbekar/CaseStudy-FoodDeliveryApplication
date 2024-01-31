package com.hexaware.hotpot.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Payment {
	
	@Id
    @Column(name = "PaymentID")
    private int paymentId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private Customers customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CartID")
    private Cart cart;

    @Column(name = "PaymentDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paymentDate;

    @NotNull(message = "Amount is required")
    @Column(name = "Amount")
    private BigDecimal amount;

    @NotBlank(message = "Payment method is required")
    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @NotBlank(message = "Transaction ID is required")
    @Column(name = "TransactionID")
    private String transactionID;

	public Payment() {
		super();
	}

	public Payment(int paymentId, Customers customer, Cart cart, Timestamp paymentDate, BigDecimal amount,
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

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
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
