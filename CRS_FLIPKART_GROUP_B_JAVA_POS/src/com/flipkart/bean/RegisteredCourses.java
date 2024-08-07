package com.flipkart.bean;

public class RegisteredCourses {
    
	private int courseID;
	private int semester;
	private int studentID;
	private String grade;
	
	public RegisteredCourses(int courseID, int semester, int studentID, String grade) {
		this.courseID = courseID;
		this.semester = semester;
		this.studentID = studentID;
		this.grade = grade;
	}
	
	
	public int getCourseID() {
		return courseID;
	}



	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}



	public int getSemester() {
		return semester;
	}



	public void setSemester(int semester) {
		this.semester = semester;
	}



	public int getStudentID() {
		return studentID;
	}



	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	
	
	
	
}
