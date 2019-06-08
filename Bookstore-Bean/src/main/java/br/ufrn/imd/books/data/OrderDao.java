package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderDao
 */
public interface OrderDao {
  public OrderEntity addNew(OrderEntity order) throws BookstoreUnknownException;
}