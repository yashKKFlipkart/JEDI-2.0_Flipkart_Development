package com.flipkart.dao;

/**
 * This interface defines the contract for user-related database operations.
 * Implementing classes should provide the necessary logic to interact with the
 * user database, including password updates and login functionality for
 * different user roles.
 */
public interface UserDAOInterface {

    /**
     * Updates the password for a professor in the system.
     * 
     * @param userID   The unique identifier of the professor.
     * @param password The new password to set for the professor.
     */
    public void updateProfessorPassword(Integer userID, String password);
     
    /**
     * Updates the password for an admin in the system.
     * 
     * @param userID   The unique identifier of the admin.
     * @param password The new password to set for the admin.
     */
    public void updateAdminPassword(Integer userID, String password);
    
    /**
     * Updates the password for a student in the system.
     * 
     * @param userID   The unique identifier of the student.
     * @param password The new password to set for the student.
     */
    public void updateStudentPassword(Integer userID, String password);
    
    /**
     * Authenticates a user in the system based on their role.
     * 
     * @param userID   The unique identifier of the user.
     * @param password The password entered by the user.
     * @param role     The role of the user (e.g., professor, admin, student).
     * @return         True if the login is successful, false otherwise.
     */
    public boolean loginUser(Integer userID, String password, String role);
    
}
