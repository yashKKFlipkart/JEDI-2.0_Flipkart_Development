package com.flipkart.bean;

public class User {

	private String name;
	private String role;
	private String username;
	private String password;
	private Integer userID;
	
	public User() {
		
	}
	
	public User(String username, String name, String role, String password, Integer userID) {
		super();
		this.name = name;
		this.role = role;
		this.username = username;
		this.password = password;
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
	

}
