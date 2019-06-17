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
      entityManager.flush();
    } catch(Exception e) {
      throw new BookstoreUnknownException();
    }
    return intent;
  }

  @Override
  public IntentEntity refresh(IntentEntity intent) throws BookstoreUnknownException {
    try {
      entityManager.refresh(intent);
      entityManager.flush();
    } catch(Exception e) {
      throw new BookstoreUnknownException();
    }
    return intent;
  }

  @Override
  public IntentEntity findIntent(Long intentId) throws BookstoreUnknownException {
    IntentEntity intent = entityManager.find(IntentEntity.class, intentId);
    if (intent == null) {
      throw new BookstoreUnknownException("Não foi possível localizar a Intent "+ intentId);
    }
    return intent;
  }


}