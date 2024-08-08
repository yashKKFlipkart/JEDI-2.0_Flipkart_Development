package com.flipkart.business;

import java.util.List;
import com.flipkart.bean.Student;

import com.flipkart.bean.Course;

public class StudentOperation {
	
	private Student student;
	
	public StudentOperation(Student student) {
		this.student = student;
	}
    
	public void register() {
		System.out.println("Student Menu!\n");
	}
	
	public List<Course> viewRegisteredCourses(){
		return null;
		
	}
	
	public void viewReportCard() {
		
	}
	
}
