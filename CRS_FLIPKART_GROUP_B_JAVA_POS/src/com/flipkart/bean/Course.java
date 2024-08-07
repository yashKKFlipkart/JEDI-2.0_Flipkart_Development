package com.flipkart.bean;

public class Course {
    
	private int courseID;
	private String courseName;
	private String department;
	private Professor Professor;
	private int semesterOffered;
	private int availableSeats;
	
	
	
	public Course(int courseID, String courseName, String department, com.flipkart.bean.Professor professor,
			int semesterOffered, int availableSeats) {
		
		this.courseID = courseID;
		this.courseName = courseName;
		this.department = department;
		Professor = professor;
		this.semesterOffered = semesterOffered;
		this.availableSeats = availableSeats;
	}
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Professor getProfessor() {
		return Professor;
	}
	public void setProfessor(Professor professor) {
		Professor = professor;
	}
	public int getSemesterOffered() {
		return semesterOffered;
	}
	public void setSemesterOffered(int semesterOffered) {
		this.semesterOffered = semesterOffered;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	
	
	
	
}
