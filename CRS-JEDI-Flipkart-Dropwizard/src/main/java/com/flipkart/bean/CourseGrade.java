package com.flipkart.bean;

public class CourseGrade {
	
	private Integer studentID;
	private Integer courseID;
	private String grade;
	

	public CourseGrade() {
		
	}
	
	public CourseGrade(Integer studentID, Integer courseID, String grade) {
		this.studentID = studentID;
		this.courseID = courseID;
		this.grade = grade;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	

}
