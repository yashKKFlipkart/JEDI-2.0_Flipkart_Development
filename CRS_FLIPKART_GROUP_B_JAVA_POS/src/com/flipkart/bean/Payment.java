package com.flipkart.bean;

public class Payment {
    private String paymentID;
    private int studentID;
    private int semester;
    private double amount;
    private boolean paymentStatus;
    private String paymentDate;
    
    public String getPaymentID() {
        return paymentID;
    }
    public int getStudentID() {
        return studentID;
    }
    public int getSemester() {
        return semester;
    }
    public double getPaymentAmount()
    {
        return amount;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public boolean getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }
  
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
   
    public void setSemester(int semester) {
        this.semester = semester;
    }
    public void setPaymentAmount(double amount) {
        this.amount = amount;
    }
    
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    public void makePayment()
    {
        
    }

}
