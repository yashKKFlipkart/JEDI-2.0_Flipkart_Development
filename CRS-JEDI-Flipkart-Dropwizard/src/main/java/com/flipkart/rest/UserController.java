package com.flipkart.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.dao.UserDAOInterface;

@Path("/user")
public class UserController {

    @PUT
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User userRequest) {

        UserDAOInterface udi = new UserDAOImpl();
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
            
            return Response.status(200).entity(roleDescription+" Login Successful at " + dtf.format(now)).build();
        } else {
            return Response.status(401).entity("Invalid credentials!").build();
        }
    }
}
