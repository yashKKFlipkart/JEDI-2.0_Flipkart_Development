package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.business.*;
import com.flipkart.dao.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CRSProfessorMenu {
	private ProfessorOperationsInterface po = new ProfessorOperations();
	StudentDAOInterface sdi = new StudentDAOImpl();
	ProfessorDAOInterface pdi = new ProfessorDAOImpl();
	private Scanner in=new Scanner(System.in);

	public void CreateProfessorMenu(Integer instructorID) {
		
		while(true){
			Professor prof = pdi.findProfessorById(instructorID);
			System.out.println("\n~~~~~~~~~~~~~~~~~~~ Welcome Professor "+prof.getName()+" ~~~~~~~~~~~~~~~~~~~\n");
			Date currentDate=new Date();
			System.out.println("\t\t"+currentDate);
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("1: Sign Up For Courses");
			System.out.println("2: Show Registered Courses");
			System.out.println("3: Add Grade ");
			System.out.println("4: View Enrolled Students");
			System.out.println("5: Logout ");
			System.out.println("Make your selection by entering the option number");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			int optionSelected =in.nextInt();
			switch(optionSelected)
			{
				case 1:
					signUpForCourses(instructorID);
					break;
				case 2:
					showRegisteredCourses(instructorID);
					break;
				case 3:
					addGrade(instructorID);
					break;
				case 4:
					viewEnrolledStudents(instructorID);
					break;
				case 5:
					return;
			default:
				System.out.println("~~~~~~~~~~~~~ Wrong Choice!!! ~~~~~~~~~~~~~");
			}
		}

	}
		private void showRegisteredCourses(Integer instructorID) {
			
			pdi.viewSignedUpCourses(instructorID);
			
		}
		public int viewEnrolledStudents(Integer instructorID) {
			System.out.println("Enter courseId to check enrolled students");
			int courseID = in.nextInt();
			in.nextLine();
			System.out.println("Viewing enrolled students");
			pdi.ViewEnrolledStudents(courseID);
			return courseID;
			
		}
		public void addGrade(Integer instructorID) {
			System.out.println("Adding grades");
			int courseID = viewEnrolledStudents(instructorID);
			System.out.println("Enter Student Id to add grade");
			int studentID = in.nextInt();
			in.nextLine();
			System.out.println("Enter grade: A, B, C, D, F");
			String grade = in.nextLine();
			pdi.addGrade(studentID, courseID, grade);
			
		}
		public void signUpForCourses(Integer instructorID) {
			System.out.println("Viewing courses for selection");
			
			// Show Available Courses
			ArrayList<Course> courses = sdi.viewAvailableCourses();
			ArrayList<Course> coursesAvailableToSignUp = new ArrayList<Course>();
			// Choose courses to sign up which have no instructor right now
			for(Course course: courses) {
				if(course.getInstructorID() == 0 ) {
					coursesAvailableToSignUp.add(course);
					System.out.println("CourseID:"+course.getCourseID()+" CourseName:"+course.getCoursename()+" InstructorId:"+course.getInstructorID());
				}
			}
			
			// Sign up and add to list
			if(coursesAvailableToSignUp.isEmpty()) {
				System.out.println("No course available for signing up. All courses alloted instructors already!");
				return;
			}
			System.out.println("Enter courseIDs to sign up for course, enter 0 to quit!");
			while(true) {
				System.out.print("Enter courseID:");
				int courseID = in.nextInt();
				in.nextLine();
				
				if(courseID==0) {
					System.out.println("Thank You for signing up for courses!");
					break;
				}
				for(Course course: coursesAvailableToSignUp) {
					if(course.getCourseID() == courseID) {
						pdi.registerCourse(instructorID, course.getCoursename(), courseID);
						break;
					}
				}
			}
		}
}

