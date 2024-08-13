package com.flipkart.dao;

import com.flipkart.bean.Professor;

/**
 * Interface representing the data access object (DAO) for professor-related operations.
 * Provides methods for professors to manage grades, view their courses, and other tasks.
 */
public interface ProfessorDAOInterface {

    /**
     * Adds a grade for a student in a specific course.
     * 
     * @param studentID Unique identifier of the student.
     * @param courseID Unique identifier of the course.
     * @param alphaGrade The grade in alphabetic format (e.g., A, B, C).
     */
    public void addGrade(Integer studentID, Integer courseID, String alphaGrade);

    /**
     * Displays a list of students enrolled in a specific course.
     * 
     * @param courseID Unique identifier of the course.
     */
    public void ViewEnrolledStudents(Integer courseID);

    /**
     * Displays the courses that a professor has signed up for.
     * 
     * @param instructorID Unique identifier of the professor.
     */
    public void viewSignedUpCourses(int instructorID);

    /**
     * Allows a professor to sign up for a course.
     * 
     * @param instructorID Unique identifier of the professor.
     * @param courseName Name of the course.
     * @param courseID Unique identifier of the course.
     */
    public void registerCourse(int instructorID, String courseName, Integer courseID);

    /**
     * Finds a professor by their username.
     * 
     * @param username Username of the professor.
     * @return Professor object corresponding to the username.
     */
    public Professor findProfessorByUsername(String username);

    /**
     * Finds a professor by their unique identifier.
     * 
     * @param instructorID Unique identifier of the professor.
     * @return Professor object corresponding to the instructor ID.
     */
    public Professor findProfessorById(Integer instructorID);

    /**
     * Displays a list of all professors.
     */
    public void viewProfessors();
}
