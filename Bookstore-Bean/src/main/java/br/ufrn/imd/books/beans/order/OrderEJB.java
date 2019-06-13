package br.ufrn.imd.books.beans.order;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.data.OrderDao;
import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderEJB
 */
@Stateless(name = "OrderEJB")
public class OrderEJB implements OrderRemoteEJB, OrderLocalEJB {

  @EJB
  OrderDao orderDAO;

  @EJB
  BookDao bookDAO;

  @Override
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException {
    ArrayList<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
    order.setItems(items);
    return orderDAO.persist(order);
  }

  @Override
  public OrderEntity addItem(final Long orderId, final Long bookId, final int quantity) throws BookstoreUnknownException {
    OrderEntity order = orderDAO.findOrder(orderId);
    BookEntity book = bookDAO.findBook(bookId);

    order.getItems().add(new OrderItemEntity(book, quantity));
    orderDAO.persist(order);
    return order;
  }

}