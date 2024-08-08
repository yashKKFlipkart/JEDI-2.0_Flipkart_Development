package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

@SuppressWarnings("unused")
public class AdminOperation{

	private Admin admin;
	
	public AdminOperation(Admin admin) {
		this.admin = admin;
	}
	

	public void enableDisableFeePayment(int semesterId) {

	}

	public void approveStudentRegistration(int studentId,int semesterId) {

	}

	public void addProfessor(Professor professor) {
		System.out.println("Professor Added Successfully!");
	}

	public void removeProfessor(int professorID) {
		System.out.println(professorID+" removed Sucessfully");
	}

	public void removeCourse(int courseID) {
		System.out.println(courseID+" removed Sucessfully");
	}
	

	public void addCourse(String courseName, int courseID, int semester) {
		System.out.println(courseName + " Added Sucessfully");
	}

	
	public HashMap<String,ArrayList<Integer> > viewCourseStudentList(String courseID, int semester, Boolean viewAll) {
		return null;

	}

	public ReportCard generateReportCard(int studentID) {
		return null;


	}

	
	public List<Student> getPendingStudentAccountsList() {
		return null;

		
	}

	public void approveStudentAccount(Integer studentID) {
	}
	
	
	public void AdminMenu() {
		System.out.println("Welcome to Admin Menu!\n");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 to Add Course");
		System.out.println("Enter 2 to Remove Course");
		System.out.println("Enter 3 to Add Professor");
		System.out.println("Enter 4 to Remove Professor");
		int optionSelected = in.nextInt();  
		String dummy = in.nextLine();
		switch(optionSelected) {
		
			case 1:
				System.out.println("Add Courses Menu");
				System.out.println("Enter Course Name");
				String courseName = in.nextLine();
				System.out.println("Enter Course ID");
				int courseId = in.nextInt();
				System.out.println("Enter Semester ID");
				int semesterID = in.nextInt();
				this.addCourse(courseName, courseId, semesterID);
				break;
			case 2:
				System.out.println("Remove Courses Menu");
				System.out.println("Enter Course ID");
				courseId = in.nextInt();
				this.removeCourse(courseId);
				break;
			case 3:
				System.out.println("Add Professor Menu");
				Professor prof = new Professor(501, "Rohit", "Professor", "pass", "2020", "Associate", "Computer", "8888899999");
				this.addProfessor(prof);
				break;
			case 4:
				System.out.println("Remove Professor Menu");
				System.out.println("Enter Professor ID");
				int professorID = in.nextInt();
				this.removeProfessor(professorID);
				break;
			default:
				System.out.println("Wrong Option Chosen");
		}
		
		
	}
}