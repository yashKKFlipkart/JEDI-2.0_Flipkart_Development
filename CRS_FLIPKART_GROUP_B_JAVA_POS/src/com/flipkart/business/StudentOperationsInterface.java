package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

public interface StudentOperationsInterface {
	public List<Student> getStudents() ;
	public boolean addStudent(String username, String name, String role, String password,Integer studentID, String department);
	public Student findStudentByUsername(String username);
	public Student findStudentByStudentId(int studentID);
	public boolean addCourse(int studentID, int courseID, String courseName);
	public boolean dropCourse(int studentID, int courseID);
	public ArrayList<Course> viewAvailableCourses();
	public ReportCard viewReportCard(int StudentID, int semesterId);
	public Boolean checkPaymentStatus(int StudentID);
	public void makePayment(Payment payment);
	public void viewRegisteredCourses(int studentID);
	public void viewStudents();

}
