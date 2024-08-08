package com.flipkart.business;

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

    public void recordGrade(Student student, Course course, String grade)  
	{
		
	}

	public void viewEnrolledStudents(String courseID, Integer semesterID) {
		
			
	}

	public void registerCourse(int instructorID, Integer semesterID, String courseID) {
		
	}
	
	public void register() {
		System.out.println("Professor Menu!\n");
	}

	
}