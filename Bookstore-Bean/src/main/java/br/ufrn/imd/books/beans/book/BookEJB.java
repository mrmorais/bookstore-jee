package br.ufrn.imd.books.beans.book;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.entity.BookEntity;

/**
 * BookEJB
 */
@Stateless(name = "BookEJB")
public class BookEJB implements BookRemoteEJB, BookLocalEJB {

  @EJB
  private BookDao bookDAO;

  @Override
  public BookEntity createNew(BookEntity book) {
    return bookDAO.addNew(book);
  }
}