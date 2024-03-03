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

	@Query(value = "SELECT o.*, c.customer_name AS customerName, CONCAT(da.house_no, ', ', da.area, ', ', da.landmark, ' ',da.city,' ', da.pincode) AS fullAddress, r.name, "
	        + "(SELECT GROUP_CONCAT(CONCAT(m.item_name, ' - ', od.quantity) SEPARATOR ', ') "
	        + " FROM order_details od "
	        + " JOIN menu_items m ON od.menuid = m.menu_itemid "
	        + " WHERE od.orderid = o.orderid) AS menuItems "
	        + "FROM orders o "
	        + "JOIN customers c ON o.custid = c.customerid "
	        + "JOIN delivery_address da ON c.aid = da.addressid "
	        + "JOIN administrator admin ON o.res_id = admin.restaurant_id "
	        + "JOIN restaurants r ON admin.restaurant_id = r.restaurantid "
	        + "WHERE admin.adminid = :adminId "
	        + "ORDER BY o.orderid DESC", nativeQuery = true)
	List<Object[]> findAllOrdersWithCustomerInfo( @Param("adminId") int adminId);
	
	
	@Query(value = "SELECT od.*, o.*, m.item_name AS menuName " +
            "FROM order_details od " +
            "JOIN orders o ON od.orderid = o.orderid " +
            "JOIN menu_items  m ON od.menuid = m.menu_itemid " +
            "WHERE o.custid = :customerId", nativeQuery = true)
	List<Object[]> findOrderDetailsAndOrdersByCustomerId(long customerId);
	


}
