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
		System.out.println("Approve Student Registration!");

	}
	void addCourse(String course_name, String courseID, int semester){
		System.out.println("Add Course!");
		
	}
	void removeCourse(String courseID) {
		System.out.println("Remove Course!");
	}
	void addProfessor(Professor professor) {
		System.out.println("Add Professor!");
	}
	void removeProfessor(int professorID) {
		System.out.println("Remove Professor!");
	}
	Float calculateCpi(ReportCard rc) {
		System.out.println("Calculate CPI");
		return null;
	}
	ReportCard generateReportCard(int studentID) {
		System.out.println("Generate report Card");
		return null;
		
	}
	void sendFeePayNotification() {
		System.out.println("Send Fee Payment Notification");
	}

	void PaymentCompletionNotification() {
		System.out.println("Payment Complete Notification");
	}
	
}
