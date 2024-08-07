package com.flipkart.bean;
import java.util.List;

public class CourseCatalog {
    private List<Course> courses;

	public CourseCatalog(List<Course> courses) {
		this.setCourses(courses);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
    
}
