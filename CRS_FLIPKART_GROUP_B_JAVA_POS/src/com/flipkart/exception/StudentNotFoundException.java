package com.flipkart.exception;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException; // For SQL related exceptions
import java.util.logging.Logger; // For logging purposes
import java.util.logging.Level;  // For setting logging levels


public class StudentNotFoundException extends Exception {
	public StudentNotFoundException(String message) {
        super(message);
    }
}
