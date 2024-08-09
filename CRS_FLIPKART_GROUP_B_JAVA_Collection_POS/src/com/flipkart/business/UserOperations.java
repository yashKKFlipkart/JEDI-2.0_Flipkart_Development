package com.flipkart.business;

public class UserOperations implements UserOperationsInterface {

	public UserOperations() {
		
	}
	public boolean loginUser(String userID, String password, String role) {
		System.out.println("Login User!");
		return false;
		
	}
	public void updateStudentPassword(String userID, String password) {
		System.out.println("Update Student Password!");
	}
	public void updateAdminPassword(String userID, String password) {
		System.out.println("Update Admin Password!");
	}
	public void updateProfessorPassword(String userID, String password) {
		System.out.println("Update Professor Password!");
	}
}
