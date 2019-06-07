package br.ufrn.imd.books.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/HelloWorld")
public class HelloWorldService {

	@GET
	@Path("/sayHello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {
		return Response.ok(new Object()).build();
	}

	@GET
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newUser() {
		return Response.ok(new Object()).build();
	}
}
