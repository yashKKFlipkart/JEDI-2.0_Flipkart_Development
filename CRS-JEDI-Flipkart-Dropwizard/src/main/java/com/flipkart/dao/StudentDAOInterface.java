package com.flipkart.dao;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

/**
 * Interface representing the data access object (DAO) for student-related operations.
 * Provides methods for managing student accounts, courses, payments, and report cards.
 */
public interface StudentDAOInterface {

    /**
     * Checks the payment status for a specific student.
     * 
     * @param StudentID Unique identifier of the student.
     * @return True if the payment is completed, false otherwise.
     */
    public Boolean checkPaymentStatus(int StudentID);

    /**
     * Adds a new student with the specified details.
     * 
     * @param username Username for the student.
     * @param name Name of the student.
     * @param role Role of the student in the organization.
     * @param password Password for the student.
     * @param studentID Unique identifier for the student.
     * @param department Department of the student.
     * @return True if the student was added successfully, false otherwise.
     */
    public boolean addStudent(String username, String name, String role, String password, Integer studentID, String department);

    /**
     * Retrieves the report card for a specific student.
     * 
     * @param StudentID Unique identifier of the student.
     * @return ReportCard object containing the student's academic performance.
     */
    public ReportCard viewReportCard(int StudentID);

    /**
     * Displays the list of courses a student is registered for.
     * 
     * @param studentID Unique identifier of the student.
     */
    public List<Course> viewRegisteredCourses(int studentID);

    /**
     * Finds a student by their username.
     * 
     * @param username Username of the student.
     * @return Student object corresponding to the username.
     */
    public Student findStudentByUsername(String username);

    /**
     * Finds a student by their unique identifier.
     * 
     * @param studentID Unique identifier of the student.
     * @return Student object corresponding to the student ID.
     */
    public Student findStudentByStudentId(int studentID);

    /**
     * Retrieves the list of available courses.
     * 
     * @return ArrayList of Course objects representing the available courses.
     */
    public ArrayList<Course> viewAvailableCourses();

    /**
     * Adds a course to a student's registered courses.
     * 
     * @param studentID Unique identifier of the student.
     * @param courseID Unique identifier of the course.
     * @param courseName Name of the course.
     * @return True if the course was added successfully, false otherwise.
     */
    public boolean addCourse(int studentID, int courseID, String courseName);

    /**
     * Removes a course from a student's registered courses.
     * 
     * @param studentID Unique identifier of the student.
     * @param courseID Unique identifier of the course.
     * @return True if the course was dropped successfully, false otherwise.
     */
    public boolean dropCourse(int studentID, int courseID);

    /**
     * Completes the registration process for a student in a specific semester.
     * 
     * @param studentId Unique identifier of the student.
     * @param semesterId Unique identifier of the semester.
     * @return True if the registration was completed successfully, false otherwise.
     */
    public boolean finishRegistration(int studentId, int semesterId);

    /**
     * Adds a payment entry for a student.
     * 
     * @param paymentID Unique identifier of the payment.
     * @param studentID Unique identifier of the student.
     */
    public void addPaymentEntry(int paymentID, int studentID);

    /**
     * Processes a payment for a specific payment ID.
     * 
     * @param paymentID Unique identifier of the payment.
     */
    public boolean makePayment(int paymentID);

    /**
     * Displays a list of all students.
     */
    public void viewStudents();

}
