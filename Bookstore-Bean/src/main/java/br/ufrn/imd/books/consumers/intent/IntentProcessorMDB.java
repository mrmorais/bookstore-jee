package br.ufrn.imd.books.consumers.intent;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.ufrn.imd.books.beans.book.BookLocalEJB;
import br.ufrn.imd.books.beans.intent.IntentLocalEJB;
import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.entity.RegistryEntity;
import br.ufrn.imd.books.entity.SellOrderEntity;
import br.ufrn.imd.books.entity.RegistryEntity.RegistryType;
import br.ufrn.imd.books.entity.SellOrderEntity.SellOrderType;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentProcessorMDB
 * 
 * @author Maradona Morais
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/intentProcessQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class IntentProcessorMDB implements MessageListener {

  @EJB
  private IntentLocalEJB intentEJB;

  @EJB
  private BookLocalEJB bookEJB;

  /**
   * Creates a book registry for a Order Item
   * @param orderItem order item
   * @param type registry type (IN, OUT)
   * @param visible if the registry is visible or not
   * @throws BookstoreUnknownException
   */
  private void createRegistryForOrderItem(
      OrderItemEntity orderItem
    , RegistryType type
    , boolean visible
  ) throws BookstoreUnknownException {
    BookEntity book = orderItem.getBook();
    RegistryEntity registry = new RegistryEntity(
      type, orderItem.getQuantity(), visible);
      
    bookEJB.addRegistry(book.getId(), registry);
  }

  @Override
  public void onMessage(Message message) {
    /**
     * check the type:
     *   - transaction
     *     - create a CONSOLIDATED sellorder +  registry out visible
     *   - reservation
     *     - has customer?
     *       - create a open sellorder (registry in blocked)
     *     - no has customer?
     *       - create a registry in visible
     */
    try {
      Long intentId = message.getLongProperty("intentId");
      IntentEntity intent = intentEJB.findEntity(intentId);
      
      switch(intent.getType()) {
        case RESERVATION: // Customer or store reservation
          if (intent.getCustomer() == null) {
            // has no customer? -> create Registry IN visible
            for (OrderItemEntity orderItem : intent.getOrder().getItems()) {
              createRegistryForOrderItem(orderItem, RegistryType.IN, true);
            }
          } else {
            // has customer? -> create OPEN sell order + Itens Registry IN not visible
            for (OrderItemEntity orderItem : intent.getOrder().getItems()) {
              createRegistryForOrderItem(orderItem, RegistryType.IN, false);
              intentEJB.createSellOrder(intentId, new SellOrderEntity(SellOrderType.OPEN));
            }
          }
          
          break;
        case TRANSACTION: // Direct sell
          // create CONSOLIDATED sell Order
          // create Itens Registry OUT
          for (OrderItemEntity orderItem : intent.getOrder().getItems()) {
            createRegistryForOrderItem(orderItem, RegistryType.OUT, true);
          }
          intentEJB.createSellOrder(intentId, new SellOrderEntity(SellOrderType.CONSOLIDATED));
          break;
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  
}
