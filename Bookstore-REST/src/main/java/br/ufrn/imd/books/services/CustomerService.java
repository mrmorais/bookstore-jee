package br.ufrn.imd.books.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.books.beans.customer.CustomerLocalEJB;
import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;
import br.ufrn.imd.books.utils.ExceptionWrapper;

/**
 * CustomerService
 * @author Maradona Morais
 */
@Path("/customer")
public class CustomerService {

  @EJB
  private CustomerLocalEJB customerEJB;

  @POST
  @Path("/create")
  @Produces("application/json; charset=UTF-8")
  @Consumes("application/x-www-form-urlencoded; charset=UTF-8")
  public Response create(
      @FormParam("name") final String name
		, @FormParam("contact") final String contact
  ) {
    try {
      CustomerEntity customer = customerEJB.createNew(
        new CustomerEntity(name, contact)
      );
      return Response.ok(customer).build();
    } catch(BookstoreUnknownException e) {
      return Response.status(500).entity(new ExceptionWrapper(e)).build();
    }
  }

  @GET
	@Path("/all")
	@Produces("application/json; charset=UTF-8")
	public Response getAll() {
		try {
			List<CustomerEntity> allCustomers = customerEJB.getAll();
			return Response.ok(allCustomers).build();
		} catch(BookstoreUnknownException unknownException) {
			return Response.status(500).entity(new ExceptionWrapper(unknownException)).build();
		}
  }  
}