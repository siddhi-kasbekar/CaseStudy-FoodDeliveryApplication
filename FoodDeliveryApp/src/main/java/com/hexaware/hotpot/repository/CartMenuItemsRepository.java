package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.hotpot.entities.CartMenuItems;

public interface CartMenuItemsRepository  extends JpaRepository<CartMenuItems, Long>{

}
