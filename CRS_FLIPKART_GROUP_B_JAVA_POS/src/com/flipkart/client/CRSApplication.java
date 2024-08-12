/**
 * 
 */
package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.dao.*;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminOperations;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.business.StudentOperations;


import java.util.Scanner;

/**
 * 
 */
public class CRSApplication {
	
	private Scanner in = new Scanner(System.in);
	private StudentDAOInterface sdi = new StudentDAOImpl();
	private StudentOperations sOps = new StudentOperations();
	private ProfessorOperations pOps = new ProfessorOperations() ;
	UserDAOInterface udi = new UserDAOImpl();

	public CRSApplication(){
		 
	}
	
	public static void main(String[] args) {
		CRSApplication newUser = new CRSApplication();
		newUser.createMenu();
	}
	private void createMenu() {

		while (true) {
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome to CRS Menu ~~~~~~~~~~~~~~~~~~~\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Login");
			System.out.println("2: Student Registration");
			System.out.println("3: Update Password");
			System.out.println("4: Exit ");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			in.nextLine();
			switch (optionSelected) {
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
					System.out.println("Exited Successfully!");
					in.close();
					return;
				default:
					System.out.println("Invalid input");
					break;
			}
		}
	}


	private void login() {

		String username=null;
		String password=null;
		String role;
		int userID;

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Choose your Role: ");
		System.out.println("S for Student");
		System.out.println("P for Professor");
		System.out.println("A for Admin");
		role = in.nextLine();
		System.out.println("Enter your username: ");
		username = in.nextLine();
		System.out.println("Enter your password: ");
		password = in.nextLine();
		System.out.println("Enter your user ID: ");
		userID = in.nextInt();
		in.nextLine();
		switch (role) {
		case "S":
			boolean response = udi.loginUser(userID, password, role);
			System.out.println(response);
			if(response) {				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Student Login Successful");
				CRSStudentMenu stud = new CRSStudentMenu();
				stud.CreateStudentMenu(userID);
			}
			break;

		case "P":
			boolean response2 = udi.loginUser(userID, password, role);
			System.out.println(response2);
			if(response2) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Professor Login Successful");
				CRSProfessorMenu prof = new CRSProfessorMenu();
				prof.CreateProfessorMenu(userID);
			}
			break;
				
		case "A":
			boolean response3 = udi.loginUser(userID, password, role);
			System.out.println(response3);
			if(response3) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Admin Login Successful");
				CRSAdminMenu adm = new CRSAdminMenu();
				adm.CreateAdminMenu(userID);				
			}
			break;

		default:
			System.out.println("Wrong Option Selected!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
	
	void registerStudent() {
		System.out.println("enter username");
		String username = in.nextLine();
		System.out.println("enter password");
		String password = in.nextLine();
		System.out.println("enter name");
		String name = in.nextLine();
		System.out.println("enter department");
		String department = in.nextLine();
		System.out.println("enter studentID");
		int studentID = in.nextInt();

		if(sdi.addStudent(username, name, "S", password, studentID, department)) {
			System.out.println("Student Added Successfully");
		}
		else {
			System.out.println("Student Already exists");
		}
		
	}
	
	void updatePassword() {
		System.out.println("In Update Password Menu");
		System.out.println("Enter your role: S for Student, P for Professor, A for Admin ");
		String role = in.nextLine();
		switch (role) {
			case "S":
				System.out.println("Enter your username: ");
				String username = in.nextLine();
				System.out.println("Enter your current password: ");
				String password = in.nextLine();
				System.out.println("Enter your studentId: ");
				Integer studentID = in.nextInt();
				in.nextLine();
				
				boolean response = udi.loginUser(studentID, password, role);
				if(response) {
					// student credentials valid -> allow to change password
					System.out.println("Enter your New Password: ");
					String newPassword = in.nextLine();
					udi.updateStudentPassword(studentID, newPassword);
				}else {
					System.out.println("Invalid Student Credentials!");
				}
				
				break;

			case "P":
				System.out.println("Enter your username: ");
				String username2 = in.nextLine();
				System.out.println("Enter your current password: ");
				String password2 = in.nextLine();
				System.out.println("Enter your instructorId: ");
				Integer instructorID = in.nextInt();
				in.nextLine();
				
				boolean response2 = udi.loginUser(instructorID, password2, role);
				if(response2) {
					// professor credentials valid -> allow to change password
					System.out.println("Enter your New Password: ");
					String newPassword = in.nextLine();
					udi.updateProfessorPassword(instructorID, newPassword);
				}else {
					System.out.println("Invalid Professor Credentials!");
				}
				
				break;
				
			case "A":
				System.out.println("Enter your username: ");
				String username3 = in.nextLine();
				System.out.println("Enter your current password: ");
				String password3 = in.nextLine();
				System.out.println("Enter your adminID: ");
				Integer adminID = in.nextInt();
				in.nextLine();
				
				boolean response3 = udi.loginUser(adminID, password3, role);
				if(response3) {
					// admin credentials valid -> allow to change password
					System.out.println("Enter your New Password: ");
					String newPassword = in.nextLine();
					udi.updateStudentPassword(adminID, newPassword);
				}else {
					System.out.println("Invalid Admin Credentials!");
				}
				
				break;

			default:
				System.out.println("Wrong Option Selected!");
				return;
		}

	}

}
