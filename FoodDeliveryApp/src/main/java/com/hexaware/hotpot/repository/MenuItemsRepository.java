package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {

	List<MenuItems> findByCategory(String category);

	List<MenuItems> findByItemNameContainingIgnoreCase(String keyword);

	List<MenuItems> findByMenuItemsCategory(String category);

	List<MenuItems> findByMenuItemsRestaurantId(Long restaurantId);

}
