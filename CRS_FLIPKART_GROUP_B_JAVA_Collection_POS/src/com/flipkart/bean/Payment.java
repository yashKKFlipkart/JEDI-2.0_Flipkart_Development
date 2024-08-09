package com.flipkart.bean;

public class Payment {


	private Double amount;
	private Integer studentID;
	private Integer paymentID;
	private Boolean paymentStatus;

	public Payment() {
		
	}
	
	public Payment(Integer studentID, Integer paymentID, Boolean paymentStatus, Double amount) {
		this.amount = amount;
		this.studentID = studentID;
		this.paymentID = paymentID;
		this.paymentStatus = paymentStatus;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
