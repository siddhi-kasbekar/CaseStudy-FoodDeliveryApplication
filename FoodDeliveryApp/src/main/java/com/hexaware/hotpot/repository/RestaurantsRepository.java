package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Restaurants;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Integer> {

	


	List<Restaurants> findByLocation(String location);


	List<Restaurants> findByNameContainingIgnoreCase(String keyword);


}
