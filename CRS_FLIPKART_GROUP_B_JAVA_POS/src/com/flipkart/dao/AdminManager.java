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

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.utils.DButils;
public class AdminManager {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "root");
    }

    public void createAdmins(int userID, String name, String role, String username, String password, String doj) {
        String insertUserSQL = "INSERT INTO User (userID, name, role, username, password) VALUES (?, ?, ?, ?, ?)";
        String insertAdminSQL = "INSERT INTO Admin (adminID, doj) VALUES (?, ?)";

        try (Connection connection = DButils.getConnection();
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
    
    public boolean addStudent(String username, String name, String role, String password, Integer studentID, String department) {
        Connection conn = null;
        PreparedStatement pstmtUser = null;
        PreparedStatement pstmtStudent = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // SQL query to insert into User table
            String sqlUser = "INSERT INTO User (userID, name, role, username, password) VALUES (?, ?, ?, ?, ?)";
            pstmtUser = conn.prepareStatement(sqlUser);
            pstmtUser.setInt(1, studentID);
            pstmtUser.setString(2, name);
            pstmtUser.setString(3, role);
            pstmtUser.setString(4, username);
            pstmtUser.setString(5, password);

            // Execute the insert into User table
            pstmtUser.executeUpdate();

            // SQL query to insert into Student table
            String sqlStudent = "INSERT INTO Student (studentID, department) VALUES (?, ?)";
            pstmtStudent = conn.prepareStatement(sqlStudent);
            pstmtStudent.setInt(1, studentID);
            pstmtStudent.setString(2, department);

            // Execute the insert into Student table
            pstmtStudent.executeUpdate();

            // Commit the transaction
            conn.commit();

            // Return true indicating success
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Rollback the transaction in case of error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmtUser != null) pstmtUser.close();
                if (pstmtStudent != null) pstmtStudent.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   
   
   
public Boolean checkPaymentStatus(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to get the payment status from the Payment table
            String sql = "SELECT payment_status FROM Payment WHERE studentID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentID);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if a result was returned
            if (rs.next()) {
                // Get the payment status from the result set
                int paymentStatusInt = rs.getInt("payment_status");
                return paymentStatusInt == 1; // Assuming 1 represents true (paid) and 0 represents false (not paid)
            } else {
                // No payment record found for the student
                System.out.println("No payment record found for student ID: " + studentID);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



public ReportCard viewReportCard(int studentID) {
        Connection conn = null;
        PreparedStatement pstmtReportCard = null;
        PreparedStatement pstmtCourseGrade = null;
        ResultSet rsReportCard = null;
        ResultSet rsCourseGrade = null;
        ReportCard reportCard = null;
        HashMap<String, String> grades = new HashMap<>();

        try {
            // Establish connection to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to get CPI from ReportCard table
            String sqlReportCard = "SELECT cpi FROM ReportCard WHERE studentID = ?";
            pstmtReportCard = conn.prepareStatement(sqlReportCard);
            pstmtReportCard.setInt(1, studentID);
            rsReportCard = pstmtReportCard.executeQuery();

            // Get CPI value
            Float cpi = null;
            if (rsReportCard.next()) {
                cpi = rsReportCard.getFloat("cpi");
            }

            // SQL query to get grades from CourseGrade table
            String sqlCourseGrade = "SELECT courseID, grade FROM CourseGrade WHERE studentID = ?";
            pstmtCourseGrade = conn.prepareStatement(sqlCourseGrade);
            pstmtCourseGrade.setInt(1, studentID);
            rsCourseGrade = pstmtCourseGrade.executeQuery();

            // Populate grades HashMap
            while (rsCourseGrade.next()) {
                String courseID = rsCourseGrade.getString("courseID");
                String grade = rsCourseGrade.getString("grade");
                grades.put(courseID, grade);
            }

            // Create ReportCard object
            reportCard = new ReportCard(grades, studentID, cpi);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsReportCard != null) rsReportCard.close();
                if (rsCourseGrade != null) rsCourseGrade.close();
                if (pstmtReportCard != null) pstmtReportCard.close();
                if (pstmtCourseGrade != null) pstmtCourseGrade.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reportCard;
    }



//DONE
public void viewRegisteredCourses(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to retrieve the registered courses for the given student
            String sql = "SELECT c.courseID, c.courseName, c.instructorID " +
                         "FROM RegisteredCourses rc " +
                         "JOIN Course c ON rc.courseID = c.courseID " +
                         "WHERE rc.studentID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentID);

            rs = pstmt.executeQuery();

            // Display the result
            System.out.println("Registered Courses for Student ID: " + studentID);
            while (rs.next()) {
                int courseID = rs.getInt("courseID");
                String courseName = rs.getString("courseName");
                int instructorID = rs.getInt("instructorID");

                System.out.println("Course ID: " + courseID + ", Course Name: " + courseName + ", Instructor ID: " + instructorID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




// done
public Student findStudentByStudentId(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        ArrayList<Course> registeredCourses = new ArrayList<>();

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to get student details from User and Student tables
            String sqlStudent = "SELECT User.username, User.name, User.role, User.password, Student.department "
                              + "FROM User JOIN Student ON User.userID = Student.studentID "
                              + "WHERE Student.studentID = ?";
            pstmt = conn.prepareStatement(sqlStudent);
            pstmt.setInt(1, studentID);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if a result was returned
            if (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String password = rs.getString("password");
                String department = rs.getString("department");

                // Create Student object
                student = new Student(username, name, role, password, studentID, department, registeredCourses);

                // Retrieve registered courses
                String sqlCourses = "SELECT Course.courseID, Course.courseName, Course.instructorID, Course.totalSeats, "
                                  + "Course.availableSeats, Course.isAvailableThisSemester "
                                  + "FROM RegisteredCourses JOIN Course ON RegisteredCourses.courseID = Course.courseID "
                                  + "WHERE RegisteredCourses.studentID = ?";
                pstmt = conn.prepareStatement(sqlCourses);
                pstmt.setInt(1, studentID);

                // Execute the query
                rs = pstmt.executeQuery();

                // Populate registeredCourses list
                while (rs.next()) {
                    Integer courseID = rs.getInt("courseID");
                    String courseName = rs.getString("courseName");
                    Integer instructorID = rs.getInt("instructorID");
                    Integer totalSeats = rs.getInt("totalSeats");
                    Integer availableSeats = rs.getInt("availableSeats");
                    boolean isAvailableThisSemester = rs.getBoolean("isAvailableThisSemester");

                    Course course = new Course(courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester);
                    registeredCourses.add(course);
                }

                // Update Student object with registered courses
                student.setregisteredCourses(registeredCourses);
            } else {
                System.out.println("No student found with ID: " + studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
    }




//done
public Student findStudentByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        ArrayList<Course> registeredCourses = new ArrayList<>();

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to get studentID and department from User and Student tables
            String sqlUser = "SELECT User.userID, User.name, User.role, User.password, Student.department "
                            + "FROM User JOIN Student ON User.userID = Student.studentID "
                            + "WHERE User.username = ?";
            pstmt = conn.prepareStatement(sqlUser);
            pstmt.setString(1, username);

            // Execute the query
            rs = pstmt.executeQuery();

            // Check if a result was returned
            if (rs.next()) {
                Integer studentID = rs.getInt("userID");
                String name = rs.getString("name");
                String role = rs.getString("role");
                String password = rs.getString("password");
                String department = rs.getString("department");

                // Create Student object
                student = new Student(username, name, role, password, studentID, department, registeredCourses);

                // Retrieve registered courses
                String sqlCourses = "SELECT Course.courseID, Course.courseName, Course.instructorID, Course.totalSeats, "
                                  + "Course.availableSeats, Course.isAvailableThisSemester "
                                  + "FROM RegisteredCourses JOIN Course ON RegisteredCourses.courseID = Course.courseID "
                                  + "WHERE RegisteredCourses.studentID = ?";
                pstmt = conn.prepareStatement(sqlCourses);
                pstmt.setInt(1, studentID);

                // Execute the query
                rs = pstmt.executeQuery();

                // Populate registeredCourses list
                while (rs.next()) {
                    Integer courseID = rs.getInt("courseID");
                    String courseName = rs.getString("courseName");
                    Integer instructorID = rs.getInt("instructorID");
                    Integer totalSeats = rs.getInt("totalSeats");
                    Integer availableSeats = rs.getInt("availableSeats");
                    boolean isAvailableThisSemester = rs.getBoolean("isAvailableThisSemester");

                    Course course = new Course(courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester);
                    registeredCourses.add(course);
                }

                // Update Student object with registered courses
                student.setregisteredCourses(registeredCourses);
            } else {
                System.out.println("No student found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
    }





public ArrayList<Course> viewAvailableCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList<>();

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to get available courses
            String sql = "SELECT courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester "
                       + "FROM Course "
                       + "WHERE isAvailableThisSemester = 1";
            pstmt = conn.prepareStatement(sql);

            // Execute the query
            rs = pstmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                Integer courseID = rs.getInt("courseID");
                String courseName = rs.getString("courseName");
                Integer instructorID = rs.getInt("instructorID");
                Integer totalSeats = rs.getInt("totalSeats");
                Integer availableSeats = rs.getInt("availableSeats");
                boolean isAvailableThisSemester = rs.getBoolean("isAvailableThisSemester");

                // Create Course object
                Course course = new Course(courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return courses;
    }





// CHECK ONCE MORE
public boolean addCourse(int studentID, Integer courseID, String courseName) {
        Connection conn = null;
        PreparedStatement pstmtCheckCourse = null;
        PreparedStatement pstmtCheckSeats = null;
        PreparedStatement pstmtRegister = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // Check if the course exists and has available seats
            String sqlCheckCourse = "SELECT courseID, availableSeats FROM Course WHERE courseID = ? AND courseName = ? AND isAvailableThisSemester = 1";
            pstmtCheckCourse = conn.prepareStatement(sqlCheckCourse);
            pstmtCheckCourse.setInt(1, courseID);
            pstmtCheckCourse.setString(2, courseName);
            rs = pstmtCheckCourse.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("availableSeats");

                if (availableSeats > 0) {
                    // Register the student in the course
                    String sqlRegister = "INSERT INTO RegisteredCourses (studentID, courseID) VALUES (?, ?)";
                    pstmtRegister = conn.prepareStatement(sqlRegister);
                    pstmtRegister.setInt(1, studentID);
                    pstmtRegister.setInt(2, courseID);
                    pstmtRegister.executeUpdate();

                    // Update the available seats
                    String sqlUpdateSeats = "UPDATE Course SET availableSeats = availableSeats - 1 WHERE courseID = ?";
                    pstmtCheckSeats = conn.prepareStatement(sqlUpdateSeats);
                    pstmtCheckSeats.setInt(1, courseID);
                    pstmtCheckSeats.executeUpdate();

                    // Return true indicating success
                    return true;
                } else {
                    System.out.println("No available seats for this course.");
                    return false;
                }
            } else {
                System.out.println("Course not found or not available this semester.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtCheckCourse != null) pstmtCheckCourse.close();
                if (pstmtCheckSeats != null) pstmtCheckSeats.close();
                if (pstmtRegister != null) pstmtRegister.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





// done
public boolean dropCourse(int studentID, Integer courseID) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // SQL query to delete the course registration
            String sql = "DELETE FROM RegisteredCourses WHERE studentID = ? AND courseID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, courseID);

            // Execute the delete operation
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Commit the transaction
                conn.commit();
                return true; // Course was successfully dropped
            } else {
                // No rows affected means the course registration was not found
                conn.rollback();
                return false; // No course found to drop
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Rollback the transaction in case of error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false; // Error occurred
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   



// done
public boolean finishRegistration(int studentId) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // Check if the student is registered for the semester
            String sqlCheck = "SELECT is_approved FROM SemesterRegistration WHERE studentID = ? AND registration_date = ?"; // Assuming registration_date is used as semesterId
            pstmtCheck = conn.prepareStatement(sqlCheck);
            pstmtCheck.setInt(1, studentId);
            pstmtCheck.setString(2, "2023-08-01"); // Example date, adjust as needed

            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                // Update the registration status to approved
                String sqlUpdate = "UPDATE SemesterRegistration SET is_approved = 1 WHERE studentID = ? AND registration_date = ?";
                pstmtUpdate = conn.prepareStatement(sqlUpdate);
                pstmtUpdate.setInt(1, studentId);
                pstmtUpdate.setString(2, "2023-08-01"); // Example date, adjust as needed

                int rowsAffected = pstmtUpdate.executeUpdate();

                if (rowsAffected > 0) {
                    // Commit the transaction
                    conn.commit();
                    return true; // Registration successfully finalized
                } else {
                    // Rollback in case of no rows affected
                    conn.rollback();
                    return false; // No registration found to update
                }
            } else {
                // Rollback if no record found
                conn.rollback();
                return false; // Student not registered for this semester
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Rollback the transaction in case of error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false; // Error occurred
        } finally {
            try {
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtUpdate != null) pstmtUpdate.close();
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



//done
public void makePayment(Payment payment) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // SQL query to insert payment details into the Payment table
            String sql = "INSERT INTO Payment (paymentID, studentID, amount, payment_status) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, payment.getPaymentID());
            pstmt.setInt(2, payment.getStudentID());
            pstmt.setDouble(3, payment.getAmount());
            pstmt.setBoolean(4, payment.getPaymentStatus());

            // Execute the insert operation
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Commit the transaction
                conn.commit();
                System.out.println("Payment made successfully.");
            } else {
                // Rollback in case of no rows affected
                conn.rollback();
                System.out.println("Failed to make payment.");
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    // Rollback the transaction in case of error
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



// done
public void viewStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");

            // SQL query to select all students from the Student table
            String sql = "SELECT s.studentID, s.department, u.name, u.username " +
                         "FROM Student s JOIN User u ON s.studentID = u.userID";
            pstmt = conn.prepareStatement(sql);

            // Execute the query
            rs = pstmt.executeQuery();

            // Print the result
            System.out.println("StudentID\tName\t\tUsername\tDepartment");
            System.out.println("----------------------------------------------------");
            while (rs.next()) {
                int studentID = rs.getInt("studentID");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String department = rs.getString("department");

                System.out.printf("%d\t\t%s\t%s\t%s%n", studentID, name, username, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
 // Example usage
    public static void main(String[] args) {
        AdminManager adminManager = new AdminManager();
        // int userID, String name, String role, String username, String password, String doj
        adminManager.createAdmins(13, "admin","Admin" ,"admin" , "pass", "2024-08-12 10:33:00");

        // FOR ADD STUDENT IN USER
//
//        boolean result = adminManager.addStudent("jdoe", "John Doe", "student", "password123", 10, "Computer Science");
//
//        System.out.println("Student added: " + result);

        	

       

        

//   	for add course

//        boolean result = adminManager.addCourse(1, 101, "Introduction to Programming");
//
//        System.out.println("Course added: " + result);

    

        

        // for viewRegisteredCourses

//        adminManager.viewRegisteredCourses(1);

        

        

        

        // for drop courses

//        boolean success = adminManager.dropCourse(1, 101); // Example studentID and courseID
//
//        if (success) {
//
//            System.out.println("Course dropped successfully.");
//
//        } else {
//
//            System.out.println("Failed to drop course.");
//
//        }

        

       

        // for finish registration

//        boolean success = adminManager.finishRegistration(3); // Example studentId and semesterId
//
//        if (success) {
//
//            System.out.println("Registration finalized successfully.");
//
//        } else {
//
//            System.out.println("Failed to finalize registration.");
//
//        }

        

        

        //for make payment

//        Payment payment = new Payment(1, 3, true, 100000.00); // Example values
//
//        adminManager.makePayment(payment);

        

        // for view students

        adminManager.viewStudents();

        

        

        // for payment status

//       int studentID = 1; 
//
//        Boolean paymentStatus = adminManager.checkPaymentStatus(studentID);
//
//        System.out.println("Payment Status for student ID " + studentID + ": " + (paymentStatus ? "Paid" : "Not Paid"));

        

        

        

        // for find student by username

//        String username = "alice_smith"; // Example username
//
//        Student student = adminManager.findStudentByUsername(username);
//
//
//
//        if (student != null) {
//
//            System.out.println("Student found: " + student.getName());
//
//            System.out.println("Department: " + student.getDepartment());
//
//            System.out.println("Registered Courses: ");
//
//            for (Course course : student.getregisteredCourses()) {
//
//                System.out.println("Course ID: " + course.getCourseID() + ", Course Name: " + course.getCoursename());
//
//            }
//
//        }

        

        

     // for find student by studentid

//        int studentID = 1; // Example studentID
//
//        Student student = adminManager.findStudentByStudentId(studentID);
//
//
//
//        if (student != null) {
//
//            System.out.println("Student found: " + student.getName());
//
//            System.out.println("Department: " + student.getDepartment());
//
//            System.out.println("Registered Courses: ");
//            for (Course course : student.getregisteredCourses()) {
//
//                System.out.println("Course ID: " + course.getCourseID() + ", Course Name: " + course.getCoursename());
//
//            }
//
//        }



        

        // for view available courses

//       ArrayList<Course> availableCourses = adminManager.viewAvailableCourses();
//
//
//
//        System.out.println("Available Courses:");
//
//        for (Course course : availableCourses) {
//
//            System.out.println("Course ID: " + course.getCourseID() 
//
//                + ", Course Name: " + course.getCoursename()
//
//                + ", Instructor ID: " + course.getInstructorID()
//
//                + ", Total Seats: " + course.getTotalSeats()
//
//                + ", Available Seats: " + course.getAvailableSeats());
//
//        }

        

        

        // for report card manager

//        ReportCard reportCard = adminManager.viewReportCard(1); // Assuming 1 is a valid studentID
//
//
//
//        if (reportCard != null) {
//
//            System.out.println("Student ID: " + reportCard.getStudentID());
//
//            System.out.println("CPI: " + reportCard.getCpi());
//
//            System.out.println("Grades: " + reportCard.getGrades());
//
//        } else {
//
//            System.out.println("No report card found for the given student ID.");
//
//        }
    }
}