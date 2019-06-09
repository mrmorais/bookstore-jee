package br.ufrn.imd.books.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderDaoBean
 */
@Stateless
public class OrderDaoBean implements OrderDao {
  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Override
  public OrderEntity addNew(OrderEntity order) throws BookstoreUnknownException {
    try {
      entityManager.persist(order);
      order.getItems().forEach(item -> entityManager.persist(item));
      return order;
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
  }
}