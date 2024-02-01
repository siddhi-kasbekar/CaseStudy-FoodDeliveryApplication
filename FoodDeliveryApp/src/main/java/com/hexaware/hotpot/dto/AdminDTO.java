package com.hexaware.hotpot.dto;



public class AdminDTO {
	
    private int adminId;
   
    private String userName;
    
    private String password;

    private String name;

    private String email;

	public AdminDTO() {
		super();
	}

	public AdminDTO(int adminId, String userName, String password, String name, String email) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    

}
