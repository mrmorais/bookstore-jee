package br.ufrn.imd.books.producers.intent;

import java.util.Set;

import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentQueueProducer
 */
public interface IntentQueueProducer {
  public void sendIntentSetToProcess(Set<IntentEntity> intents) throws BookstoreUnknownException;
}