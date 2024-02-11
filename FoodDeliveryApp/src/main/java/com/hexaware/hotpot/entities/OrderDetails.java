package com.hexaware.hotpot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

/*
 * Author: Nipurna Bandi
 * 
 * Entity description: contains properties related to orderDetails , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */


@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    private int orderDetailId;

	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderID")
    private Orders order;

	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MenuID")
    private MenuItems menuItem;

    @NotNull(message = "quantity is required")
    private int quantity;

	public OrderDetails() {
		super();
	}

	public OrderDetails(int orderDetailId, Orders order, MenuItems menuItem, int quantity) {
		super();
		this.orderDetailId = orderDetailId;
		this.order = order;
		this.menuItem = menuItem;
		this.quantity = quantity;
	}

	
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public MenuItems getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItems menuItem) {
		this.menuItem = menuItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", order=" + order + ", menuItem=" + menuItem
				+ ", quantity=" + quantity + "]";
	}

    
    
}
