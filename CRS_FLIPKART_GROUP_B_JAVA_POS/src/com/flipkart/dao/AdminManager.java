package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
public class AdminManager {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
    }

    public void createAdmins(int userID, String name, String role, String username, String password, String doj) {
        String insertUserSQL = "INSERT INTO User (userID, name, role, username, password) VALUES (?, ?, ?, ?, ?)";
        String insertAdminSQL = "INSERT INTO Admin (adminID, doj) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement userStatement = connection.prepareStatement(insertUserSQL);
             PreparedStatement adminStatement = connection.prepareStatement(insertAdminSQL)) {

            // Insert into User table
            userStatement.setInt(1, userID);
            userStatement.setString(2, name);
            userStatement.setString(3, role);
            userStatement.setString(4, username);
            userStatement.setString(5, password);
            userStatement.executeUpdate();

            // Insert into Admin table
            adminStatement.setInt(1, userID); // Use userID as adminID
            adminStatement.setTimestamp(2, Timestamp.valueOf(doj)); // Convert String to Timestamp
            int rowsAffected = adminStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin created successfully.");
            } else {
                System.out.println("Admin creation failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating admin: " + e.getMessage());
        }
    }

    public Integer findAdminByUsername(String username) {
        Integer adminID = null;
        String query = "SELECT a.adminID FROM User u " +
                       "JOIN Admin a ON u.userID = a.adminID " +
                       "WHERE u.username = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                adminID = rs.getInt("adminID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminID;
    }
    
    public void addCourse(String courseName, int courseID, Integer instructorID, int totalSeats, int availableSeats, boolean isAvailableThisSemester) {
        String query = "INSERT INTO Course (courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, courseID);
            stmt.setString(2, courseName);

            // Check if instructorID is provided, set it or null
            if (instructorID != null) {
                stmt.setInt(3, instructorID); // Set instructorID if provided
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER); // Set NULL if not provided
            }

            stmt.setInt(4, totalSeats); // Set totalSeats
            stmt.setInt(5, availableSeats); // Set availableSeats
            stmt.setBoolean(6, isAvailableThisSemester); // Pass boolean directly

            stmt.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean removeCourse(int courseID) {
        // First, check if the course exists
        String checkQuery = "SELECT COUNT(*) FROM Course WHERE courseID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            
            checkStmt.setInt(1, courseID);
            ResultSet rs = checkStmt.executeQuery();
            
            // Check if the course exists
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Course with ID " + courseID + " does not exist.");
                return false; // Course does not exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }

        // If course exists, proceed to delete
        String deleteQuery = "DELETE FROM Course WHERE courseID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement deleteStmt = con.prepareStatement(deleteQuery)) {
            
            deleteStmt.setInt(1, courseID);
            int rowsAffected = deleteStmt.executeUpdate();
            
            // Check if any rows were affected by the delete operation
            return rowsAffected > 0; // Returns true if the course was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }
    
    public void addProfessor(int instructorID, String name, String username, String password, String department, String designation) {
        String userQuery = "INSERT INTO User (userID, name, role, username, password) VALUES (?, ?, ?, ?, ?)";
        String professorQuery = "INSERT INTO Professor (instructorID, department, designation) VALUES (?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement userStmt = con.prepareStatement(userQuery);
             PreparedStatement professorStmt = con.prepareStatement(professorQuery)) {
            
            // Insert into User table
            userStmt.setInt(1, instructorID); // Using instructorID as userID
            userStmt.setString(2, name);
            userStmt.setString(3, "professor"); // Role is set as "professor"
            userStmt.setString(4, username);
            userStmt.setString(5, password);
            
            int userRowsAffected = userStmt.executeUpdate();
            
            // Check if user was added successfully
            if (userRowsAffected > 0) {
                // Insert into Professor table
                professorStmt.setInt(1, instructorID);
                professorStmt.setString(2, department);
                professorStmt.setString(3, designation);
                professorStmt.executeUpdate();
                System.out.println("Professor added successfully.");
            } else {
                System.out.println("Failed to add user for professor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeProfessor(int professorID) {
        // First, check if the professor exists
        String checkQuery = "SELECT COUNT(*) FROM Professor WHERE instructorID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            
            checkStmt.setInt(1, professorID);
            ResultSet rs = checkStmt.executeQuery();
            
            // Check if the professor exists
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Professor with ID " + professorID + " does not exist.");
                return false; // Professor does not exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }

        // Set instructorID to NULL in Course table for courses taught by the professor
        String updateCourseQuery = "UPDATE Course SET instructorID = NULL WHERE instructorID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement updateCourseStmt = con.prepareStatement(updateCourseQuery)) {
            
            updateCourseStmt.setInt(1, professorID);
            updateCourseStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }

        // If professor exists, proceed to delete from Professor and User tables
        String deleteProfessorQuery = "DELETE FROM Professor WHERE instructorID = ?";
        String deleteUserQuery = "DELETE FROM User WHERE userID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement deleteProfessorStmt = con.prepareStatement(deleteProfessorQuery);
             PreparedStatement deleteUserStmt = con.prepareStatement(deleteUserQuery)) {
            
            // Delete from Professor table
            deleteProfessorStmt.setInt(1, professorID);
            deleteProfessorStmt.executeUpdate();
            
            // Delete from User table
            deleteUserStmt.setInt(1, professorID); // Assuming instructorID is the same as userID
            deleteUserStmt.executeUpdate();
            
            System.out.println("Professor and associated user record removed successfully.");
            return true; // Return true after successful deletion
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }



    
 // Example usage
    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager();
        
//        // Create an admin
//        int userID = 2; // Example userID
//        String name = "Dohnnnyyy Joe";
//        String role = "admin";
//        String username = "johndoey";
//        String password = "password1234"; // In a real application, hash this password!
//        String doj = "2024-08-12 10:00:00"; // Example date of joining
//        
//        // Create the admin
//        adminManager.createAdmins(userID, name, role, username, password, doj);
//        
//        // Find the admin by username
//        Integer foundAdminID = adminManager.findAdminByUsername(username);
//        
//        if (foundAdminID != null) {
//            System.out.println("Admin found with ID: " + foundAdminID);
//        } else {
//            System.out.println("Admin not found with username: " + username);
//        }
        
//    	// Example parameters for adding a course with an instructor
//        String courseNameWithInstructor = "Introduction to Programming";
//        int courseIDWithInstructor = 101; // Unique ID for the course
//        Integer instructorID = 24; // Assuming this instructorID exists in the Professor table
//        int totalSeats = 30;
//        int availableSeats = 30;
//        boolean isAvailableThisSemester = true;
////
////        // Add the course with an instructor
//        adminManager.addCourse(courseNameWithInstructor, courseIDWithInstructor, instructorID, totalSeats, availableSeats, isAvailableThisSemester);
//
//        // Example parameters for adding a course without an instructor
//        String courseNameWithoutInstructor = "Data Science Basics";
//        int courseIDWithoutInstructor = 102; // Unique ID for the course
//
//        // Add the course without an instructor
//        adminManager.addCourse(courseNameWithoutInstructor, courseIDWithoutInstructor, null, totalSeats, availableSeats, isAvailableThisSemester);

//        int courseIDToRemove = 102; // Assuming this courseID may or may not exist
//
//        boolean isRemoved = adminManager.removeCourse(courseIDToRemove);
//        
//        if (isRemoved) {
//            System.out.println("Course removed successfully.");
//        } else {
//            System.out.println("Course could not be removed (may not exist).");
//        }
        
//        int instructorID = 24; // Unique ID for the professor
//        String name = "Dr. John Doe";
//        String username = "jdoe";
//        String password = "securepassword123";
//        String department = "Computer Science";
//        String designation = "Assistant Professor";
//
//        // Add the professor
//        adminManager.addProfessor(instructorID, name, username, password, department, designation);
        
     // Example: Remove a professor with a specific instructorID
        int professorIDToRemove = 24; // Assuming this ID may or may not exist

        boolean isRemoved = adminManager.removeProfessor(professorIDToRemove);
        
        if (isRemoved) {
            System.out.println("Professor removed successfully.");
        } else {
            System.out.println("Professor could not be removed (may not exist).");
        }

        
    }
}