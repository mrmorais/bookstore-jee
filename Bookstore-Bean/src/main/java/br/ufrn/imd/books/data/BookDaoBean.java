package br.ufrn.imd.books.data;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.books.entity.BookEntity;

@Stateful
public class BookDaoBean implements BookDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  public BookEntity addNew(BookEntity bookTitle) {
    System.out.println(bookTitle.getTitle());
    try {
      entityManager.persist(bookTitle);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookTitle;
  }
}