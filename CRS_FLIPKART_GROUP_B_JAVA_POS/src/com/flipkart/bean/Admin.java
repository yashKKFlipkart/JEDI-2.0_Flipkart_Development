package com.flipkart.bean;

public class Admin extends User{
    private String doj;
    private Integer adminID;

    public Admin() {
    	super();
    }
    
    public Admin(String username, String name, String role, String password, String doj, Integer adminID) {
		super(username, name, role, password,adminID);
		this.doj = doj;
		this.adminID = adminID;
	}
    
	public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

}
