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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Author: Nipurna Bandi
 * 
 * Entity description: contains properties related to orders , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private int orderId;

	@Column(name = "OrderDate")
	private LocalDateTime orderDate; // format for json, "2024-02-04T10:30:00"

	@NotNull(message = "Total cost is required")
	@Column(name = "TotalCost", nullable = false)
	private double totalCost;

	@NotBlank(message = "Status is required")
	@Column(name = "Status")
	private String status;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "custID")
	private Customers customer;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resId")
	private Restaurants restaurant;

	public Orders() {
		super();
	}

	public Orders(int orderId, LocalDateTime orderDate, double totalCost, String status, Customers customer,
			Restaurants restaurant) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
		this.customer = customer;
		this.restaurant = restaurant;

	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", totalCost=" + totalCost + ", status="
				+ status + ", customer=" + customer + ", restaurant=" + restaurant + "]";
	}

}
