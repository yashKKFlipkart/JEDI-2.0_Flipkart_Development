package com.flipkart.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

public interface AdminDAOInterface {
	
	private Connection getConnection() {
		return null;
	}

	public void createAdmins(int userID, String name, String role, String username, String password, String doj);
	
	public Integer findAdminByUsername(String username);
	
	public void approveStudentRegistration(int studentId);
	
    public void addCourse(String courseName, int courseID, Integer instructorID, int totalSeats, int availableSeats, boolean isAvailableThisSemester);
    
	public boolean removeCourse(int courseID) ;
	
	public void addProfessor(int instructorID, String name, String username, String password, String department, String designation);
	
	public boolean removeProfessor(int professorID);
	
	public Float calculateCpi(int studentId) ;
	
	public ReportCard generateReportCard(int studentID);
	
	public void sendFeePayNotification() ;
	
	public void PaymentCompletionNotification(int studentID) ;
	
	public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID);
	
	public List<Student> getPendingStudentAccountsList();

	
}
