package com.hexaware.hotpot.dto;



public class CustomersDTO {

	private int custId;

	private String custName;
	
	private String gender;
	
	private String email;
	
	private String phone;
	
	 private String username;
	 
	private String password;

	public CustomersDTO() {
		super();
	}

	public CustomersDTO(int custId, String custName, String gender, String email, String phone, String username,
			String password) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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
	
	
}
