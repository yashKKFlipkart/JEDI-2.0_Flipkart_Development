package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

@SuppressWarnings("unused")
public class AdminOperation{

	private Admin admin;
    private List<Professor> professors = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Student> pendingStudents = new ArrayList<>();
    private HashMap<Integer, Boolean> feePaymentStatus = new HashMap<>();
	
	public AdminOperation(Admin admin) {
		this.admin = admin;
	}
	

	public void enableDisableFeePayment(int semesterId) {
		if (feePaymentStatus.containsKey(semesterId)) {
            boolean currentStatus = feePaymentStatus.get(semesterId);
            feePaymentStatus.put(semesterId, !currentStatus);
        } else {
            feePaymentStatus.put(semesterId, true);
        }
        System.out.println("Fee payment for semester " + semesterId + " is now " + (feePaymentStatus.get(semesterId) ? "enabled" : "disabled"));
	}

	public void approveStudentRegistration(int studentId,int semesterId) {
		for (Student student : pendingStudents) {
            if (student.getUserID() == studentId) {
                pendingStudents.remove(student);
                System.out.println("Student registration for student ID " + studentId + " has been approved for semester " + semesterId);
                return;
            }
        }
        System.out.println("Student ID " + studentId + " not found in pending registrations.");
	}

	public void addProfessor(Professor professor) {
		professors.add(professor);
        System.out.println("Professor " + professor.getName() + " added successfully!");	
        }

	public void removeProfessor(int professorID) {
		 professors.removeIf(professor -> professor.getUserID() == professorID);
	     System.out.println("Professor with ID " + professorID + " removed successfully");	
	     }

	public void removeCourse(int courseID) {
		courses.removeIf(course -> course.getCourseID() == courseID);
        System.out.println("Course with ID " + courseID + " removed successfully");	}
	

	public void addCourse(String courseName, int courseID, int semester) {
		Course course = new Course(courseID, courseName, courseName, null, semester, semester);
        courses.add(course);
        System.out.println("Course " + courseName + " added successfully");	}

	
	public HashMap<String,ArrayList<Integer> > viewCourseStudentList(String courseID, int semester, Boolean viewAll) {
		HashMap<String, ArrayList<Integer>> courseStudentList = new HashMap<>();
        courseStudentList.put(courseID, new ArrayList<>()); 
        System.out.println("Course student list viewed successfully");
        return courseStudentList;
	}

	public ReportCard generateReportCard(int studentID) {
		List<RegisteredCourses> registeredCourses = new ArrayList<>(); 
        float cpi = 8.5f;  
        ReportCard reportCard = new ReportCard(studentID, 1, cpi, registeredCourses); 
        System.out.println("Report card generated for student ID " + studentID);
        return reportCard;

	}

	
	public List<Student> getPendingStudentAccountsList() {
		System.out.println("Fetching list of pending student accounts");
        return pendingStudents;
		
	}

	public void approveStudentAccount(Integer studentID) {
		for (Student student : pendingStudents) {
            if (student.getStudentID() == studentID) {
                pendingStudents.remove(student);
                System.out.println("Student account with ID " + studentID + " approved");
                return;
            }
        }
        System.out.println("Student account with ID " + studentID + " not found in pending list.");
	}
	
	
	public void AdminMenu() {
		System.out.println("Welcome to Admin Menu!\n");
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 to Add Course");
		System.out.println("Enter 2 to Remove Course");
		System.out.println("Enter 3 to Add Professor");
		System.out.println("Enter 4 to Remove Professor");
		int optionSelected = in.nextInt();  
		String dummy = in.nextLine();
		switch(optionSelected) {
		
			case 1:
				System.out.println("Add Courses Menu");
				System.out.println("Enter Course Name");
				String courseName = in.nextLine();
				System.out.println("Enter Course ID");
				int courseId = in.nextInt();
				System.out.println("Enter Semester ID");
				int semesterID = in.nextInt();
				this.addCourse(courseName, courseId, semesterID);
				break;
			case 2:
				System.out.println("Remove Courses Menu");
				System.out.println("Enter Course ID");
				courseId = in.nextInt();
				this.removeCourse(courseId);
				break;
			case 3:
				System.out.println("Add Professor Menu");
				Professor prof = new Professor(501, "Rohit", "Professor", "pass", "2020", "Associate", "Computer", "8888899999");
				this.addProfessor(prof);
				break;
			case 4:
				System.out.println("Remove Professor Menu");
				System.out.println("Enter Professor ID");
				int professorID = in.nextInt();
				this.removeProfessor(professorID);
				break;
			default:
				System.out.println("Wrong Option Chosen");
		}
		
		
	}
}