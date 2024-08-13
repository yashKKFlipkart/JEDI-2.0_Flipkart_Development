package com.flipkart.bean;

import java.util.HashMap;

public class ReportCard {


	private Integer studentID;
	private Float cpi;
	HashMap<String, String> grades = new HashMap<String, String>();

	public ReportCard() {
		cpi = (float) 0.0;
		studentID = 0;
	}

	public ReportCard(HashMap<String, String> grades, Integer studentID, Float cgpa) {
		this.grades = grades;
		this.studentID = studentID;
		this.cpi = cgpa;
	}

	public HashMap<String, String> getGrades() {
		return grades;
	}

	public void setGrades(HashMap<String, String> grades) {
		this.grades = grades;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Float getCpi() {
		return cpi;
	}

	public void setCpi(Float cgpa) {
		this.cpi = cgpa;
	}
	
}
