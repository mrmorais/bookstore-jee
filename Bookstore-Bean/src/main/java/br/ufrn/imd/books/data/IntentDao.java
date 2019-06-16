package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * IntentDao
 * @author Maradona Morais
 */
public interface IntentDao {
  public IntentEntity persist(IntentEntity intent) throws BookstoreUnknownException;
  public IntentEntity findIntent(final Long intentId) throws BookstoreUnknownException;
}