package com.flipkart.bean;

public class PaymentNotification {

	private String paymentID;
	private int notificationID;
	private String notificationMessage;
	private int studentID;
	
	public PaymentNotification(String paymentID, int notificationID, String notificationMessage, int studentID) {
		
		this.paymentID = paymentID;
		this.notificationID = notificationID;
		this.notificationMessage = notificationMessage;
		this.studentID = studentID;
	}
	
	public String getPaymentID() {
		return paymentID;
	}



	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}



	public int getNotificationID() {
		return notificationID;
	}



	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}



	public String getNotificationMessage() {
		return notificationMessage;
	}



	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}



	public int getStudentID() {
		return studentID;
	}



	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	
}
