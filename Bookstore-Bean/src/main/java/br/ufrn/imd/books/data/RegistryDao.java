package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.RegistryEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * RegistryDao
 * @author Maradona Morais
 */
public interface RegistryDao {
  public RegistryEntity persist() throws BookstoreUnknownException;
}