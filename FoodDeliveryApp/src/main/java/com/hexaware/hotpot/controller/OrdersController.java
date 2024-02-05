package com.hexaware.hotpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.services.IOrderService;

@RestController
@RequestMapping("/order")
public class OrdersController {

	@Autowired
	IOrderService service;
	
	@PostMapping("/placeorder")
	public String placeOrder(@RequestBody OrdersDTO orderDTO) {
		service.placeOrder(orderDTO);
		
		return "order placed";
	}

	@GetMapping("/getbyid/{orderId}")
	public Orders getOrderById(@PathVariable int orderId) {
		return service.getOrderById(orderId);
	}

	@PutMapping("/update/{orderId}/{status}")
	public String updateOrderStatus(@PathVariable int orderId,@PathVariable String status) {
		service.updateOrderStatus(orderId, status);
		return "status updated successfully";
	}
}
