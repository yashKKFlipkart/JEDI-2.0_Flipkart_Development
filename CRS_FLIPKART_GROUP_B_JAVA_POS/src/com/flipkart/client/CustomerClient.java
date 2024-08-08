/**
 * 
 */
package com.flipkart.client;

import java.util.Date;
import java.util.Scanner;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.SemesterRegistration;
import com.flipkart.bean.Student;
import com.flipkart.business.*;

/**
 * 
 */
public class CustomerClient {
	
	public static void main(String args[]) {
		
		// 1 -> Login : (Enter the Username, Enter the password, Role: Student/Professor/Admin)
		//	If Student -> Student Menu, etc
		// 2 -> Register Student
		// 3 -> Update Password
		// 4 -> Exit
		
		Scanner in = new Scanner(System.in);  
		while(true) {
			
			System.out.println("Enter\n1 for Login \n2 for Student Registration \n3 for Updating Password\n4 for exit");
			int optionSelected = in.nextInt();  
			String dummy = in.nextLine();
			switch(optionSelected) {
			case 1: 
				System.out.print("Enter your Role (S for Student, P for Professor, A for Admin)");
				String role = in.next();
				System.out.print("Enter the Username");
				String username = in.next();
				System.out.print("Enter the Password");
				String password = in.next();
				if(role.equals("S")) {
					Student stud = new Student(100, username, "Student", password, "2021", "Computer", "9999988888", 2021);
					StudentOperation sop = new StudentOperation(stud);
					sop.StudentMenu();
				}
				else if(role.equals("P")) {
					Professor prof = new Professor(101, username, "Professor", password, "2020", "Associate", "Computer", "8888899999");
					ProfessorOperation po = new ProfessorOperation(prof);
					po.ProfessorMenu();
				}
				else if(role.equals("A")) {
					Admin admin = new Admin(102, username, "Admin", password, "2019");
					AdminOperation ao = new AdminOperation(admin);
					ao.AdminMenu();
//					System.out.println("Admin Menu!\n");
				}
				else {
					System.out.println("Wrong Option Selected!\n");
				}
				break;
			case 2:
				Date date = new Date();
				SemesterRegistration sr = new SemesterRegistration(100, 1, date);
				System.out.println("This is for Student Registration\n");
				break;
			case 3:
				System.out.println("This is for Updating Password\n");
				System.out.println(" Updating Password Menu");
				System.out.print("Enter the Username");
				String username1 = in.next();
				System.out.print("Enter the Old Password");
				String oldPassword = in.next();
				System.out.print("Enter the New Password");
				String newPassword = in.next();
				System.out.println("Password Updated Successfully!\n");
				break;
			case 4: 
				System.out.println("System Exit. Thank You!\n");
				return;
			default:
				System.out.println("Wrong option Selected!\n");
			}
		}
	}
}
