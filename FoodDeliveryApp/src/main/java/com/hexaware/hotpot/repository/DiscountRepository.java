package com.hexaware.hotpot.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Optional<Discount> findFirstByOrderByStartDateDesc();
    
    Optional<Discount> findByStartDateBeforeAndEndDateAfter(LocalDate currentDateStart, LocalDate currentDateEnd);



}
