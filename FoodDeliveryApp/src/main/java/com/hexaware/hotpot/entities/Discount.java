package com.hexaware.hotpot.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to Discount , getter and setters , 
 * constructors and relevant validations and mappings
 *
 */

@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_id_sequence")
	@SequenceGenerator(name = "discount_id_sequence", sequenceName = "DISCOUNT_ID_SEQUENCE",initialValue = 301, allocationSize = 1)
	@Column(name = "DiscountID")
	private int discountId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RestaurantID")
	private Restaurants restaurant;

	@NotNull(message = "Discount percentage is required")
	@Column(name = "DiscountPercentage")
	private BigDecimal discountPercentage;

	@NotNull(message = "Start date is required")
	@Column(name = "StartDate")
	private LocalDate startDate;

	@NotNull(message = "End date is required")
	@Column(name = "EndDate")
	private LocalDate endDate;

	public Discount() {
		super();
	}

	public Discount(int discountId, Restaurants restaurant, BigDecimal discountPercentage, LocalDate startDate,
			LocalDate endDate) {
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Discount [discountId=" + discountId + ", restaurant=" + restaurant + ", discountPercentage="
				+ discountPercentage + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
