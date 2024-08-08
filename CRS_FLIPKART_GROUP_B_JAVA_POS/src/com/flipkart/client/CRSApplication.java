package com.flipkart.client;

import java.util.Scanner;

public class CRSApplication {

	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		CRSApplication newUser = new CRSApplication();
		newUser.showMenu();
	}

	private void showMenu() {
		while (true) {
			System.out.println("\n************* Welcome to CRS Application *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Login");
			System.out.println("Press 2: Student Registration");
			System.out.println("Press 3: Update Password");
			System.out.println("Press 4: Exit ");
			System.out.println("*********************************************************");
			int menuOption = sc.nextInt();
			sc.nextLine();

			switch (menuOption) {
			case 1:
				login();
				break;

			case 2:
				registerStudent();
				break;

			case 3:
				updatePassword();
				break;

			case 4:
				sc.close();
				System.out.println("Exited Successfully!");
				return;

			default:
				System.out.println("Invalid input");
			}
		}
	}

	private void login() {
		String username, password, role = null;

		System.out.println("********************************");
		System.out.println("Enter your Username: ");
		username = sc.nextLine();
		System.out.println("Enter your Password: ");
		password = sc.nextLine();
		System.out.println("Choose your Role: Student/Admin/Professor");
		System.out.println("Press S for Student");
		System.out.println("Press P for Professor");
		System.out.println("Press A for Admin");
		role = sc.nextLine();
		switch (role) {
		case "S":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as a Student");
			System.out.println("Welcome " + username + " !!");
            CRSStudent stud = new CRSStudent();
            stud.CreateStudentMenu(username);
			break;

		case "P":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as a Professor");
			System.out.println("Welcome " + username +" Sir!");
            CRSProfessor prof = new CRSProfessor();
            prof.CreateProfessorMenu(username);
			break;

		case "A":
			System.out.println("********************************");
			System.out.println("Logged In Successfully as an Admin");
			System.out.println("Welcome " + username + " !!");
            CRSAdmin adm = new CRSAdmin();
            adm.CreateAdminMenu(username);
			break;

		default:
			System.out.println("Invalid Role");
			System.out.println("********************************");
		}
	}
	void registerStudent() {
		
		System.out.println("in register");
	}
	void updatePassword() {
		System.out.println("in upadte");
	}
}
