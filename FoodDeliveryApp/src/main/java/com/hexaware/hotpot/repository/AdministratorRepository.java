package com.hexaware.hotpot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.Customers;
import com.hexaware.hotpot.entities.MenuCategory;
import com.hexaware.hotpot.entities.MenuItems;
import com.hexaware.hotpot.entities.Restaurants;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByUsername(String username);
    
    List<Administrator> findByRole(String role);
    
    @Query("SELECT m FROM MenuItems m WHERE m.restaurant = (SELECT a.restaurant FROM Administrator a WHERE a.adminId = :adminId)")
    List<MenuItems> findMenuItemsByAdminId(int adminId);

    @Query("SELECT c FROM MenuCategory c WHERE c.restaurant = (SELECT a.restaurant FROM Administrator a WHERE a.adminId = :adminId)")
    List<MenuCategory> findCategoryByAdminId(int adminId);

	Administrator findByAdminId(long adminId);

	Administrator getByAdminId(long adminId);

	

}
