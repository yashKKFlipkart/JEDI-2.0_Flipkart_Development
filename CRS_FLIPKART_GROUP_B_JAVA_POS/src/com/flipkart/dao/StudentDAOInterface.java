package com.flipkart.dao;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

public interface StudentDAOInterface {

	public Boolean checkPaymentStatus(int StudentID);
	
	public boolean addStudent(String username, String name, String role, String password,Integer studentID, String department);
	
	public ReportCard viewReportCard(int StudentID);
	
	public void viewRegisteredCourses(int studentID);
	
	public Student findStudentByUsername(String username);
	
	public Student findStudentByStudentId(int studentID);
	
	public ArrayList<Course> viewAvailableCourses();
	
	public boolean addCourse(int studentID, int courseID, String courseName);
	
	public boolean dropCourse(int studentID, int courseID);
	
	public boolean finishRegistration(int studentId, int semesterId);
	
	public void makePayment(Payment payment);
	
	public void viewStudents();
	
}
