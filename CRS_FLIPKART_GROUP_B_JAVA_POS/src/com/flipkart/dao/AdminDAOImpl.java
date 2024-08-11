package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import java.sql.Timestamp;

public class AdminDAOImpl implements AdminDAOInterface {
	
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
    }

    @Override
    public void createAdmins(int userID, String name, String role, String username, String password, String doj) {
        // Implementation for creating Admin records
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

    @Override
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

    @Override
    public void approveStudentRegistration(int studentId, int semesterId) {
        // Implementation for approving student registration
    }

    @Override
    public void addCourse(String courseName, int courseID) {
        String query = "INSERT INTO Course (courseID, courseName) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, courseID);
            stmt.setString(2, courseName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean removeCourse(int courseID) {
        String query = "DELETE FROM Course WHERE courseID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, courseID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addProfessor(Professor professor) {
        String query = "INSERT INTO Professor (instructorID, name, username, password, department, designation) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, professor.getInstructorID());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getUsername());
            stmt.setString(4, professor.getPassword());
            stmt.setString(5, professor.getDepartment());
            stmt.setString(6, professor.getDesignation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProfessor(int professorID) {
        String query = "DELETE FROM Professor WHERE instructorID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, professorID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Float calculateCpi(ReportCard rc) {
        // Implementation for calculating CPI
        return null;
    }

    @Override
    public ReportCard generateReportCard(int studentID) {
        // Implementation for generating report card
        return null;
    }

    @Override
    public void sendFeePayNotification() {
        // Implementation for sending fee payment notification
    }

    @Override
    public void PaymentCompletionNotification() {
        // Implementation for payment completion notification
    }

    @Override
    public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID) {
        // Implementation for viewing course student list
        return null;
    }

    @Override
    public List<Student> getPendingStudentAccountsList() {
        // Implementation for getting pending student accounts list
        return null;
    }
}
