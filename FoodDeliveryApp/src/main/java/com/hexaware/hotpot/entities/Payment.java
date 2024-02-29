package com.hexaware.hotpot.entities;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
	@SequenceGenerator(name = "payment_id_sequence", sequenceName = "PAYMENT_ID_SEQUENCE",initialValue = 1, allocationSize = 1)
	@Column(name = "PaymentID")
	private int paymentId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customers customer;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "CartID")
//	@JsonIgnore
//	private Cart cart;

	private LocalDateTime paymentDate;

	@NotNull(message = "Amount is required")
	private double amount;

	@NotBlank(message = "Payment method is required")
	private String paymentMethod;

	@NotBlank(message = "Transaction ID is required")
	@Pattern(regexp = "\\d{10}", message = "Transaction ID must be 10 digits")
	private String transactionID;
	
	@NotBlank
    @Pattern(regexp = "\\b\\d{16}\\b", message = "Card number must be a 16-digit numeric value")
	private String cardNumber;
	
	@NotBlank
	private String expiryDate;
	
	 @Min(value = 100, message = "CVV must be a 3-digit numeric value")
	    @Max(value = 999, message = "CVV must be a 3-digit numeric value")
	private int cvv;
	
    
	@NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Customer name should contain only alphabets")
	private String cardHolder;
	
	
	private String status;
	
	
	
	

	public Payment() {
		super();
	}

	public Payment(int paymentId, Customers customer, Cart cart, LocalDateTime paymentDate, double amount,
			String paymentMethod, String transactionID) {
		super();
		this.paymentId = paymentId;
		this.customer = customer;
//		this.cart = cart;
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

//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}


	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", customer=" + customer +  ", paymentDate="
				+ paymentDate + ", amount=" + amount + ", paymentMethod=" + paymentMethod + ", transactionID="
				+ transactionID + "]";
	}

}
