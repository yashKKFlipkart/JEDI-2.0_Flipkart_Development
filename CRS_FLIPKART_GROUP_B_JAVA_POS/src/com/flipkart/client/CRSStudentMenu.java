package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.flipkart.business.StudentOperations;

public class CRSStudentMenu {

	private Scanner in = new Scanner(System.in);
	private StudentOperationsInterface so = new StudentOperations();
	StudentDAOInterface sdi = new StudentDAOImpl();
	AdminDAOInterface adi = new AdminDAOImpl();
	public void CreateStudentMenu(int studentID) {

		while (true) {
			Student stud = sdi.findStudentByStudentId(studentID);
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Student "+stud.getName()+" ~~~~~~~~~~~~~~~~~~~\n");
			Date currentDate=new Date();
			System.out.println("\t\t"+currentDate);
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: View Available Courses");
			System.out.println("2: Add Course");
			System.out.println("3: Drop Course");
			System.out.println("4: Finish registration ");
			System.out.println("5: View Registered Courses");
			System.out.println("6: View Report Card");
			System.out.println("7: Check Payment Status");
			System.out.println("8: Do Payment");
			System.out.println("9: Logout");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			in.nextLine();
			switch (optionSelected) {
			case 1:
				viewAvailableCourses();
				break;
			case 2:
				addCourse(studentID);
				break;
			case 3:
				dropCourse(studentID);
				break;
			case 4:
				finishRegistration(studentID);
				break;
			case 5:
				viewRegisteredCourses(studentID);
				break;
			case 6:
				viewReportCard(studentID);
				break;
			case 7:
				checkPaymentStatus(studentID);
				break;
			case 8:
				makePayment(studentID);
				break;
			case 9:
				return;
			default:
				System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
			}
		}
	}

	private ArrayList<Course> viewAvailableCourses() {
		// TODO Auto-generated method stub
		ArrayList<Course> courses = sdi.viewAvailableCourses();
		System.out.println("Availble Courses are: ");
		for(Course course: courses) {
			System.out.println("CourseID:"+course.getCourseID()+" CourseName:"+course.getCoursename());				
		}	
		return courses;
	}
	
	private void addCourse(int studentID) {
		ArrayList<Course> courses = viewAvailableCourses();
		// Ask to select courseID and 0 to exit
		System.out.println("Enter courseIDs to add the course");
		
		System.out.print("Enter first primary course ID: ");
		int c1 = in.nextInt();
		in.nextLine();
		System.out.print("Enter second primary course ID: ");
		int c2 = in.nextInt();
		in.nextLine();
		System.out.print("Enter third primary course ID: ");
		int c3 = in.nextInt();
		in.nextLine();
		System.out.print("Enter fourth primary course ID: ");
		int c4 = in.nextInt();
		in.nextLine();
		System.out.print("Enter first elective course ID: ");
		int c5 = in.nextInt();
		in.nextLine();
		System.out.print("Enter second elective course ID: ");
		int c6 = in.nextInt();
		in.nextLine();
		
		for(Course course: courses) {
			int courseID = course.getCourseID();
			if(courseID == c1) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
			else if(courseID == c2) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
			else if(courseID == c3) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
			else if(courseID == c4) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
			else if(courseID == c5) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
			else if(courseID == c6) {
				sdi.addCourse(studentID, courseID, course.getCoursename());
				System.out.println(course.getCoursename()+" added successfully!");
			}
		}
	}
	
	private void dropCourse(int studentID) {
		viewRegisteredCourses(studentID);
		System.out.println("Enter courseIDs to remove the course, enter 0 to quit!");
		while(true) {
			System.out.print("Enter courseID:");
			int courseID = in.nextInt();
			in.nextLine();
			
			if(courseID==0) {
				System.out.println("Thank You for removing the courses!");
				break;
			}
			boolean response = sdi.dropCourse(studentID, courseID);
			if(response) {
				System.out.println("Course Removed Successfully!");
			}else {
				System.out.println("Course Not Registered by Student!");
			}
		}
	}

	private void finishRegistration(int studentID) {
		// TODO Auto-generated method stub
		sdi.addPaymentEntry(studentID, studentID);
		System.out.println("Student Registration Completed Successfully!");
		viewRegisteredCourses(studentID);
	}
	
	private void viewRegisteredCourses(int studentID) {
		sdi.viewRegisteredCourses(studentID);
	}
	
	private void viewReportCard(int studentID) {
	    // Generate the report card for the student
	    ReportCard rc = adi.generateReportCard(studentID);
	    
	    if (rc == null) {
	        System.out.println("No report card available for the student with ID: " + studentID);
	        return;
	    }

	    // Retrieve the CPI and grades from the report card
	    Float cpi = rc.getCpi();
	    HashMap<String, String> grades = rc.getGrades();

	    // Print the report card information
	    System.out.println("Report Card for Student ID: " + studentID);
	    System.out.println("CPI: " + cpi);

	    System.out.println("Grades:");
	    for (Map.Entry<String, String> entry : grades.entrySet()) {
	        System.out.println("Course ID: " + entry.getKey() + ", Grade: " + entry.getValue());
	    }
	}

	private void checkPaymentStatus(int studentID) {
		// TODO Auto-generated method stub
		boolean response = sdi.checkPaymentStatus(studentID);
		if(response) {
			System.out.println("Fees Already Paid");
		}else {
			System.out.println("Fee Payment Pending! Please make payment");
		}
		
	}
	private void makePayment(int studentID) {
		// TODO Auto-generated method stub
		boolean response = sdi.checkPaymentStatus(studentID);
		if(response) {
			System.out.println("Fees Already Paid");
			return;
		}
		sdi.makePayment(studentID);
		adi.PaymentCompletionNotification(studentID);
		
	}

}
