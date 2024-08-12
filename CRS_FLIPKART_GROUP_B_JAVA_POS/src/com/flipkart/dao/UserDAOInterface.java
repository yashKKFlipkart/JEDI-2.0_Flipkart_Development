package com.flipkart.dao;

public interface UserDAOInterface {

	public void updateProfessorPassword(Integer userID, String password);
	 
	public void updateAdminPassword(Integer userID, String password);
	
	public void updateStudentPassword(Integer userID, String password);
	
	public boolean loginUser(Integer userID, String password, String role);
	
}
