package com.flipkart.bean;
import java.util.Date;

public class Payment {
    
	private String paymentID;
	private int studentID;
	private int semester;
	private double amount;
	private boolean paymentStatus;
	private Date paymentDate;
	
	public Payment(String paymentID, int studentID, int semester, double amount, boolean paymentStatus,
			Date paymentDate) {
		
		this.paymentID = paymentID;
		this.studentID = studentID;
		this.semester = semester;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}
	
	
	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	
	
	
	
}
