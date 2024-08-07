package com.flipkart.bean;

import java.util.List;

public class ReportCard {
    
	private int studentID;
	private int semester;
	private float cpi;
	private List<RegisteredCourses> registeredCourses;
	
	
	public ReportCard(int studentID, int semester, float cpi, List<RegisteredCourses> registeredCourses) {
		
		this.studentID = studentID;
		this.semester = semester;
		this.cpi = cpi;
		this.registeredCourses = registeredCourses;
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


	public float getCpi() {
		return cpi;
	}


	public void setCpi(float cpi) {
		this.cpi = cpi;
	}


	public List<RegisteredCourses> getRegisteredCourses() {
		return registeredCourses;
	}


	public void setRegisteredCourses(List<RegisteredCourses> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
	
	
	
	
	
}
