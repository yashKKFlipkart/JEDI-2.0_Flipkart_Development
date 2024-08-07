package com.flipkart.bean;

public class CourseGrade {
	
	private Course course;
    private String grade;
	
    
    
    public CourseGrade(Course course, String grade) {
		
		this.course = course;
		this.grade = grade;
	}
    
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
    
}
