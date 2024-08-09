package com.flipkart.client;

import java.util.Scanner;

public class CRSAdminMenu {

	public void CreateAdminMenu(String adminID) {
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Admin ~~~~~~~~~~~~~~~~~~~\n");
			System.out.println("\nChoose an option from the Menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Approve Student Registration");
			System.out.println("2: Add Grade");
			System.out.println("3: Remove Course");
			System.out.println("4: Add Professor");
			System.out.println("5: Remove Professor");
			System.out.println("6: Send Payment Notification");
			System.out.println("7: View Available Courses");
			System.out.println("8: Logout");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			
			switch (optionSelected) {
				case 1:
					approveStudentRegistration(adminID);
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

	private void sendFeePaymentNotification(String adminId) {
		// TODO Auto-generated method stub

	}

	private void viewReportCard(String adminId) {
		// TODO Auto-generated method stub

	}

	private void removeProfessor(String adminId) {
		// TODO Auto-generated method stub

	}

	private void addProfessor(String adminId) {
		// TODO Auto-generated method stub

	}

	private void removeCourse(String adminId) {
		// TODO Auto-generated method stub

	}

	private void addCourse(String adminId) {
		// TODO Auto-generated method stub

	}

	private void approveStudentRegistration(String adminId) {
		// TODO Auto-generated method stub

	}
}
