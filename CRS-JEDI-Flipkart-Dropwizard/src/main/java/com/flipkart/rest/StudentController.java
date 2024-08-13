package com.flipkart.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseAddRequest;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.ReportCard;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.StudentDAOInterface;

@Path("/student")
public class StudentController {
	
	StudentDAOInterface sdi = new StudentDAOImpl();
	AdminDAOInterface adi = new AdminDAOImpl();
	
	@GET
    @Path("/availablecourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAvailableCourses() {
        ArrayList<Course> courses = sdi.viewAvailableCourses();

        if (courses == null || courses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No courses available").build();
        }

        return Response.status(Response.Status.OK).entity(courses).build();
    }
	
	@POST
    @Path("/addcourses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(CourseAddRequest request) {
        if (request == null || request.getStudentID() == null || request.getCourseIDs() == null || request.getCourseIDs().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Student ID and course IDs are required").build();
        }

        // Fetch available courses
        ArrayList<Course> courses = sdi.viewAvailableCourses();

        // Map course IDs to course names
        List<Integer> courseIDs = request.getCourseIDs();
        List<String> addedCourses = new ArrayList<>();

        for (Course course : courses) {
            int courseID = course.getCourseID();
            if (courseIDs.contains(courseID)) {
                sdi.addCourse(request.getStudentID(), courseID, course.getCoursename());
                addedCourses.add(course.getCoursename());
            }
        }

        if (addedCourses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No matching courses found to add").build();
        }

        return Response.status(Response.Status.OK).entity("Courses added successfully: " + String.join(", ", addedCourses)).build();
    }
	
	@POST
    @Path("/dropcourses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(RegisteredCourses request) {
        if (request == null || request.getStudentID() == null || request.getCourseID() == null || request.getCourseID().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Student ID and course IDs are required").build();
        }

        List<Integer> courseIDs = request.getCourseID();
        List<Integer> removedCourses = new ArrayList<>();

        for (Integer courseID : courseIDs) {
            if (courseID == 0) {
                break;
            }
            boolean response = sdi.dropCourse(request.getStudentID(), courseID);
            if (response) {
                removedCourses.add(courseID);
            }
        }

        if (removedCourses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No courses were removed").build();
        }

        return Response.status(Response.Status.OK).entity("Courses removed successfully: " + removedCourses).build();
    }
	
	@POST
    @Path("/finishregistration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response finishRegistration(Integer studentID) {
        if (studentID == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Student ID is required").build();
        }

        // Add payment entry for the student
        sdi.addPaymentEntry(studentID, studentID);

        // Log the completion of registration
        System.out.println("Student Registration Completed Successfully!");

        return Response.status(Response.Status.OK).entity("Registration completed successfully! ").build();
    }

	@GET
    @Path("/registeredcourses/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRegisteredCourses(@PathParam("studentID") int studentID) {
        List<Course> registeredCourses = sdi.viewRegisteredCourses(studentID);

        if (registeredCourses == null || registeredCourses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No registered courses found for student ID: " + studentID).build();
        }

        return Response.status(Response.Status.OK).entity(registeredCourses).build();
    }
	
	@GET
    @Path("/reportcard/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewReportCard(@PathParam("studentID") int studentID) {
        ReportCard rc = adi.generateReportCard(studentID);

        if (rc == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No report card available for the student with ID: " + studentID).build();
        }

        Float cpi = rc.getCpi();
        HashMap<String, String> grades = rc.getGrades();

        ReportCard response = new ReportCard(grades, studentID, cpi);
        return Response.status(Response.Status.OK).entity(response).build();
    }
	
	@GET
    @Path("/paymentstatus/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkPaymentStatus(@PathParam("studentID") int studentID) {
        boolean response = sdi.checkPaymentStatus(studentID);

        if (response) {
            return Response.status(Response.Status.OK).entity("Fees Already Paid").build();
        } else {
            return Response.status(Response.Status.PAYMENT_REQUIRED).entity("Fee Payment Pending! Please make payment").build();
        }
    }
	
	@POST
    @Path("/makepayment/{studentID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePayment(@PathParam("studentID") int studentID) {
        boolean paymentStatus = sdi.checkPaymentStatus(studentID);

        if (paymentStatus) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Fees Already Paid").build();
        }

        boolean paymentMade = sdi.makePayment(studentID);
        if (paymentMade) {
            adi.PaymentCompletionNotification(studentID);
            return Response.status(Response.Status.OK).entity("Payment Successful and Notification Sent").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Payment Failed").build();
        }
    }
	
	
}
