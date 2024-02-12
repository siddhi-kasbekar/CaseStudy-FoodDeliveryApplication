package com.hexaware.hotpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.PaymentDTO;
import com.hexaware.hotpot.entities.Payment;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.services.IPaymentService;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentRestController {
	
	@Autowired
	IPaymentService paymentService;
	
	@PostMapping("/process-payment/{customerId}/{cartId}")
    @PreAuthorize("hasAuthority('customer')")
	public Payment processPayment(@PathVariable long customerId , @PathVariable int cartId , @RequestBody  PaymentDTO paymentDTO) throws CustomerNotFoundException{
		return paymentService.processPayment(customerId,cartId,paymentDTO);
		
	}

}
