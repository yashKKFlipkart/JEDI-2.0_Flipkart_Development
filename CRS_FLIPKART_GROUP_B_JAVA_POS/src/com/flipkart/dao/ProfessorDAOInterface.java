package com.flipkart.dao;

import com.flipkart.bean.Professor;

public interface ProfessorDAOInterface {

	public void addGrade(Integer studentID,Integer courseID, String alphaGrade);
	
	public void ViewEnrolledStudents(Integer courseID);
	
	public void viewCourseProfessor(int instructorID) ;
	
	public void registerCourse(int instructorID, String courseName, Integer courseID);
	
	public Professor findProfessorByUsername(String username);

	public void viewProfessors();
	
}
