package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.ApprovalRequest;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.dao.AdminDAOInterface;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.dao.ProfessorDAOInterface;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.StudentDAOInterface;

@Path("/admin")
public class AdminController {
	
	AdminDAOInterface adi = new AdminDAOImpl();
	StudentDAOInterface sdi = new StudentDAOImpl();
	ProfessorDAOInterface pdi = new ProfessorDAOImpl();
	
	@GET
    @Path("/pendingapproval")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingStudentAccounts() {
        List<Student> pendingStudents = adi.getPendingStudentAccountsList();
        if (pendingStudents.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No unapproved students").build();
        }
        return Response.status(Response.Status.OK).entity(pendingStudents).build();
    }
	
	@POST
    @Path("/approvestudentregistration/{adminId}/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudentRegistration(
        @PathParam("adminId") int adminId,
        @PathParam("studentId") int studentId) {

        // Approve the student with the given ID
        adi.approveStudentRegistration(studentId);
        return Response.status(Response.Status.OK).entity("Student with ID " + studentId + " approved successfully").build();
    }
	
	@POST
    @Path("/addcourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {

        String courseName = course.getCoursename();
		int courseID = course.getCourseID();
		int totalSeats = course.getTotalSeats();
		int availableSeats=totalSeats;
		boolean isAvailableThisSemester = true;
        
        // Add the course using the DAO
        boolean response = adi.addCourse(courseName, courseID, totalSeats, availableSeats, isAvailableThisSemester);

        if(response) {
        	return Response.status(Response.Status.CREATED)
        			.entity("Course added successfully!")
        			.build();        	
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Failed to add course. Please check the details and try again.")
                .build();
        
    }
	
	@DELETE
    @Path("/deletecourse/{adminId}/{courseID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCourse(
        @PathParam("adminId") int adminId,
        @PathParam("courseID") int courseID) {

        // Remove the course using the DAO
        boolean response = adi.removeCourse(courseID);
        if (response) {
            return Response.status(Response.Status.OK)
                .entity("Course removed successfully!")
                .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Course could not be removed. It may not exist.")
                .build();
        }
    }
	
	@POST
	@Path("/addprofessor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor request) {
	    boolean response = adi.addProfessor(
	        request.getInstructorID(),
	        request.getName(),
	        request.getUsername(),
	        request.getPassword(),
	        request.getDepartment(),
	        request.getDesignation()
	    );
	    
	    if (response) {
	        return Response.status(Response.Status.CREATED)
	                .entity("Professor added successfully!")
	                .build();
	    } else {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to add professor.")
	                .build();
	    }
	}
	
	@DELETE
	@Path("/removeprofessor/{instructorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProfessor(@PathParam("instructorID") int instructorID) {
	    boolean response = adi.removeProfessor(instructorID);
	    
	    if (response) {
	        return Response.status(Response.Status.OK)
	                .entity("Professor removed successfully!")
	                .build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("Professor not found or could not be removed.")
	                .build();
	    }
	}
	
	@POST
	@Path("/sendfeepaymentnotification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendFeePaymentNotification() {
	    try {
	        adi.sendFeePayNotification();
	        return Response.status(Response.Status.OK)
	                .entity("Fee payment notifications sent successfully!")
	                .build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to send fee payment notifications.")
	                .build();
	    }
	}
	
	@GET
	@Path("/viewavailablecourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAvailableCourses() {
	    try {
	        List<Course> courses = sdi.viewAvailableCourses();
	        if (courses.isEmpty()) {
	            return Response.status(Response.Status.NO_CONTENT)
	                    .entity("No available courses found.")
	                    .build();
	        } else {
	            return Response.status(Response.Status.OK)
	                    .entity(courses)
	                    .build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to retrieve available courses.")
	                .build();
	    }
	}
	

}
