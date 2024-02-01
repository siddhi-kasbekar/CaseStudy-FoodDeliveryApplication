package com.hexaware.hotpot.dto;

import java.math.BigDecimal;
import java.security.Timestamp;

import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentDTO {

	private int paymentId;

	private Timestamp paymentDate;

	private BigDecimal amount;

	private String paymentMethod;

	private String transactionID;

	public PaymentDTO() {
		super();
	}

	public PaymentDTO(int paymentId, Timestamp paymentDate, BigDecimal amount, String paymentMethod,
			String transactionID) {
		super();
		this.paymentId = paymentId;
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
	
	
}
