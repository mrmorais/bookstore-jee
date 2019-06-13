package br.ufrn.imd.books.beans.order;

import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderCommonEJB
 */
public interface OrderCommonEJB {
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException;
  public OrderEntity addItem(Long orderId, Long bookId, int quantity) throws BookstoreUnknownException;
}