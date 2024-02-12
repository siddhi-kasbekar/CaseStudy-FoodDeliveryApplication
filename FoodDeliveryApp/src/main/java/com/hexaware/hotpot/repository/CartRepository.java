package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Cart;
import com.hexaware.hotpot.entities.Customers;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
//	List<Cart> findByCustomerCustomerId(long custId);
//	
//    Cart findByCustomer(Customers customer);
	
    Cart findByCustomerId(long customerId);


	


}
