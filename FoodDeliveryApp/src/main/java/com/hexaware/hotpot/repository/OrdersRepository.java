package com.hexaware.hotpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.hotpot.entities.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Orders findById(int orderId);

	// @Query("SELECT o FROM Orders o JOIN FETCH o.orderDetails od JOIN FETCH
	// od.menuItem WHERE o.customer.customerId = :customerId")
	List<Orders> findByCustomerCustomerId(long customerId);

	@Query(value = "SELECT o.*, c.customer_name AS customerName " 
	+ "FROM orders o "
			+ "JOIN customers c ON o.custid = c.customerid ", nativeQuery = true)
	List<Object[]> findAllOrdersWithCustomerInfo();
	
	
	@Query(value = "SELECT od.*, o.*, m.item_name AS menuName " +
            "FROM order_details od " +
            "JOIN orders o ON od.orderid = o.orderid " +
            "JOIN menu_items  m ON od.menuid = m.menu_itemid " +
            "WHERE o.custid = :customerId", nativeQuery = true)
	List<Object[]> findOrderDetailsAndOrdersByCustomerId(long customerId);
	


}
