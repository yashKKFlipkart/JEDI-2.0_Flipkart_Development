/**
 * 
 */
package com.flipkart.client;

import com.flipkart.bean.Admin;
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
	private StudentOperations sOps = new StudentOperations();
	private ProfessorOperations pOps = new ProfessorOperations() ;

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
			System.out.println("4: Add Student");
			System.out.println("5: Show all students");
			System.out.println("6: Add Professor");
			System.out.println("7: Show all professors");
			System.out.println("8: Exit ");
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
					addStudent();
					break;
					
				case 5:
					showAllStudents();
					break;
					
				case 6:
					addProfessor();
					break;
					
				case 7:
					showAllProfessors();
					break;
					
				case 8:
					in.close();
					System.out.println("Exited Successfully!");
					return;
				default:
					System.out.println("Invalid input");
					break;
			}
		}
	}

	private void showAllStudents(){
		sOps.viewStudents();
	}
	private void showAllProfessors(){
		pOps.viewProfessors();
	}


	private void login() {

		String username=null;
		String password=null;
		String role;

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Enter your username: ");
		username = in.nextLine();
		System.out.println("Enter your password: ");
		password = in.nextLine();
		System.out.println("Choose your Role: ");
		System.out.println("S for Student");
		System.out.println("P for Professor");
		System.out.println("A for Admin");
		role = in.nextLine();
		switch (role) {
		case "S":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Student Login Successful");
            CRSStudentMenu stud = new CRSStudentMenu();
            stud.CreateStudentMenu(username);
			break;

		case "P":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Professor Login Successful");
            CRSProfessorMenu prof = new CRSProfessorMenu();
            prof.CreateProfessorMenu(username);
			break;

		case "A":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Admin Login Successful");
            CRSAdminMenu adm = new CRSAdminMenu();
            adm.CreateAdminMenu(username);
			break;

		default:
			System.out.println("Wrong Option Selected!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
	
	void registerStudent() {
		System.out.println("In Register Student Menu");
	}
	
	void addStudent() {
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

		if(sOps.addStudent(username,name,"student",password,studentID,department)){
			System.out.println("Student Added Successfully");
		}else{
			System.out.println("student already exists");
		}
	}
	void addProfessor() {
		System.out.println("enter username");
		String username = in.nextLine();
		System.out.println("enter password");
		String password = in.nextLine();
		System.out.println("enter name");
		String name = in.nextLine();
		System.out.println("enter department");
		String department = in.nextLine();
		System.out.println("enter instructorID");
		int instructorID = in.nextInt();
		System.out.println("enter designation");
		String designation = in.nextLine();

		if(pOps.addProfessor(username,name,"professor",password,instructorID,department,designation)){
			System.out.println("Professor Added Successfully");
		}else{
			System.out.println("Professor already exists");
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

				Student currentStudent= sOps.findStudentByUsername(username);

				if(currentStudent!=null){
					if (currentStudent.getPassword().equals(password)) {
						System.out.println("Enter your New Password: ");
						String newPassword = in.nextLine();
						currentStudent.setPassword(newPassword);
						System.out.println("Successfully updated password");
					}
					else {
						System.out.println("Invalid Password");
					}
				}
				break;

			case "P":
				System.out.println("Enter your username: ");
				String profUsername = in.nextLine();
				System.out.println("Enter your password: ");
				String profPassword = in.nextLine();
				Professor currentProfessor = pOps.findProfessorByUsername(profUsername);
				if(currentProfessor!=null){
					if (currentProfessor.getPassword().equals(profPassword)) {
						System.out.println("Enter your New Password: ");
						String newPassword = in.nextLine();
						currentProfessor.setPassword(newPassword);
						System.out.println("Successfully updated password"+ currentProfessor.getPassword());
					}
					else{
						System.out.println("Invalid Password");
					}
				}

				break;
			case "A":
				System.out.println("Enter your username: ");
				String adminUsername = in.nextLine();
				System.out.println("Enter your password: ");
				String adminPassword = in.nextLine();
				AdminOperations admin = new AdminOperations();
				Admin currentAdmin= new Admin();
				String pass= admin.findAdminByUsername(adminUsername);
				if (pass.equals(adminPassword)) {
					System.out.println("Enter your New Password: ");
					String newAdminPassword = in.nextLine();
					currentAdmin.setPassword(newAdminPassword);
					System.out.println("Successfully updated password");
					System.out.println("Your new Password is: " + currentAdmin.getPassword());
				}
				else {
					System.out.println("Invalid Password");
				}

				break;

			default:
				return;
		}

	}

}
