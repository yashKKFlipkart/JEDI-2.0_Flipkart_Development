package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

public interface AdminOperationsInterface {
	
	public void createAdmins();
	
	public String findAdminByUsername(String username);
	
	public void approveStudentRegistration(int studentId,int semesterId);
	
	public void addCourse(String courseName, int courseID);
	
	public boolean removeCourse(int courseID) ;
	
	public void addProfessor(Professor professor);
	
	public void removeProfessor(int professorID);
	
	public Float calculateCpi(ReportCard rc) ;
	
	public ReportCard generateReportCard(int studentID);
	
	public void sendFeePayNotification() ;
	
	public void PaymentCompletionNotification() ;
	
	public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID);
	
	public List<Student> getPendingStudentAccountsList();

	

}
