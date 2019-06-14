package br.ufrn.imd.books.consumers.demand;

import javax.ejb.EJB;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.entity.BookEntity;

/**
 * DemandMDB
 * 
 * @author Maradona Morais
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/demandQueue")
    , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    , @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
  })
public class DemandManagerMDB implements MessageListener {

  @EJB
  private BookDao bookDao;

  @Override
  public void onMessage(Message message) {
    try {
      bookDao.persist(new BookEntity("Test book", "Test author", "test isbn", 20.0, 15.0));
    } catch(Exception e) {

    }

  }
}
