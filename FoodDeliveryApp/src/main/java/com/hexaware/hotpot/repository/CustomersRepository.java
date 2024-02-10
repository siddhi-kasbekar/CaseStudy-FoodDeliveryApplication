package com.hexaware.hotpot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByUsername(String username);


}
