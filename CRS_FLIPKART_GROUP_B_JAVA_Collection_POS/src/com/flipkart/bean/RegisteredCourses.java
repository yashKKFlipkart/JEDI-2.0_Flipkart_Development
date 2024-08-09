package com.flipkart.bean;

import java.util.ArrayList;

public class RegisteredCourses {

	private Integer studentID;
	private Integer semesterID;
	private ArrayList<Integer> courseID = new ArrayList<Integer>();
	
	public RegisteredCourses() {
		
	}
	
	public RegisteredCourses(Integer studentID, Integer semesterID, ArrayList<Integer> courseID) {
		this.studentID = studentID;
		this.semesterID = semesterID;
		this.courseID = courseID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(Integer semesterID) {
		this.semesterID = semesterID;
	}

	public ArrayList<Integer> getCourseID() {
		return courseID;
	}

	public void setCourseID(ArrayList<Integer> courseID) {
		this.courseID = courseID;
	}
	
}
