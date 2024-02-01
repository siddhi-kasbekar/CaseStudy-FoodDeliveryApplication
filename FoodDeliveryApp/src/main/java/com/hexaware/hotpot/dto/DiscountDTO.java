package com.hexaware.hotpot.dto;

import java.math.BigDecimal;
import java.sql.Date;



public class DiscountDTO {
	
	
    private int discountId;

    private BigDecimal discountPercentage;

    private Date startDate;

    private Date endDate;

	public DiscountDTO() {
		super();
	}

	public DiscountDTO(int discountId, BigDecimal discountPercentage, Date startDate, Date endDate) {
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
    
    

}
