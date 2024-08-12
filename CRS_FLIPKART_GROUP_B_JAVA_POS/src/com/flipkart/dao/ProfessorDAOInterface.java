package com.flipkart.dao;

import com.flipkart.bean.Professor;

public interface ProfessorDAOInterface {

	public void addGrade(Integer studentID,Integer courseID, String alphaGrade);
	
	// view enrolled students
	public void ViewEnrolledStudents(Integer courseID);
	
	// view courses signed by by the professor
	public void viewSignedUpCourses(int instructorID) ;
	
	// sign up for course
	public void registerCourse(int instructorID, String courseName, Integer courseID);
	
	public Professor findProfessorByUsername(String username);
	
	public Professor findProfessorById(Integer instructorID);

	public void viewProfessors();
	
}
