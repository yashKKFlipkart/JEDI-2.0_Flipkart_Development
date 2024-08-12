package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.utils.DButils;

public class UserDAOImpl implements UserDAOInterface {

//    private Connection getConnection() throws SQLException {
//        // Replace with your actual database URL, username, and password
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
//    }

    @Override
    public void updateProfessorPassword(Integer userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'P'";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, (userID));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Professor password updated successfully.");
            } else {
                System.out.println("No Professor found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdminPassword(Integer userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'A'";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, (userID));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Admin password updated successfully.");
            } else {
                System.out.println("No Admin found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentPassword(Integer userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'S'";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, (userID));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student password updated successfully.");
            } else {
                System.out.println("No Student found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginUser(Integer userID, String password, String role) {
        String userQuery = "SELECT * FROM User WHERE userID = ? AND password = ? AND role = ?";
        String semesterQuery = "SELECT * FROM SemesterRegistration WHERE studentID = ? AND is_approved = 1";

        try (Connection con = DButils.getConnection();
             PreparedStatement userStmt = con.prepareStatement(userQuery);
             PreparedStatement semesterStmt = con.prepareStatement(semesterQuery)) {

            // Normalize role to uppercase for consistent handling
            role = role.toUpperCase();

            // Check if the user is a student
            if ("S".equals(role) || "STUDENT".equals(role)) {
                semesterStmt.setInt(1, userID);
                try (ResultSet semesterRs = semesterStmt.executeQuery()) {
                    if (semesterRs.next()) {
                        // Student is approved, proceed with login
                        userStmt.setInt(1, userID);
                        userStmt.setString(2, password);
                        userStmt.setString(3, "S");
                    } else {
                        System.out.println("Student is not approved yet. Cannot Login");
                        return false;
                    }
                }
            } else {
                // Non-student login
                userStmt.setInt(1, userID);
                userStmt.setString(2, password);
                userStmt.setString(3, role);
            }

            try (ResultSet userRs = userStmt.executeQuery()) {
                return userRs.next();  // Return true if a record is found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
