package com.flipkart.bean;

public class Admin extends User{
    private String doj;

    public Admin(String doj) {
    	super();
    	this.doj = doj;
    }
    public Admin() {
    	super();
    }
    
    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

}
