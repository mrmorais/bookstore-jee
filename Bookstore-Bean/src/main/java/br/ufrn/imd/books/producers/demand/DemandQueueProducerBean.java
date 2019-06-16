package br.ufrn.imd.books.producers.demand;

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

import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandQueueProducerBean
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
@Stateless
public class DemandQueueProducerBean implements DemandQueueProducer {

  @Override
  public void sendIntentToDemandQueue(Long intentId) throws BookstoreUnknownException {
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

  
}