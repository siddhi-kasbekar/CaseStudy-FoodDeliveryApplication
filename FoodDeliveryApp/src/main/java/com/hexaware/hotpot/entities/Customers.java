package com.hexaware.hotpot.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public final class Customers {
	
	@Id
	private long customerId;
	
	@NotBlank(message = "Customer name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Customer name should contain only alphabets")
	private String customerName;
	
	private String gender;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number")
	private String phone;
	
	 @NotBlank(message = "Username is required")
	 @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username should contain only alphanumeric characters and underscores")
	private String username;
	 
	 @NotBlank(message = "Password is required")
	 @Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	 
	private  String role;

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Orders> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}

	public Set<Payment> getPaymentSet() {
		return paymentSet;
	}

	public void setPaymentSet(Set<Payment> paymentSet) {
		this.paymentSet = paymentSet;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "aid")
	private DeliveryAddress address;
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	 private Set<Orders> orderSet= new HashSet<>();
	 
	 @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	    private Set<Payment> paymentSet = new HashSet<>(); 

	    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	    private Cart cart;

	public Customers() {
		super();
		this.cart = new Cart();
	}

	public Customers(long customerId, String customerName, String gender, String email, String phone, String username,
			String password, DeliveryAddress address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.address = address;
	}

	
	public long getCustId() {
		return customerId;
	}

	public void setCustId(long custId) {
		this.customerId = custId;
	}

	public String getCustName() {
		return customerName;
	}

	public void setCustName(String custName) {
		this.customerName = custName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}

	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customers [custId=" + customerId + ", custName=" + customerName + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", address=" + address
				+ "]";
	}
	
	
	
	
	
	

}
