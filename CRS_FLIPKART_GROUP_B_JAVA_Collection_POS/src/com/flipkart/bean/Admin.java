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
    
    public Admin(String username, String name, String role, String password, String doj) {
		super(username, name, role, password);
		this.doj = doj;
	}
    
	public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

}
