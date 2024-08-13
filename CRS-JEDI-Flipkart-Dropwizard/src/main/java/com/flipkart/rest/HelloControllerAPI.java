package com.flipkart.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
// javax -> java extension; ws-> web service; rs -> restful service
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloapi")
@Produces(MediaType.APPLICATION_JSON)
public class HelloControllerAPI {
	
	@GET
	@Path("/hellog")
	public String helloGetService() {
		return "This is my First Dropward Service";
	}
	
	@POST
	@Path("/hellop")
	public String helloPostService() {
		return "This is my First Dropward Service";
	}
	
	@PUT
	@Path("/helloput")
	public String helloPutService() {
		return "This is my First Dropward Service";
	}
	
	@DELETE
	@Path("/hellod")
	public String helloDeleteService() {
		return "This is my First Dropward Service";
	}
	
	
}
