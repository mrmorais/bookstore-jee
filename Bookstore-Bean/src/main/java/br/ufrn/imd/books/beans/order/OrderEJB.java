package br.ufrn.imd.books.beans.order;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.OrderDao;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderEJB
 */
@Stateless(name = "OrderEJB")
public class OrderEJB implements OrderRemoteEJB, OrderLocalEJB {

  @EJB
  OrderDao orderDAO;

  @Override
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException {
    return orderDAO.addNew(order);
  }

}