package com.hexaware.hotpot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Administrator;
import com.hexaware.hotpot.entities.MenuItems;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Optional<Administrator> findByUsername(String username);
    
    List<Administrator> findByRole(String role);
    
    @Query("SELECT m FROM MenuItems m WHERE m.restaurant = (SELECT a.restaurant FROM Administrator a WHERE a.adminId = :adminId)")
    List<MenuItems> findMenuItemsByAdminId(int adminId);



}
