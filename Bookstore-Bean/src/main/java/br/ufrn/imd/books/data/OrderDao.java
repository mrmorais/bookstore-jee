package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderDao
 */
public interface OrderDao {
  public OrderEntity persist(OrderEntity order) throws BookstoreUnknownException;
  public OrderEntity findOrder(Long orderId) throws BookstoreUnknownException;
  public OrderEntity addItem(OrderItemEntity item, OrderEntity entity) throws BookstoreUnknownException;
}