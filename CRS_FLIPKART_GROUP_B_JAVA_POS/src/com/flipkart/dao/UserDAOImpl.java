package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAOInterface {

    private Connection getConnection() throws SQLException {
        // Replace with your actual database URL, username, and password
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
    }

    @Override
    public void updateProfessorPassword(String userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'P'";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, Integer.parseInt(userID));
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
    public void updateAdminPassword(String userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'A'";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, Integer.parseInt(userID));
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
    public void updateStudentPassword(String userID, String password) {
        String query = "UPDATE User SET password = ? WHERE userID = ? AND role = 'S'";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, Integer.parseInt(userID));
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
    public boolean loginUser(String userID, String password, String role) {
        String query = "SELECT * FROM User WHERE userID = ? AND password = ? AND role = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(userID));
            stmt.setString(2, password);
            stmt.setString(3, role.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Return true if a record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
