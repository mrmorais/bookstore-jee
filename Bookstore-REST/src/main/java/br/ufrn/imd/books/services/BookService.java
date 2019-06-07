package br.ufrn.imd.books.services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.imd.books.beans.book.BookLocalEJB;
import br.ufrn.imd.books.entity.BookEntity;

@Path("/book")
public class BookService {

  @EJB
  BookLocalEJB bookEJB;

	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {
    BookEntity obj = bookEJB.createNew(new BookEntity("A graça da coisa", "Martha Medeiros", "00-00", 15.2, 10.0));
		return Response.ok(obj).build();
	}

}
