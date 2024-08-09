package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;

public class StudentOperations {
	private List<Student> students = new ArrayList<>();

public StudentOperations(){
	Student s1 = new Student();
	s1.setDepartment("CS");
	s1.setName("Rohan");
	s1.setPassword("password");
	s1.setRole("Student");
	s1.setStudentID(201);
	s1.setUsername("Rohan");
	students.add(s1);
	Student s2 = new Student();
	s2.setDepartment("CS");
	s2.setName("Mohan");
	s2.setPassword("password");
	s2.setRole("Student");
	s2.setStudentID(202);
	s2.setUsername("Mohan");
	students.add(s2);
	Student s3 = new Student();
	s3.setDepartment("CS");
	s3.setName("Sohan");
	s3.setPassword("password");
	s3.setRole("Student");
	s3.setStudentID(203);
	s3.setUsername("Sohan");
	students.add(s3);
}

	public List<Student> getStudents() {
		return students;
	}
	
	public boolean addStudent(String username, String name, String role, String password,Integer studentID, String department) {
		if(findStudentByUsername(username)==null){
			Student s1 = new Student();
			s1.setDepartment(department);
			s1.setName(name);
			s1.setPassword(password);
			s1.setRole("Student");
			s1.setStudentID(studentID);
			s1.setUsername(username);
			students.add(s1);
			return true;
		}
        return false;
    }
	
	public Student findStudentByUsername(String username){
		for (Student student : students) {
			if(student.getUsername().equals(username)){
				return student;
			}
		}
		return null;
	}
	
	public Student findStudentByStudentId(int studentID) {
		for(Student student : students) {
			if(student.getStudentID().equals(studentID)) {
				return student;
			}
		}
		return null;
	}

//	void registerCourses(int studentId, int courseID) {
//		System.out.println("Register Courses!");
//	}
	
	public boolean addCourse(int studentID, int courseID, String courseName) {
		// creating a course object
		Course course = new Course();
		course.setCourseID(courseID);
		course.setCoursename(courseName);
		course.setAvailableThisSemester(true);
		
		Student student = findStudentByStudentId(studentID);
		student.getregisteredCourses().add(course);
		System.out.println("Course Added Successfully");
		return true;
	}
	
	public boolean dropCourse(int studentID, int courseID) {
		Student student = findStudentByStudentId(studentID);
	    if (student == null) {
	        System.out.println("Student not found!");
	        return false;
	    }
	    
	    List<Course> registeredCourses = student.getregisteredCourses();
	    Course courseToDrop = null;
	    
	    for (Course course : registeredCourses) {
	        if (course.getCourseID().equals(courseID)) {
	            courseToDrop = course; // Store the reference to the course to drop
	            break;
	        }
	    }
	    
	    if (courseToDrop == null) {
	        System.out.println("Course is not registered by student, so cannot drop it!");
	        return false;
	    }
	    
	    registeredCourses.remove(courseToDrop);
	    System.out.println("Course Dropped Successfully");
	    return true;
	}
	
//	boolean finishRegistration(int studentId, int semesterId) {
//		System.out.println("Finish Registration!");
//		return true;
//	}
	
	public ArrayList<Course> viewAvailableCourses(){
		System.out.println("View Available Courses!");
		return null;
	}
	public ReportCard viewReportCard(int StudentID, int semesterId) {
		System.out.println("View Report Card!");
		return null;
		
	}
	Boolean checkPaymentStatus(int StudentID) {
		System.out.println("Check Payment Status!");
		return false;
	}
	void makePayment(Payment payment) {
		System.out.println("Make Payment!");

	}
	public void viewRegisteredCourses(int studentID) {
		Student student = findStudentByStudentId(studentID);
		System.out.println("Courses regsitered by"+student.getName()+"are: ");
		List<Course> registeredCourses = student.getregisteredCourses();
		for(Course course: registeredCourses) {
			System.out.println(course.getCoursename() +" "+ course.getCourseID());
		}
	}

	public void viewStudents() {
		for (Student student : students) {
			System.out.println(student.getName()+" "+student.getDepartment()+" "+student.getStudentID()+" "+student.getUsername());
		}
	}
}
