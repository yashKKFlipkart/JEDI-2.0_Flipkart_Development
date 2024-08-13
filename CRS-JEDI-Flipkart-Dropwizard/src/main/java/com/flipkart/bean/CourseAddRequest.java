package com.flipkart.bean;

import java.util.List;

public class CourseAddRequest {
    private Integer studentID;
    private List<Integer> courseIDs;

    // Getters and setters
    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public List<Integer> getCourseIDs() {
        return courseIDs;
    }

    public void setCourseIDs(List<Integer> courseIDs) {
        this.courseIDs = courseIDs;
    }
}
