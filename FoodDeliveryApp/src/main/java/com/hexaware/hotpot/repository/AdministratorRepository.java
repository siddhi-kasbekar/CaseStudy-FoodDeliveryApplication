package com.hexaware.hotpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}