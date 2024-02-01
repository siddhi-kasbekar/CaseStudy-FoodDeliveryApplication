package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
