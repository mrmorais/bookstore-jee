package br.ufrn.imd.books.beans.customer;

import java.util.List;

import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * CustomerCommonEJB
 * @author Maradona Morais
 */
public interface CustomerCommonEJB {
  CustomerEntity createNew(final CustomerEntity customer) throws BookstoreUnknownException;
  List<CustomerEntity> getAll() throws BookstoreUnknownException;
}