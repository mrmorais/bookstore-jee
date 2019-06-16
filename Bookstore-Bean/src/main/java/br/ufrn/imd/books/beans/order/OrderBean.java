package br.ufrn.imd.books.beans.order;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;

import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.data.CustomerDao;
import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.data.OrderDao;
import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.entity.IntentEntity.IntentType;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderEJB
 * 
 * @author Maradona Morais
 */
@JMSDestinationDefinitions(
  value={
    @JMSDestinationDefinition(
        name = "java:/queue/demandQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "demandQueue"
    ),
  }
)
@Stateless(name = "OrderEJB")
public class OrderBean implements OrderRemoteEJB, OrderLocalEJB {

  @EJB
  private OrderDao orderDAO;

  @EJB
  private BookDao bookDAO;

  @EJB
  private IntentDao intentDAO;

  @EJB
  private CustomerDao customerDAO;

  /**
   * Creates a new empty book order
   * 
   * @param order order to be created
   * @return persisted order
   */
  @Override
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException {
    ArrayList<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
    order.setItems(items);
    return orderDAO.persist(order);
  }

  /**
   * Create a new item and attach it to a existing book order
   * 
   * @param orderId  order id
   * @param bookId   book id
   * @param quantity number of titles
   * @return the updated book order
   */
  @Override
  public OrderEntity addItem(final Long orderId, final Long bookId, final int quantity)
      throws BookstoreUnknownException {
    OrderEntity order = orderDAO.findOrder(orderId);

    if (order.getIntent() != null) {
      throw new BookstoreUnknownException("Não é possível adicionar itens à uma ordem registrada.");
    }

    BookEntity book = bookDAO.findBook(bookId);

    order.getItems().add(new OrderItemEntity(book, quantity));
    orderDAO.persist(order);
    return order;
  }

  private void sendIntentToDemandQueue(final Long intentId) throws BookstoreUnknownException {
    try {
      Context context = new InitialContext();

      Queue demandQueue = (Queue) context.lookup("java:/queue/demandQueue");
      QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");

      QueueConnection connection = connectionFactory.createQueueConnection();
      QueueSession sendSession = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

      MessageProducer producer = sendSession.createProducer(demandQueue);
      Message msg = sendSession.createObjectMessage();
      msg.setLongProperty("intentId", intentId);
      producer.send(msg);

      sendSession.close();
      connection.close();
    } catch(Exception exception) {
      throw new BookstoreUnknownException("Não foi possível registrar intent");
    }
  }

  @Override
  public OrderEntity checkout(Long orderId, Long customerId, IntentType intentType) throws BookstoreUnknownException {
    OrderEntity order = orderDAO.findOrder(orderId);

    if (order.getIntent() != null) {
      throw new BookstoreUnknownException("Não é possível registrar uma ordem já registrada.");
    }

    CustomerEntity customer = null;
    if (customerId != null) {
      try {
        customer = customerDAO.findCustomer(customerId);
      } catch(Exception e) {
        customer = null;
      }
    }

    IntentEntity intent = new IntentEntity(order, customer, intentType);
    intentDAO.persist(intent);
    order.setIntent(intent);

    switch (intentType) {
      case RESERVATION:
        sendIntentToDemandQueue(intent.getId());
        break;
      case TRANSACTION:
        // TODO: Create a Process Intent queue 
        break;
    }

    return order;
  }

  @Override
  public OrderEntity findOrder(Long orderId) throws BookstoreUnknownException {
    return orderDAO.findOrder(orderId);
  }

}
