package com.flipkart.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.PasswordUpdateRequest;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.StudentDAOInterface;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.dao.UserDAOInterface;

@Path("/user")
public class UserController {
	
	UserDAOInterface udi = new UserDAOImpl();
	StudentDAOInterface sdi = new StudentDAOImpl();

	@PUT
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(User userRequest) {

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		Integer userID = userRequest.getUserID();
		String username = userRequest.getUsername();
		String password = userRequest.getPassword();
		String role = userRequest.getRole();

		if (userID == null || username == null || password == null || role == null) {
			return Response.status(400).entity("All fields are required").build();
		}

		boolean response = udi.loginUser(userID, password, role);

		if (response) {
			String roleDescription = "";
			switch (role) {
			case "S":
				roleDescription = "Student";
				break;
			case "P":
				roleDescription = "Professor";
				break;
			case "A":
				roleDescription = "Admin";
				break;
			default:
				System.out.println("Wrong Option Selected!");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				return Response.status(400).entity("Invalid role selected").build();
			}

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(roleDescription + " Login Successful");
			System.out.println("Welcome " + username);

			return Response.status(200).entity(roleDescription + " Login Successful at " + dtf.format(now)).build();
		} else {
			return Response.status(401).entity("Invalid credentials!").build();
		}
	}

	// POST API for student self registration

	@POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStudent(Student student) {
        // Check if the student object is valid
        if (student == null || student.getUsername() == null || student.getName() == null ||
            student.getPassword() == null || student.getStudentID() == null || student.getDepartment() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("All fields are required").build();
        }

        // Attempt to add the student
        boolean response = sdi.addStudent(student.getUsername(), student.getName(), "S", student.getPassword(), student.getStudentID(), student.getDepartment());

        if (!response) {
            System.out.println("User was not added");
            System.out.println("=======================================");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add student").build();
        } else {
            System.out.println("User added successfully! Wait for admin approval!");
            System.out.println("=======================================");
            String result = "Added student: " + student.getName() + " | Student ID: " + student.getStudentID()
                    + ". Wait for admin approval!";
            return Response.status(Response.Status.CREATED).entity(result).build();
        }
    }
	
	@PUT
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePassword(PasswordUpdateRequest request) {
        // Validate the request object
        if (request == null || request.getRole() == null || request.getUsername() == null ||
            request.getCurrentPassword() == null || request.getNewPassword() == null || request.getUserId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("All fields are required").build();
        }

        boolean validCredentials = false;

        switch (request.getRole()) {
            case "S":
                validCredentials = udi.loginUser(request.getUserId(), request.getCurrentPassword(), "S");
                if (validCredentials) {
                    udi.updateStudentPassword(request.getUserId(), request.getNewPassword());
                }
                break;

            case "P":
                validCredentials = udi.loginUser(request.getUserId(), request.getCurrentPassword(), "P");
                if (validCredentials) {
                    udi.updateProfessorPassword(request.getUserId(), request.getNewPassword());
                }
                break;

            case "A":
                validCredentials = udi.loginUser(request.getUserId(), request.getCurrentPassword(), "A");
                if (validCredentials) {
                    udi.updateAdminPassword(request.getUserId(), request.getNewPassword()); // Adjust method name if needed
                }
                break;

            default:
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid role").build();
        }

        if (validCredentials) {
            return Response.status(Response.Status.OK).entity("Password updated successfully").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }

}
