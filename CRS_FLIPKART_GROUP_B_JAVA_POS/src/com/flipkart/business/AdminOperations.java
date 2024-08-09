package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminOperations implements AdminOperationsInterface {

	CourseCatalog courseCatalogObject = new CourseCatalog();
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
	
	public void approveStudentRegistration(int studentId,int semesterId) {
		System.out.println("Approve Student Registration!");

	}
	public void addCourse(String courseName, int courseID){
		List<Course> courseCatalog = courseCatalogObject.getCourseCatalog();
		Course c1 = new Course();
		c1.setCoursename(courseName);
		c1.setCourseID(courseID);
		courseCatalog.add(c1);
		System.out.println("Added Course to Course Catalog!");
		
	}
	public boolean removeCourse(int courseID) {
		List<Course> courseCatalog = courseCatalogObject.getCourseCatalog();
		for(Course course : courseCatalog) {
			if(course.getCourseID().equals(courseID)) {
				courseCatalog.remove(course);
				System.out.println("Removed Course Successfully!");
				return true;
			}
		}
		System.out.println("Course To be Removed Not Found!");
		return false;
	}
	public void addProfessor(Professor professor) {
		System.out.println("Add Professor!");
	}
	
	public void removeProfessor(int professorID) {
		System.out.println("Remove Professor!");
	}
	
	public Float calculateCpi(ReportCard rc) {
		System.out.println("Calculate CPI");
		return null;
	}
	
	public ReportCard generateReportCard(int studentID) {
		System.out.println("Generate report Card");
		return null;
		
	}
	public void sendFeePayNotification() {
		System.out.println("Send Fee Payment Notification");
	}
	
	public HashMap<Integer, ArrayList<Student>> viewCourseStudentList(int courseID){
		return null;
	}
	
	public List<Student> getPendingStudentAccountsList(){
		return null;
	}

	@Override
	public void PaymentCompletionNotification() {
		// TODO Auto-generated method stub
		
	}
	
}
