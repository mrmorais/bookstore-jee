
package br.ufrn.imd.books.data;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.ufrn.imd.books.entity.BookTitle;

@Named
@RequestScoped
class BookTitleDAO {
  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Resource
  private UserTransaction userTransaction;

  public BookTitle addNew(BookTitle bookTitle) {
    try {
      userTransaction.begin();
      entityManager.persist(bookTitle);
      userTransaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bookTitle;
  }
}