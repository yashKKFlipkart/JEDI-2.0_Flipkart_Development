package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAOImpl implements StudentDAOInterface {

//private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/crs", "root", "sanjeev-flipkart");
//    }

    //DONE
    @Override
    public boolean addStudent(String username, String name, String role, String password, Integer studentID, String department) {
        Connection conn = null;
        PreparedStatement pstmtUser = null;
        PreparedStatement pstmtStudent = null;
        PreparedStatement rstmtUser = null;
        PreparedStatement rstmtStudent = null;

        try {
            // Establish connection to the database
            conn = DButils.getConnection();

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

            String sqlSemesterRegistration = "Insert INTO SemesterRegistration (studentID, registration_date, is_approved) VALUES (?,?,?)";

            rstmtUser = conn.prepareStatement(sqlSemesterRegistration);
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            rstmtUser.setInt(1, studentID);
            rstmtUser.setDate(2, sqlDate);
            rstmtUser.setInt(3, 0);
            
            rstmtUser.executeUpdate();
            
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

   
   
   
@Override
public Boolean checkPaymentStatus(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
        conn = DButils.getConnection();

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



@Override
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
        conn = DButils.getConnection();

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
@Override
public void viewRegisteredCourses(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DButils.getConnection();

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
@Override
public Student findStudentByStudentId(int studentID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        ArrayList<Course> registeredCourses = new ArrayList<>();

        try {
            // Establish connection to the database
        conn = DButils.getConnection();

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
@Override
public Student findStudentByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        ArrayList<Course> registeredCourses = new ArrayList<>();

        try {
            // Establish connection to the database
        conn = DButils.getConnection();

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





@Override
public ArrayList<Course> viewAvailableCourses() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList<>();

        try {
            // Establish connection to the database
        conn = DButils.getConnection();

            // SQL query to get available courses
            String sql = "SELECT courseID, courseName, instructorID, totalSeats, availableSeats, isAvailableThisSemester "
                       + "FROM Course "
                       + "WHERE isAvailableThisSemester = 1 AND availableSeats > 0";
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
@Override
public boolean addCourse(int studentID, int courseID, String courseName) {
        Connection conn = null;
        PreparedStatement pstmtCheckCourse = null;
        PreparedStatement pstmtCheckSeats = null;
        PreparedStatement pstmtRegister = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DButils.getConnection();

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
@Override
public boolean dropCourse(int studentID, int courseID) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish connection to the database
            conn = DButils.getConnection();

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
@Override

    public boolean finishRegistration(int studentId, int semesterId) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
        conn = DButils.getConnection();

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
@Override
public void addPaymentEntry(int paymentID, int studentID) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        // Establish connection to the database
        conn = DButils.getConnection();

        // SQL query to insert payment details into the Payment table
        String sql = "INSERT INTO Payment (paymentID, studentID, amount, payment_status) VALUES (?, ?, ?, ?)";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, paymentID);
        pstmt.setInt(2, studentID);
        pstmt.setDouble(3, 50000.0);  // Default amount
        pstmt.setBoolean(4, false);   // Default status (unpaid)

        // Execute the insert operation
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Payment entry added successfully.");
        } else {
            System.out.println("Failed to add payment entry.");
        }
    } catch (SQLException e) {
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

@Override
public void makePayment(int paymentID) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        // Establish connection to the database
        conn = DButils.getConnection();

        // SQL query to update the payment status to true
        String sql = "UPDATE Payment SET payment_status = ? WHERE paymentID = ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setBoolean(1, true);   // Update status to paid
        pstmt.setInt(2, paymentID);

        // Execute the update operation
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Payment status updated successfully.");
        } else {
            System.out.println("Failed to update payment status.");
        }
    } catch (SQLException e) {
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
@Override
public void viewStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
        	conn = DButils.getConnection();

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
}
