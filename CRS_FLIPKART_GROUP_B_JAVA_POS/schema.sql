DROP DATABASE IF EXISTS crs; 

CREATE DATABASE crs;

USE crs;

CREATE TABLE User(
	userID INT PRIMARY KEY,
	name VARCHAR(45),
	role VARCHAR(10) NOT NULL,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL
);

-- Admin table
CREATE TABLE Admin (
    adminID INT,
    doj DATETIME,
    PRIMARY KEY (adminID),
    FOREIGN KEY (adminID) REFERENCES User(userID) ON DELETE CASCADE
);


-- Professor table
CREATE TABLE Professor (
    instructorID  INT,
    department VARCHAR(45),
    designation VARCHAR(45),
    PRIMARY KEY (instructorID),
    FOREIGN KEY (instructorID) REFERENCES User(userID) ON DELETE CASCADE
);


-- Course table
CREATE TABLE Course (
    courseID INT PRIMARY KEY,
    courseName VARCHAR(45) NOT NULL,
    instructorID INT,
    totalSeats INT,
    availableSeats INT,
    isAvailableThisSemester TINYINT,
    FOREIGN KEY (instructorID) REFERENCES Professor(instructorID) ON DELETE CASCADE
    
);

CREATE TABLE Student(
	studentID INT,
    department VARCHAR(45),
    PRIMARY KEY (studentID),
    FOREIGN KEY (studentID) REFERENCES User(userID) ON DELETE CASCADE
);

CREATE TABLE CourseGrade (
	studentID INT,
	courseID INT NOT NULL,
	grade VARCHAR(2) NOT NULL,
	PRIMARY KEY (studentID, courseID),
    FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE
) ;

-- Payment table
CREATE TABLE Payment (
    paymentID INT PRIMARY KEY,
    studentID INT NOT NULL,
    amount FLOAT,
    payment_status TINYINT,
    FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE
);

CREATE TABLE PaymentNotification (
	notificationID INT AUTO_INCREMENT PRIMARY KEY,
	paymentID INT NOT NULL,
	studentID INT NOT NULL,
	notification_message VARCHAR(255),
	FOREIGN KEY (paymentID) REFERENCES Payment(paymentID) ON DELETE CASCADE,
    FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE
);



CREATE TABLE RegisteredCourses (
    studentID INT NOT NULL,
    courseID INT NOT NULL,
    PRIMARY KEY (studentID, courseID),
    FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE
);

-- ReportCard table
CREATE TABLE ReportCard (
	studentID INT PRIMARY KEY,
	cpi FLOAT,
	FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE
);

CREATE TABLE SemesterRegistration(
	studentID INT PRIMARY KEY,
	registration_date DATETIME,
	is_approved TINYINT,
	FOREIGN KEY (studentID) REFERENCES Student(studentID) ON DELETE CASCADE
);


