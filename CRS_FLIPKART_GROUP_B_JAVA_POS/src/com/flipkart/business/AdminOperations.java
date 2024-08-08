package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations {

	List<Admin> admins=  new ArrayList<Admin>();
	
	public AdminOperations() {
		Admin ad= new Admin();
		ad.setName("admin");
		ad.setPassword("password");
		ad.setUsername("admin");
		ad.setRole("Admin");
		admins.add(ad);
	}

	public void createAdmins(){
		
	}

	public String findAdminByUsername(String username){
		createAdmins();

		for(Admin ad: admins){
			if(ad.getUsername().equals(username)){
				String password= ad.getPassword();
				return password;
			}
		}
		return null;
	}
	
	void approveStudentRegistration(int studentId,int semesterId) {

	}
	void addCourse(String course_name, String courseID, int semester){
		
	}
	void removeCourse(String courseID) {
		
	}
	void addProfessor(Professor professor) {
		
	}
	void removeProfessor(int professorID) {
		
	}
	Float CalculateCgpa(ReportCard gc) {
		return null;
	}
	ReportCard generateGradeCard(int studentID) {
		return null;
		
	}
	void sendFeePayNotification() {
		
	}

	void PaymentCompletionNotification() {

	}
	
}
