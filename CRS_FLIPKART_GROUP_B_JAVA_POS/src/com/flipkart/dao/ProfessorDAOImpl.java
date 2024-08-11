package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import com.flipkart.bean.Professor;

public class ProfessorDAOImpl implements ProfessorDAOInterface {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_flipfit", "root", "sanjeev-flipkart");
    }

    @Override
    public void addGrade(Integer studentID, Integer semesterID, String courseID, String alphaGrade) {
        // Implementation for adding grades
    }

    @Override
    public void ViewEnrolledStudents(String courseID, Integer semesterID) {
        // Implementation for viewing enrolled students
    }

    @Override
    public void viewCourseProfessor(int instructorID) {
        // Implementation for viewing course professor
    }

    @Override
    public void registerCourse(int instructorID, String courseName, int courseID) {
        // Implementation for registering a course
    }

    @Override
    public Professor findProfessorByUsername(String username) {
        Professor professor = null;
        String query = "SELECT * FROM Professor WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                professor = new Professor();
                professor.setInstructorID(rs.getInt("instructorID"));
                professor.setName(rs.getString("name"));
                professor.setUsername(rs.getString("username"));
                professor.setPassword(rs.getString("password"));
                professor.setDepartment(rs.getString("department"));
                professor.setDesignation(rs.getString("designation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor;
    }

    @Override
    public void viewProfessors() {
        // Implementation for viewing professors
    }
}
