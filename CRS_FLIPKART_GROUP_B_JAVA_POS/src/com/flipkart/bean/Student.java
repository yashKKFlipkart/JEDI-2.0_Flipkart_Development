package com.flipkart.bean;

public class Student extends User{
	
	public Student(int studentID) {
		super(studentID);
		this.studentID = studentID;
	}

	public Student(int userID, String name, String role, String password, String joiningYear, String department, String phoneNumber, int batch) {
		super(userID, name, role, password, joiningYear);
		// TODO Auto-generated constructor stub
		this.studentID = userID;
		this.department = department;
		this.phoneNumber = phoneNumber;
		this.batch = batch;
	}
	
	private int studentID;
	private String department;
	private String phoneNumber;
	private int batch;
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}
	
}
