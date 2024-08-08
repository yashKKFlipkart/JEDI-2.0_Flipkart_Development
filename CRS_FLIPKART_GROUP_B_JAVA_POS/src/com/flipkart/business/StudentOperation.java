package com.flipkart.business;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Student;

import com.flipkart.bean.Course;

public class StudentOperation {
	
	private Student student;
	
	public StudentOperation(Student student) {
		this.student = student;
	}
	
	public List<Course> viewRegisteredCourses(int studentID){
		System.out.println("These are the registed courses for studentID:"+ studentID);
		System.out.println("CourseID: 567 \nCourseID: 309");
		return null;
		
	}
	
	public void viewReportCard() {
		System.out.println("-------------Report Card-------------");
		System.out.println("CourseID: 567 Grade: A");
		System.out.println("CourseID: 309 Grade: B");
		System.out.println("Your CPI is 9.0/10");
		System.out.println("-------------------------------------");
	}
	
	public void StudentMenu() {
		System.out.println("Welcome to Student Menu!\n");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter\n1 to View Registered Courses \n2 to View Report Card");
		int optionSelected = in.nextInt();  
		String dummy = in.nextLine();
		switch(optionSelected) {
		
			case 1:
				System.out.println("View Registered Courses Menu");
				System.out.println("Enter Student ID");
				int studentID = in.nextInt();
				this.viewRegisteredCourses(studentID);
				break;
			case 2:
				System.out.println("View Report Card Menu");
				this.viewReportCard();
				break;
			default:
				System.out.println("Wrong Option Chosen");
		}
	}
	
}
