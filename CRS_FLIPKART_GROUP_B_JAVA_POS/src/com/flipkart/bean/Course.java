package com.flipkart.bean;

public class Course {

	private Integer courseID;
	private String coursename;
	private Integer instructorID;
	private Integer totalSeats;
	private Integer availableSeats;
	private boolean isAvailableThisSemester;
	
	public Course() {
		super();
	}
	
	public Course(Integer courseID, String coursename, Integer instructorID, Integer totalSeats, Integer availableSeats,
			boolean isOffered) {
		super();
		this.courseID = courseID;
		this.coursename = coursename;
		this.instructorID = instructorID;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.isAvailableThisSemester = isOffered;
	}

	

	public Integer getCourseID() {
		return courseID;
	}
	
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Integer getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(Integer instructorID) {
		this.instructorID = instructorID;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public boolean isAvailableThisSemester() {
		return isAvailableThisSemester;
	}

	public void setAvailableThisSemester(boolean isOffered) {
		this.isAvailableThisSemester = isOffered;
	}

	

}
