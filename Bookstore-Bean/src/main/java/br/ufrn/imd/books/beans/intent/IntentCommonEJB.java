package br.ufrn.imd.books.beans.intent;

import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentCommonEJB
 */
public interface IntentCommonEJB {
  IntentEntity findEntity(final Long intentId) throws BookstoreUnknownException;
}