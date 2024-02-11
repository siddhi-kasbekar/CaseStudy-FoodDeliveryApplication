package com.hexaware.hotpot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
 * Author: Siddhi Kasbekar
 * 
 * Entity description: contains properties related to administrator , getter and setters , 
 * constructors and relevant validations and mappings
*/


@Entity
public class Administrator {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Username must contain only letters, numbers, underscores, and hyphens")
    @Column(name = "UserName", nullable = false, unique = true)
    private String username;


    @Column(name = "Password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "Name")
    private String name;

    @Email(message = "Invalid email format")
    @Column(name = "Email", unique = true)
    private String email;
    
    private   String role ;
    
    

	


	public Administrator() {
		super();
	}

	public Administrator(int adminId, String username, String password, String name, String email) {
		super();
		this.adminId = adminId;
		this.username = username;
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
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
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
		return "Administrator [adminId=" + adminId + ", userName=" + username + ", password=" + password + ", name="
				+ name + ", email=" + email + "]";
	}
    
    

   
}
