package br.ufrn.imd.books.beans.intent;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.entity.SellOrderEntity;
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

  @Override
  public IntentEntity createSellOrder(Long intentId, SellOrderEntity sellOrder) throws BookstoreUnknownException {
    try {
      IntentEntity intent = intentDAO.findIntent(intentId);
      intent.setSellOrder(sellOrder);
      return intentDAO.refresh(intent);
    } catch(Exception e) {
      throw new BookstoreUnknownException("Não foi possível criar ordem de venda.");
    }
  }
}
