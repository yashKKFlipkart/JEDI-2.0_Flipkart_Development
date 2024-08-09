package com.flipkart.business;

public interface UserOperationsInterface {
	
	public boolean loginUser(String userID, String password, String role);
	public void updateStudentPassword(String userID, String password);
	
	public void updateAdminPassword(String userID, String password);
	public void updateProfessorPassword(String userID, String password);

}
