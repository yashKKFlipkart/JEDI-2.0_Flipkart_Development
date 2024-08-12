package com.flipkart.client;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.flipkart.business.AdminOperations;

public class CRSAdminMenu {

	private Scanner in = new Scanner(System.in);
	AdminOperationsInterface ao = new AdminOperations();
	AdminDAOInterface adi = new AdminDAOImpl();
	StudentDAOInterface sdi = new StudentDAOImpl();
	ProfessorDAOInterface pdi = new ProfessorDAOImpl();
	
	public void CreateAdminMenu(int adminID) {
		
		while (true) {
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Admin ~~~~~~~~~~~~~~~~~~~\n");
			Date currentDate=new Date();
			System.out.println("\t\t"+currentDate);
			System.out.println("\nChoose an option from the Menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Approve Student Registration");
			System.out.println("2: Add Course");
			System.out.println("3: Remove Course");
			System.out.println("4: Add Professor");
			System.out.println("5: Remove Professor");
			System.out.println("6: Send Payment Notification");
			System.out.println("7: View Available Courses");
			System.out.println("8: Logout");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			in.nextLine();
			switch (optionSelected) {
				case 1:
					approveStudentRegistration(adminID);
					break;
				case 2:
					addCourse(adminID);
					break;
				case 3:
					removeCourse(adminID);
					break;
	
				case 4:
					addProfessor(adminID);
					break;
				case 5:
					removeProfessor(adminID);
					break;
				case 6:
					sendFeePaymentNotification(adminID);
					break;
				case 7:
					viewAvailableCourses();
					break;
				case 8:
					return;
				default:
					System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
			}
		}
	}

	private void approveStudentRegistration(int adminId) {
		// TODO Auto-generated method stub
		
		List<Student> pendingApprovalList = adi.getPendingStudentAccountsList();
		// Display Pending Student List (studentID, name)
		if(pendingApprovalList.isEmpty()) {
			System.out.println("No unapproved students");
			return;
		}
		System.out.println("List of Unapproved Students are:");
		for(Student student : pendingApprovalList) {
			System.out.println("Name:"+student.getName()+" studentID:"+student.getUserID());
		}
		// Press 1 to approve all students, Press 2 to approve student by ID
		System.out.println("Press 1 to approve all students, Press 2 to approve student by ID ");
		int optionSelected = in.nextInt();
		in.nextLine();
		if(optionSelected == 1) {
			for(Student student : pendingApprovalList) {
				adi.approveStudentRegistration(student.getUserID());
				System.out.println("Name:"+student.getName()+" studentID:"+student.getUserID() +"approved successfully");
			}
		}else if(optionSelected == 2){
			System.out.println("Enter student Id to approve: ");
			int studentID = in.nextInt();
			in.nextLine();
			Student stud = null;
			for(Student student : pendingApprovalList) {
				if(student.getStudentID() == studentID) {
					stud = student;
					break;
				}
			}
			if(stud!=null) {
				adi.approveStudentRegistration(studentID);
				System.out.println("Name:"+stud.getName()+" studentID:"+stud.getUserID() +"approved successfully");
			}
			else {
				System.out.println("Student Id does not exist!");
			}
		}
		else {
			System.out.println("Wrong Option Selected!");
		}
		
	}
	

	private void addCourse(int adminId) {
		// TODO Auto-generated method stub
		String courseName;
		int courseID;
		
		int totalSeats;
		int availableSeats;
		boolean isAvailableThisSemester;
		
		System.out.println("Enter Course Details to be added!");
		System.out.println("Enter Course Name:");
		courseName = in.nextLine();
		System.out.println("Enter Course ID:");
		courseID = in.nextInt();
		in.nextLine();
		System.out.println("Enter Total Seats:");
		totalSeats = in.nextInt();
		in.nextLine();
		
		
		availableSeats = totalSeats;
		isAvailableThisSemester = true;
		
		adi.addCourse(courseName, courseID, totalSeats, availableSeats, isAvailableThisSemester);
	}

	
	private void removeCourse(int adminID) {
		viewAvailableCourses();
		System.out.println("Enter CourseID to remove a course:");
		int courseID = in.nextInt();
		in.nextLine();
		boolean response = adi.removeCourse(courseID);
		if(response) {
			System.out.println("Course Removed Successfully!");
		}else {
			System.out.println("Course could not be Removed!");
		}
	}
	
	
	private void addProfessor(int adminId) {
		// TODO Auto-generated method stub
		int instructorID;
		String name;
		String username;
		String password;
		String department;
		String designation;
		System.out.println("Enter Professor Details to Add!");
		System.out.println("Enter Professor ID:");
		instructorID = in.nextInt();
		in.nextLine();
		System.out.println("Enter Professor Name:");
		name = in.nextLine();
		System.out.println("Enter Professor Username:");
		username = in.nextLine();
		System.out.println("Enter Professor Password:");
		password = in.nextLine();
		System.out.println("Enter Professor Department:");
		department = in.nextLine();
		System.out.println("Enter Professor Designation:");
		designation = in.nextLine();
		
		adi.addProfessor(instructorID, name, username, password, department, designation);

	}
	
	private void removeProfessor(int adminId) {
		// TODO Auto-generated method stub
		pdi.viewProfessors();
		System.out.println("Enter ProfessorID to remove professor:");
		int instructorID = in.nextInt();
		in.nextLine();
		boolean response = adi.removeProfessor(instructorID);
		if(response) {
			System.out.println("Professor Removed Successfully!");
		}else {
			System.out.println("Professor could not be Removed!");
		}
	}
	
	private void sendFeePaymentNotification(int adminId) {
		// TODO Auto-generated method stub
		adi.sendFeePayNotification();
	}
	
	private void viewAvailableCourses() {
		// TODO Auto-generated method stub
		ArrayList<Course> courses = sdi.viewAvailableCourses();
		System.out.println("Viewing All Available Courses");
		for(Course course: courses) {
			System.out.println("CourseID:"+course.getCourseID()+" CourseName:"+course.getCoursename()+" InstructorId:"+course.getInstructorID());
		}
	}
	
}
