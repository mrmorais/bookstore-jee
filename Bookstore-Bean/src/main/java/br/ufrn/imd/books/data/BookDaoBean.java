package br.ufrn.imd.books.data;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

@Stateful
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
    try {
      return entityManager.find(BookEntity.class, bookId);
    } catch (Exception e){
      throw new BookstoreUnknownException("Livro não localizado.");
    }
  }
}