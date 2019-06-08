package br.ufrn.imd.books.data;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

@Stateful
public class BookDaoBean implements BookDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  public BookEntity addNew(BookEntity bookTitle) throws BookstoreUnknownException {
    try {
      entityManager.persist(bookTitle);
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
    return bookTitle;
  }
}