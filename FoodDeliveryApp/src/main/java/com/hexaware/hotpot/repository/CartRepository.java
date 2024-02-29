package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	

	
    Cart findByCustomerId(long customerId);
    
    @Query(nativeQuery = true,
            value = "SELECT c.cartid, c.total, cd.menuitem_id,m.item_name, cd.price, cd.quantity, cd.price * cd.quantity AS individualTotal " +
                    "FROM cart c " +
                    "JOIN cart_details cd ON c.cartid = cd.cart_id " +
                    "JOIN menu_items m ON cd.menuitem_id = m.menu_itemid " +
                    "WHERE c.custid = :customerId")
     List<Object[]> getCartDetailsByCustomerId(@Param("customerId") Long customerId);
    




	


}
