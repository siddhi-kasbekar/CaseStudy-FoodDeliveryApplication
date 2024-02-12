package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.CartDetails;

@Repository
public interface CartDetailsRepository  extends JpaRepository<CartDetails, Long>{
	
    List<CartDetails> findByCart_CartId(long cartId);



}
