/**
 * 
 */
package com.flipkart.bean;

/**
 * 
 */
public class User {
	
	private int userID;
	private String name;
	private String role;
	private String password;
	private String joiningYear;
	
	
	
	public User(int userID) {
		this.userID = userID;
	}
	
	
	public User(int userID, String name, String role, String password, String joiningYear) {
		this.userID = userID;
		this.name = name;
		this.role = role;
		this.password = password;
		this.joiningYear = joiningYear;
	}


	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public String getJoiningYear() {
		return joiningYear;
	}
	public void setJoiningYear(String joiningYear) {
		this.joiningYear = joiningYear;
	}
	
	
}
