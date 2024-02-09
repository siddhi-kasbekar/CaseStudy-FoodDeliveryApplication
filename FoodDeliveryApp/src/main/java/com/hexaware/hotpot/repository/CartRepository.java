package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	List<Cart> findByCustomerCustomerId(long custId);
	
	


}
