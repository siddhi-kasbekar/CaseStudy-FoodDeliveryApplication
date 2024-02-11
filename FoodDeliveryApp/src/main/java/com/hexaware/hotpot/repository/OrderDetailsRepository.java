package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.OrderDetails;
import com.hexaware.hotpot.entities.Orders;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	
	OrderDetails  findByOrder(Orders order);

}
