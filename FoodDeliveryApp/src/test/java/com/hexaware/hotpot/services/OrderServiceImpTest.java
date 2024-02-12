package com.hexaware.hotpot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.exception.OrderNotFoundException;

@SpringBootTest
class OrderServiceImpTest {

	@Autowired
	IOrderService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

//	@Test
//	void testPlaceOrder() {
//		OrdersDTO orderDTO=new OrdersDTO();
//		orderDTO.setOrderDate(null);
//		orderDTO.setTotalCost(900.0);
//		orderDTO.setStatus("pending");
//		orderDTO.setCustomerId(null);
//		orderDTO.setRestaurantId(null);
//		
//		Orders order=service.placeOrder(orderDTO);
//		 assertNotNull(order);
//		
//	}

	@Test
	void testGetOrderById() throws OrderNotFoundException {
		Orders order=service.getOrderById(1);
		assertEquals(600,order.getTotalCost());
	}

	@Test
	void testUpdateOrderStatus() throws OrderNotFoundException {
		String status="paid";
		String order=service.updateOrderStatus(1, status);
		assertEquals("order updated",order);
	}
	
	@Test
	void testViewOrderHistory() {
		List<Orders> orders =service.viewOrderHistory(1);
		boolean flag=orders.isEmpty();
		assertTrue(flag);
		
	}

}
