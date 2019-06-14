package br.ufrn.imd.books.beans.order;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.BookDao;
import br.ufrn.imd.books.data.IntentDao;
import br.ufrn.imd.books.data.OrderDao;
import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.entity.IntentEntity;
import br.ufrn.imd.books.entity.IntentEntity.IntentType;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderEJB
 * 
 * @author Maradona Morais
 */
@Stateless(name = "OrderEJB")
public class OrderEJB implements OrderRemoteEJB, OrderLocalEJB {

  @EJB
  OrderDao orderDAO;

  @EJB
  BookDao bookDAO;

  @EJB
  IntentDao intentDAO;

  /**
   * Creates a new empty book order
   * 
   * @param order order to be created
   * @return persisted order
   */
  @Override
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException {
    ArrayList<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
    order.setItems(items);
    return orderDAO.persist(order);
  }

  /**
   * Create a new item and attach it to a existing book order
   * 
   * @param orderId  order id
   * @param bookId   book id
   * @param quantity number of titles
   * @return the updated book order
   */
  @Override
  public OrderEntity addItem(final Long orderId, final Long bookId, final int quantity)
      throws BookstoreUnknownException {
    OrderEntity order = orderDAO.findOrder(orderId);

    if (order.getIntent() != null) {
      throw new BookstoreUnknownException("Não é possível adicionar itens à uma ordem registrada.");
    }

    BookEntity book = bookDAO.findBook(bookId);

    order.getItems().add(new OrderItemEntity(book, quantity));
    orderDAO.persist(order);
    return order;
  }

  @Override
  public OrderEntity checkout(Long orderId, IntentType intentType) throws BookstoreUnknownException {
    OrderEntity order = orderDAO.findOrder(orderId);

    if (order.getIntent() != null) {
      throw new BookstoreUnknownException("Não é possível registrar uma ordem já registrada.");
    }

    IntentEntity intent = new IntentEntity(order, intentType);

    intentDAO.persist(intent);

    order.setIntent(intent);

    return order;
  }

  @Override
  public OrderEntity findOrder(Long orderId) throws BookstoreUnknownException {
    return orderDAO.findOrder(orderId);
  }

}