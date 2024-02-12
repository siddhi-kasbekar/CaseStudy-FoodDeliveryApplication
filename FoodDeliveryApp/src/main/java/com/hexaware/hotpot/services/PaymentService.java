package com.hexaware.hotpot.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.PaymentDTO;
import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Payment;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.repository.CartRepository;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {
	
	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public Payment processPayment(long customerId, int cartId, PaymentDTO paymentDTO) throws CustomerNotFoundException {
		 // Retrieve customer and cart entities from their IDs
	    Customers customer = customerRepo.findById(customerId)
	            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

	    Cart cart = cartRepo.findById(cartId)
	            .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

	    // Create a payment entity
	    Payment payment = new Payment();
	    payment.setCustomer(customer);
	    payment.setCart(cart);
	    payment.setPaymentDate(LocalDateTime.now());
	    payment.setAmount(paymentDTO.getAmount());
	    payment.setPaymentMethod(paymentDTO.getPaymentMethod());
	    payment.setTransactionID(paymentDTO.getTransactionID());

	    return paymentRepo.save(payment); // Save the payment details
	    
	}
}


