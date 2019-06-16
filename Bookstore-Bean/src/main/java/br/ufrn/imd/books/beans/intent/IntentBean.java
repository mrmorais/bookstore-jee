package br.ufrn.imd.books.beans.intent;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentBean
 */
@Stateless
public class IntentBean implements IntentLocalEJB, IntentRemoteEJB {

  @EJB
  private IntentDao intentDAO;

  @Override
  public IntentEntity findEntity(Long intentId) throws BookstoreUnknownException {
    return intentDAO.findIntent(intentId);
  }
}