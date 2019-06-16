package br.ufrn.imd.books.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.books.beans.demand.DemandLocalEJB;
import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.utils.ExceptionWrapper;

/**
 * DemandService
 * @author Maradona Morais
 */
@Path("/demand")
public class DemandService {

  @EJB
  private DemandLocalEJB demandEJB;

  @GET
  @Path("/{id}/consolidate")
  @Produces("application/json; charset=UTF-8")
  public Response consolidate(final @PathParam("id") Long id) {
    try {
      demandEJB.consolidateDemand(id);
      return Response.ok("Demanda enviada para consolidação").build();
    } catch(Exception e) {
      return Response.status(500).entity(new ExceptionWrapper(e)).build();
    }
  }

  @GET
  @Path("/all")
  @Produces("application/json; charset=UTF-8")
  public Response getAll() {
    try {
      List<DemandEntity> demands = demandEJB.getAll();
      return Response.ok(demands).build();
    } catch(Exception e) {
      return Response.status(500).entity(new ExceptionWrapper(e)).build();
    }
  }
  
}