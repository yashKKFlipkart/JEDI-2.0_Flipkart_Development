package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Professor;

public interface ProfessorOperationsInterface {
	
	public void addGrade(Integer studentID, Integer semesterID,String courseID, String alphaGrade);
	
	public void ViewEnrolledStudents(String courseID, Integer semesterID);
	
	public void viewCourseProfessor(int instructorID) ;
	
	public void registerCourse(int instructorID, String courseName, int courseID);
	
	public Professor findProfessorByUsername(String username);

	public void viewProfessors();
	
	
}	
