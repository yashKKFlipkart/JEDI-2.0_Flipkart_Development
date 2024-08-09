package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfessorOperations {
	
	private List<Professor> professors = new ArrayList<>();
	
	
	public ProfessorOperations() {
		Professor p1 = new Professor();
		p1.setName("Rohit");
		p1.setUsername("Rohit");
		p1.setPassword("password");
		p1.setDepartment("Computer");
		p1.setInstructorID(101);
		p1.setDesignation("Associate");
		p1.setRole("Professor");
		professors.add(p1);
		Professor p2 = new Professor();
		p2.setName("Mohit");
		p2.setUsername("Mohit");
		p2.setPassword("password");
		p2.setDepartment("Computer");
		p2.setInstructorID(102);
		p2.setDesignation("Senior");
		p2.setRole("Professor");
		professors.add(p2);
	}
	
	public List<Professor> getProfessors() {
		return professors;
	}
	
	public boolean addProfessor(String username, String name, String role, String password, Integer instructorID, String department, String designation) {
		if(findProfessorByUsername(username)==null){
			Professor p1 = new Professor();
			p1.setName(name);
			p1.setUsername(username);
			p1.setPassword(password);
			p1.setDepartment(department);
			p1.setInstructorID(instructorID);
			p1.setDesignation(designation);
			p1.setRole("Professor");
			professors.add(p1);
			return true;
		}
		return false;
	}
	public Professor findProfessorByUsername(String username){
		for (Professor professor : professors) {
			if(professor.getUsername().equals(username)){
				return professor;
			}
		}
		return null;
	}
	void addGrade(Integer studentID, Integer semesterID,String courseID, String alphaGrade) {
		System.out.println("Add Grade");
	}
	void ViewEnrolledStudents(String courseID, Integer semesterID) {
		System.out.println("View Enrolled Students");
	}
	void CourseSelection(Integer instructorID, Integer semesterID, String courseID) {
		System.out.println("Course Selection");
	}
	public void viewProfessors() {
		for (Professor professor : professors) {

			System.out.println(professor.getName()+" "+professor.getUsername()+" "+professor.getInstructorID()+" "+professor.getDepartment() + " " + professor.getDesignation());
		}
	}
}
