package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	List<Cart> findByCustomer_CustId(long custId);
	
	@Query("SELECT c FROM Cart c JOIN c.menuItemSet mi WHERE c.customer.custId = :custId AND mi.menuitem_id = :menuitem_id")
    Cart findByCustomer_CustIdAndMenuItemSet_Menuitem_id(
        @Param("custId") long custId,
        @Param("menuitem_id") long menuitem_id
    );
	
	//Cart findByCustomer_CustIdAndMenuItemSet_Menuitem_id(long custId, long menuitem_id);


}
