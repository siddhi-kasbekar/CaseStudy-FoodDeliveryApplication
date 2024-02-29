package com.hexaware.hotpot.dto;
import java.math.BigDecimal;
import java.util.List;

public class PaymentDTO {

    // Payment details
    private double amount;
    private String paymentMethod;
    private String transactionID;
    private String cardNumber;
    private String expiryDate;
    private int cvv;
    private String cardHolder;
    private String status;
    private double totalCost;

  
	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	// Additional details
    private List<MenuItemsDTO> menuItems;

    // Constructors, getters, and setters...

    public PaymentDTO() {
        // Default constructor
    }

    // Constructor with all fields
    public PaymentDTO(double amount, String paymentMethod, String transactionID,
                             String cardNumber, String expiryDate, int cvv, String cardHolder,
                             List<MenuItemsDTO> menuItems, String status,double totalCost) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionID = transactionID;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardHolder = cardHolder;
        this.menuItems = menuItems;
        this.status=status;
        this.totalCost=totalCost;
    }

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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

	
	public List<MenuItemsDTO> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemsDTO> menuItems) {
		this.menuItems = menuItems;
	}

	  public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

  
}

