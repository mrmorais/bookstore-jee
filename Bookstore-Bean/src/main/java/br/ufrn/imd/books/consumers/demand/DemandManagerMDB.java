package br.ufrn.imd.books.consumers.demand;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Schedule;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.ufrn.imd.books.beans.book.BookLocalEJB;
import br.ufrn.imd.books.beans.demand.DemandLocalEJB;
import br.ufrn.imd.books.entity.DemandEntity;

/**
 * DemandMDB
 * 
 * @author Maradona Morais
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/demandQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class DemandManagerMDB implements MessageListener {

  private static int MAX_INTENTS_PER_DEMAND = 50;

  @EJB
  private DemandLocalEJB demandEJB;

  @EJB
  private BookLocalEJB bookEJB;

  @Override
  public void onMessage(Message message) {
    // Recieves an "intentId" to be attached
    try {
      // Before attach the intent verifies if the max limit per
      // demand got reached -> Send demand.
      DemandEntity openDemand = demandEJB.getOpenDemand();
      if (openDemand.getIntents().size() == MAX_INTENTS_PER_DEMAND) {
        demandEJB.sendDemand();
      }

      Long intentId = message.getLongProperty("intentId");
      demandEJB.attachIntentToOpenDemand(intentId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Schedule(dayOfWeek="*", hour="*", minute="*")
  @Lock(LockType.WRITE)
  public void processBatchOfIntents() {
    try {
      DemandEntity openDemand = demandEJB.getOpenDemand();
      if (openDemand.getIntents().size() > 0) {
        demandEJB.sendDemand();
      }
    } catch(Exception e) {
      /** Nothing to do */
    }
  }

}
