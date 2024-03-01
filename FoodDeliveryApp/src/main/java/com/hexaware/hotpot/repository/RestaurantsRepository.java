package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Restaurants;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Integer> {

	


	List<Restaurants> findByLocation(String location);


	List<Restaurants> findByNameContainingIgnoreCase(String keyword);
	
	@Query(value = "SELECT r.* FROM Restaurants r JOIN Administrator a ON r.restaurantid = a.restaurant_id WHERE a.adminid = :adminId", nativeQuery = true)
    List<Restaurants> findRestaurantsByAdminId( int adminId);


}
