package br.ufrn.imd.books.consumers.demand;

import javax.ejb.EJB;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.ufrn.imd.books.beans.demand.DemandLocalEJB;
import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.entity.DemandEntity.DemandStatus;

/**
 * DemandMDB
 * 
 * @author Maradona Morais
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/demandQueue")
    , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    , @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
  })
public class DemandManagerMDB implements MessageListener {

  @EJB
  private DemandLocalEJB demandEJB;

  @Override
  public void onMessage(Message message) {
    // Recieves a "intentId" to be attached
    try {
      Long intentId = message.getLongProperty("intentId");
      DemandEntity openDemand = demandEJB.getOpenDemand();

      if (openDemand == null) {
        // If there's no existing open demand create one
        openDemand = new DemandEntity();
        openDemand.setStatus(DemandStatus.OPEN);
        demandEJB.createNew(openDemand);
      }
      demandEJB.attachIntentToOpenDemand(intentId);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
