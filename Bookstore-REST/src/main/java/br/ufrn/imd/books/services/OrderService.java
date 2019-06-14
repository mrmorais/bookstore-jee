package br.ufrn.imd.books.services;

import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.books.beans.order.OrderLocalEJB;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.IntentEntity.IntentType;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;
import br.ufrn.imd.books.utils.ExceptionWrapper;

/**
 * OrderService
 * @author Maradona Morais
 */
@Path("/order")
public class OrderService {

  @EJB
  OrderLocalEJB orderEJB;

  /**
   * Creates a new empty book order
   * @return created book order
   */
  @Path("/create")
  @POST
  @Produces("application/json; charset=UTF-8")
  public Response create() {
    try {
      OrderEntity obj = orderEJB.createNew(new OrderEntity(new Date()));
      return Response.ok(obj).build();
    } catch(BookstoreUnknownException unknownErr) {
			// Unknown or undefined server error
			return Response.status(500).entity(new ExceptionWrapper(unknownErr)).build();
		}
  }

  @Path("/{id}")
  @GET
  @Produces("application/json; charset=UTF-8")
  public Response get(final @PathParam("id") Long id) {
    try {
      OrderEntity obj = orderEJB.findOrder(id);
      return Response.ok(obj).build();
    } catch(BookstoreUnknownException exception) {
      return Response.status(500).entity(new ExceptionWrapper(exception)).build();
    }
  }

  /**
   * Add item to a existing book order
   * @param id book order id
   * @param bookId book id
   * @param quantity quantity of titles
   * @return updated book order
   */
  @Path("/{id}/add")
  @POST
  @Produces("application/json; charset=UTF-8")
  @Consumes("application/x-www-form-urlencoded; charset=UTF-8")
  public Response addItem(final @PathParam("id") Long id, final @FormParam("bookId") Long bookId
    , final @FormParam("quantity") int quantity) {
      try {
        OrderEntity obj = orderEJB.addItem(id, bookId, quantity);
        return Response.ok(obj).build();
      } catch(BookstoreUnknownException unknownErr) {
        // Unknown or undefined server error
        return Response.status(500).entity(new ExceptionWrapper(unknownErr)).build();
      }
  }

  /**
   * Checksout a order, generating a service Intent
   * @param id order id
   * @param type intent type
   * @return the updated order
   */
  @Path("/{id}/checkout")
  @POST
  @Produces("application/json; charset=UTF-8")
  @Consumes("application/x-www-form-urlencoded; charset=UTF-8")
  public Response checkout(final @PathParam("id") Long id, final @FormParam("type") String type) {
    try {
      IntentType intentType;
      switch(type) {
        case "reservation":
          intentType = IntentType.RESERVATION;
          break;
        case "transaction":
          intentType = IntentType.TRANSACTION;
          break;
        default:
          intentType = null;
      }

      if (intentType == null) {
        // This means an unknow intent type. Returns a Bad Request error
        return Response.status(400).entity(new ExceptionWrapper(
          new Exception("O tipo de Intent inserido não foi reconhecido como válido.")
        )).build();
      }

      OrderEntity obj = orderEJB.checkout(id, intentType);

      return Response.ok(obj).build();
    } catch(BookstoreUnknownException unknownException) {
      return Response.status(500).entity(new ExceptionWrapper(unknownException)).build();
    }
  }
}