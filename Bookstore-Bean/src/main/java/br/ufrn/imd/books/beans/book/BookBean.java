package br.ufrn.imd.books.beans.book;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.entity.RegistryEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * BookEJB
 */
@Stateless(name = "BookEJB")
public class BookBean implements BookRemoteEJB, BookLocalEJB {

  @EJB
  private BookDao bookDAO;

  @Override
  public BookEntity createNew(BookEntity book) throws BookstoreUnknownException {
    return bookDAO.persist(book);
  }

  @Override
  public List<BookEntity> getAll() throws BookstoreUnknownException {
    return bookDAO.getAll();
  }

  @Override
  public BookEntity addRegistry(Long bookId, RegistryEntity registry) throws BookstoreUnknownException {
    try {
      BookEntity book = bookDAO.findBook(bookId);
      book.getRegistries().add(registry);
      return bookDAO.persist(book);
    } catch(Exception e) {
      throw new BookstoreUnknownException();
    }
  }
}