package com.hexaware.hotpot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to administrator , getter and setters , 
 * constructors and relevant validations and mappings
*/


@Entity
@Table(name = "Administrator")
public class Administrator {

    @Id
    @Column(name = "AdminID")
    private int adminId;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username must contain only letters, numbers, underscores, and hyphens")
    @Column(name = "UserName", nullable = false, unique = true)
    private String userName;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name")
    private String name;

    @Email(message = "Invalid email format")
    @Column(name = "Email", unique = true)
    private String email;
    
    private   String role ;
    
    

	


	public Administrator() {
		super();
	}

	public Administrator(int adminId, String userName, String password, String name, String email) {
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
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", name="
				+ name + ", email=" + email + "]";
	}
    
    

   
}
