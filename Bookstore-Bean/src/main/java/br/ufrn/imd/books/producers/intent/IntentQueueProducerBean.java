package br.ufrn.imd.books.producers.intent;

import java.util.Set;

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

import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentQueueProducerBean
 */
@JMSDestinationDefinitions(
  value={
    @JMSDestinationDefinition(
        name = "java:/queue/intentProcessQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "intentProcessQueue"
    ),
  }
)
@Stateless
public class IntentQueueProducerBean implements IntentQueueProducer {

  private void sendIntentToProcessQueue(final Long intentId, QueueSession session, Queue queue) throws Exception {
      MessageProducer producer = session.createProducer(queue);
      Message msg = session.createObjectMessage();
      msg.setLongProperty("intentId", intentId);
      producer.send(msg);
  }

  @Override
  public void sendIntentSetToProcess(Set<IntentEntity> intents) throws BookstoreUnknownException {
    try {
      Context context = new InitialContext();

      Queue intentProcessQueue = (Queue) context.lookup("java:/queue/intentProcessQueue");
      QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("java:/ConnectionFactory");

      QueueConnection connection = connectionFactory.createQueueConnection();
      QueueSession sendSession = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

      for (IntentEntity intent : intents) {
        sendIntentToProcessQueue(intent.getId(), sendSession, intentProcessQueue);
      }

      sendSession.close();
      connection.close();
    } catch(Exception exception) {
      throw new BookstoreUnknownException("Não foi possível processar intent");
    }
  }

  
}