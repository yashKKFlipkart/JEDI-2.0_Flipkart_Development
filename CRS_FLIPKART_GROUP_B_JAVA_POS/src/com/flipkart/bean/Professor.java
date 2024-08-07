package com.flipkart.bean;

public class Professor {
    private String professorID;
	private String name;
	private String email;
    private String designation;
    private String phoneNumber;

    public String getProfessorID() {
        return professorID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    //TODO Remaining-->


    // public void signUpForCourse()
    // {

    // }

    // public List<CourseCatalog> viewCourses()
    // {

    // }
    // //Add Course class
    // public void recordGrade(Student student,Course course,String grade)
    // {

    // } 
    
}
