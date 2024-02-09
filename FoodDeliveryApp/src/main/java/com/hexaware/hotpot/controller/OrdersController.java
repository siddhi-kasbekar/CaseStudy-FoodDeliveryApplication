package com.hexaware.hotpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.OrdersDTO;
import com.hexaware.hotpot.entities.Orders;
import com.hexaware.hotpot.services.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrdersController {

	@Autowired
	IOrderService service;
	
	@PostMapping("/placeOrder")
    @PreAuthorize("hasAuthority('customer')")
	public void placeOrder(@Valid @RequestBody OrdersDTO orderDTO,@RequestParam int cartId,@RequestParam long customerId, @RequestParam List<Long> menuItemIds) {
		service.placeOrder(orderDTO, cartId, customerId, menuItemIds);
		
		
	}

	@GetMapping("/getById/{orderId}")
    @PreAuthorize("hasAuthority('customer')")
	public Orders getOrderById(@PathVariable int orderId) {
		return service.getOrderById(orderId);
	}

	@PutMapping("/update/{orderId}/{status}")
    @PreAuthorize("hasAuthority('customer')")
	public void updateOrderStatus(@PathVariable int orderId,@PathVariable String status) {
		service.updateOrderStatus(orderId, status);
		
	}
	
	@GetMapping("/viewHistory/{customerId}")
    @PreAuthorize("hasAuthority('customer')")
	public List<Orders> viewOrderHistory(@PathVariable int customerId){
		return service.viewOrderHistory(customerId);
	}
}
