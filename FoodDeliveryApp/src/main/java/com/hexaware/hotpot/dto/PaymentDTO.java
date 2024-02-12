package com.hexaware.hotpot.dto;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;

public class PaymentDTO {

	private int paymentId;
    private long customerId; 
    private int cartId; 
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private String paymentMethod;
    private String transactionID;

    public PaymentDTO() {
    	super();
    }

    public PaymentDTO(int paymentId, long customerId, int cartId, LocalDateTime paymentDate, BigDecimal amount,
            String paymentMethod, String transactionID) {
       
    	super();
    	this.paymentId = paymentId;
        this.customerId = customerId;
        this.cartId = cartId;
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
	
	
}
