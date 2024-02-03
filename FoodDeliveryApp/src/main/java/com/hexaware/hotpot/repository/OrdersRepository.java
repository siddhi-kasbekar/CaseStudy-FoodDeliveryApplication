package com.hexaware.hotpot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Orders findById(int orderId);

}
