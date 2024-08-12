package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.utils.DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAOInterface {

//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
//    }

    @Override
    public void addGrade(Integer studentID, Integer courseID, String alphaGrade) {
        String query = "INSERT INTO CourseGrade (studentID, courseID, grade) VALUES (?, ?, ?)"
                     + "ON DUPLICATE KEY UPDATE grade = VALUES(grade)";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            stmt.setInt(2, courseID);
            stmt.setString(3, alphaGrade);
            stmt.executeUpdate();
            System.out.println("Grade added/updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ViewEnrolledStudents(Integer courseID) {
        String query = "SELECT s.studentID, u.name FROM Student s "
                     + "JOIN RegisteredCourses rc ON s.studentID = rc.studentID "
                     + "JOIN Course c ON rc.courseID = c.courseID "
                     + "JOIN User u ON s.studentID = u.userID "
                     + "WHERE c.courseID = ?"; // Assuming semesterID is not used in the current schema
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, courseID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("studentID") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // view signed up courses by the instructor
    public void viewSignedUpCourses(int instructorID) {
    	String query = "SELECT courseID, courseName, totalSeats, availableSeats, isAvailableThisSemester "
                + "FROM Course WHERE instructorID = ?";
    	try (Connection con = DButils.getConnection();
                PreparedStatement stmt = con.prepareStatement(query)) {
    		// Set the instructorID parameter in the query
            stmt.setInt(1, instructorID);

            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery();
            
         // Iterate through the result set and display course details
            while (rs.next()) {
                int courseID = rs.getInt("courseID");
                String courseName = rs.getString("courseName");
                int totalSeats = rs.getInt("totalSeats");
                int availableSeats = rs.getInt("availableSeats");
                boolean isAvailableThisSemester = rs.getBoolean("isAvailableThisSemester");

                // Display the course information
                System.out.println("Course ID: " + courseID);
                System.out.println("Course Name: " + courseName);
                System.out.println("Total Seats: " + totalSeats);
                System.out.println("Available Seats: " + availableSeats);
                System.out.println("Available This Semester: " + isAvailableThisSemester);
                System.out.println("------------------------------------------------");
            }
    		
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }

    @Override
    public void registerCourse(int instructorID, String courseName, Integer courseID) {
        String query = "INSERT INTO Course (courseID, courseName, instructorID) VALUES (?, ?, ?)"
                     + "ON DUPLICATE KEY UPDATE courseName = VALUES(courseName), instructorID = VALUES(instructorID)";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, courseID);
            stmt.setString(2, courseName);
            stmt.setInt(3, instructorID);
            stmt.executeUpdate();
            System.out.println("Course registered/updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Professor findProfessorByUsername(String username) {
        String query = "SELECT u.userID, u.name, u.username, u.password, p.department, p.designation "
                     + "FROM Professor p JOIN User u ON p.instructorID = u.userID "
                     + "WHERE u.username = ?";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Professor professor = new Professor();
                professor.setUserID(rs.getInt("userID"));
                professor.setName(rs.getString("name"));
                professor.setUsername(rs.getString("username"));
                professor.setPassword(rs.getString("password"));
                professor.setDepartment(rs.getString("department"));
                professor.setDesignation(rs.getString("designation"));
                return professor;
            } else {
                System.out.println("No professor found with the given username.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void viewProfessors() {
        String query = "SELECT u.userID, u.name, u.username, p.department, p.designation FROM Professor p "
                     + "JOIN User u ON p.instructorID = u.userID";
        try (Connection con = DButils.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("userID")
                                 + ", Name: " + rs.getString("name")
                                 + ", Username: " + rs.getString("username")
                                 + ", Department: " + rs.getString("department")
                                 + ", Designation: " + rs.getString("designation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
