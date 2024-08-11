package com.flipkart.bean;

public class CourseGrade {
	
	private String grade;
	private Course course;
	//Check

	public CourseGrade() {
		
	}
	
	public CourseGrade(String grade) {
		this.grade = grade;
	}
	
	public CourseGrade(String grade, Course course) {
		this.grade = grade;
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	

}
