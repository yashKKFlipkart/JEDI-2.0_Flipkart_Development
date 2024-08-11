package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAOInterface {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_flipfit", "root", "sanjeev-flipkart");
    }

    @Override
    public void updateProfessorPassword(String userID, String password) {
        String query = "UPDATE Professor SET password = ? WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setString(2, userID);
            stmt.executeUpdate();
            System.out.println("Professor password updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdminPassword(String userID, String password) {
        String query = "UPDATE Admin SET password = ? WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setString(2, userID);
            stmt.executeUpdate();
            System.out.println("Admin password updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudentPassword(String userID, String password) {
        String query = "UPDATE Student SET password = ? WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setString(2, userID);
            stmt.executeUpdate();
            System.out.println("Student password updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginUser(String userID, String password, String role) {
        String query = "";
        if (role.equalsIgnoreCase("S")) {
            query = "SELECT * FROM Student WHERE username = ? AND password = ?";
        } else if (role.equalsIgnoreCase("P")) {
            query = "SELECT * FROM Professor WHERE username = ? AND password = ?";
        } else if (role.equalsIgnoreCase("A")) {
            query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        } else {
            return false;
        }
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, userID);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
