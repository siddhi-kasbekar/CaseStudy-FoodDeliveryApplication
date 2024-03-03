package com.hexaware.hotpot.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.hotpot.dto.PaymentDTO;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.Payment;
import com.hexaware.hotpot.exception.CustomerNotFoundException;
import com.hexaware.hotpot.exception.RestaurantNotFoundException;
import com.hexaware.hotpot.repository.CustomersRepository;
import com.hexaware.hotpot.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService {
	
	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public void processPayment(long customerId, PaymentDTO paymentDTO) throws CustomerNotFoundException {


		Customers customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setTransactionID(generateRandomTransactionID()); // Set a random transaction ID
        payment.setCardNumber(paymentDTO.getCardNumber());
        payment.setExpiryDate(paymentDTO.getExpiryDate());
        payment.setCvv(paymentDTO.getCvv());
        payment.setCardHolder(paymentDTO.getCardHolder());
        payment.setStatus("success");
        

        Payment processedPayment = paymentRepo.save(payment);


        try {
            orderService.placeOrder(customerId, paymentDTO.getMenuItems(), paymentDTO.getTotalCost());



        } catch (RestaurantNotFoundException | CustomerNotFoundException e) {
            
            e.printStackTrace();
        }
	}
	
	private String generateRandomTransactionID() {
	    Random random = new Random();
	    int transactionIDLength = 10;
	    StringBuilder sb = new StringBuilder(transactionIDLength);
	    for (int i = 0; i < transactionIDLength; i++) {
	        sb.append(random.nextInt(10));
	    }
	    return sb.toString();
	}
}
	


