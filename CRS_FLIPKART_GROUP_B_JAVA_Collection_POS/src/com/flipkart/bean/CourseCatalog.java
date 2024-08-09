package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalog {

	private List<Course> courseCatalog;
	
	public CourseCatalog() {
		courseCatalog = new ArrayList<Course>();
	}

	public CourseCatalog(List<Course> courseCatalog) {
		this.courseCatalog = courseCatalog;
	}

	public List<Course> getCourseCatalog() {
		return courseCatalog;
	}

	public void setCourseCatalog(List<Course> courseCatalog) {
		this.courseCatalog = courseCatalog;
	}
	
	public boolean addCourseToCourseCatalog(Course course) {
	    if (course == null) {
	        System.out.println("Cannot add a null course to the catalog.");
	        return false;
	    }
	    this.courseCatalog.add(course);
	    return true;
	}

	public boolean removeCourseFromCourseCatalog(Course course) {
	    if (course == null) {
	        System.out.println("Cannot remove a null course from the catalog.");
	        return false;
	    }
	    boolean removed = this.courseCatalog.remove(course);
	    if (!removed) {
	        System.out.println("Course not found in the catalog.");
	    }
	    return removed;
	}
	
}
