/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

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
//				System.out.println("The Username is: "+username);
				System.out.print("Enter the Password");
				String password = in.next();
//				System.out.println("The Password is: "+password);
				if(role.equals("S")) {
					System.out.println("Student Menu!\n");
				}
				else if(role.equals("P")) {
					System.out.println("Professor Menu!\n");
				}
				else if(role.equals("A")) {
					System.out.println("Admin Menu!\n");
				}
				else {
					System.out.println("Wrong Option Selected!\n");
				}
				break;
			case 2:
				System.out.println("This is for Student Registration\n");
				break;
			case 3:
				System.out.println("This is for Updating Password\n");
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
