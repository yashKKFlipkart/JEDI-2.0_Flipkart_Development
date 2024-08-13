package com.flipkart.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseGrade;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.StudentDAOInterface;

@Path("/professor")
public class ProfessorController {
	
	StudentDAOInterface sdi = new StudentDAOImpl();
	ProfessorDAOInterface pdi = new ProfessorDAOImpl();
	
	@POST
    @Path("/signup/{instructorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUpForCourses(@PathParam("instructorID") int instructorID, ArrayList<Integer> courseIDs) {
        ArrayList<Course> courses = sdi.viewAvailableCourses();
        ArrayList<Course> coursesAvailableToSignUp = new ArrayList<>();

        for (Course course : courses) {
            if (course.getInstructorID() == 0) {
                coursesAvailableToSignUp.add(course);
            }
        }

        if (coursesAvailableToSignUp.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No courses available for sign-up. All courses have instructors already.").build();
        }

        for (int courseID : courseIDs) {
            if (courseID == 0) {
                continue;
            }
            for (Course course : coursesAvailableToSignUp) {
                if (course.getCourseID() == courseID) {
                    pdi.registerCourse(instructorID, course.getCoursename(), courseID);
                    break;
                }
            }
        }

        return Response.status(Response.Status.OK).entity("Signed up for selected courses successfully.").build();
    }
	
	
	@GET
    @Path("/viewregisteredcourses/{instructorID}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showRegisteredCourses(@PathParam("instructorID") int instructorID) {
        ArrayList<Course> courses = pdi.viewSignedUpCourses(instructorID);

        if (courses.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No courses registered for this instructor.").build();
        }

        return Response.status(Response.Status.OK).entity(courses).build();
    }
	
	@GET
    @Path("/viewenrolledstudents/{courseID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(@PathParam("courseID") int courseID) {
        ArrayList<Student> enrolledStudents = pdi.ViewEnrolledStudents(courseID);

        if (enrolledStudents.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No students enrolled in this course.").build();
        }

        return Response.status(Response.Status.OK).entity(enrolledStudents).build();
    }
	
	@POST
    @Path("/addgrade/{instructorID}/{courseID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGrade(
        @PathParam("instructorID") int instructorID,
        @PathParam("courseID") int courseID,
        CourseGrade gradeRequest) {

        // Extract details from the request
        int studentID = gradeRequest.getStudentID();
        String grade = gradeRequest.getGrade();

        // Add grade using the DAO method
        pdi.addGrade(studentID, courseID, grade);

        return Response.status(Response.Status.OK).entity("Grade added successfully.").build();
    }
	
}
