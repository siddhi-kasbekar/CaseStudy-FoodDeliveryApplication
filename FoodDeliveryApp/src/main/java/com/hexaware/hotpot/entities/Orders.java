package com.hexaware.hotpot.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Orders {
	
	@Id
    @Column(name = "OrderID")
	
    private int orderId;
	
	@Column(name = "OrderDate",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

	@NotNull(message = "Total cost is required")
    @Column(name = "TotalCost", nullable = false)
    private BigDecimal totalCost;

	@NotBlank(message = "Status is required")
    @Column(name = "Status")
    private String status;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "CartID")
//    private Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "custID")
    private Customers customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resId")
    private Restaurants restaurant;
	
	@ManyToMany(cascade = CascadeType.ALL)
    private Set<MenuItems> menuItems = new HashSet<>();

	public Orders() {
		super();
	}

	public Orders(int orderId, LocalDateTime orderDate, BigDecimal totalCost, String status,  Customers customer,
			Restaurants restaurant, Set<MenuItems> menuItems) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
		//this.cart = cart;
		this.customer = customer;
		this.restaurant = restaurant;
		this.menuItems = menuItems;
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

//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

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

	public Set<MenuItems> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItems> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", totalCost=" + totalCost + ", status="
				+ status +  ", customer=" + customer + ", restaurant=" + restaurant + ", menuItems="
				+ menuItems + "]";
	}
	
	
	

}
