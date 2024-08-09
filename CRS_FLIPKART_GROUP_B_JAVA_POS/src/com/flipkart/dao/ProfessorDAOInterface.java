package com.flipkart.dao;

import com.flipkart.bean.Professor;

public interface ProfessorDAOInterface {

	public void addGrade(Integer studentID, Integer semesterID,String courseID, String alphaGrade);
	
	public void ViewEnrolledStudents(String courseID, Integer semesterID);
	
	public void viewCourseProfessor(int instructorID) ;
	
	public void registerCourse(int instructorID, String courseName, int courseID);
	
	public Professor findProfessorByUsername(String username);

	public void viewProfessors();
	
}
