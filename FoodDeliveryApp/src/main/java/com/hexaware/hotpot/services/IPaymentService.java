package com.hexaware.hotpot.services;

import com.hexaware.hotpot.dto.PaymentDTO;
import com.hexaware.hotpot.entities.Payment;
import com.hexaware.hotpot.exception.CustomerNotFoundException;

public interface IPaymentService {
	
	
//	public Payment processPayment(long customerId, int cartId,PaymentDTO paymentDTO) throws CustomerNotFoundException;

    public void processPayment(long customerId, PaymentDTO paymentDTO) throws CustomerNotFoundException ;

	

}
