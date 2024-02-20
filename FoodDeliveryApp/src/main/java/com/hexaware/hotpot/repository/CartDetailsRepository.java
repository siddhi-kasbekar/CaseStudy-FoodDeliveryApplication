package com.hexaware.hotpot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.CartDetails;

@Repository
public interface CartDetailsRepository  extends JpaRepository<CartDetails, Long>{
	
    List<CartDetails> findByCart_CartId(long cartId);
    
//    @Query("SELECT COALESCE(SUM(cd.total), 0) FROM CartDetails cd WHERE cd.cart.id = :cartId")
//    double calculateTotalByCartId(@Param("cartId") Long cartId);

    Optional<CartDetails> findByCart_CartIdAndMenuItem_MenuItemId(int cartId, long menuItemId);



}
