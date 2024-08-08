package com.flipkart.business;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.Student;


@SuppressWarnings("unused")
public class ProfessorOperation{

	private Professor professor;
	
	public ProfessorOperation(Professor professor) {
		this.professor = professor;
	}

    public void recordGrade(int studentID, int courseID, String grade)  
	{
		System.out.println("Student Grade Recorded Sucessfully!");
		System.out.println("Student Details:\nStudentID:"+ studentID + " CourseID:"+courseID+" Grade: "+ grade);
	}

	public void viewEnrolledStudent(int courseID, int semesterID) {
		
		System.out.println("Students Enrolled for courseID:" +courseID + " semesterID:"+semesterID+ " are: \nStudentID: 100,\nStudentID: 109\n");
	}

	public void registerCourse(int instructorID, int semesterID, int courseID) {
		System.out.println("Registered for Course Sucessfully!");
		System.out.println("Details:\nInstructorID:"+ instructorID + " SemesterID:"+semesterID+" CourseID: "+ courseID);
	}
	
	public void ProfessorMenu() {
		System.out.println("Professor Menu!\n");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter\n1 for Recording Grade \n2 to View Enrolled Students \n3 for Registering in course");
		int optionSelected = in.nextInt();  
		String dummy = in.nextLine();
		switch(optionSelected) {
		
			case 1:
				System.out.println("Record Grade Menu");
				System.out.println("Enter Student ID");
				int studentID = in.nextInt();
				System.out.println("Enter Course ID");
				int courseID = in.nextInt();
				dummy = in.nextLine();
				System.out.println("Enter Student Grade");
				String grade = in.nextLine(); 
				this.recordGrade(studentID, courseID, grade);
				break;
			case 2:
				System.out.println("Student Enrollment Information Menu");
				System.out.println("Enter Course ID");
				courseID = in.nextInt();
				System.out.println("Enter Semester ID");
				int semesterID = in.nextInt();
				this.viewEnrolledStudent(courseID, semesterID);
				break;
			case 3:
				System.out.println("Register for Course Menu");
				System.out.println("Enter Instructor ID");
				int instructorID = in.nextInt();
				System.out.println("Enter Semester ID");
				semesterID = in.nextInt();
				System.out.println("Enter Course ID");
				courseID = in.nextInt();
				this.registerCourse(instructorID, semesterID, courseID);
				break;
			default:
				System.out.println("Wrong Option Chosen");
		}
		
	}

	
}