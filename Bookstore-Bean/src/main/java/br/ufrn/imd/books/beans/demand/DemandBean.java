package br.ufrn.imd.books.beans.demand;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.DemandDao;
import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandBean
 */
@Stateless
public class DemandBean implements DemandLocalEJB, DemandRemoteEJB {

  @EJB
  DemandDao demandDAO;

  @EJB
  IntentDao intentDAO;

  @Override
  public DemandEntity createNew(DemandEntity demand) throws BookstoreUnknownException {
    return demandDAO.persist(demand);
  }

  @Override
  public DemandEntity attachIntentToOpenDemand(Long intentId) throws BookstoreUnknownException {
    DemandEntity openDemand = demandDAO.getOpenDemand();
    IntentEntity intentToAttach = intentDAO.findIntent(intentId);

    openDemand.getIntents().add(intentToAttach);
    demandDAO.persist(openDemand);
    return openDemand;
  }

  @Override
  public List<DemandEntity> getAll() throws BookstoreUnknownException {
    return demandDAO.getAllDemands();
  }

  @Override
  public DemandEntity getOpenDemand() {
    return demandDAO.getOpenDemand();
  }

}