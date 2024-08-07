package com.flipkart.bean;

import java.util.Date;

public class SemesterRegistration {
    
	private int studentID;
	private int semester;
	private Date dateOfRegistration;
	
	
	public SemesterRegistration(int studentID, int semester, Date dateOfRegistration) {
		this.studentID = studentID;
		this.semester = semester;
		this.dateOfRegistration = dateOfRegistration;
	}
	
	
	public int getStudentID() {
		return studentID;
	}


	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	public int getSemester() {
		return semester;
	}


	public void setSemester(int semester) {
		this.semester = semester;
	}


	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}


	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	
	
}
