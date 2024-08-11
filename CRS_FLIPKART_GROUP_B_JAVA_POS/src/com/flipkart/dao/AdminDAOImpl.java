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

public class AdminDAOImpl implements AdminDAOInterface {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_flipfit", "root", "sanjeev-flipkart");
    }

    @Override
    public void createAdmins() {
        // Implementation for creating admin records
    }

    @Override
    public String findAdminByUsername(String username) {
        String password = null;
        String query = "SELECT password FROM Admin WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
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
