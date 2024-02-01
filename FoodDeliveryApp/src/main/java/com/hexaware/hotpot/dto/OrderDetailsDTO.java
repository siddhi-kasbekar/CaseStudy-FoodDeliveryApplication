package com.hexaware.hotpot.dto;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Orders;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class OrderDetailsDTO {

	 private int orderDetailId;

	    private int quantity;

		public OrderDetailsDTO() {
			super();
		}

		public OrderDetailsDTO(int orderDetailId, int quantity) {
			super();
			this.orderDetailId = orderDetailId;
			this.quantity = quantity;
		}

		public int getOrderDetailId() {
			return orderDetailId;
		}

		public void setOrderDetailId(int orderDetailId) {
			this.orderDetailId = orderDetailId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

    
}
