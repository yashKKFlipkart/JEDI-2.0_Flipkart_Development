package com.flipkart.dao;

public interface UserDAOInterface {

	public void updateProfessorPassword(String userID, String password);
	 
	public void updateAdminPassword(String userID, String password);
	
	public void updateStudentPassword(String userID, String password);
	
	public boolean loginUser(String userID, String password, String role);
	
}
