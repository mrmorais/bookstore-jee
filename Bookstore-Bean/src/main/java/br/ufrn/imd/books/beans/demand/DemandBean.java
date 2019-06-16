package br.ufrn.imd.books.beans.demand;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.DemandDao;
import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.entity.DemandEntity.DemandStatus;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;
import br.ufrn.imd.books.producers.intent.IntentQueueProducer;

/**
 * DemandBean
 * @author Maradona Morais
 */
@Stateless
public class DemandBean implements DemandLocalEJB, DemandRemoteEJB {

  @EJB
  DemandDao demandDAO;

  @EJB
  IntentDao intentDAO;

  @EJB
  IntentQueueProducer intentQueueProducer;

  @Override
  public DemandEntity createNew(DemandEntity demand) throws BookstoreUnknownException {
    return demandDAO.persist(demand);
  }

  @Override
  public DemandEntity attachIntentToOpenDemand(Long intentId) throws BookstoreUnknownException {
    DemandEntity openDemand = getOpenDemand();
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
  public DemandEntity getOpenDemand() throws BookstoreUnknownException {
    DemandEntity openDemand = demandDAO.getOpenDemand();
    if (openDemand == null) {
      // If there's no existing open demand create one
      openDemand = new DemandEntity();
      openDemand.setStatus(DemandEntity.DemandStatus.OPEN);
      openDemand = createNew(openDemand);
    }
    return openDemand;
  }

  @Override
  public void sendDemand() throws BookstoreUnknownException {
    DemandEntity openDemand = getOpenDemand();
    if(openDemand.getIntents().size() > 0) {
      openDemand.setStatus(DemandEntity.DemandStatus.SENT);
      getOpenDemand(); // Call it again to create the new open demand
    }
  }

  @Override
  public void consolidateDemand(Long demandId) throws BookstoreUnknownException {
    DemandEntity demand = demandDAO.findDemand(demandId);
    // Unpack intents to Process Intent queue
    intentQueueProducer.sendIntentSetToProcess(demand.getIntents());
    demand.setStatus(DemandStatus.CONSOLIDATED);
    demandDAO.persist(demand);
  }

}