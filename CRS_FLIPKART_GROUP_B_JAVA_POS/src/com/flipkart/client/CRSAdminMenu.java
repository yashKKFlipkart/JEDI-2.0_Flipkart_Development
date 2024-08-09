package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.AdminOperations;

public class CRSAdminMenu {

	private Scanner in = new Scanner(System.in);
	AdminOperations ao = new AdminOperations();
	
	public void CreateAdminMenu(int adminID) {
		
		while (true) {
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Admin ~~~~~~~~~~~~~~~~~~~\n");
			System.out.println("\nChoose an option from the Menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Approve Student Registration");
			System.out.println("2: Add Grade");
			System.out.println("3: Add / Remove Course");
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
				case 2:
					addCourse(adminID);
					break;
				case 3:
					addOrRemoveCourse(adminID);
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
				case 8:
					return;
				default:
					System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
			}
		}
	}

	private void viewAvailableCourses() {
		// TODO Auto-generated method stub
		
	}

	private void sendFeePaymentNotification(int adminId) {
		// TODO Auto-generated method stub

	}

	private void viewReportCard(int adminId) {
		// TODO Auto-generated method stub

	}

	private void removeProfessor(int adminId) {
		// TODO Auto-generated method stub

	}

	private void addProfessor(int adminId) {
		// TODO Auto-generated method stub

	}

	private void addOrRemoveCourse(int adminID) {
		System.out.println("Press 1 to Add Course, Press 2 to Remove Course");
		int optionSelected = in.nextInt();
		in.nextLine();
		switch(optionSelected) {
		
		case 1: 
			System.out.print("Enter Course Name:");
			String courseName = in.nextLine();
			System.out.print("Enter Course ID:");
			String cID = in.nextLine();
			int courseID = Integer.valueOf(cID);
			ao.addCourse(courseName, courseID);
			break;
		case 2:
			System.out.println("Enter Course ID to be removed:");
			int courseIDToRemove = in.nextInt();
			ao.removeCourse(courseIDToRemove);
			break;
		default:
			System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
		
		}
		

	}

	private void addCourse(int adminId) {
		// TODO Auto-generated method stub

	}

	private void approveStudentRegistration(int adminId) {
		// TODO Auto-generated method stub

	}
}
