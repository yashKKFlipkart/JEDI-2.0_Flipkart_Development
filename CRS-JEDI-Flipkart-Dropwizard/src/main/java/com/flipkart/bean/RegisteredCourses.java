package com.flipkart.bean;

import java.util.ArrayList;

public class RegisteredCourses {

	private Integer studentID;
	private ArrayList<Integer> courseID = new ArrayList<Integer>();
	
	public RegisteredCourses() {
		
	}
	
	public RegisteredCourses(Integer studentID, ArrayList<Integer> courseID) {
		this.studentID = studentID;
		this.courseID = courseID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public ArrayList<Integer> getCourseID() {
		return courseID;
	}

	public void setCourseID(ArrayList<Integer> courseID) {
		this.courseID = courseID;
	}
	
}
