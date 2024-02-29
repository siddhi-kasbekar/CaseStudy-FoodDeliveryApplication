package com.hexaware.hotpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.hotpot.dto.PaymentDTO;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.services.IPaymentService;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentRestController {
	
	@Autowired
	IPaymentService paymentService;
	

	 @PostMapping("processPayment/{customerId}")
	    @PreAuthorize("hasAuthority('customer')")
	    public String processPayment(@PathVariable long customerId, @RequestBody PaymentDTO paymentDTO) {
	        try {
	            paymentService.processPayment(customerId, paymentDTO);
	            return "payment successful";

	        } catch (CustomerNotFoundException e) {
	            return "customer not found";

	        } 
	    }

}
