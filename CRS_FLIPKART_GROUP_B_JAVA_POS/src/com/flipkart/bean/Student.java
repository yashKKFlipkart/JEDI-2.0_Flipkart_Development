package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User{

	private Integer studentID;
	private String department;
	private ArrayList<Course> registeredCourses = new ArrayList<Course>();
	
	public Student() {
		super();
	}
	
	public Student(String username, String name, String role, String password,Integer studentID, String department, ArrayList<Course> registeredCourses) {
		super(username,name,role,password, studentID);
		this.studentID = studentID;
		this.department = department;
		this.registeredCourses = registeredCourses;
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

	public ArrayList<Course> getregisteredCourses() {
		return registeredCourses;
	}

	public void setregisteredCourses(ArrayList<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
	
	
}
