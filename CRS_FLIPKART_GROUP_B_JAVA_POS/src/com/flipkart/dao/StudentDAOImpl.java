package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAOImpl implements StudentDAOInterface {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/demo_flipfit";
        String user = "root";
        String password = "sanjeev-flipkart";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Boolean checkPaymentStatus(int studentID) {
        String query = "SELECT COUNT(*) FROM Payment WHERE studentID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addStudent(String username, String name, String role, String password, Integer studentID, String department) {
        String query = "INSERT INTO Student (studentID, username, name, role, password, department) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            stmt.setString(2, username);
            stmt.setString(3, name);
            stmt.setString(4, role);
            stmt.setString(5, password);
            stmt.setString(6, department);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ReportCard viewReportCard(int studentID) {
        String query = "SELECT * FROM ReportCard WHERE studentID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ReportCard reportCard = new ReportCard();
                reportCard.setStudentID(rs.getInt("studentID"));
                reportCard.setCpi(rs.getFloat("cpi"));

                HashMap<String, String> grades = new HashMap<>();

                reportCard.setGrades(grades);
                return reportCard;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 
    @Override
    public void viewRegisteredCourses(int studentID) {
        String query = "SELECT * FROM Course WHERE courseID IN (SELECT courseID FROM Student_Course WHERE studentID = ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Course ID: " + rs.getInt("courseID") + ", Course Name: " + rs.getString("courseName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findStudentByUsername(String username) {
        String query = "SELECT * FROM Student WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setUsername(rs.getString("username"));
                student.setName(rs.getString("name"));
                student.setRole(rs.getString("role"));
                student.setPassword(rs.getString("password"));
                student.setDepartment(rs.getString("department"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findStudentByStudentId(int studentID) {
        String query = "SELECT * FROM Student WHERE studentID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setUsername(rs.getString("username"));
                student.setName(rs.getString("name"));
                student.setRole(rs.getString("role"));
                student.setPassword(rs.getString("password"));
                student.setDepartment(rs.getString("department"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Course> viewAvailableCourses() {
        String query = "SELECT * FROM Course";
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("courseID"));
                course.setCoursename(rs.getString("courseName"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public boolean addCourse(int studentID, int courseID, String courseName) {
        String query = "INSERT INTO Student_Course (studentID, courseID) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            stmt.setInt(2, courseID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dropCourse(int studentID, int courseID) {
        String query = "DELETE FROM Student_Course WHERE studentID = ? AND courseID = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            stmt.setInt(2, courseID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean finishRegistration(int studentId, int semesterId) {
        return false;
    }

  
    
    @Override
    public void makePayment(Payment payment) {
        String query = "INSERT INTO Payment (studentID, amount) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            int studentID = payment.getStudentID(); 
            double amount = payment.getAmount();    
            
            stmt.setInt(1, studentID);   
            stmt.setFloat(2, (float) amount);    

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void viewStudents() {
        String query = "SELECT * FROM Student";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("studentID") +
                                   ", Name: " + rs.getString("name") +
                                   ", Department: " + rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
