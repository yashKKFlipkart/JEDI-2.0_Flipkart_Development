package com.flipkart.client;

import com.flipkart.business.*;
import java.util.Scanner;

public class CRSProfessorMenu {
	private ProfessorOperationsInterface po = new ProfessorOperations();

	public void CreateProfessorMenu(String instructorID) {
		Scanner in=new Scanner(System.in);
		
		while(true){
			
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Professor ~~~~~~~~~~~~~~~~~~~\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Selection");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: View Enrolled Students");
			System.out.println("Press 4: Logout ");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			switch(optionSelected)
			{
				case 1:
					courseSelection(instructorID);
					break;
				case 2:
					addGrade(instructorID);
					break;
					
				case 3:
					viewEnrolledStudents(instructorID);
					break;
				case 4:
					return;
			default:
				System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
			}
		}

	}
		public void viewEnrolledStudents(String instructorID) {
			System.out.println("Viewing enrolled students");
		}
		public void addGrade(String instructorID) {
			System.out.println("Adding grades");
		}
		public void courseSelection(String instructorID) {
			System.out.println("Viewing courses for selection");
		}
}

