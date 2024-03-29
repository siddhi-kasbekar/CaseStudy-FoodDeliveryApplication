package com.hexaware.hotpot.dto;


import java.time.LocalDate;



public class DiscountDTO {
	
	
    private int discountId;

    private int discountPercentage;

    private LocalDate startDate;

    private LocalDate endDate;

	public DiscountDTO() {
		super();
	}

	public DiscountDTO(int discountId, int discountPercentage, LocalDate startDate, LocalDate endDate) {
		super();
		this.discountId = discountId;
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

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
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
    
    

}
