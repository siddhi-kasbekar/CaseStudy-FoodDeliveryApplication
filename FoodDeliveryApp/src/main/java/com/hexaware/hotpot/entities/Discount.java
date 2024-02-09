package com.hexaware.hotpot.entities;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Discount {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RestaurantID")
    private Restaurants restaurant;

    @NotNull(message = "Discount percentage is required")
    @Column(name = "DiscountPercentage")
    private BigDecimal discountPercentage;

    @NotNull(message = "Start date is required")
    @Column(name = "StartDate")
    private Date startDate;

    @NotNull(message = "End date is required")
    @Column(name = "EndDate")
    private Date endDate;

	public Discount() {
		super();
	}

	public Discount(int discountId, Restaurants restaurant, BigDecimal discountPercentage, Date startDate,
			Date endDate) {
		super();
		this.discountId = discountId;
		this.restaurant = restaurant;
		this.discountPercentage = discountPercentage;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Discount [discountId=" + discountId + ", restaurant=" + restaurant + ", discountPercentage="
				+ discountPercentage + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
    
    

}
