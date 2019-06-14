package br.ufrn.imd.books.consumers.demand;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * DemandMDB
 * 
 * @author Maradona Morais
 */
@MessageDriven(name="demandQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/DEMAND_QUEUE")
    , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    , @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
  })
public class DemandManagerMDB implements MessageListener {

  @Override
  public void onMessage(Message message) {

  }
}