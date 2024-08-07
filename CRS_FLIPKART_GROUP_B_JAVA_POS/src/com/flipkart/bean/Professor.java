package com.flipkart.bean;

public class Professor extends User {
	
    private int professorID;
    private String designation;
    private String department;
    private String phoneNumber;
	
    public Professor(int userID, String name, String role, String password, String joiningYear, String designation, String department, String phoneNumber) {
		super(userID, name, role, password, joiningYear);
		this.professorID = userID;
		this.designation = designation;
		this.department = department;
		this.phoneNumber = phoneNumber;
	}
    
    
    public int getProfessorID() {
		return professorID;
	}

	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

<<<<<<< HEAD
    public void signUpForCourse()
    {
        
    }
    // public List<CourseCatalog> viewCourses()
    // {
=======
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
>>>>>>> d08018498c8361c4d94438f63a45bd54825898fc

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
    //Changed
}
