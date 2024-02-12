package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Orders findById(int orderId);
	
	//@Query("SELECT o FROM Orders o JOIN FETCH o.orderDetails od JOIN FETCH od.menuItem WHERE o.customer.customerId = :customerId")
    List<Orders> findByCustomerCustomerId(long customerId);

}
