package com.flipkart.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

/**
 * Interface representing the data access object (DAO) for admin-related operations.
 * Provides methods to manage admin users, courses, professors, and other administrative tasks.
 */
public interface AdminDAOInterface {
	
	/**
	 * Establishes a connection to the database.
	 * This method is intended to be implemented by classes that use this interface.
	 * 
	 * @return Connection object to interact with the database.
	 */
	private Connection getConnection() {
		return null;
	}

	/**
	 * Creates a new admin user with the specified details.
	 * 
	 * @param userID Unique identifier for the admin.
	 * @param name Name of the admin.
	 * @param role Role of the admin in the organization.
	 * @param username Username for the admin.
	 * @param password Password for the admin.
	 * @param doj Date of joining of the admin.
	 */
	public void createAdmins(int userID, String name, String role, String username, String password, String doj);
	
	/**
	 * Finds the admin ID by username.
	 * 
	 * @param username Username of the admin.
	 * @return Admin ID associated with the username.
	 */
	public Integer findAdminByUsername(String username);
	
	/**
	 * Finds an admin by their unique identifier.
	 * 
	 * @param adminID Unique identifier of the admin.
	 * @return Admin object corresponding to the admin ID.
	 */
	public Admin findAdminById(Integer adminID);
	
	/**
	 * Approves the registration of a student.
	 * 
	 * @param studentId Unique identifier of the student to approve.
	 */
	public void approveStudentRegistration(int studentId);
	
    /**
     * Adds a new course with the specified details.
     * 
     * @param courseName Name of the course.
     * @param courseID Unique identifier for the course.
     * @param totalSeats Total number of seats available for the course.
     * @param availableSeats Number of seats currently available.
     * @param isAvailableThisSemester Indicates if the course is available in the current semester.
     */
    public void addCourse(String courseName, int courseID, int totalSeats, int availableSeats, boolean isAvailableThisSemester);
    
	/**
	 * Removes a course by its unique identifier.
	 * 
	 * @param courseID Unique identifier of the course to remove.
	 * @return True if the course was removed successfully, false otherwise.
	 */
	public boolean removeCourse(int courseID);
	
	/**
	 * Adds a new professor with the specified details.
	 * 
	 * @param instructorID Unique identifier for the professor.
	 * @param name Name of the professor.
	 * @param username Username for the professor.
	 * @param password Password for the professor.
	 * @param department Department of the professor.
	 * @param designation Designation of the professor.
	 */
	public void addProfessor(int instructorID, String name, String username, String password, String department, String designation);
	
	/**
	 * Removes a professor by their unique identifier.
	 * 
	 * @param professorID Unique identifier of the professor to remove.
	 * @return True if the professor was removed successfully, false otherwise.
	 */
	public boolean removeProfessor(int professorID);
	
	/**
	 * Calculates the Cumulative Performance Index (CPI) of a student.
	 * 
	 * @param studentId Unique identifier of the student.
	 * @return CPI of the student.
	 */
	public Float calculateCpi(int studentId);
	
	/**
	 * Generates a report card for a student.
	 * 
	 * @param studentID Unique identifier of the student.
	 * @return ReportCard object containing the student's academic performance.
	 */
	public ReportCard generateReportCard(int studentID);
	
	/**
	 * Sends a notification to students to pay their fees.
	 */
	public void sendFeePayNotification();
	
	/**
	 * Sends a notification upon completion of payment by a student.
	 * 
	 * @param studentID Unique identifier of the student who completed the payment.
	 */
	public void PaymentCompletionNotification(int studentID);
	
	/**
	 * Retrieves the list of students enrolled in a specific course.
	 * 
	 * @param courseID Unique identifier of the course.
	 * @return HashMap where the key is the course ID and the value is the list of students enrolled.
	 */
	public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID);
	
	/**
	 * Retrieves the list of student accounts that are pending approval.
	 * 
	 * @return List of students whose accounts are pending approval.
	 */
	public List<Student> getPendingStudentAccountsList();

}
