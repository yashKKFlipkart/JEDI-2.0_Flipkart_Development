package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Professor;

public interface ProfessorOperationsInterface {
	
	public List<Professor> getProfessors() ;
	public boolean addProfessor(String username, String name, String role, String password, Integer instructorID, String department, String designation);
	
	public Professor findProfessorByUsername(String username);
	public void addGrade(Integer studentID, Integer semesterID,String courseID, String alphaGrade);
	
	public void ViewEnrolledStudents(String courseID, Integer semesterID);
	public void CourseSelection(Integer instructorID, Integer semesterID, String courseID);
	
	public void viewProfessors();
}	
