package com.flipkart.business;

public class UserOperations {

	public UserOperations() {
		
	}
	boolean loginUser(String userID, String password, String role) {
		System.out.println("Login User!");
		return false;
		
	}
	void updateStudentPassword(String userID, String password) {
		System.out.println("Update Student Password!");
	}
	void updateAdminPassword(String userID, String password) {
		System.out.println("Update Admin Password!");
	}
	void updateProfessorPassword(String userID, String password) {
		System.out.println("Update Professor Password!");
	}
}
