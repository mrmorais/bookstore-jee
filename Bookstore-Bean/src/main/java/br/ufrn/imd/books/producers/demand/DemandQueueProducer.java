package br.ufrn.imd.books.producers.demand;

import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandQueueProducer
 * @author Maradona Morais
 */
public interface DemandQueueProducer {
  public void sendIntentToDemandQueue(final Long intentId) throws BookstoreUnknownException;
}