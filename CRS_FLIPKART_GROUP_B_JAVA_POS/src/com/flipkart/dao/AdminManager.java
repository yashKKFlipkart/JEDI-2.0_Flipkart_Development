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

    // Example usage
    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager();
        
        // Create an admin
        int userID = 2; // Example userID
        String name = "Dohnnnyyy Joe";
        String role = "admin";
        String username = "johndoey";
        String password = "password1234"; // In a real application, hash this password!
        String doj = "2024-08-12 10:00:00"; // Example date of joining
        
        // Create the admin
        adminManager.createAdmins(userID, name, role, username, password, doj);
        
        // Find the admin by username
        Integer foundAdminID = adminManager.findAdminByUsername(username);
        
        if (foundAdminID != null) {
            System.out.println("Admin found with ID: " + foundAdminID);
        } else {
            System.out.println("Admin not found with username: " + username);
        }
    }
}