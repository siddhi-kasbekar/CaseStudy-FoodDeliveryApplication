package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

}
