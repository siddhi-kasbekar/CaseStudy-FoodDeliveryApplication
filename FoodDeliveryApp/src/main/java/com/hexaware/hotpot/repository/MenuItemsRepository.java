package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {

}
