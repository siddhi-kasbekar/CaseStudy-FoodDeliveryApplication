package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.hotpot.entities.MenuCategory;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {

	List<MenuCategory> findByRestaurantRestaurantId(int restaurantId);
}
