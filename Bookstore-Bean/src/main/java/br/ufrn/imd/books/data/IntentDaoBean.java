package br.ufrn.imd.books.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentDaoBean
 */
@Stateless
public class IntentDaoBean implements IntentDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Override
  public IntentEntity persist(IntentEntity intent) throws BookstoreUnknownException {
    try {
      entityManager.persist(intent);
      return intent;
    } catch(Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  
}