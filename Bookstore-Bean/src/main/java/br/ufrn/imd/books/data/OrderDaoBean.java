package br.ufrn.imd.books.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderDaoBean
 */
@Stateless
public class OrderDaoBean implements OrderDao {
  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Override
  public OrderEntity persist(OrderEntity order) throws BookstoreUnknownException {
    try {
      entityManager.persist(order);
      return order;
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public OrderEntity findOrder(Long orderId) throws BookstoreUnknownException {
    try {
      OrderEntity order = entityManager.find(OrderEntity.class, orderId);
      if (order == null) {
        throw new BookstoreUnknownException("Não foi possível localizar a ordem");
      }
      return order;
    } catch(Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public OrderEntity addItem(OrderItemEntity item, OrderEntity entity) throws BookstoreUnknownException {
    return null;
  }
}