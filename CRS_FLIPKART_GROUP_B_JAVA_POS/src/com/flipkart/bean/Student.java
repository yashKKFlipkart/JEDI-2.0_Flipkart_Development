package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User{

	private Integer studentID;
	private String department;
	private ArrayList<Integer> courseID = new ArrayList<Integer>();
	
	public Student() {
		super();
	}
	
	public Student(String username, String name, String role, String password,Integer studentID, String department, ArrayList<Integer> courseID) {
		super(username,name,role,password);
		this.studentID = studentID;
		this.department = department;
		this.courseID = courseID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<Integer> getCourseID() {
		return courseID;
	}

	public void setCourseID(ArrayList<Integer> courseID) {
		this.courseID = courseID;
	}
	
	
}
