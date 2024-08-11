CREATE DATABASE demo_flipfit;

USE demo_flipfit;

-- Admin table
CREATE TABLE Admin (
    adminID INT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Professor table
CREATE TABLE Professor (
    instructorID INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    department VARCHAR(255),
    designation VARCHAR(255)
);

-- Student table
CREATE TABLE Student (
    studentID INT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    department VARCHAR(255)
);

-- Course table
CREATE TABLE Course (
    courseID INT PRIMARY KEY,
    courseName VARCHAR(255) NOT NULL
);

-- Enrollment table to track which students are enrolled in which courses
CREATE TABLE Student_Course (
    studentID INT,
    courseID INT,
    PRIMARY KEY (studentID, courseID),
    FOREIGN KEY (studentID) REFERENCES Student(studentID),
    FOREIGN KEY (courseID) REFERENCES Course(courseID)
);

-- Payment table
CREATE TABLE Payment (
    paymentID INT AUTO_INCREMENT PRIMARY KEY,
    studentID INT,
    amount FLOAT NOT NULL,
    paymentDate DATE NOT NULL,
    FOREIGN KEY (studentID) REFERENCES Student(studentID)
);

-- ReportCard table
CREATE TABLE ReportCard (
    reportCardID INT AUTO_INCREMENT PRIMARY KEY,
    studentID INT,
    semesterID INT,
    GPA FLOAT,
    FOREIGN KEY (studentID) REFERENCES Student(studentID)
);
