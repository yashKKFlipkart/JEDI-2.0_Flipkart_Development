package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
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

    public void approveStudentRegistration(int studentId) {
        String query = "UPDATE SemesterRegistration SET is_approved = 1 WHERE studentID = ?";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, studentId);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Student registration approved successfully for student ID: " + studentId);
            } else {
                System.out.println("No registration found for student ID: " + studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Float calculateCpi(int studentId) {
        String query = "SELECT grade FROM CourseGrade WHERE studentID = ?";
        float totalGradePoints = 0;
        int totalCourses = 0;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String grade = rs.getString("grade");
                totalGradePoints += convertGradeToPoints(grade); // Convert the grade to grade points
                totalCourses++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }

        // Calculate and return CPI
        if (totalCourses > 0) {
            return totalGradePoints / totalCourses; // Average of grade points
        } else {
            return null; // Return null if no courses found
        }
    }

    // Helper method to convert letter grades to grade points
    private float convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return 4.0f;
            case "B":
                return 3.0f;
            case "C":
                return 2.0f;
            case "D":
                return 1.0f;
            case "F":
                return 0.0f;
            default:
                return 0.0f; // Default case for unrecognized grades
        }
    }

    public void sendFeePayNotification() {
        String paymentQuery = "SELECT p.paymentID, p.studentID, p.amount, u.name " +
                              "FROM Payment p JOIN User u ON p.studentID = u.userID " +
                              "WHERE p.payment_status = 1"; // Assuming 1 means payment successful

        String notificationQuery = "INSERT INTO PaymentNotification (paymentID, studentID, notification_message) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement paymentStmt = con.prepareStatement(paymentQuery);
             PreparedStatement notificationStmt = con.prepareStatement(notificationQuery)) {
            
            ResultSet rs = paymentStmt.executeQuery();
            
            while (rs.next()) {
                int paymentID = rs.getInt("paymentID");
                int studentID = rs.getInt("studentID");
                float amount = rs.getFloat("amount");
                String studentName = rs.getString("name");

                // Constructing the notification message
                String notificationMessage = "Dear " + studentName + ", your payment of $" + amount + " is pending! Please complete payment.";

                // Insert the notification into the PaymentNotification table
                notificationStmt.setInt(1, paymentID);
                notificationStmt.setInt(2, studentID);
                notificationStmt.setString(3, notificationMessage);
                notificationStmt.executeUpdate();

                System.out.println("Notification sent for payment ID: " + paymentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void PaymentCompletionNotification(int studentID) {
        // Query to retrieve the most recent successful payment for the given student
        String paymentQuery = "SELECT p.paymentID, p.amount, u.name " +
                              "FROM Payment p JOIN User u ON p.studentID = u.userID " +
                              "WHERE p.studentID = ? AND p.payment_status = 1"; // Assuming 1 means payment successful

        String notificationQuery = "INSERT INTO PaymentNotification (paymentID, studentID, notification_message) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement paymentStmt = con.prepareStatement(paymentQuery);
             PreparedStatement notificationStmt = con.prepareStatement(notificationQuery)) {
            
            // Set the studentID parameter in the payment query
            paymentStmt.setInt(1, studentID);
            ResultSet rs = paymentStmt.executeQuery();
            
            if (rs.next()) {
                int paymentID = rs.getInt("paymentID");
                float amount = rs.getFloat("amount");
                String studentName = rs.getString("name");

                // Constructing the notification message
                String notificationMessage = "Dear " + studentName + ", your payment of $" + amount + " has been processed successfully.";

                // Insert the notification into the PaymentNotification table
                notificationStmt.setInt(1, paymentID);
                notificationStmt.setInt(2, studentID);
                notificationStmt.setString(3, notificationMessage);
                notificationStmt.executeUpdate();

                System.out.println("Payment completion notification sent for payment ID: " + paymentID);
            } else {
                System.out.println("No successful payment found for student ID: " + studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID) {
        HashMap<Integer, ArrayList<Student>> courseStudentMap = new HashMap<>();
        ArrayList<Student> studentList = new ArrayList<>();

        String query = "SELECT s.studentID, s.department, u.name " +
                       "FROM RegisteredCourses rc " +
                       "JOIN Student s ON rc.studentID = s.studentID " +
                       "JOIN User u ON s.studentID = u.userID " +
                       "WHERE rc.courseID = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            // Set the courseID parameter in the query
            stmt.setInt(1, courseID);
            ResultSet rs = stmt.executeQuery();
            
            // Populate the studentList with results
            while (rs.next()) {
                int studentID = rs.getInt("studentID");
                
                // Get student object from studentID
                Student student = getStudentFromId(studentID);
                
                studentList.add(student);
            }
            
            // Put the courseID and its corresponding student list into the map
            courseStudentMap.put(courseID, studentList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return courseStudentMap;
    }

    private Student getStudentFromId(int studentID) {
        String query = "SELECT s.studentID, s.department, u.name, u.role, u.username, u.password " +
                       "FROM Student s JOIN User u ON s.studentID = u.userID " +
                       "WHERE s.studentID = ?";
        Student student = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            // Set the studentID parameter in the query
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            
            // Check if a student record is found
            if (rs.next()) {
                String department = rs.getString("department");
                String studentName = rs.getString("name");
                String role = rs.getString("role");
                String username = rs.getString("username");
                String password = rs.getString("password"); // Get password if needed
                
                // Create a new Student object using the new constructor
                student = new Student();
                student.setUsername(username);
                student.setName(studentName);
                student.setRole(role);
                student.setPassword(password);
                student.setStudentID(studentID);
                student.setDepartment(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return student; // Returns null if no student is found
    }
    
    public List<Student> getPendingStudentAccountsList() {
        List<Student> pendingStudents = new ArrayList<>();
        String query = "SELECT s.studentID " +
                       "FROM Student s JOIN SemesterRegistration sr ON s.studentID = sr.studentID " +
                       "WHERE sr.is_approved = 0"; // Assuming 0 means not approved

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            ResultSet rs = stmt.executeQuery();
            
            // Loop through the result set and get student details
            while (rs.next()) {
                int studentID = rs.getInt("studentID");
                Student student = getStudentFromId(studentID);
                
                // Add the student object to the pendingStudents list
                if (student != null) {
                    pendingStudents.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pendingStudents; // Returns list of pending students
    }
    
    public ReportCard generateReportCard(int studentID) {
        HashMap<String, String> grades = new HashMap<>();

        String query = "SELECT courseID, grade FROM CourseGrade WHERE studentID = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            // Set the studentID parameter in the query
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            
            // Loop through the result set to get grades
            while (rs.next()) {
                String courseID = rs.getString("courseID");
                String grade = rs.getString("grade");
                
                grades.put(courseID, grade); // Add course ID and grade to the map
            }
            
            // Get the CPI using the existing calculateCpi method
            Float cpi = calculateCpi(studentID);

            // Create and return the ReportCard object
            ReportCard reportCard = new ReportCard(grades, studentID, cpi);
            return reportCard;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null in case of an error
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
//        int professorIDToRemove = 24; // Assuming this ID may or may not exist
//
//        boolean isRemoved = adminManager.removeProfessor(professorIDToRemove);
//        
//        if (isRemoved) {
//            System.out.println("Professor removed successfully.");
//        } else {
//            System.out.println("Professor could not be removed (may not exist).");
//        }
//        int studentIdToApprove = 3; // Assuming this ID may or may not exist
//
//        adminManager.approveStudentRegistration(studentIdToApprove);
        
//        int studentIdToTest = 3; // Student ID based on your sample data
//
//        Float cpi = adminManager.calculateCpi(studentIdToTest);
//        if (cpi != null) {
//            System.out.println("CPI for student ID " + studentIdToTest + " is: " + cpi);
//        } else {
//            System.out.println("Could not calculate CPI for student ID " + studentIdToTest);
//        }
        
//        int courseID = 102; // Example course ID, change this to a valid one from your database
//        
//        HashMap<Integer, ArrayList<Student>> studentListMap = adminManager.viewCourseStudentList(courseID);
//        
//        // Print the student list for the specified course ID
//        if (studentListMap.containsKey(courseID)) {
//            ArrayList<Student> students = studentListMap.get(courseID);
//            System.out.println("Students enrolled in course ID " + courseID + ":");
//            for (Student student : students) {
//                System.out.println("Student ID: " + student.getStudentID());
//                System.out.println("Name: " + student.getName());
//                System.out.println("Department: " + student.getDepartment());
//                System.out.println("Role: " + student.getRole());
//                System.out.println("Username: " + student.getUsername());
//                System.out.println("---------------------------");
//            }
//        } else {
//            System.out.println("No students found for course ID: " + courseID);
//        }
        
//        List<Student> pendingStudents = adminManager.getPendingStudentAccountsList();
//        
//        // Print the details of pending students
//        if (pendingStudents.isEmpty()) {
//            System.out.println("No pending student accounts.");
//        } else {
//            System.out.println("Pending Student Accounts:");
//            for (Student student : pendingStudents) {
//                System.out.println("Student ID: " + student.getStudentID());
//                System.out.println("Name: " + student.getName());
//                System.out.println("Department: " + student.getDepartment());
//                System.out.println("Role: " + student.getRole());
//                System.out.println("Username: " + student.getUsername());
//                System.out.println("---------------------------");
//            }
//        }
        
        ReportCard reportCard = new ReportCard();
        int studentID = 3; // Example student ID
        ReportCard generatedReportCard = adminManager.generateReportCard(studentID);

        if (generatedReportCard != null) {
            System.out.println("Report Card for Student ID: " + generatedReportCard.getStudentID());
            System.out.println("CPI: " + generatedReportCard.getCpi());
            System.out.println("Grades: " + generatedReportCard.getGrades());
        } else {
            System.out.println("Failed to generate report card.");
        }
        
    }
}