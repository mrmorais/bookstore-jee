package br.ufrn.imd.books.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

@Stateless
public class BookDaoBean implements BookDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  public BookEntity persist(BookEntity bookTitle) throws BookstoreUnknownException {
    try {
      entityManager.persist(bookTitle);
      return bookTitle;
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public List<BookEntity> getAll() throws BookstoreUnknownException {
    try {
      TypedQuery<BookEntity> query = entityManager.createNamedQuery("getAllBooks", BookEntity.class);
      return query.getResultList();
    } catch (Exception e){
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public BookEntity findBook(Long bookId) throws BookstoreUnknownException {
    BookEntity book = entityManager.find(BookEntity.class, bookId);
    if (book == null) {
      throw new BookstoreUnknownException("Livro não localizado.");
    }
    return book;
  }
}