package com.hexaware.hotpot.dto;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdersDTO {

	private int orderId;

	private Timestamp orderDate;

	private BigDecimal totalCost;

	private String status;

	public OrdersDTO() {
		super();
	}

	public OrdersDTO(int orderId, Timestamp orderDate, BigDecimal totalCost, String status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
