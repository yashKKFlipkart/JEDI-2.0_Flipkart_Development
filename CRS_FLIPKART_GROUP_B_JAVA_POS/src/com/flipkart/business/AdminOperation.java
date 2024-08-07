package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;

@SuppressWarnings("unused")
public class AdminOperation{


	public void enableDisableFeePayment(int semesterId) {

	}

	public void approveStudentRegistration(int studentId,int semesterId) {

	}

	public void addProfessor(Professor professor) {

	}

	public void removeProfessor(int professorID) {
	
	}

	public void removeCourse(String courseID) {

	}
	

	public void addCourse(String course_Name, String courseID, int semester) {
		
	}

	
	public HashMap<String,ArrayList<Integer> > viewCourseStudentList(String courseID, int semester, Boolean viewAll) {
		return null;

	}

	public ReportCard generateReportCard(int studentID) {
		return null;


	}

	
	public List<Student> getPendingStudentAccountsList() {
		return null;

		
	}

	public void approveStudentAccount(Integer studentID) {
	}
}