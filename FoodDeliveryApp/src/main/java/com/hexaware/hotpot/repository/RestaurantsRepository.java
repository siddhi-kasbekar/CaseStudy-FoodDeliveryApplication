package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Integer> {

	


	List<Restaurants> findByLocation(String location);

}
