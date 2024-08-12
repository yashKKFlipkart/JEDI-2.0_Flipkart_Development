package com.flipkart.exception;
import java.sql.SQLException;


public class CourseNotFoundException extends Exception {
	public CourseNotFoundException(String message) {
        super(message);
    }
}
