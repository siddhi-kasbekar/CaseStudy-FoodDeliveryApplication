package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.CartMenuItems;

@Repository
public interface CartMenuitemsRepository  extends JpaRepository<CartMenuItems, Long>{

}
