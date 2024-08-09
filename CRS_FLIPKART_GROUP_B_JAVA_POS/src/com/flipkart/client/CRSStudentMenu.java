package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.StudentOperations;

public class CRSStudentMenu {

	private Scanner in = new Scanner(System.in);
	private StudentOperations so = new StudentOperations();
	
	public void CreateStudentMenu(int studentID) {

		while (true) {
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Student ~~~~~~~~~~~~~~~~~~~\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Course Selection");
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
				registerCourses(studentID);
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

	private void makePayment(int studId) {
		// TODO Auto-generated method stub

	}

	private void checkPaymentStatus(int studId) {
		// TODO Auto-generated method stub

	}

	private void viewReportCard(int studId) {
		// TODO Auto-generated method stub

	}

	private void viewRegisteredCourses(int studentID) {
		so.viewRegisteredCourses(studentID);
	}

	private void finishRegistration(int studId) {
		// TODO Auto-generated method stub

	}

	private void dropCourse(int studentID) {
		System.out.println("Enter Course ID to be removed:");
		int courseID = in.nextInt();
		so.dropCourse(studentID, courseID);
	}

	private void addCourse(int studentID) {
		System.out.print("Enter Course Name:");
		String courseName = in.nextLine();
		System.out.print("Enter Course ID:");
		String cID = in.nextLine();
		int courseID = Integer.valueOf(cID);
		boolean ans = so.addCourse(studentID, courseID, courseName);
		if(!ans) {
			System.out.println("Error in adding Course");
		}
	}

	private void registerCourses(int studId) {
	}

}
