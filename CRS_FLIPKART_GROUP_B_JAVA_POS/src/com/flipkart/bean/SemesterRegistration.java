package com.flipkart.bean;

import java.util.Date;

public class SemesterRegistration {

	private Integer studentID;
	private Integer semesterID;
	private Date registrationDate;
	private Boolean isApproved;
	
	
	public SemesterRegistration() {
		
	}
	
	public SemesterRegistration(Integer studentID, Date dateOfRegistration, Boolean isApproved,
			Integer semesterID) {
		this.studentID = studentID;
		this.registrationDate = dateOfRegistration;
		this.isApproved = isApproved;
		this.semesterID = semesterID;
	}
	
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date dateOfRegistration) {
		this.registrationDate = dateOfRegistration;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Integer getSemesterID() {
		return semesterID;
	}
	public void setSemesterID(Integer semesterID) {
		this.semesterID = semesterID;
	}
	
}
