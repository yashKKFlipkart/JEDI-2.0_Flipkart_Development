package com.flipkart.bean;

public class PaymentNotification {


	private String notificationMessage;
	private String notificationID;
	private Integer paymentID;
	private Integer studentID;

	public PaymentNotification() {
		
	}
	
	public PaymentNotification(String notificationMessage, String notificationID, Integer paymentID) {
		this.notificationMessage = notificationMessage;
		this.notificationID = notificationID;
		this.paymentID = paymentID;
	}

	public String getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String message) {
		this.notificationMessage = message;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

}
